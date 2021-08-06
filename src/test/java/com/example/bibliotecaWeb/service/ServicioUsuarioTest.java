package com.example.bibliotecaWeb.service;

import com.example.bibliotecaWeb.DTO.UsuarioDTO;
import com.example.bibliotecaWeb.modal.Usuario;
import com.example.bibliotecaWeb.repository.RepositorioUsuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;




@SpringBootTest
public class ServicioUsuarioTest {

    @MockBean
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private ServicioUsuario servicioUsuario;

    private Date objeDate = new Date();
    private String strDataFormat = "hh: mm: ss a dd-MMM-aaaa";
    private SimpleDateFormat objsDF = new SimpleDateFormat(strDataFormat);

    @Test
    @DisplayName("Usuario test")
    public void findAll(){
        var usuario1 = new Usuario();
        usuario1.setIdUsuario("4");
        usuario1.setNombre("Gabriela Grajales");
        usuario1.setFecha(objsDF.format(objeDate));

        var usuario2 = new Usuario();
        usuario2.setIdUsuario("6");
        usuario2.setNombre("Daniela Ocampo");
        usuario2.setFecha(objsDF.format(objeDate));

        var usuario3 = new Usuario();
        usuario3.setIdUsuario("10");
        usuario3.setNombre("Kevin Arango");
        usuario3.setFecha(objsDF.format(objeDate));

        var lista = new ArrayList<Usuario>();
        lista.add(usuario1);
        lista.add(usuario2);
        lista.add(usuario3);
        Mockito.when(repositorioUsuario.findAll()).thenReturn(lista);
        var respuesta = servicioUsuario.obtenerTodos();

        Assertions.assertEquals(3,respuesta.size());
        Assertions.assertEquals(usuario1.getIdUsuario(), respuesta.get(0).getIdUsuario());
        Assertions.assertEquals(usuario2.getIdUsuario(), respuesta.get(1).getIdUsuario());
        Assertions.assertEquals(usuario3.getIdUsuario(), respuesta.get(2).getIdUsuario());


    }

    @Test
    void crear(){
        var usuario1 = new Usuario();
        usuario1.setIdUsuario("4");
        usuario1.setNombre("Gabriela Grajales");
        usuario1.setFecha(objsDF.format(objeDate));

        var usuario3 = new UsuarioDTO();
        usuario3.setIdUsuario("10");
        usuario3.setNombre("Kevin Arango");
        usuario3.setFecha(objsDF.format(objeDate));

        Mockito.when(repositorioUsuario.save(any())).thenReturn((usuario1));

        var resultado = servicioUsuario.crear(usuario3);
        Assertions.assertEquals(usuario1.getIdUsuario(), resultado.getIdUsuario());

    }


}