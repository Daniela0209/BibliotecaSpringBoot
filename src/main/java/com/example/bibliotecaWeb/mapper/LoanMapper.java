package com.example.bibliotecaWeb.mapper;

import com.example.bibliotecaWeb.DTO.PrestamoDTO;
import com.example.bibliotecaWeb.modal.Prestamo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LoanMapper {

    public Prestamo fromDTO(PrestamoDTO dto){
        Prestamo prestamo = new Prestamo();
        prestamo.setIdPrestamo(dto.getIdPrestamo());
        prestamo.setIdUsuario(dto.getIdUsuario());
        prestamo.setIdRecursos(dto.getIdRecursos());
        prestamo.setFechaPrestamo(dto.getFechaPrestamo());
        return prestamo;
    }
    public PrestamoDTO fromCollection(Prestamo collection){
        PrestamoDTO prestamoDTO = new PrestamoDTO();
        prestamoDTO.setIdPrestamo(collection.getIdPrestamo());
        prestamoDTO.setIdUsuario(collection.getIdUsuario());
        prestamoDTO.setIdRecursos(collection.getIdRecursos());
        prestamoDTO.setFechaPrestamo(collection.getFechaPrestamo());
        return prestamoDTO;
    }

    public List<PrestamoDTO> fromCollectionList(List<Prestamo> collection) {
        if (collection == null) {
            return null;

        }
        List<PrestamoDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()) {
            Prestamo prestamo = (Prestamo) listTracks.next();
            list.add(fromCollection(prestamo));
        }

        return list;
    }

}
