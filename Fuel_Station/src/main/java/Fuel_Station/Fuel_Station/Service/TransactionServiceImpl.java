package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.FuelStation;
import Fuel_Station.Fuel_Station.Entity.Transaction;
import Fuel_Station.Fuel_Station.Repository.TransactionRepository;
import Fuel_Station.Fuel_Station.dto.request.TransactionRequest;
import Fuel_Station.Fuel_Station.dto.response.FuelStationResponse;
import Fuel_Station.Fuel_Station.dto.response.MessageResponse;
import Fuel_Station.Fuel_Station.dto.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private FuelStationService fuelStationService;

    public Transaction getById(Long transactionId){
        return transactionRepository.getByTransactionId(transactionId).orElse(null);
    }

    @Override
    public ResponseEntity<?> getAllTransactions() {
        List<Transaction> transactionList= transactionRepository.findAll();
        List<TransactionResponse> responses=new ArrayList<>();
        for (Transaction transaction:transactionList){
            TransactionResponse response = new TransactionResponse(
            transaction.getTransactionId(),
            transaction.getDateTime(),
            transaction.getQuantity()
        
            );
        responses.add(response);
        }
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            200,
                            "All Transactions fetched successfully",
                            responses
                    )
            );
    }

    @Override
    public ResponseEntity<?> getTransactionById(Long transactionId) {
        Optional<Transaction> OptionalTransaction = transactionRepository.findById(transactionId);
        if (OptionalTransaction.isEmpty()) {
            
        return ResponseEntity.ok().body(
            new MessageResponse<>(
                    400,
                    "Transaction id not found",
                    null
            )
    );}
            Transaction transaction=OptionalTransaction.get();
            TransactionResponse response=new TransactionResponse(
                transaction.getTransactionId(),
                transaction.getQuantity(),
                transaction.getDateTime());
                return ResponseEntity.ok().body(new MessageResponse<>(
                    200,
                    "Fuel station fetched successfully",
                    response
            ));
    }

    @Override
    public ResponseEntity<?> addTransaction(TransactionRequest transactionRequest) {
        Transaction transaction =new TransactionResponse(

                transactionRequest.getDateTime(),
                transactionRequest.getQuantity());
               
        transactionRepository.save(transaction);
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Fuel station added successfully",
                        null
                )
        );
    }

    @Override
    public ResponseEntity<?> updateTransaction(Long transactionId, TransactionRequest transaction) {
        Transaction existingTransaction =  transactionRepository.getByTransactionId(transactionId);
if (existingTransaction==null) {
    return ResponseEntity.ok().body(
        new MessageResponse<>(
                400,
                "Fuel station not found",
                null
        )
);
    
}
        existingTransaction.setQuantity(transaction.getQuantity());
        existingTransaction.setDateTime(transaction.getDateTime());
         transactionRepository.save(existingTransaction);
         return ResponseEntity.ok().body( 
             new MessageResponse<>(
            200,
            "Transaction updated successfully",
            null
    ));
}
    

    @Override
    public ResponseEntity<?> deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
        return ResponseEntity.ok().body(
            new MessageResponse<>(
                    200,
                    "Transaction deleted successfully",
                    null
            ));
    }

    public  ResponseEntity<?>  getTransactionsByStationId(Long stationId) {
        FuelStation fuelStation = fuelStationService.getById(stationId);
        if(fuelStation != null){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "Owner not found",
                            null
                    )
            );
        }
          List<Transaction> transactionList=  transactionRepository.findByFuelStation(fuelStation);
        List<TransactionResponse> responses=new ArrayList<>();
        for(Transaction transaction:transactionList){
            TransactionResponse response=new TransactionResponse(
            transaction.getDateTime(),
            transaction.getTransactionId(),
            transaction.getQuantity()
            );
responses.add(response);
        }
        return ResponseEntity.ok().body(
            new MessageResponse<>(
                    200,
                    "All Transactions fetched successfully",
                    responses
            )
    );
        }
        
    }

