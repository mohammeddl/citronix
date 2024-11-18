package com.citronix.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citronix.demo.model.Tree;

public interface TreeRepository extends JpaRepository<Tree, Long> {
}