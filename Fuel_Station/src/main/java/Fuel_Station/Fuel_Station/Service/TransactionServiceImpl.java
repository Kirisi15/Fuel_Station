package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.TransactionEntity;
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
    public List<TransactionEntity> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public TransactionEntity getTransactionById(Long transactionId) {
        Optional<TransactionEntity> transaction = transactionRepository.findById(transactionId);

            return transaction.get();

    }

    @Override
    public TransactionEntity addTransaction(TransactionEntity transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public TransactionEntity updateTransaction(Long transactionId, TransactionEntity transaction) {
        TransactionEntity existingTransaction = getTransactionById(transactionId);

        existingTransaction.setQuantity(transaction.getQuantity());
        existingTransaction.setDateTime(transaction.getDateTime());
        return transactionRepository.save(existingTransaction);
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
