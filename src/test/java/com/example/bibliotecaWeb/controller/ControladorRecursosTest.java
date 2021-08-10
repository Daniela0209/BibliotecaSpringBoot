package com.example.bibliotecaWeb.controller;

import com.example.bibliotecaWeb.DTO.RecursosDTO;
import com.example.bibliotecaWeb.service.ServicioRecursos;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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


        doReturn(recursosDTO).when(servicioRecursos).crear(any());

        mockMvc1.perform(post("/recursos/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnRecursos)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idRecursos", is("79")));

    }

     static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("obtenerRecursoId")
    public void ObtenerRecursoPorId() throws Exception{

        RecursosDTO returnRecursos = new RecursosDTO();
        RecursosDTO recursosDTO = new RecursosDTO();

        returnRecursos.setIdRecursos("321");
        returnRecursos.setTitulo("Maganize");
        returnRecursos.setTematica("Science");
        returnRecursos.isEstado(Boolean.TRUE);
        returnRecursos.setTipoRecurso("04/08/2020");

        recursosDTO.setIdRecursos("233");
        recursosDTO.setTitulo("Book");
        recursosDTO.setTematica("Math");
        recursosDTO.isEstado(Boolean.TRUE);
        recursosDTO.setTipoRecurso("09/08/2021");


        doReturn(recursosDTO).when(servicioRecursos).crear(any());

        mockMvc1.perform(get("/recursos/obtenerPorId/233")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnRecursos)))
                .andExpect(status().isOk());

    }
    
    @Test
    @DisplayName("obtenerTodos")
    public void obtenerTodos() throws Exception{

        RecursosDTO returnRecursos = new RecursosDTO();
        RecursosDTO recursosDTO = new RecursosDTO();

        returnRecursos.setIdRecursos("321");
        returnRecursos.setTitulo("Maganize");
        returnRecursos.setTematica("Science");
        returnRecursos.isEstado(Boolean.TRUE);
        returnRecursos.setTipoRecurso("04/08/2020");

        recursosDTO.setIdRecursos("233");
        recursosDTO.setTitulo("Book");
        recursosDTO.setTematica("Math");
        recursosDTO.isEstado(Boolean.TRUE);
        recursosDTO.setTipoRecurso("09/08/2021");

        doReturn(recursosDTO).when(servicioRecursos).crear(any());


        mockMvc1.perform(get("/recursos/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnRecursos)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    @DisplayName("BuscarTipoRecurso")
    public void tipoRecursos() throws Exception{

        RecursosDTO returnRecursos = new RecursosDTO();
        RecursosDTO recursosDTO = new RecursosDTO();


        returnRecursos.setIdRecursos("321");
        returnRecursos.setTitulo("Maganize");
        returnRecursos.setTematica("Science");
        returnRecursos.isEstado(Boolean.TRUE);
        returnRecursos.setTipoRecurso("04/08/2020");

        recursosDTO.setIdRecursos("233");
        recursosDTO.setTitulo("Book");
        recursosDTO.setTematica("Math");
        recursosDTO.isEstado(Boolean.TRUE);
        recursosDTO.setTipoRecurso("09/08/2021");

        doReturn(recursosDTO).when(servicioRecursos).crear(any());

        mockMvc1.perform(get("/recursos/todosRecursos/Book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnRecursos)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("BuscarRecursoTematica")
    public void BuscarRecursoTematica() throws Exception{

        RecursosDTO returnRecursos = new RecursosDTO();
        RecursosDTO recursosDTO = new RecursosDTO();


        returnRecursos.setIdRecursos("321");
        returnRecursos.setTitulo("Maganize");
        returnRecursos.setTematica("Science");
        returnRecursos.isEstado(Boolean.TRUE);
        returnRecursos.setTipoRecurso("04/08/2020");

        recursosDTO.setIdRecursos("233");
        recursosDTO.setTitulo("Book");
        recursosDTO.setTematica("Math");
        recursosDTO.isEstado(Boolean.TRUE);
        recursosDTO.setTipoRecurso("09/08/2021");

        doReturn(recursosDTO).when(servicioRecursos).crear(any());

        mockMvc1.perform(get("/recursos/tematicas/Math")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnRecursos)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
}