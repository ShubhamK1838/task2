package com.zestindia.t2.service;

import com.zestindia.t2.entity.MasterEntity;

import java.util.Optional;

public interface MasterEntityService<T extends MasterEntity> {


    T save(T t);

    void update(String id, T t);

    void removeById(String id);

    Optional<T> get(String id);

    java.util.List<T> getAll();

}
