package com.yggdrasil.paper.repository;


import com.yggdrasil.paper.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface UserRepository extends JpaRepository<User, String> {
}
