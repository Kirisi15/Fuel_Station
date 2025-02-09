package Fuel_Station.Fuel_Station.Service;
import Fuel_Station.Fuel_Station.dto.request.SmsRequest;


import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;



@Service

public class SmsService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromPhoneNumber;

    public String sendSms(SmsRequest smsRequest) {
        try {

            if (smsRequest.getTo() == null || smsRequest.getTo().isEmpty()) {
                return "Recipient phone number is missing.";
            }
            if (smsRequest.getBody() == null || smsRequest.getBody().isEmpty()) {
                return "Message body is missing.";
            }


            String toPhoneNumber = smsRequest.getTo();
            if (!toPhoneNumber.startsWith("+")) {
                toPhoneNumber = "+1" + toPhoneNumber; // Adjust as needed
            }


            Twilio.init(accountSid, authToken);


            Message message = Message.creator(
                    new PhoneNumber(toPhoneNumber),
                    new PhoneNumber(fromPhoneNumber),
                    smsRequest.getBody()
            ).create();

            return "Message sent successfully. SID: " + message.getSid();

        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send message: " + e.getMessage();
        }
    }

}



