package com.SmartWatch.controller;

import com.SmartWatch.model.request.AcceptRequest;
import com.SmartWatch.model.request.InviteRequest;
import com.SmartWatch.model.request.RejectRequest;
import com.SmartWatch.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserRelationController {

    @Autowired
    private UserRelationService userRelationService;

    @GetMapping("/get-following")
    public ResponseEntity<?> getFollowing(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return userRelationService.getFollowing(username);
    }

    @GetMapping("/get-followers")
    public ResponseEntity<?> getFollowers(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return userRelationService.getFollowers(username);
    }

    @PostMapping("/invite")
    public ResponseEntity<?> invite(@RequestBody InviteRequest request, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return userRelationService.invite(request, username);
    }

    @PostMapping("/accept")
    public ResponseEntity<?> accept(@RequestBody AcceptRequest request, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return userRelationService.accept(request, username);
    }

    @PostMapping("/reject")
    public ResponseEntity<?> reject(@RequestBody RejectRequest request, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return userRelationService.reject(request, username);
    }

}
