package io.jhoffmann.formulari.check;

import java.util.List;

import io.jhoffmann.formulari.model.FieldType;

public class CheckReplyRequestDto {
    private String uid;
    private List<FieldReply> data;
    
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public List<FieldReply> getData() {
        return data;
    }
    public void setData(List<FieldReply> data) {
        this.data = data;
    }

    
}

class FieldReply {
    private String name;
    private FieldType type;
    private Object value;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public FieldType getType() {
        return type;
    }
    public void setType(FieldType type) {
        this.type = type;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }

    
}
