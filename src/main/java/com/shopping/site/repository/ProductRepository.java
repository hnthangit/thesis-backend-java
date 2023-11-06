package com.shopping.site.repository;

import java.util.List;

import com.shopping.site.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
	
	 boolean existsByName(String name);
	 
	 boolean existsBySku(String sku);
	 
	 boolean existsByUrl(String url);
	 
	 Product findByUrl(String url);
	 
	 @Query(value = "SELECT * FROM product WHERE product.category_id IN ( SELECT category_id FROM category WHERE parent_id = ?1 OR category_id = ?1)", nativeQuery = true)
	 List<Product> findAllByParentCategory(int categoryId);	
	 
	 @Query(value = "SELECT * FROM product WHERE product.category_id = ?1", nativeQuery = true)
	 List<Product> findByCategoryId(int categoryId);
	 
	 @Query(value = "SELECT * FROM product WHERE product.manufacturer_id =?1", nativeQuery = true)
	 List<Product> findByManufacturerId(int manufacturerId);
	 
	 @Query(value = "SELECT * FROM product WHERE product_id IN (SELECT product_id FROM promotion_product WHERE promotion_id = ?1)", nativeQuery = true)
	 List<Product> findByPromotionId(int promotionId);
	 
	 @Query(value = "select Count(product_id) from product", nativeQuery = true)
	 int getTotalProduct();
	 
	 @Query(value = "SELECT * from product where product.product_id in (\r\n" + 
	 		"select product_id from (\r\n" + 
	 		"select product_id, sum(quantity) as total\r\n" + 
	 		"from order_detail\r\n" + 
	 		"group by product_id\r\n" + 
	 		"order by total desc limit 11\r\n" + 
	 		") as T)", nativeQuery = true)
	 List<Product> getTopProduct();
	 
	 @Query(value = "SELECT * from product where product.product_id ORDER BY product_id desc limit 8", nativeQuery = true)
	List<Product> getLastestProduct();
	 
	 @Query(value = "SELECT * FROM product\r\n" + 
	 		"where product_id in \r\n" + 
	 		"(\r\n" + 
	 		"select product_id from promotion_product where promotion_id = ?1\r\n" + 
	 		")\r\n" + 
	 		"ORDER BY RAND()\r\n" + 
	 		"LIMIT 3;", nativeQuery = true)
		List<Product> getRandomProduct(int promotionId);
	 
	 @Query(value = "select * from product where category_id in (\r\n" + 
	 		"	select category_id from product WHERE product_id = 1\r\n" + 
	 		") ORDER BY RAND()\r\n" + 
	 		"LIMIT 3;", nativeQuery = true)
	 List<Product> getRelatedProduct(int productId);
}
