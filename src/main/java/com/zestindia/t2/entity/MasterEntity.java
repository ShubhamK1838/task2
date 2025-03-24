package com.zestindia.t2.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.util.UUID;


@MappedSuperclass
@Data
public abstract  class MasterEntity {

    @Id
    private String id;


    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString();
    }
}
