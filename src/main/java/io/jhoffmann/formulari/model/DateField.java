package io.jhoffmann.formulari.model;

public class DateField extends AbstractComponent {
    private FieldType fieldType = FieldType.DATE;

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

}
