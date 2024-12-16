package org.example.springbootpractice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="store_id", nullable = false)
    @ManyToOne
    private Store store;

    @Column(nullable = false, length = 255)
    private String deliveryAddress;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private String orderState;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetail;
}