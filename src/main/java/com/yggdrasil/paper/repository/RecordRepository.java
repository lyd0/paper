package com.yggdrasil.paper.repository;


import com.yggdrasil.paper.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface RecordRepository extends JpaRepository<Record, Integer> {
}
