package com.example.bibliotecaWeb.service;

import com.example.bibliotecaWeb.DTO.PrestamoDTO;
import com.example.bibliotecaWeb.modal.Prestamo;
import com.example.bibliotecaWeb.modal.Recursos;
import com.example.bibliotecaWeb.repository.RepositorioPrestamo;
import com.example.bibliotecaWeb.repository.RepositorioRecursos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


@SpringBootTest
public class ServicioPrestamoTest {

    @Mock
    private RepositorioPrestamo repositorioPrestamo;

    @Mock
    private RepositorioRecursos repositorioRecursos;

    @InjectMocks
    private ServicioPrestamo servicioPrestamo;


    @Test
    @DisplayName("Prestamo test")
    public void findAll(){

        var prestamo1 = new Prestamo();
        prestamo1.setIdPrestamo("46");
        prestamo1.setIdUsuario("4");
        prestamo1.setIdRecursos("23");
        prestamo1.setFechaPrestamo("12/09/2020");

        var prestamo2 = new Prestamo();
        prestamo2.setIdPrestamo("34");
        prestamo2.setIdUsuario("10");
        prestamo2.setIdRecursos("98");
        prestamo2.setFechaPrestamo("12/03/2002");

        var prestamo3 = new Prestamo();
        prestamo3.setIdPrestamo("18");
        prestamo3.setIdUsuario("6");
        prestamo3.setIdRecursos("15");
        prestamo3.setFechaPrestamo("12/10/2020");

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
        prestamo1.setFechaPrestamo("02/09/2002");

        var prestamo3 = new PrestamoDTO();
        prestamo3.setIdPrestamo("46");
        prestamo3.setIdUsuario("4");
        prestamo3.setIdRecursos("23");
        prestamo3.setFechaPrestamo("02/09/2002");

        Mockito.when(repositorioPrestamo.save(any())).thenReturn("puede realizar su prestamo");


    }


    @Test
    void actualizar(){
        var prestamo1 = new Prestamo();
        prestamo1.setIdPrestamo("46");
        prestamo1.setIdUsuario("4");
        prestamo1.setIdRecursos("23");
        prestamo1.setFechaPrestamo("02/09/2002");

        var prestamo3 = new PrestamoDTO();
        prestamo3.setIdPrestamo("46");
        prestamo3.setIdUsuario("4");
        prestamo3.setIdRecursos("23");
        prestamo3.setFechaPrestamo("02/09/2002");

        Mockito.when(repositorioPrestamo.findById(anyString())).thenReturn(Optional.of(prestamo1));

        Mockito.when(repositorioPrestamo.save(any(Prestamo.class))).thenReturn(prestamo1);

        PrestamoDTO find = servicioPrestamo.modificar(prestamo3);

        Assertions.assertEquals(prestamo3.getIdPrestamo(),find.getIdPrestamo());


    }

    //funciona
    @Test
    void borrar() {
        var prestamo = new Prestamo();
        prestamo.setIdPrestamo("46");

        Mockito.doNothing().when(repositorioPrestamo).deleteById("46");

        servicioPrestamo.borrar("46");

        Mockito.verify(repositorioPrestamo).deleteById("46");

    }

//no funciona obtener por id, no me da.
    @Test
    void obtenerIdRecurso(){

        var prestamo = new Prestamo();
        prestamo.setIdRecursos("46");

        Mockito.when(repositorioPrestamo.findById("46")).thenReturn(java.util.Optional.of(prestamo));

        servicioPrestamo.buscarPorIdRecurso("46");
        Mockito.verify(repositorioPrestamo).findById("46");
    }

    @Test
    void devolver(){

        var recurso = new Recursos();
        recurso.setIdRecursos("20");
        recurso.setTitulo("Orgullo y prejuicio");
        recurso.setTematica("Drama");
        recurso.setEstado(false);
        recurso.setTipoRecurso("Libro");

        var prestamo = new Prestamo();
        prestamo.setIdPrestamo("6");
        prestamo.setIdUsuario("16");
        prestamo.setIdRecursos("20");
        prestamo.setFechaPrestamo("11/10");
        prestamo.setFechaPrestamo("11/11");

        Mockito.when(repositorioRecursos.findById("20")).thenReturn(Optional.of(recurso));

        String find = servicioPrestamo.devolverPrestamo("20");

        Assertions.assertEquals("El recurso lo has devuelto exitosamente.",find);
    }

    @Test
    void prestar(){
        var recurso = new Recursos();
        recurso.setIdRecursos("20");
        recurso.setTitulo("Orgullo y prejuicio");
        recurso.setTematica("Drama");
        recurso.setEstado(true);
        recurso.setTipoRecurso("Libro");

        var prestamo = new Prestamo();
        prestamo.setIdPrestamo("6");
        prestamo.setIdUsuario("16");
        prestamo.setIdRecursos("20");
        prestamo.setFechaPrestamo("11/10");
        prestamo.setFechaPrestamo("11/11");

        Mockito.when(repositorioRecursos.findById("20")).thenReturn(Optional.of(recurso));

        String find = servicioPrestamo.prestar("20");

        Assertions.assertEquals("El prestámo ha sido confirmado",find);
    }
}