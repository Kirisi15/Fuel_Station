package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.Transaction;
import Fuel_Station.Fuel_Station.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);

            return transaction.get();

    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(Long transactionId, Transaction transaction) {
        Transaction existingTransaction = getTransactionById(transactionId);

        existingTransaction.setQuantity(transaction.getQuantity());
        existingTransaction.setDateTime(transaction.getDateTime());
        return transactionRepository.save(existingTransaction);
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
    public List<Transaction> getTransactionsByStationId(Long stationId) {
        return transactionRepository.findByStation_Id(stationId);
    }
}
