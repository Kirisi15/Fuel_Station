package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.Transaction;
import Fuel_Station.Fuel_Station.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping

    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable("id") Long transactionId) {
        return transactionService.getTransactionById(transactionId);
    }
    @GetMapping("/station/{stationId}")
    public List<Transaction> getTransactionsByStation(@PathVariable("stationId") Long stationId) {
        return transactionService.getTransactionsByStationId(stationId);
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable ("id")Long transactionId, @RequestBody Transaction transaction) {
        return transactionService.updateTransaction(transactionId, transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable("id") Long transactionId) {
        transactionService.deleteTransaction(transactionId);
    }
}







