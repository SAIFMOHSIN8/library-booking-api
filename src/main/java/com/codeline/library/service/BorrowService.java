package com.codeline.library.service;

import com.codeline.library.repository.BorrowRecordRepository;
import com.codeline.library.repository.EmployeeRepository;
import com.codeline.library.repository.ResourceRepository;
import com.codeline.library.repository.ReservationRepository;
import com.codeline.library.repository.WaitlistEntryRepository;
import org.springframework.stereotype.Service;

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
}