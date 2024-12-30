package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.TransactionEntity;
import Fuel_Station.Fuel_Station.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping

    public List<TransactionEntity> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public TransactionEntity getTransactionById(@PathVariable Long transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    @PostMapping
    public TransactionEntity addTransaction(@RequestBody TransactionEntity transaction) {
        return transactionService.addTransaction(transaction);
    }

    @PutMapping("/{id}")
    public TransactionEntity updateTransaction(@PathVariable Long transactionId, @RequestBody TransactionEntity transaction) {
        return transactionService.updateTransaction(transactionId, transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long transactionId) {
        transactionService.deleteTransaction(transactionId);
    }
}







