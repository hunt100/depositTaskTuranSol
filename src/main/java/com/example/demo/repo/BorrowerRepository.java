package com.example.demo.repo;

import com.example.demo.domain.Borrower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowerRepository  extends CrudRepository<Borrower, Long> {
    List<Borrower> findAllByActiveIsTrue();
}
