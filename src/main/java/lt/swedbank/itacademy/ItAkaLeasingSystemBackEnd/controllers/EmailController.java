package lt.swedbank.itacademy.ItAkaLeasingSystemBackEnd.controllers;

import lt.swedbank.itacademy.ItAkaLeasingSystemBackEnd.services.EmailService;
import lt.swedbank.itacademy.ItAkaLeasingSystemBackEnd.utils.EndPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping(value= EndPoints.CUSTOMERS_FORGOTPASSWORD_EMAIL, method = RequestMethod.POST)
    public ResponseEntity<String> forgotPassword(@PathVariable("email") String email){
        return emailService.forgotPassword(email);
    }

    @RequestMapping(value = EndPoints.CUSTOMERS_RESETPASSWORDVAL, method = RequestMethod.GET)
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token){
        return emailService.resetPassword(token);
    }
}
