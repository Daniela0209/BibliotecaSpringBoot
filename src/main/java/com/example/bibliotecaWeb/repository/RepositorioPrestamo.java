package com.example.bibliotecaWeb.repository;


import com.example.bibliotecaWeb.modal.Prestamo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPrestamo extends MongoRepository<Prestamo, String> {
    Iterable<Prestamo> findByIdRecursos(String idRecursos);
}
