package com.example.javarestapiexample.service;

import com.example.javarestapiexample.model.ServerObject;
import com.example.javarestapiexample.repository.IServerObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerObjectService implements IServerObjectService {

    @Autowired
    IServerObjectRepository serverModelRepository;

    public List<ServerObject> findAllServerObjects() {
        return serverModelRepository.findAll();
    }

    public ServerObject findServerObjectById(Long id) {
        ServerObject serverObject;
        serverObject = serverModelRepository.findAllById(id);
        if (serverObject == null) {

        }
        return serverObject;
    }

    public ServerObject addServerObject(ServerObject serverObject) {
        return serverModelRepository.save(serverObject);
    }

    public String deleteServerObject(Long id) {
        serverModelRepository.deleteById(id);
        return "Record Successfully Deleted";
    }

    public List<ServerObject> findAllServerObjectsByName(String name) {
        List<ServerObject> serverObjectList;
        serverObjectList = serverModelRepository.findAllByName(name);
        if (serverObjectList == null) {

        }
        return serverObjectList;
    }


}
