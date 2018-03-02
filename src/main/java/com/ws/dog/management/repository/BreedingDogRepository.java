package com.ws.dog.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import com.ws.dog.management.entity.BreedingDog;

@PreAuthorize("hasRole('ADMIN')")
public interface BreedingDogRepository extends JpaRepository<BreedingDog, Long>, CustomRepository<BreedingDog> {

}
