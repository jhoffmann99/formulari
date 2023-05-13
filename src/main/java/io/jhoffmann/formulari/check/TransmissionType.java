package io.jhoffmann.formulari.check;

public enum TransmissionType {
    E_MAIL("E-Mail"), SMS("SMS"), LINK("LINK");

    TransmissionType(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    private String value;
}
