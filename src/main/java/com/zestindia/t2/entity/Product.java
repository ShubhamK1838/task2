package com.zestindia.t2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
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
public class Product extends MasterEntity {

    @NotNull
    private String name, description;
    @NotNull
    private Float price;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> category;
    private Integer quantity;
    private String brand;


}

