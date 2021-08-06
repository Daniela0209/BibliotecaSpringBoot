package com.example.bibliotecaWeb.mapper;

import com.example.bibliotecaWeb.DTO.RecursosDTO;
import com.example.bibliotecaWeb.modal.Recursos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MeansMapper {

    public Recursos fromDTO(RecursosDTO dto) {
        Recursos recursos =new Recursos();
        recursos.setIdRecursos(dto.getIdRecursos());
        recursos.setTitulo(dto.getTitulo());
        recursos.setTematica(dto.getTematica());
        recursos.setEstado(dto.isEstado(Boolean.TRUE));
        recursos.setTipoRecurso(dto.getTipoRecurso());
        return recursos;
    }

    public RecursosDTO fromCollection(Recursos collection){
        RecursosDTO recursosDTO = new RecursosDTO();
        recursosDTO.setIdRecursos(collection.getIdRecursos());
        recursosDTO.setTitulo(collection.getTitulo());
        recursosDTO.setTematica(collection.getTematica());
        recursosDTO.setEstado(collection.isEstado(Boolean.FALSE));
        recursosDTO.setTipoRecurso(collection.getTipoRecurso());
        return recursosDTO;
    }

    public List<RecursosDTO> fromCollectionList(List<Recursos> collection) {
        if (collection == null) {
            return null;

        }
        List<RecursosDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()) {
            Recursos recursos = (Recursos) listTracks.next();
            list.add(fromCollection(recursos));
        }

        return list;
    }
}
