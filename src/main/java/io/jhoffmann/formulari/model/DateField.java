package io.jhoffmann.formulari.model;

import java.time.LocalDate;

public class DateField extends AbstractComponent {
    private LocalDate value;

    public LocalDate getValue() {
        return value;
    }

    public void setValue(LocalDate value) {
        this.value = value;
    }

    
}
