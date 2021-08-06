package com.example.bibliotecaWeb.repository;


import com.example.bibliotecaWeb.modal.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuario extends MongoRepository<Usuario, String> {
}
