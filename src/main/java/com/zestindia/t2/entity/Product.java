package com.zestindia.t2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String id;
    @NotNull
    private String name, description;
    @NotNull
    private Float price;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> category;
    private Integer quantity;
    private String brand;


}

