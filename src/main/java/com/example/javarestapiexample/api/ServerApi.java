package com.example.javarestapiexample.api;


import com.example.javarestapiexample.error.ApiError;
import com.example.javarestapiexample.error.ServerObjectNotFoundException;
import com.example.javarestapiexample.error.ValidationException;
import com.example.javarestapiexample.model.ServerObject;
import com.example.javarestapiexample.service.ServerObjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
    ResponseEntity<?> getServerObjectById(@PathVariable Long id) throws ServerObjectNotFoundException {
        Optional<ServerObject> serverObjectResponse;
        serverObjectResponse = serverObjectService.findServerObjectById(id);
        return new ResponseEntity<>(serverObjectResponse, HttpStatus.OK);
    }

    @PutMapping("/addServer")
    ResponseEntity<?> addServerObject(@RequestBody @Valid ServerObject serverObject, BindingResult result) throws ValidationException {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldErrors().get(0);
            throw new ValidationException(fieldError.getDefaultMessage(), HttpStatus.BAD_REQUEST.value());
        }
        ServerObject serverObjectResponse;
        serverObjectResponse = serverObjectService.addServerObject(serverObject);
        return new ResponseEntity<>(serverObjectResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteServer/{id}")
    ResponseEntity<?> deleteServerObject(@PathVariable Long id) throws ServerObjectNotFoundException {
        String response = serverObjectService.deleteServerObject(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getServer/byName/{name}")
    ResponseEntity<?> getServerObjectByName(@PathVariable String name) throws ServerObjectNotFoundException {
        List<ServerObject> serverObjectResponseList;
        serverObjectResponseList = serverObjectService.findAllServerObjectsByName(name);
        return new ResponseEntity<>(serverObjectResponseList, HttpStatus.OK);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleInvalidFields(ValidationException ex) {
        ApiError apiError = new ApiError(ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleMessageNotReadableException(HttpMessageNotReadableException ex) {
        ApiError apiError = new ApiError(400, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServerObjectNotFoundException.class)
    public ResponseEntity<?> handleServerObjectNotFoundException(ServerObjectNotFoundException ex) {
        ApiError apiError = new ApiError(ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatusCode.valueOf(ex.getCode()));
    }


}
