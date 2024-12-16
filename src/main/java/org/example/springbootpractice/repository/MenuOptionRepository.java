package org.example.springbootpractice.repository;

import org.example.springbootpractice.entity.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
//    List<MenuOption> findByOrderDetailId(Long orderId);
}
