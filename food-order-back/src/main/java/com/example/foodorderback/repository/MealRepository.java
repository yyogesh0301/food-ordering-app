package com.example.foodorderback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.foodorderback.model.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

}
