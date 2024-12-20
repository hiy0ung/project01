package org.example.springbootpractice.stats;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsTimeResponseDto {
    private LocalDate date;
    private Integer hour;
    private Long revenue;
}
