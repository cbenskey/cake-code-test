package com.waracle.cake.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waracle.cake.entity.Cake;

/**
 * Simple JPA Respository for saving {@link Cake} entities
 */
@Repository
public interface CakeRepository extends JpaRepository<Cake, Long> {
}
