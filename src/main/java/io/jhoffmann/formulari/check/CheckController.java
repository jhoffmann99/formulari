package io.jhoffmann.formulari.check;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("check")
public class CheckController {
    private final CheckService service;

    public CheckController(CheckService service) {
        this.service = service;
    }

    @PostMapping
    public void addCheck(@RequestBody CreateCheckRequestDto dto) {
        CheckEntity savedCheck = service.createCheck(dto.getName(), dto.getTransmissionType(), dto.getTemplateName(),
                dto.getRecipients());
                
        service.createCheckRecipients(savedCheck, dto.getRecipients());
    }

    @PostMapping("reply")
    public void replyCheck(@RequestBody CheckReplyRequestDto dto) {
        service.replyCheck(dto.getUid(), dto.getData());
    }
}
