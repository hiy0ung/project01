package org.example.springbootpractice.repository;

import org.example.springbootpractice.dto.response.OrderResponseDto;
import org.example.springbootpractice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


/*
    가게의 시간대 별 매출
    요청 받을 값: 주문 날짜(년-월-일 시:분:초)

    응답할 값: 주문 총액의 합 - revenue (시간 별)

    DATE 타입 사용 경우 yyyy-mm-dd 형식 무조건 지켜져야함

    SELECT
    DATE(o.order_date) AS date,
    HOUR(o.order_date) AS hour,
    SUM(o.total_price) AS revenue
    FROM orders AS o
    WHERE YEAR(o.order_date) = 2024 AND MONTH(o.order_date) = 11 AND DAY(o.order_date) = 1
    GROUP BY DATE(o.order_date), HOUR(o.order_date)
    ORDER BY date, hour;
 */


//    @Query ("SELECT DATE(o.orderDate) AS date, " +
//            "HOUR (o.orderDate) AS hour, " +
//            "SUM(o.totalPrice) AS revenue " +
//            "FROM Order AS o " +
//            "WHERE YEAR(o.orderDate) = :orderDateYear " +
//            "AND MONTH(o.orderDate) = :orderDateMonth " +
//            "AND DAY(o.orderDate) = :orderDateDay " +
//            "AND o.orderState = '2' " +
//            "GROUP BY DATE(o.orderDate), HOUR (o.orderDate) " +
//            "ORDER BY DATE(o.orderDate), HOUR (o.orderDate)"
//    )
//    List<StatsTimeResponseDto> findRevenueByOrderDate(
//            @Param("orderDateYear") int year,
//            @Param("orderDateMonth") int month,
//            @Param("orderDateDay") int Day
//    );
//    List<Object[]> findRevenueByOrderDate(
//            // param 뒤에 "" 안orderDateMonth 값은 위의 쿼리문에서 쓰이는 이름!!
//            @Param("orderDateYear") int year,
//            @Param("orderDateMonth") int month,
//            @Param("orderDateDay") int day
//    );



    // Order
//    @Query (
//            value = "SELECT " +
//                    "o.id AS orderId, " +
//                    "SUM(((m.menu_price * od.quantity) + IFNULL(SUM(md.additional_fee), 0)) AS sumTotalPrice)) " +
//                    "FROM " +
//                    "orders AS o " +
//                    "JOIN " +
//                    "order_details AS od ON o.id = od.order_id " +
//                    "JOIN " +
//                    "menus AS m ON od.menu_id = m.id " +
//                    "LEFT JOIN   " +
//                    "menu_option_details md ON md.option_id IN (" +
//                    "        SELECT id FROM menu_options WHERE menu_id = m.id) " +
//                    "WHERE o.id = :orderId " +
//                    "GROUP BY o.id",
//            nativeQuery = true
//    )
//    Integer findBySumTotalPrice(@Param("orderId") Long orderId);




    List<Order> findByOrderState(String orderState);
}
