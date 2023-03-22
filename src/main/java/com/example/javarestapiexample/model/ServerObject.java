package com.example.javarestapiexample.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Id cannot be empty")
    private Long id;
    @NotNull(message = "Name cannot be empty")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Language cannot be empty")
    @NotBlank(message = "Language cannot be empty;")
    private String language;

    @NotNull(message = "Framework cannot be empty")
    @NotBlank(message = "Framework cannot be empty")
    private String framework;

}
