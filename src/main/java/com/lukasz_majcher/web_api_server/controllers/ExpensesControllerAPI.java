package com.lukasz_majcher.web_api_server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lukasz_majcher.web_api_server.models.Expense;
import com.lukasz_majcher.web_api_server.data.ExpensesRepository;

@RestController
@RequestMapping("/expenses/api")
public class ExpensesControllerAPI {

    // @Autowired
    private ExpensesRepository expensesData;

    public ExpensesControllerAPI(ExpensesRepository repo) {
        super();
        expensesData = repo;
    }

    @GetMapping(value = "/list")
    public List<Expense> expenses() {
        List<Expense> allExpenses = expensesData.findAll();
        return allExpenses;
    }

    @PostMapping(value = "/save")
    public Expense save(@RequestBody Expense expense) {
        if (expense != null) {
            expensesData.save(expense);
        }
        return expense;
    }

    @GetMapping(value = "/edit/{id}")
    public Expense editExpense(@PathVariable long id) {
        Optional<Expense> expense = expensesData.findById(id);

        if (expense != null) {
            return expense.get();
        } else {
            return expenses().get(0);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public Boolean deleteExpense(@PathVariable long id) {
        Optional<Expense> expense = expensesData.findById(id);

        if (expense != null) {
            expensesData.delete(expense.get());
            return true;
        }
        return false;
    }
}
