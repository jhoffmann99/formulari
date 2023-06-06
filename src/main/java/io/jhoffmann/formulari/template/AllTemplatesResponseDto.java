package io.jhoffmann.formulari.template;

import java.io.Serializable;
import java.util.List;

public class AllTemplatesResponseDto implements Serializable {
    private List<SingleTemplateResponseDto> templates;

    public List<SingleTemplateResponseDto> getTemplates() {
        return templates;
    }

    public void setTemplates(List<SingleTemplateResponseDto> templates) {
        this.templates = templates;
    }

}
