package com.example.bibliotecaWeb.service;

import com.example.bibliotecaWeb.DTO.PrestamoDTO;
import com.example.bibliotecaWeb.modal.Prestamo;
import com.example.bibliotecaWeb.modal.Recursos;
import com.example.bibliotecaWeb.repository.RepositorioPrestamo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ServicioPrestamoTest {

    @MockBean
    private RepositorioPrestamo repositorioPrestamo;

    @Autowired
    private ServicioPrestamo servicioPrestamo;

    private Date objeDate = new Date();
    private String strDataFormat = "hh: mm: ss a dd-MMM-aaaa";
    private SimpleDateFormat objsDF = new SimpleDateFormat(strDataFormat);


    @Test
    @DisplayName("Prestamo test")
    public void findAll(){

        var prestamo1 = new Prestamo();
        prestamo1.setIdPrestamo("46");
        prestamo1.setIdUsuario("4");
        prestamo1.setIdRecursos("23");
        prestamo1.setFechaPrestamo(objsDF.format(objeDate));

        var prestamo2 = new Prestamo();
        prestamo2.setIdPrestamo("34");
        prestamo2.setIdUsuario("10");
        prestamo2.setIdRecursos("98");
        prestamo2.setFechaPrestamo(objsDF.format(objeDate));

        var prestamo3 = new Prestamo();
        prestamo3.setIdPrestamo("18");
        prestamo3.setIdUsuario("6");
        prestamo3.setIdRecursos("15");
        prestamo3.setFechaPrestamo(objsDF.format(objeDate));

        var lista = new ArrayList<Prestamo>();
        lista.add(prestamo1);
        lista.add(prestamo2);
        lista.add(prestamo3);
        Mockito.when(repositorioPrestamo.findAll()).thenReturn(lista);
        var respuesta = servicioPrestamo.obtenerTodos();

        Assertions.assertEquals(3,respuesta.size());
        Assertions.assertEquals(prestamo1.getIdPrestamo(), respuesta.get(0).getIdPrestamo());
        Assertions.assertEquals(prestamo2.getIdPrestamo(), respuesta.get(1).getIdPrestamo());
        Assertions.assertEquals(prestamo3.getIdPrestamo(), respuesta.get(2).getIdPrestamo());

    }

    @Test
    void crear(){
        var prestamo1 = new Prestamo();
        prestamo1.setIdPrestamo("46");
        prestamo1.setIdUsuario("4");
        prestamo1.setIdRecursos("23");
        prestamo1.setFechaPrestamo(objsDF.format(objeDate));

        var prestamo3 = new PrestamoDTO();
        prestamo3.setIdPrestamo("18");
        prestamo3.setIdUsuario("6");
        prestamo3.setIdRecursos("15");
        prestamo3.setFechaPrestamo(objsDF.format(objeDate));

        Mockito.when(repositorioPrestamo.save(any())).thenReturn((prestamo1));

        var resultado = servicioPrestamo.crear(prestamo3);
        Assertions.assertEquals(prestamo1.getIdPrestamo(), resultado);



    }

}