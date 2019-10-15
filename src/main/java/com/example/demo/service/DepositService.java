package com.example.demo.service;

import com.example.demo.domain.Borrower;
import com.example.demo.domain.DepositOperation;
import com.example.demo.repo.DepositOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepositService {
    @Autowired
    private DepositOperationRepository depositOperationRepository;

    public List<DepositOperation> selectAll() {
        return (List<DepositOperation>) depositOperationRepository.findAll();
    }

    public Long addDeposit(DepositOperation deposit) {
        depositOperationRepository.save(deposit);
        return deposit.getId();
    }

    public List<DepositOperation> getAllDepositsByBorrower(Borrower borrower) {
        return depositOperationRepository.findAllByCurrentBorrowerOrderByCreatedAtDesc(borrower);
    }

    public DepositOperation addNewDepositForActiveBorrower(Borrower borrower) {
        List<DepositOperation> allDepositOp = depositOperationRepository.findAllByCurrentBorrowerOrderByCreatedAtDesc(borrower);
        if (allDepositOp.size() != 0) {
            DepositOperation lastDepositOp = new DepositOperation();
            lastDepositOp.setTotalAmount(allDepositOp.get(0).getTotalAmount().add(allDepositOp.get(0).getTotalAmount().multiply(new BigDecimal(allDepositOp.get(0).getPercent()))));
            lastDepositOp.setCreatedAt(LocalDateTime.now());
            lastDepositOp.setCurrentBorrower(borrower);
            lastDepositOp.setPercent(allDepositOp.get(0).getPercent());
            addDeposit(lastDepositOp);
            return lastDepositOp;
        }
        return null;
    }
}
