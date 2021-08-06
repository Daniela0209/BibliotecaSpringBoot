package com.example.bibliotecaWeb.controller;

import com.example.bibliotecaWeb.DTO.RecursosDTO;
import com.example.bibliotecaWeb.service.ServicioRecursos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
@AutoConfigureMockMvc
public class ControladorRecursosTest {

    @MockBean
    private ServicioRecursos servicioRecursos;

    @Autowired
    private MockMvc mockMvc1;

    @Test
    @DisplayName("crear recurso")
    public void crearRecurso() throws Exception{

        RecursosDTO returnRecursos = new RecursosDTO();
        RecursosDTO recursosDTO = new RecursosDTO();

        returnRecursos.setIdRecursos("79");
        returnRecursos.setTitulo("Revista Vea");
        returnRecursos.setTematica("moda");
        returnRecursos.isEstado(true);
        returnRecursos.setTipoRecurso("revista");

        recursosDTO.setIdRecursos("79");
        recursosDTO.setTitulo("El principito");
        recursosDTO.setTematica("infantil");
        recursosDTO.isEstado(true);
        recursosDTO.setTipoRecurso("libro");


    }

}