package com.codeline.library.service;

import com.codeline.library.entity.BorrowRecord;
import com.codeline.library.entity.Employee;
import com.codeline.library.entity.Resource;
import com.codeline.library.repository.BorrowRecordRepository;
import com.codeline.library.repository.EmployeeRepository;
import com.codeline.library.repository.ResourceRepository;
import com.codeline.library.repository.ReservationRepository;
import com.codeline.library.repository.WaitlistEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BorrowService {

    private final EmployeeRepository employeeRepository;
    private final ResourceRepository resourceRepository;
    private final BorrowRecordRepository borrowRecordRepository;
    private final WaitlistEntryRepository waitlistEntryRepository;
    private final ReservationRepository reservationRepository;

    public BorrowService(
            EmployeeRepository employeeRepository,
            ResourceRepository resourceRepository,
            BorrowRecordRepository borrowRecordRepository,
            WaitlistEntryRepository waitlistEntryRepository,
            ReservationRepository reservationRepository) {

        this.employeeRepository = employeeRepository;
        this.resourceRepository = resourceRepository;
        this.borrowRecordRepository = borrowRecordRepository;
        this.waitlistEntryRepository = waitlistEntryRepository;
        this.reservationRepository = reservationRepository;
    }

    public BorrowRecord borrowResource(Long employeeId, Long resourceId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        long activeBorrows =
                borrowRecordRepository.countByResourceIdAndActiveTrue(resourceId);

        if (activeBorrows >= resource.getTotalCopies()) {
            throw new RuntimeException("No copies available");
        }

        BorrowRecord borrowRecord = new BorrowRecord();

        borrowRecord.setEmployee(employee);
        borrowRecord.setResource(resource);
        borrowRecord.setBorrowedAt(LocalDateTime.now());
        borrowRecord.setActive(true);

        return borrowRecordRepository.save(borrowRecord);
    }

    public BorrowRecord returnResource(Long borrowRecordId) {

        BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowRecordId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        borrowRecord.setActive(false);
        borrowRecord.setReturnedAt(LocalDateTime.now());

        return borrowRecordRepository.save(borrowRecord);
    }
}