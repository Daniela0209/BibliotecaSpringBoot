package com.example.bibliotecaWeb.repository;

import com.example.bibliotecaWeb.modal.Recursos;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioRecursos extends MongoRepository<Recursos, String> {
    Iterable<Recursos> findByTipoRecurso(String tipoRecurso);
    Iterable<Recursos> findByTematica(String tematica);
}
