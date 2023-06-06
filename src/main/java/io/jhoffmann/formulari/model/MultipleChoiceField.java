package io.jhoffmann.formulari.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.jhoffmann.formulari.util.ChoiceOptions;

public class MultipleChoiceField extends AbstractComponent {
    private FieldType fieldType = FieldType.MULTIPLE_CHOICE;

    @JsonProperty("minOptions")
    private int minOptions;
    @JsonProperty("maxOptions")
    private int maxOptions;

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

    public int getMinOptions() {
        return minOptions;
    }

    public void setMinOptions(int minOptions) {
        this.minOptions = minOptions;
    }

    public int getMaxOptions() {
        return maxOptions;
    }

    public void setMaxOptions(int maxOptions) {
        this.maxOptions = maxOptions;
    }

}
