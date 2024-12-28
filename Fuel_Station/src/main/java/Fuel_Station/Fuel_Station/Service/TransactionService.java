package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.TransactionEntity;

import java.util.List;

public interface TransactionService {
    List<TransactionEntity> getAllTransactions();
    TransactionEntity getTransactionById(Long transactionId);
    TransactionEntity addTransaction(TransactionEntity transaction);
    TransactionEntity updateTransaction(Long transactionId, TransactionEntity transaction);
    void deleteTransaction(Long transactionId);
}
