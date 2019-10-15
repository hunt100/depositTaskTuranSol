package com.example.demo.controller;

import com.example.demo.domain.Borrower;
import com.example.demo.domain.DepositOperation;
import com.example.demo.service.BorrowerService;
import com.example.demo.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/borrowers")
public class BorrowerController {
    @Autowired
    private BorrowerService borrowerService;

    @Autowired
    private DepositService depositService;

    @GetMapping
    public String indexBorrowerPage(Model model) {
        List<Borrower> borrowers = borrowerService.selectAllActiveBorrowers();
        model.addAttribute("borrowers", borrowers);
        model.addAttribute("borrower", new Borrower());
        return "borrower-registrate";
    }

    @PostMapping("/addBorrower")
    public String addBorrower(@Valid @ModelAttribute Borrower borrower, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Borrower> borrowers = borrowerService.selectAllActiveBorrowers();
            model.addAttribute("borrowers", borrowers);
            return "borrower-registrate";
        }
        borrower.setActive(true);
        borrowerService.addBorrower(borrower);
        DepositOperation depositOperation = new DepositOperation();
        depositOperation.setPercent(0.05);
        depositOperation.setCurrentBorrower(borrower);
        depositOperation.setTotalAmount(borrower.getInput());
        depositService.addDeposit(depositOperation);
        return "redirect:/borrowers";
    }

    @GetMapping("/show/{id}")
    public String showSpecificBorrowerDeposit(@PathVariable("id")Long id, Model model) {
        Borrower borrower = borrowerService.findBorrowerById(id);
        List<DepositOperation> depositOperations = depositService.getAllDepositsByBorrower(borrower);
        model.addAttribute("deposits",depositOperations);
        model.addAttribute("borrower",borrower);
        return "borrower-deposits";
    }
}
