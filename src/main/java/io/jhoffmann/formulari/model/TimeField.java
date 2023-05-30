package io.jhoffmann.formulari.model;

public class TimeField extends AbstractComponent {
    private FieldType fieldType = FieldType.TIME;

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

}
