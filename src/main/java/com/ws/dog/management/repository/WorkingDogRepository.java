package com.ws.dog.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ws.dog.management.entity.WorkingDog;

public interface WorkingDogRepository extends JpaRepository<WorkingDog, Long>, CustomRepository<WorkingDog> {

}
