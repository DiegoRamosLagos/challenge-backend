package com.example.challenge.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persons")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Rut don't might be blank")
    @Pattern(regexp = "^\\d{1,2}\\d{3}\\d{3}[-][0-9kK]{1}$", message = "RUT format not allowed")
    private String rut;

    @NotBlank(message = "Name don't might be blank")
    private String name;

    @NotBlank(message = "Last name don't might be blank")
    @Column(name= "last_name")
    private String lastName;

    @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$",
            message = "Date format not allowed")
    @NotBlank(message = "Birth date don't might be blank")
    @Column(name = "birth_date")
    private String birthDate;
}
