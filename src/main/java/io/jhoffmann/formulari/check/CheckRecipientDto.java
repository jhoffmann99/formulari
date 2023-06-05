package io.jhoffmann.formulari.check;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CheckRecipientDto {
    private String firstName;
    
    @NotBlank
    private String lastName;

    @NotBlank
    private Salutation salutation;
    
    @NotBlank
    @Email
    private String email;
    
    private String mobilePhone;
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    
    public Salutation getSalutation() {
        return salutation;
    }
    public void setSalutation(Salutation salutation) {
        this.salutation = salutation;
    }
    
}
