package Fuel_Station.Fuel_Station.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse<T> {
    private int status;
    private String message;
    private T data;
}
