package com.codeline.library.controller;

import com.codeline.library.entity.WaitlistEntry;
import com.codeline.library.service.WaitlistService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/waitlist")
public class WaitlistController {

    private final WaitlistService waitlistService;

    public WaitlistController(WaitlistService waitlistService) {
        this.waitlistService = waitlistService;
    }

    @PostMapping
    public WaitlistEntry joinWaitlist(
            @RequestParam Long employeeId,
            @RequestParam Long resourceId) {

        return waitlistService.joinWaitlist(employeeId, resourceId);
    }
}