package com.example.BookShoop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {

    private String name;
    private String surname;
    private String password;
    private String email;
    private String role;
    private Integer id;


}
