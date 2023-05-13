package io.jhoffmann.formulari.template;

public final class TemplateMapper {
    private TemplateMapper() {}
    
    public static SingleTemplateResponseDto singleTemplateEntityToDto(TemplateEntity entity) {
        SingleTemplateResponseDto responseDto = new SingleTemplateResponseDto();
        responseDto.setComponents(entity.getComponents());
        responseDto.setTemplateName(entity.getName());

        return responseDto;
    }
}
