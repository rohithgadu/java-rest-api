package com.example.javarestapiexample.service;

import com.example.javarestapiexample.error.ServerObjectNotFoundException;
import com.example.javarestapiexample.model.ServerObject;

import java.util.List;
import java.util.Optional;

public interface IServerObjectService {
    List<ServerObject> findAllServerObjects();

    Optional<ServerObject> findServerObjectById(Long id) throws ServerObjectNotFoundException;

    ServerObject addServerObject(ServerObject serverObject);

    String deleteServerObject(Long id) throws ServerObjectNotFoundException;

    List<ServerObject> findAllServerObjectsByName(String name) throws ServerObjectNotFoundException;
}
