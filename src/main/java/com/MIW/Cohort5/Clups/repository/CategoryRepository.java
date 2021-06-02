package com.MIW.Cohort5.Clups.repository;

import com.MIW.Cohort5.Clups.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
