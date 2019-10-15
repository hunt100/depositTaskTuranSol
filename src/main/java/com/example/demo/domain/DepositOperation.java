package com.example.demo.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "deposit_operations")
public class DepositOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "percent")
    private double percent;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "borrower_id")
    private Borrower currentBorrower;

    public DepositOperation() {
        this.createdAt = LocalDateTime.now();
    }

    public DepositOperation( BigDecimal totalAmount, int percent, Borrower currentBorrower) {
        this.createdAt = LocalDateTime.now();
        this.totalAmount = totalAmount;
        this.percent = percent;
        this.currentBorrower = currentBorrower;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public Borrower getCurrentBorrower() {
        return currentBorrower;
    }

    public void setCurrentBorrower(Borrower currentBorrower) {
        this.currentBorrower = currentBorrower;
    }
}
