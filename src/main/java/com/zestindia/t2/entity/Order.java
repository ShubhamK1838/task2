package com.zestindia.t2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;


@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    private String id;
    @NotNull
    private short quantity;
    @Temporal(TemporalType.DATE)
    @Column(name = "order_date")
    private Date orderDate;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<OrderedProduct> products;
    @ManyToOne
    @JsonBackReference
    private CustomUser user;
    private float totalPrice;


}
