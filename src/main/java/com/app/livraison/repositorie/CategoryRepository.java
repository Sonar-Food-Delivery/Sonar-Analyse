package com.app.livraison.repositorie;
import com.app.livraison.entities.Category;
import com.app.livraison.entities.Meal;
import com.app.livraison.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query("SELECT DISTINCT r FROM Restaurant r JOIN r.meals m JOIN m.category c WHERE c.id = :categoryId")
    List<Restaurant> findRestaurantsByCategoryId(Long categoryId);
    @Query("SELECT DISTINCT c FROM Category c JOIN c.meals m WHERE m.id IS NOT NULL")
    List<Category> findCategoriesWithMeals();
}
