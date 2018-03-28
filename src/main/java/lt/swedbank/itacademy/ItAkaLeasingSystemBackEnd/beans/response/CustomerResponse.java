package lt.swedbank.itacademy.ItAkaLeasingSystemBackEnd.beans.response;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Lukas on 2018-03-20.
 */
public class CustomerResponse extends Response{

    private String id;

    private String email;

    private BigInteger phoneNumber;

    private String adress;

    private String customerType;

    private String country;

    private String userID;

    private String password;

    public CustomerResponse(){

    }

    public CustomerResponse(String id, String email, BigInteger phoneNumber,
                            String adress, String customerType, String country,
                            String userID, String password) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.adress = adress;
        this.customerType = customerType;
        this.country = country;
        this.userID = userID;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigInteger getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(BigInteger phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
