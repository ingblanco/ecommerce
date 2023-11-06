package com.capitole.ecommerce.infrastructure.adapter.repository;
import com.capitole.ecommerce.infrastructure.adapter.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

	@Query(value ="SELECT p " +
            "FROM Price p " +
            "JOIN p.brand b " +
            "WHERE b.id = ?1 " +
            "AND p.productId = ?2 " +
            "AND (?3 BETWEEN p.startDate AND p.endDate) " +
            "ORDER BY p.priority ASC")
    List<Price> findByBrandProductAndApplicationDate(Long brandId, Long productId, LocalDateTime applicationDate);

}



