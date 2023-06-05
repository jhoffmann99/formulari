package io.jhoffmann.formulari.model;

public class RatingField extends AbstractComponent {
    private FieldType fieldType = FieldType.RATING;
    private int max;
    private double step;
    
    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }    
    
    
}
