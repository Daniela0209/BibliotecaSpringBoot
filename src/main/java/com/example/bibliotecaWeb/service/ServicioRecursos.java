package com.example.bibliotecaWeb.service;


import com.example.bibliotecaWeb.DTO.RecursosDTO;
import com.example.bibliotecaWeb.mapper.MeansMapper;
import com.example.bibliotecaWeb.modal.Recursos;
import com.example.bibliotecaWeb.repository.RepositorioRecursos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ServicioRecursos {

    @Autowired
    RepositorioRecursos repositorioRecursos;

    MeansMapper mapper = new MeansMapper();


    public RecursosDTO crear(RecursosDTO recursosDTO) {
        Recursos recursos = mapper.fromDTO(recursosDTO);
        return mapper.fromCollection(repositorioRecursos.save(recursos));
    }

    public String obtenerPorId(String id) {
        Optional<Recursos> recurso = repositorioRecursos.findById(id);

        if (recurso.isEmpty()) {
            return "El recurso no existe.";
        }
        if (recurso.get().isEstado(Boolean.FALSE)== false) {

            return "El recurso no está disponible.";
        } else {
            return "El recurso si está disponible.";
        }
    }
    public List<RecursosDTO> obtenerTodos() {
        List<Recursos> recursos = (List<Recursos>) repositorioRecursos.findAll();
        return mapper.fromCollectionList(recursos);
    }

    public List<RecursosDTO> obtenerRecursosTipo(String tipo) {
        List<Recursos> recurso = (List<Recursos>) repositorioRecursos.findByTipoRecurso(tipo);
        return mapper.fromCollectionList(recurso);
    }

    public List<RecursosDTO> obtenerTematica(String tematica){
        List<Recursos> recurso = (List<Recursos>) repositorioRecursos.findByTematica(tematica);
        return mapper.fromCollectionList(recurso);
    }
}
