package com.shopping.site.repository;
import com.shopping.site.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("promotionRepository")
public interface PromotionRepository extends JpaRepository<Promotion, Integer>, JpaSpecificationExecutor<Promotion> {
	
	@Query(value = "SELECT * FROM `promotion` WHERE start_date<=CURDATE() and end_date>CURDATE()", nativeQuery = true)
	Promotion getCurrentPromotion();
	
	@Query(value = "select * from promotion ORDER BY promotion_id DESC LIMIT 1;", nativeQuery = true)
	Promotion getLastestPromotion();
}
