package com.graph.repository;

import com.graph.model.entity.Graph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraphRepository extends JpaRepository<Graph, Long> {
}
