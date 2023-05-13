package io.jhoffmann.formulari.model;

public class TextField extends AbstractComponent {
    private int length;
    private boolean multiLine;
    
     public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public boolean isMultiLine() {
        return multiLine;
    }
    public void setMultiLine(boolean multiLine) {
        this.multiLine = multiLine;
    }
    
}
