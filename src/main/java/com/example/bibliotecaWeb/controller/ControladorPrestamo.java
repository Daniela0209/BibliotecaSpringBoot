package com.example.bibliotecaWeb.controller;


import com.example.bibliotecaWeb.DTO.PrestamoDTO;
import com.example.bibliotecaWeb.DTO.UsuarioDTO;
import com.example.bibliotecaWeb.service.ServicioPrestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamo")
public class ControladorPrestamo {

    @Autowired
    ServicioPrestamo servicioPrestamo;


    @PostMapping("/crearPrestamo")
    public String create(@RequestBody PrestamoDTO prestamoDTO) {
        return servicioPrestamo.crear(prestamoDTO);
    }
    @PutMapping("/prestar/{id}")
    public ResponseEntity prestar(@PathVariable("id")String id, @PathVariable("id") String userId) {
        return new ResponseEntity(servicioPrestamo.prestar(id), HttpStatus.OK);
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity devolver(@PathVariable("id")String id, @PathVariable("id") String userId) {
        return new ResponseEntity(servicioPrestamo.devolverPrestamo(id), HttpStatus.OK);
    }

    @PutMapping("/modificarprestamo")
    public ResponseEntity<UsuarioDTO> update(@RequestBody PrestamoDTO prestamoDTO){
        if (prestamoDTO.getIdUsuario() != null){
            return new ResponseEntity(servicioPrestamo.modificar(prestamoDTO),HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/eliminarprestamo/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            servicioPrestamo.borrar(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/findAllPrestamo")
    public ResponseEntity<List<PrestamoDTO>> findAll(){
        return new ResponseEntity(servicioPrestamo.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/buscarIdRecurso/{idRecursos}")
    public ResponseEntity<List<PrestamoDTO>> buscarTipoRecurso(@PathVariable("idRecursos") String idRecursos) {
        return new ResponseEntity(servicioPrestamo.buscarPorIdRecurso(idRecursos), HttpStatus.OK);
    }

}
