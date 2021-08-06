package com.example.bibliotecaWeb.service;


import com.example.bibliotecaWeb.DTO.PrestamoDTO;
import com.example.bibliotecaWeb.mapper.LoanMapper;
import com.example.bibliotecaWeb.modal.Prestamo;
import com.example.bibliotecaWeb.modal.Recursos;
import com.example.bibliotecaWeb.repository.RepositorioPrestamo;
import com.example.bibliotecaWeb.repository.RepositorioRecursos;
import com.example.bibliotecaWeb.repository.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioPrestamo {

    @Autowired
    RepositorioPrestamo repositorioPrestamo;

    @Autowired
    RepositorioUsuario repositorioUsuario;

    @Autowired
    RepositorioRecursos repositorioRecursos;

    LoanMapper mapper = new LoanMapper();

    public String crear(PrestamoDTO prestamoDTO) {
        Prestamo prestamo = mapper.fromDTO(prestamoDTO);
        if(repositorioRecursos.existsById(prestamo.getIdRecursos())&&repositorioUsuario.existsById(prestamo.getIdUsuario())){
            Optional<Recursos> recurso = repositorioRecursos.findById(prestamoDTO.getIdRecursos());
            if (recurso.get().isEstado(Boolean.TRUE)){
                recurso.get().setEstado(Boolean.FALSE);
                mapper.fromCollection(repositorioPrestamo.save(prestamo));
                return "Puede realizar su prestámo.";
            }else{
                return "No puede hacer la creación del prestámo";
            }
        }
        return "No se puede hacer el prestámo, si no existe el usuario o el recurso.";
    }

    public String prestar(String id){
        Recursos resources = repositorioRecursos.findById(id).orElseThrow(()
                -> new RuntimeException("Asegurese de verificar si puede realizar su prestámo"));
        if (resources.isEstado(Boolean.TRUE)){
            resources.setEstado(Boolean.FALSE);
            repositorioRecursos.save(resources);
            return "El prestámo ha sido confirmado";
        }else {
            return "Asegurese de verificar si puede realizar su prestámo";
        }
    }

    public String devolverPrestamo(String id){
        Recursos resources = repositorioRecursos.findById(id).orElseThrow(()
                -> new RuntimeException("No se encontró el recurso"));
        if (resources.isEstado(Boolean.TRUE)){
            return "El recurso no lo has prestado, por lo tanto no puedes devolverlo.";
        }else {
            resources.setEstado(Boolean.TRUE);
            repositorioRecursos.save(resources);
            return "El recurso lo has devuelto exitosamente.";
        }
    }

    public PrestamoDTO modificar(PrestamoDTO prestamoDTO) {
        Prestamo prestamo = mapper.fromDTO(prestamoDTO);
        repositorioPrestamo.findById(prestamo.getIdPrestamo()).orElseThrow(() -> new RuntimeException("prestamo no encontrado"));
        return mapper.fromCollection(repositorioPrestamo.save(prestamo));
    }

    public void borrar(String id) {
        repositorioPrestamo.deleteById(id);
    }

    public List<PrestamoDTO> buscarPorIdRecurso(String idRecursos){
        List<Prestamo> prestamo = (List<Prestamo>) repositorioPrestamo.findByIdRecursos(idRecursos);
        return mapper.fromCollectionList(prestamo);
    }
}
