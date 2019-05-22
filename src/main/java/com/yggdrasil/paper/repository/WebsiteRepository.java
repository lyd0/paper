package com.yggdrasil.paper.repository;

import com.yggdrasil.paper.entity.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface WebsiteRepository extends JpaRepository<Website, String> {
}
