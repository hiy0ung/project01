package org.example.springbootpractice.repository;

import org.example.springbootpractice.dto.response.OrderDetailResponseDto;
import org.example.springbootpractice.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    // OrderDetail

//    @Query(
//            value = """
//
//SELECT
//    od.order_id AS orderId,
//    m.menu_name AS orderMenuName,
//    (m.menu_price * od.quantity +
//        IFNULL(SUM(md.additional_fee), 0)) AS menuTotalPrice,
//    od.quantity AS quantity
//FROM
//    order_details od
//JOIN
//    menus m ON od.menu_id = m.id
//LEFT JOIN
//    menu_options mo ON mo.menu_id = m.id
//LEFT JOIN
//    menu_option_details md ON md.option_id = mo.id
//WHERE od.order_id = orders.id
//GROUP BY
//    od.id, m.menu_name, m.menu_price, od.quantity, od.order_id
//"""
//    , nativeQuery = true)
@Query(
        value = """
        SELECT 
            od.order_id AS orderId,
            m.menu_name AS orderMenuName,
            (m.menu_price * od.quantity) + 
            IFNULL(SUM(md.additional_fee), 0) AS menuTotalPrice,  -- 메뉴 가격과 수량을 곱하고 옵션의 추가 금액을 더함
            od.quantity AS quantity,
            mo.id AS menuOptionId,
            mo.option_name AS menuOptionName,
            md.id AS menuOptionDetailId,
            md.option_detail_name AS menuOptionDetailName,
            md.additional_fee AS menuOptionDetailAdditionalFee
        FROM 
            order_details od
        JOIN 
            menus m ON od.menu_id = m.id
        LEFT JOIN 
            menu_options mo ON mo.menu_id = m.id
        LEFT JOIN 
            menu_option_details md ON md.option_id = mo.id
        WHERE 
            od.order_id = :orderId
        GROUP BY 
            od.order_id, m.menu_name, m.menu_price, od.quantity, mo.id, mo.option_name, 
            md.id, md.option_detail_name, md.additional_fee
        ORDER BY 
            od.order_id, mo.id, md.id
    """,
        nativeQuery = true)
    List<Object[]> findOrderDetailsWithOptions(@Param("orderId") Long orderId);

    List<OrderDetail> findMenuOptionByOrderId(Long orderId);
}
