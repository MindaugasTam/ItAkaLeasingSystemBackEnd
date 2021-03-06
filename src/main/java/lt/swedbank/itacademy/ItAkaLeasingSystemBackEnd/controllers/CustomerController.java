package lt.swedbank.itacademy.ItAkaLeasingSystemBackEnd.controllers;

import lt.swedbank.itacademy.ItAkaLeasingSystemBackEnd.beans.documents.BusinessCustomer;
import lt.swedbank.itacademy.ItAkaLeasingSystemBackEnd.beans.documents.PrivateCustomer;
import lt.swedbank.itacademy.ItAkaLeasingSystemBackEnd.beans.errors.ErrorDetails;
import lt.swedbank.itacademy.ItAkaLeasingSystemBackEnd.beans.front.EmailCredentials;
import lt.swedbank.itacademy.ItAkaLeasingSystemBackEnd.beans.response.BusinessCustomerResponse;
import lt.swedbank.itacademy.ItAkaLeasingSystemBackEnd.beans.response.CustomerResponse;
import lt.swedbank.itacademy.ItAkaLeasingSystemBackEnd.beans.response.PrivateCustomerResponse;
import lt.swedbank.itacademy.ItAkaLeasingSystemBackEnd.services.CustomerService;
import lt.swedbank.itacademy.ItAkaLeasingSystemBackEnd.utils.EndPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Lukas
 */
@RestController
@ControllerAdvice
@CrossOrigin
@RequestMapping(value = "/")
public class CustomerController extends ResponseEntityExceptionHandler {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = EndPoints.CUSTOMERS)
    public List<CustomerResponse> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @RequestMapping(value = EndPoints.CUSTOMERS_ADD_BUSINESS_CUSTOMER, method = RequestMethod.POST)
    public BusinessCustomerResponse addCustomer(@Valid @RequestBody BusinessCustomer customer){
        return new BusinessCustomerResponse(customerService.addNewBusinessCustomer(customer));
    }

    @RequestMapping(value = EndPoints.CUSTOMERS_ADD_PRIVATE_CUSTOMER, method = RequestMethod.POST)
    public CustomerResponse addCustomer(@Valid @RequestBody PrivateCustomer customer){
        return new PrivateCustomerResponse(customerService.addNewPrivateCustomer(customer));
    }

    @RequestMapping(value = EndPoints.CUSTOMERS_EXISTS_BY_USER_ID, method = RequestMethod.POST)
    public ResponseEntity existsCustomerByID(@PathVariable("userId") String userId){
        boolean exists = customerService.existsCustomerByUserID(userId);
        return exists ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = EndPoints.CUSTOMERS_EXISTS_BY_EMAIL, method = RequestMethod.POST)
    public ResponseEntity existsCustomerByEmail(@PathVariable("email") String email){
        boolean exists = customerService.existsCustomerByEmail(email);
        return exists ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = EndPoints.CUSTOMERS_EXISTSBY_ID_AND_EMAIL, method = RequestMethod.POST)
    public ResponseEntity<Object> existsCustomerByIdAndEmail(@RequestBody EmailCredentials credentials){
        boolean exists = customerService.existsCustomerByUserIDAndEmail(credentials.getUserId(), credentials.getEmail());
        return exists ? new ResponseEntity<>("User found", HttpStatus.OK) :
                new ResponseEntity<>("No such user found", HttpStatus.NOT_FOUND);
    }




    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<String> errorMessages = new ArrayList<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            errorMessages.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ErrorDetails errorDetails = new ErrorDetails(dateFormat.format(new Date()), "Validation failure", errorMessages);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
