package com.mywebapps.catalogservice.repository;

import com.mywebapps.catalogservice.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
    Author findByName(String name);
}
