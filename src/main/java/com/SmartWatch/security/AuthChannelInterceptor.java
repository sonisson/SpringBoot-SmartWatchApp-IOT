package com.SmartWatch.security;

import com.SmartWatch.repository.UserRelationRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthChannelInterceptor implements ChannelInterceptor {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserRelationRepository userRelationRepository;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        if (StompCommand.CONNECT.equals(command)) {
            String authHeader = getFirstNativeHeader(accessor);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                try {
                    Claims claims = tokenUtil.getClaims(token);
                    String username = claims.getSubject();
                    String role = claims.get("role", String.class);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority(role)));
                    accessor.setUser(authentication);
                    accessor.getSessionAttributes().put("user", authentication);
                } catch (Exception e) {
                    throw new AccessDeniedException("Token không hợp lệ: " + e.getMessage());
                }
            } else {
                throw new AccessDeniedException("Thiếu token.");
            }
        }
        if (!StompCommand.CONNECT.equals(command)) {
            Authentication sessionAuth = (Authentication) accessor.getSessionAttributes().get("user");
            if (sessionAuth != null) {
                accessor.setUser(sessionAuth);
            }
        }
        if (StompCommand.SUBSCRIBE.equals(command)) {
            String destination = accessor.getDestination();
            Authentication authentication = (Authentication) accessor.getUser();
            if (authentication == null) {
                throw new AccessDeniedException("Chưa xác thực.");
            }
            if (destination != null && destination.startsWith("/topic/patient/")) {
                String[] parts = destination.split("/");
                String patientUsername = parts[3];
                String careUsername = (String) authentication.getPrincipal();

                if (!userRelationRepository.existsByCareUserEntityUsernameAndPatientUserEntityUsernameAndStatus(careUsername, patientUsername, "accepted")) {
                    throw new AccessDeniedException("Không có quyền truy cập");
                }
            }
        }
        return message;
    }

    private String getFirstNativeHeader(StompHeaderAccessor accessor) {
        List<String> values = accessor.getNativeHeader("Authorization");
        return (values != null && !values.isEmpty()) ? values.get(0) : null;
    }
}