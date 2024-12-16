package org.example.springbootpractice.repository;

import org.example.springbootpractice.entity.MenuOptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuOptionDetailRepository extends JpaRepository<MenuOptionDetail, Long> {
}
