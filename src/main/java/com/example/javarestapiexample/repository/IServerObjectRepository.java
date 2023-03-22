package com.example.javarestapiexample.repository;

import com.example.javarestapiexample.model.ServerObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IServerObjectRepository extends MongoRepository<ServerObject, Long> {
    Optional<ServerObject> findById(Long id);

    List<ServerObject> findAllByName(String name);
}
