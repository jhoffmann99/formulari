package io.jhoffmann.formulari.model;

public class YesNoField  extends AbstractComponent {
    private FieldType fieldType = FieldType.YES_NO;

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    
}
