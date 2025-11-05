package com.example.demo.repository.categoryRepository;

import com.example.demo.model.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {

}
