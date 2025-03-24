package com.zestindia.t2.service.impl;


import com.zestindia.t2.entity.MasterEntity;
import com.zestindia.t2.repository.MasterEntityRepository;
import com.zestindia.t2.service.MasterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class MasterEntityServiceImpl<T extends MasterEntity> implements MasterEntityService<T> {

    @Autowired
    private MasterEntityRepository<T> repository;

    public T save(T entity) {
        return repository.save(entity);
    }

    public Optional<T> get(String id) {
        return repository.findById(id);
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public void removeById(String id) {
        T t = get(id).orElseThrow(() -> new RuntimeException("Resource Not Found With Id: " + id));

        repository.delete(t);

    }

    public void update(String id, T entity) {
        var dbEntity = get(id).orElseThrow(() -> new RuntimeException("Resource Not Found With Id: " + id));
        entity.setId(dbEntity.getId());
        repository.save(entity);
    }




}
