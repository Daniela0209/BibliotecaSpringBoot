package com.example.bibliotecaWeb.service;

import com.example.bibliotecaWeb.DTO.UsuarioDTO;
import com.example.bibliotecaWeb.modal.Usuario;
import com.example.bibliotecaWeb.repository.RepositorioUsuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class ServicioUsuarioTest {

    @Mock
    private RepositorioUsuario repositorioUsuario;

    @InjectMocks
    private ServicioUsuario servicioUsuario;



    @Test
    @DisplayName("Usuario test")
    public void findAll() {
        var usuario1 = new Usuario();
        usuario1.setIdUsuario("4");
        usuario1.setNombre("Gabriela Grajales");
        usuario1.setFecha("02/02/2020");

        var usuario2 = new Usuario();
        usuario2.setIdUsuario("6");
        usuario2.setNombre("Daniela Ocampo");
        usuario2.setFecha("02/02/2020");

        var usuario3 = new Usuario();
        usuario3.setIdUsuario("10");
        usuario3.setNombre("Kevin Arango");
        usuario3.setFecha("02/02/2021");

        var lista = new ArrayList<Usuario>();
        lista.add(usuario1);
        lista.add(usuario2);
        lista.add(usuario3);
        Mockito.when(repositorioUsuario.findAll()).thenReturn(lista);
        var respuesta = servicioUsuario.obtenerTodos();

        Assertions.assertEquals(3, respuesta.size());
        Assertions.assertEquals(usuario1.getIdUsuario(), respuesta.get(0).getIdUsuario());
        Assertions.assertEquals(usuario2.getIdUsuario(), respuesta.get(1).getIdUsuario());
        Assertions.assertEquals(usuario3.getIdUsuario(), respuesta.get(2).getIdUsuario());


    }

    @Test
    void crear() {
        var usuario1 = new Usuario();
        usuario1.setIdUsuario("4");
        usuario1.setNombre("Gabriela Grajales");
        usuario1.setFecha("02/02/2020");

        var usuario3 = new UsuarioDTO();
        usuario3.setIdUsuario("4");
        usuario3.setNombre("Gabriela Grajales");
        usuario3.setFecha("02/02/2020");

        Mockito.when(repositorioUsuario.save(any())).thenReturn((usuario1));

        var resultado = servicioUsuario.crear(usuario3);
        Assertions.assertEquals(usuario1.getIdUsuario(), resultado.getIdUsuario());

    }

    @Test
    void actualizar() {

        var usuario1 = new Usuario();
        usuario1.setIdUsuario("6");
        usuario1.setNombre("Daniela Ocampo");
        usuario1.setFecha("25/09");

        var usuario2 = new UsuarioDTO();
        usuario2.setIdUsuario("6");
        usuario2.setNombre("Daniela Ocampo");
        usuario2.setFecha("25/09");

        Mockito.when(repositorioUsuario.findById(anyString())).thenReturn(Optional.of(usuario1));
        Mockito.when(repositorioUsuario.save(any(Usuario.class))).thenReturn(usuario1);
        UsuarioDTO find = servicioUsuario.modificar(usuario2);
        Assertions.assertEquals(usuario2.getIdUsuario(),find.getIdUsuario());
    }

    @Test
    void borrar() {
        var usuario = new Usuario();
        usuario.setIdUsuario("18");

        Mockito.doNothing().when(repositorioUsuario).deleteById("18");

        servicioUsuario.borrar("18");

        Mockito.verify(repositorioUsuario).deleteById("18");


    }
}