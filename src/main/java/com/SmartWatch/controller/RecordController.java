package com.SmartWatch.controller;

import com.SmartWatch.model.request.SaveRecordRequest;
import com.SmartWatch.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/get-records")
    public ResponseEntity<?> getRecords(@RequestParam("username") String patientUsername, Authentication authentication) {
        String careUsername = (String) authentication.getPrincipal();
        return recordService.getRecords(careUsername, patientUsername);
    }

    @PostMapping("/save-record")
    public ResponseEntity<?> saveRecord(@RequestBody SaveRecordRequest request, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return recordService.saveRecord(request, username);
    }

}
