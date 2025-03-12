package com.zestindia.t2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    private String id;
    private String userId;
    private String productId;
    private short quantity;
    @Temporal(TemporalType.DATE)
    @Column(name = "order_date")
    private Date orderDate;

}
