package com.app.bdt.service;

import com.app.bdt.model.entity.Talento;

import java.util.List;

public interface ITalentoService {
    List<Talento> getTalentos();

    Talento createTalento(Talento talento);
}
