package Fuel_Station.Fuel_Station.Controller;

import Fuel_Station.Fuel_Station.Entity.Transaction;
import Fuel_Station.Fuel_Station.Service.TransactionService;
import Fuel_Station.Fuel_Station.dto.request.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable("id") Long transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    @GetMapping("/station/{stationId}")
    public ResponseEntity<?> getTransactionsByStation(@PathVariable("stationId") Long stationId) {
        return transactionService.getTransactionsByStationId(stationId);
    }

    @PostMapping("")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionRequest transaction) {
        return transactionService.addTransaction(transaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable("id") Long transactionId, @RequestBody TransactionRequest transaction) {
        return transactionService.updateTransaction(transactionId, transaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("id") Long transactionId) {
        return transactionService.deleteTransaction(transactionId);
    }
}







