package org.example.springbootpractice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu_options")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MenuOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String optionName;

    @Builder.Default
    @OneToMany(mappedBy = "menuOption", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuOptionDetail> menuOptionDetails = new ArrayList<>();

    @OneToMany(mappedBy = "menuOption", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuOptionGroup> menuOptionGroups = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "menu_id")
//    private Menu menu;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_detail_id", nullable = false)
//    private OrderDetail orderDetail;

}
