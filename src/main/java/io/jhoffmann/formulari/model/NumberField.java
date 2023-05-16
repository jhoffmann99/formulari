package io.jhoffmann.formulari.model;

public class NumberField extends AbstractComponent {
    private Double min;
    private Double max;
    private FieldType fieldType = FieldType.NUMBER;
    
    private boolean hasMin() {
        return min != null;
    }

    private boolean hasMax() {
        return max != null;
    }
    
    public Double getMin() {
        return min;
    }
    public void setMin(Double min) {
        this.min = min;
    }
    public Double getMax() {
        return max;
    }
    public void setMax(Double max) {
        this.max = max;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }
    
}
