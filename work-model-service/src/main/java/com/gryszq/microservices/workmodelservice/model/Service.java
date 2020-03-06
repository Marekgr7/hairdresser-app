package com.gryszq.microservices.workmodelservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class Service {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Service name is required")
    private String name;

    @NotNull(message = "Duration time is required")
    private Integer duration;

    @NotNull(message = "Service price is required")
    private Integer price;

    private String description;
}
