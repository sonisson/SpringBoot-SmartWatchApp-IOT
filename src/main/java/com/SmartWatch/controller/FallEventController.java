package com.SmartWatch.controller;

import com.SmartWatch.model.request.SaveFallEventRequest;
import com.SmartWatch.service.FallEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class FallEventController {

    @Autowired
    private FallEventService fallEventService;

    @GetMapping("/get-fall-events")
    public ResponseEntity<?> getFallEvents(@RequestParam("username") String username, Authentication authentication) {
        String careUsername = (String) authentication.getPrincipal();
        return fallEventService.getFallEvents(careUsername, username);
    }

    @PostMapping("/save-fall-event")
    public ResponseEntity<?> saveFallEvent(@RequestBody SaveFallEventRequest request, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return fallEventService.saveFallEvent(request, username);
    }

}
