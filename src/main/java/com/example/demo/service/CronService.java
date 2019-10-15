package com.example.demo.service;

import com.example.demo.domain.Borrower;
import com.example.demo.domain.DepositOperation;
import com.example.demo.gateway.IMailGateway;
import com.example.demo.gateway.JavaMailruGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CronService {
    @Autowired
    private BorrowerService borrowerService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private IMailGateway mailGateway;

    @Scheduled(cron = "* 0/10 7-22 * * *")
    public void addDepositByTime() {
        List<Borrower> borrowers = borrowerService.selectAllActiveBorrowers();
        for (Borrower br : borrowers) {
            DepositOperation dop = depositService.addNewDepositForActiveBorrower(br);
            mailGateway.send(br.getEmail(), dop);
        }

    }
}
