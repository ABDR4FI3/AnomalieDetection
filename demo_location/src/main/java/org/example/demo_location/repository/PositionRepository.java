package org.example.demo_location.repository;

import org.example.demo_location.Entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, String> {
}