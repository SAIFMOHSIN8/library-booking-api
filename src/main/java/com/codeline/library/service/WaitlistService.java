package com.codeline.library.service;

import com.codeline.library.entity.Employee;
import com.codeline.library.entity.Resource;
import com.codeline.library.entity.WaitlistEntry;
import com.codeline.library.repository.EmployeeRepository;
import com.codeline.library.repository.ResourceRepository;
import com.codeline.library.repository.WaitlistEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WaitlistService {

    private final EmployeeRepository employeeRepository;
    private final ResourceRepository resourceRepository;
    private final WaitlistEntryRepository waitlistEntryRepository;

    public WaitlistService(EmployeeRepository employeeRepository,
                           ResourceRepository resourceRepository,
                           WaitlistEntryRepository waitlistEntryRepository) {

        this.employeeRepository = employeeRepository;
        this.resourceRepository = resourceRepository;
        this.waitlistEntryRepository = waitlistEntryRepository;
    }

    public WaitlistEntry joinWaitlist(Long employeeId, Long resourceId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        WaitlistEntry waitlistEntry = new WaitlistEntry();

        waitlistEntry.setEmployee(employee);
        waitlistEntry.setResource(resource);
        waitlistEntry.setJoinedAt(LocalDateTime.now());

        waitlistEntry.setActive(true);
        waitlistEntry.setPosition(1);

        return waitlistEntryRepository.save(waitlistEntry);
    }
}