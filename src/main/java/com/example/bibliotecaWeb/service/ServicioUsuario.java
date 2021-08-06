package com.example.bibliotecaWeb.service;


import com.example.bibliotecaWeb.DTO.UsuarioDTO;
import com.example.bibliotecaWeb.mapper.UserMapper;
import com.example.bibliotecaWeb.modal.Usuario;
import com.example.bibliotecaWeb.repository.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuario {

    @Autowired
    RepositorioUsuario repositorioUsuario;

    UserMapper mapper = new UserMapper();

    public UsuarioDTO crear(UsuarioDTO usuarioDTO) {
        Usuario usuarios = mapper.fromDTO(usuarioDTO);
        return mapper.fromCollection(repositorioUsuario.save(usuarios));
    }

    public UsuarioDTO modificar(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.fromDTO(usuarioDTO);
        repositorioUsuario.findById(usuario.getIdUsuario()).orElseThrow(() -> new RuntimeException("usuario no encontrado"));
        return mapper.fromCollection(repositorioUsuario.save(usuario));
    }
    public void borrar(String id) {
        repositorioUsuario.deleteById(id);
    }
}


