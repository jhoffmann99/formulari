package io.jhoffmann.formulari.check;

public class CheckDetailsResponseDto {
    private String checkId;
    private String subject;
    private String greeting;

    private Salutation salutation;
    private String firstName;
    private String lastName;
    

    public String getCheckId() {
        return checkId;
    }
    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getGreeting() {
        return greeting;
    }
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
    public Salutation getSalutation() {
        return salutation;
    }
    public void setSalutation(Salutation salutation) {
        this.salutation = salutation;
    }
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

    

}
