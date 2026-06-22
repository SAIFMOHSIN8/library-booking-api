package com.codeline.library.controller;

import com.codeline.library.entity.BorrowRecord;
import com.codeline.library.service.BorrowService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping
    public BorrowRecord borrowResource(
            @RequestParam Long employeeId,
            @RequestParam Long resourceId) {

        return borrowService.borrowResource(employeeId, resourceId);
    }
    @PostMapping("/return")
    public BorrowRecord returnResource(
            @RequestParam Long borrowRecordId) {

        return borrowService.returnResource(borrowRecordId);
    }
}