package com.zestindia.t2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
public class OrderedProduct {



        @Id
        private String id;
        private String productId;
        @NotNull
        private String name, description;
        @NotNull
        private Float price;
        @ManyToMany(fetch = FetchType.EAGER)
        private List<Category> category;
        private Integer quantity;
        private String brand;

}
