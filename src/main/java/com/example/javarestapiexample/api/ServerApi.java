package com.example.javarestapiexample.api;


import com.example.javarestapiexample.model.ServerObject;
import com.example.javarestapiexample.service.ServerObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ServerApi {

    @Autowired
    ServerObjectService serverObjectService;

    @GetMapping("/getServer")
    ResponseEntity<?> getAllServerObjects() {
        List<ServerObject> serverObjectResponseList;
        serverObjectResponseList = serverObjectService.findAllServerObjects();
        return new ResponseEntity<>(serverObjectResponseList, HttpStatus.OK);
    }

    @GetMapping("/getServer/byId/{id}")
    ResponseEntity<?> getServerObjectById(@PathVariable Long id) {
        ServerObject serverObjectResponse;
        serverObjectResponse = serverObjectService.findServerObjectById(id);
        return new ResponseEntity<>(serverObjectResponse, HttpStatus.OK);
    }

    @PutMapping("/addServer")
    ResponseEntity<?> addServerObject(@RequestBody ServerObject serverObject) {
        ServerObject serverObjectResponse;
        serverObjectResponse = serverObjectService.addServerObject(serverObject);
        return new ResponseEntity<>(serverObjectResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteServer/{id}")
    ResponseEntity<?> deleteServerObject(@PathVariable Long id) {
        String response = serverObjectService.deleteServerObject(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getServer/byName/{name}")
    ResponseEntity<?> getServerObjectByName(@PathVariable String name) {
        List<ServerObject> serverObjectResponseList;
        serverObjectResponseList = serverObjectService.findAllServerObjectsByName(name);
        return new ResponseEntity<>(serverObjectResponseList, HttpStatus.OK);
    }


}
