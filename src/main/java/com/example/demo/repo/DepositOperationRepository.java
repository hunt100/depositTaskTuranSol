package com.example.demo.repo;

import com.example.demo.domain.Borrower;
import com.example.demo.domain.DepositOperation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositOperationRepository extends CrudRepository<DepositOperation, Long> {
    List<DepositOperation> findAllByCurrentBorrowerOrderByCreatedAtDesc(Borrower borrower);
}
