package io.jhoffmann.formulari.check;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("check")
public class CheckController {
    private final CheckService service;

    public CheckController(CheckService service) {
        this.service = service;
    }
}
