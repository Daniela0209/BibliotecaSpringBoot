package com.example.bibliotecaWeb.controller;

import com.example.bibliotecaWeb.DTO.UsuarioDTO;
import com.example.bibliotecaWeb.service.ServicioUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.doReturn;
import static org.hamcrest.Matchers.is;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
public class ControladorUsuarioTest {

    @MockBean
    private ServicioUsuario servicioUsuario;

    @Autowired
    private MockMvc mockMvc1;

    @Test
    @DisplayName("crear usuario")
    public void crearusuario() throws Exception {


        UsuarioDTO returnUsuario = new UsuarioDTO();
        UsuarioDTO usuarioDTO = new UsuarioDTO();


        returnUsuario.setIdUsuario("35");
        returnUsuario.setNombre("Gaby Grajales");
        returnUsuario.setFecha("02/10/2002");

        usuarioDTO.setIdUsuario("35");
        usuarioDTO.setNombre("Kevin Galeano");
        usuarioDTO.setFecha("05/04/2021");

        doReturn(usuarioDTO).when(servicioUsuario).crear(any());

        mockMvc1.perform(post("/usuario/crearusuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnUsuario)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idUsuario", is("35")));

    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("modificarUsuario")
    public void modificar() throws Exception {

        UsuarioDTO returnUsuario = new UsuarioDTO();
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        returnUsuario.setIdUsuario("23");
        returnUsuario.setNombre("Sara agudelo");
        returnUsuario.setFecha("12/09/2020");

        usuarioDTO.setIdUsuario("23");
        usuarioDTO.setNombre("Sara agudelo");
        usuarioDTO.setFecha("11/04/2021");

        doReturn(usuarioDTO).when(servicioUsuario).modificar(any());

        mockMvc1.perform(MockMvcRequestBuilders.put("/usuario/modificarusuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnUsuario)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    @DisplayName("borrarUsuario")
    void borrarUsuario() throws Exception {

        UsuarioDTO returnUsuario = new UsuarioDTO();
        UsuarioDTO usuarioDTO = new UsuarioDTO();


        returnUsuario.setIdUsuario("35");
        returnUsuario.setNombre("Gaby Grajales");
        returnUsuario.setFecha("02/10/2002");

        usuarioDTO.setIdUsuario("35");
        usuarioDTO.setNombre("Kevin Galeano");
        usuarioDTO.setFecha("05/04/2021");

        doReturn(usuarioDTO).when(servicioUsuario).crear(any());

        mockMvc1.perform(MockMvcRequestBuilders.delete("/usuario/eliminarusuario/35")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnUsuario)))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("obtener Recursos")
    void obtenerTodos() throws Exception {

        UsuarioDTO returnUsuario = new UsuarioDTO();
        UsuarioDTO usuarioDTO = new UsuarioDTO();


        returnUsuario.setIdUsuario("35");
        returnUsuario.setNombre("Gaby Grajales");
        returnUsuario.setFecha("02/10/2002");

        usuarioDTO.setIdUsuario("35");
        usuarioDTO.setNombre("Kevin Galeano");
        usuarioDTO.setFecha("05/04/2021");

        doReturn(usuarioDTO).when(servicioUsuario).crear(any());

        mockMvc1.perform(get("/usuario/findAllUsuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnUsuario)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}