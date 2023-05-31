package io.jhoffmann.formulari.model;

import io.jhoffmann.formulari.util.ChoiceOptions;

public class MultipleChoiceField extends AbstractComponent {
    private FieldType fieldType = FieldType.MULTIPLE_CHOICE;
    
    @ChoiceOptions
    private String options;

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }


    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }
}
