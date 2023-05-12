package io.jhoffmann.formulari.template;


public class CreateTemplateRequestDto {
    private String templateName;

    private Components components;
    
   
    public String getTemplateName() {
        return templateName;
    }
    public void setTemplateName(String name) {
        this.templateName = name;
    }
    public Components getComponents() {
        return components;
    }
    public void setComponents(Components components) {
        this.components = components;
    }

   

    
}
