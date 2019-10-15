package com.example.demo.gateway;

import com.example.demo.domain.DepositOperation;
import org.springframework.context.annotation.Profile;

public interface IMailGateway {
    void send(String emailTo, DepositOperation currentDeposit);
}
