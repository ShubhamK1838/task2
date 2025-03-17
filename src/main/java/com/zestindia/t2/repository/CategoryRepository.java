package com.zestindia.t2.repository;


import com.zestindia.t2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByName(String name);

}
