package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Transaction;

import java.util.List;
public interface TransactionService {
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(Long transactionId);
    Transaction addTransaction(Transaction transaction);
    Transaction updateTransaction(Long transactionId, Transaction transaction);
    void deleteTransaction(Long transactionId);
    List<Transaction> getTransactionsByStationId(Long stationId);
}
