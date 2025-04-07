package com.javatechie.restapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product", schema = "local")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    private String name;
    private Long price;
    private Long quantity;

}
