package Fuel_Station.Fuel_Station.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Fuel_Station.Fuel_Station.dto.request.SmsRequest;
import Fuel_Station.Fuel_Station.Service.SmsService;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/sms")

public class SmsController {
    private SmsService smsService;

    @PostMapping("/send")
    public String sendSms(@RequestBody SmsRequest smsRequest)
    {
        return smsService.sendSms(smsRequest);
    }

}
