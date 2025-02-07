package Fuel_Station.Fuel_Station.Service;

import Fuel_Station.Fuel_Station.Entity.*;
import Fuel_Station.Fuel_Station.Repository.TransactionRepository;
import Fuel_Station.Fuel_Station.dto.request.TransactionRequest;
import Fuel_Station.Fuel_Station.dto.response.FuelStationResponse;
import Fuel_Station.Fuel_Station.dto.response.MessageResponse;
import Fuel_Station.Fuel_Station.dto.response.TransactionResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private FuelStationService fuelStationService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private FuelService fuelService;
    @Autowired
    private VehicleService vehicleService;

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
                    transaction.getQuantity(),
                    transaction.getDateTime()
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
            );
        }
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
        FuelStation fuelStation = fuelStationService.getById(transactionRequest.getFuelStationId());
        if(fuelStation == null){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "Fuel station not found",
                            null
                    )
            );
        }
        Fuel fuel = fuelService.getById(transactionRequest.getFuelId());
        if(fuel == null){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "Fuel not found",
                            null
                    )
            );
        }
        Employee employee = employeeService.getById(transactionRequest.getEmployeeId());
        if(employee == null){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "Employee not found",
                            null
                    )
            );
        }
        Vehicle vehicle = vehicleService.getById(transactionRequest.getVehicleId());
        if(vehicle == null){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "Vehicle not found",
                            null
                    )
            );
        }
        long currentMillis = System.currentTimeMillis();
        LocalDateTime localDateTime = Instant.ofEpochMilli(currentMillis)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        Transaction transaction = new Transaction(
                fuelStation,
                fuel,
                vehicle,
                employee,
                transactionRequest.getQuantity(),
                localDateTime
        );
        transactionRepository.save(transaction);
        return ResponseEntity.ok().body(
                new MessageResponse<>(
                        200,
                        "Transaction added Successfully",
                        null
                )
        );
    }

    @Override
    public ResponseEntity<?> updateTransaction(Long transactionId, TransactionRequest transactionRequest) {
        Optional<Transaction> optionalTransaction =  transactionRepository.getByTransactionId(transactionId);
        if (optionalTransaction.isEmpty()) {
         return ResponseEntity.ok().body(
            new MessageResponse<>(
                    400,
                    "Transaction not found",
                    null
            )
        );
    }
        Transaction transaction = optionalTransaction.get();
        transaction.setQuantity(transaction.getQuantity());
        transaction.setDateTime(transaction.getDateTime());
         transactionRepository.save(transaction);
         return ResponseEntity.ok().body(
             new MessageResponse<>(
            200,
            "Transaction updated successfully",
            null
    ));
}


    @Override
    @Transactional
    public ResponseEntity<?> deleteTransaction(Long transactionId) {
        Optional<Transaction> transaction = transactionRepository.getByTransactionId(transactionId);
        if(transaction.isEmpty()){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "Transaction not found",
                            null
                    )
            );
        }
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
        if(fuelStation == null){
            return ResponseEntity.ok().body(
                    new MessageResponse<>(
                            400,
                            "Station not found",
                            null
                    )
            );
        }
        List<Transaction> transactionList=  transactionRepository.findByFuelStation(fuelStation);
        List<TransactionResponse> responses=new ArrayList<>();
        for(Transaction transaction:transactionList){
            TransactionResponse response=new TransactionResponse(
                    transaction.getTransactionId(),
                    transaction.getQuantity(),
                    transaction.getDateTime()
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

