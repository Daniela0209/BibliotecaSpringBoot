package com.example.bibliotecaWeb.service;

import com.example.bibliotecaWeb.DTO.RecursosDTO;
import com.example.bibliotecaWeb.modal.Recursos;
import com.example.bibliotecaWeb.repository.RepositorioRecursos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ServicioRecursosTest {

    @Mock
    private RepositorioRecursos repositorioRecursos;

    @InjectMocks
    private ServicioRecursos servicioRecursos;



    @Test
    @DisplayName("Recursos test")
    public void findAll() {

        var recurso1 = new Recursos();
        recurso1.setIdRecursos("98");
        recurso1.setTitulo("La chica del tren");
        recurso1.setTematica("Drama");
        recurso1.isEstado(Boolean.TRUE);
        recurso1.setTipoRecurso("Libro");

        var recurso2 = new Recursos();
        recurso2.setIdRecursos("23");
        recurso2.setTitulo("Revista vea");
        recurso2.setTematica("Chisme");
        recurso2.isEstado(Boolean.TRUE);
        recurso2.setTipoRecurso("Revista");

        var recurso3 = new Recursos();
        recurso3.setIdRecursos("15");
        recurso3.setTitulo("El diario de una ilusion");
        recurso3.setTematica("romantica");
        recurso3.isEstado(Boolean.TRUE);
        recurso3.setTipoRecurso("libro");

        var lista = new ArrayList<Recursos>();
        lista.add(recurso1);
        lista.add(recurso2);
        lista.add(recurso3);
        Mockito.when(repositorioRecursos.findAll()).thenReturn(lista);
        var respuesta = servicioRecursos.obtenerTodos();

        Assertions.assertEquals(3,respuesta.size());
        Assertions.assertEquals(recurso1.getIdRecursos(), respuesta.get(0).getIdRecursos());
        Assertions.assertEquals(recurso2.getIdRecursos(), respuesta.get(1).getIdRecursos());
        Assertions.assertEquals(recurso3.getIdRecursos(), respuesta.get(2).getIdRecursos());

    }


    @Test
    void crear(){

        var recurso1 = new Recursos();
        recurso1.setIdRecursos("98");
        recurso1.setTitulo("La chica del tren");
        recurso1.setTematica("Drama");
        recurso1.isEstado(Boolean.TRUE);
        recurso1.setTipoRecurso("Libro");

        var recurso3 = new RecursosDTO();
        recurso3.setIdRecursos("98");
        recurso3.setTitulo("La chica del tren");
        recurso3.setTematica("Drama");
        recurso3.isEstado(Boolean.TRUE);
        recurso3.setTipoRecurso("Libro");

        Mockito.when(repositorioRecursos.save(any())).thenReturn((recurso1));

        var resultado = servicioRecursos.crear(recurso3);
        Assertions.assertEquals(recurso1.getIdRecursos(), resultado.getIdRecursos());

    }
}