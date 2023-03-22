package com.example.javarestapiexample.service;

import com.example.javarestapiexample.error.ServerObjectNotFoundException;
import com.example.javarestapiexample.model.ServerObject;
import com.example.javarestapiexample.repository.IServerObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServerObjectService implements IServerObjectService {

    @Autowired
    IServerObjectRepository serverModelRepository;

    public List<ServerObject> findAllServerObjects() {
        return serverModelRepository.findAll();
    }

    public Optional<ServerObject> findServerObjectById(Long id) throws ServerObjectNotFoundException {
        Optional<ServerObject> serverObject;
        serverObject = serverModelRepository.findById(id);
        if (serverObject.isEmpty()) {
            throw new ServerObjectNotFoundException("Server Object Not Found by the given id", HttpStatus.NOT_FOUND.value());
        }
        return serverObject;
    }

    public ServerObject addServerObject(ServerObject serverObject) {
        return serverModelRepository.save(serverObject);
    }

    public String deleteServerObject(Long id) throws ServerObjectNotFoundException {
        Optional<ServerObject> serverObject;
        serverObject = serverModelRepository.findById(id);
        if (serverObject.isEmpty()) {
            throw new ServerObjectNotFoundException("Unable to delete record, Server Object Not Found by the given id", HttpStatus.NOT_FOUND.value());
        }
        serverModelRepository.deleteById(id);
        return "Record Successfully Deleted";
    }

    public List<ServerObject> findAllServerObjectsByName(String name) throws ServerObjectNotFoundException {
        List<ServerObject> serverObjectList;
        serverObjectList = serverModelRepository.findAllByName(name);
        if (serverObjectList.isEmpty()) {
            throw new ServerObjectNotFoundException("Server Object Not Found by the given name", HttpStatus.NOT_FOUND.value());
        }
        return serverObjectList;
    }


}
