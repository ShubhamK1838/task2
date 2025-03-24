package com.zestindia.t2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends  MasterEntity {

    @NotNull(message = "Category Name Should Not Null")
    @Column(unique = true)
    private String name;
}
