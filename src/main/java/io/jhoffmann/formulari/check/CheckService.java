package io.jhoffmann.formulari.check;

import org.springframework.stereotype.Service;

@Service
public class CheckService {
    private final CheckRepository repository;

    public CheckService(CheckRepository repository) {
        this.repository = repository;
    }
}
