package com.example.javarestapiexample.api;


import com.example.javarestapiexample.error.ApiError;
import com.example.javarestapiexample.error.ServerObjectNotFoundException;
import com.example.javarestapiexample.error.ValidationException;
import com.example.javarestapiexample.model.ApiResponse;
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
        ApiResponse<List<ServerObject>> response = new ApiResponse<>(true,200,serverObjectResponseList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getServer/byId/{id}")
    ResponseEntity<?> getServerObjectById(@PathVariable Long id) throws ServerObjectNotFoundException {
        Optional<ServerObject> serverObjectResponse;
        serverObjectResponse = serverObjectService.findServerObjectById(id);
        ApiResponse<Optional<ServerObject>> response = new ApiResponse<>(true,200,serverObjectResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/addServer")
    ResponseEntity<?> addServerObject(@RequestBody @Valid ServerObject serverObject, BindingResult result) throws ValidationException {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldErrors().get(0);
            throw new ValidationException(fieldError.getDefaultMessage(), HttpStatus.BAD_REQUEST.value());
        }
        ServerObject serverObjectResponse;
        serverObjectResponse = serverObjectService.addServerObject(serverObject);
        ApiResponse<ServerObject> response = new ApiResponse<>(true,201,serverObjectResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteServer/{id}")
    ResponseEntity<?> deleteServerObject(@PathVariable Long id) throws ServerObjectNotFoundException {
        String res = serverObjectService.deleteServerObject(id);
        ApiResponse<String> response = new ApiResponse<>(true,200,res);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getServer/byName/{name}")
    ResponseEntity<?> getServerObjectByName(@PathVariable String name) throws ServerObjectNotFoundException {
        List<ServerObject> serverObjectResponseList;
        serverObjectResponseList = serverObjectService.findAllServerObjectsByName(name);
        ApiResponse<List<ServerObject>> response = new ApiResponse<>(true,200,serverObjectResponseList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleInvalidFields(ValidationException ex) {
        ApiError error = new ApiError(ex.getMessage());
        ApiResponse<ApiError> response = new ApiResponse<>(false,400,error);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleMessageNotReadableException(HttpMessageNotReadableException ex) {
        ApiError error = new ApiError(ex.getMessage());
        ApiResponse<ApiError> response = new ApiResponse<>(false,400,error);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServerObjectNotFoundException.class)
    public ResponseEntity<?> handleServerObjectNotFoundException(ServerObjectNotFoundException ex) {
        ApiError error = new ApiError(ex.getMessage());
        ApiResponse<ApiError> response = new ApiResponse<>(false,ex.getCode(),error);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(ex.getCode()));
    }


}
