package com.ws.dog.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ws.dog.management.entity.TrainingDog;

public interface TrainingDogRepository extends JpaRepository<TrainingDog, Long>, CustomRepository<TrainingDog> {

}
