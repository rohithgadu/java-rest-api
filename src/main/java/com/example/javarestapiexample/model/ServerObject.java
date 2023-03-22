package com.example.javarestapiexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "servers")
public class ServerObject {
    @Id
    private Long id;

    private String name;

    private String language;

    private String framework;

}
