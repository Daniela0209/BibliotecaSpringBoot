package com.example.bibliotecaWeb.controller;


import com.example.bibliotecaWeb.DTO.UsuarioDTO;
import com.example.bibliotecaWeb.service.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class ControladorUsuario {

    @Autowired
    ServicioUsuario servicioUsuario;


    @PostMapping("/crearusuario")
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDTO) {
        return new ResponseEntity(servicioUsuario.crear(usuarioDTO), HttpStatus.CREATED);
    }

    @PutMapping("/modificarusuario")
    public ResponseEntity<UsuarioDTO> update(@RequestBody UsuarioDTO usuarioDTO){
        if (usuarioDTO.getIdUsuario() != null){
            return new ResponseEntity(servicioUsuario.modificar(usuarioDTO),HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/eliminarusuario/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            servicioUsuario.borrar(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
