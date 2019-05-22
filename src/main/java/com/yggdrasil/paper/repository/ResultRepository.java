package com.yggdrasil.paper.repository;


import com.yggdrasil.paper.entity.Record;
import com.yggdrasil.paper.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface ResultRepository extends JpaRepository<Result, Integer> {
}
