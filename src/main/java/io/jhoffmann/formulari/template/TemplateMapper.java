package io.jhoffmann.formulari.template;

public final class TemplateMapper {
    private TemplateMapper() {
    }

    public static SingleTemplateResponseDto singleTemplateEntityToDto(TemplateEntity entity) {
        SingleTemplateResponseDto responseDto = new SingleTemplateResponseDto();
        responseDto.setComponents(entity.getComponents());
        responseDto.setName(entity.getName());

        responseDto.setUid(entity.getUid());
        responseDto.setCreatedAt(entity.getCreatedAt());

        return responseDto;
    }
}
