package com.zestindia.t2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private String name, description;
    private float price;
    @OneToMany
    private List<Category> category;

}
