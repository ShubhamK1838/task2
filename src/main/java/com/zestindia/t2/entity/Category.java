package com.zestindia.t2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    private String id;
    private String name;

}
