package org.example.springbootpractice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_option_details")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MenuOptionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String optionDetailName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_option_id", nullable = false)
    private MenuOption menuOption;

    @Column
    private int additionalFee;
}
