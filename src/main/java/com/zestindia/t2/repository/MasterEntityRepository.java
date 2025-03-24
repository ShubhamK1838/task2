package com.zestindia.t2.repository;

import com.zestindia.t2.entity.MasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface MasterEntityRepository<T extends MasterEntity> extends JpaRepository<T, String> {
}
