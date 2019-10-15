package com.example.demo.service;

import com.example.demo.domain.Borrower;
import com.example.demo.repo.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowerService {
    @Autowired
    private BorrowerRepository borrowerRepository;

    public List<Borrower> selectAll() {
        return (List<Borrower>) borrowerRepository.findAll();
    }

    public Long addBorrower(Borrower borrower) {
        borrowerRepository.save(borrower);
        return borrower.getId();
    }

    public void updateBorrower (Long id, Borrower borrower) {
        Optional<Borrower> optBorrower = borrowerRepository.findById(id);
        if (optBorrower.isPresent()) {
            borrower.setId(id);
            borrowerRepository.save(borrower);
        }
        else {
            throw new IllegalArgumentException("Invalid borrower id" + borrower.getId());
        }
    }

    public List<Borrower> selectAllActiveBorrowers() {
        return borrowerRepository.findAllByActiveIsTrue();
    }

    public Borrower findBorrowerById(Long id) {
        Optional<Borrower> borrower = borrowerRepository.findById(id);
        if (!borrower.isPresent()) {
            throw new IllegalArgumentException("id - " + id);
        }
        return borrower.get();
    }
}
