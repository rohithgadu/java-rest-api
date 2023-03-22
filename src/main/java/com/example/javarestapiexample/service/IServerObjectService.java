package com.example.javarestapiexample.service;

import com.example.javarestapiexample.model.ServerObject;

import java.util.List;

public interface IServerObjectService {
    List<ServerObject> findAllServerObjects();

    ServerObject findServerObjectById(Long id);

    ServerObject addServerObject(ServerObject serverObject);

    String deleteServerObject(Long id);

    List<ServerObject> findAllServerObjectsByName(String name);
}
