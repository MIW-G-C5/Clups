package com.MIW.Cohort5.Clups.repository;

import com.MIW.Cohort5.Clups.model.Category;
import com.MIW.Cohort5.Clups.model.ClupsUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryByCategoryCode(int categoryCode);
}
