package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Transaction;
import Fuel_Station.Fuel_Station.Entity.Vehicle;
import Fuel_Station.Fuel_Station.dto.request.TransactionRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface TransactionService {
    ResponseEntity<?> getAllTransactions();
    ResponseEntity<?> getTransactionById(Long transactionId);
    ResponseEntity<?> addTransaction(TransactionRequest transactionRequest);
    ResponseEntity<?> updateTransaction(Long transactionId, TransactionRequest transactionRequest);
    ResponseEntity<?> deleteTransaction(Long transactionId);
    ResponseEntity<?> getTransactionsByStationId(Long stationId);
}
