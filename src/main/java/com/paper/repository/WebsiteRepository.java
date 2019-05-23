package com.paper.repository;

import com.paper.entity.Website;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebsiteRepository extends JpaRepository<Website, String> {
}
