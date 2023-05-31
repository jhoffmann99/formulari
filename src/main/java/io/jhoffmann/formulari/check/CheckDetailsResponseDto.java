package io.jhoffmann.formulari.check;

public class CheckDetailsResponseDto {
    private String checkId;
    private String subject;
    private String greeting;
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

    

}
