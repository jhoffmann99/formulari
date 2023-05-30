package io.jhoffmann.formulari.model;

public class DateTimeField extends AbstractComponent {
    private FieldType fieldType = FieldType.DATE_TIME;

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

}
