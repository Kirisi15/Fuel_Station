package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface TransactionService {
    ResponseEntity<?> getAllTransactions();
    ResponseEntity<?> getTransactionById(Long transactionId);
    ResponseEntity<?> addTransaction(Transaction transaction);
    ResponseEntity<?> updateTransaction(Long transactionId, Transaction transaction);
    ResponseEntity<?> deleteTransaction(Long transactionId);
    ResponseEntity<?> getTransactionsByStationId(Long stationId);
}
