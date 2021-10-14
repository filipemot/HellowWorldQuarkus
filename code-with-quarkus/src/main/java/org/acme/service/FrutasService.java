package org.acme.service;

import io.quarkus.arc.Lock;
import org.acme.dto.FrutasDTO;
import org.acme.model.Fruta;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Lock // Não chama os metodos paralelamente
@ApplicationScoped
public final class FrutasService {

    @Lock(value = Lock.Type.READ, time = 3, unit = TimeUnit.SECONDS)
    public List<Fruta> list() {
        return Fruta.listAll();
    }

    @Transactional
    public void create(FrutasDTO frutasDTO) {
        Fruta fruta = new Fruta();
        fruta.nome = frutasDTO.getNome();
        fruta.qtd = frutasDTO.getQtd();
        fruta.persist();
    }
}
