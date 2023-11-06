package com.shopping.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IProductImageDAO;
import com.shopping.entity.ProductImage;
import com.shopping.repository.ProductImageRepository;

@Repository("productImageDAO")
@Transactional
public class ProductImageImpl implements IProductImageDAO{

	@Autowired
	private ProductImageRepository productImageRepository;
	
	
	@Override
	public void insertOrUpdate(ProductImage entity) {
		productImageRepository.save(entity);
	}

	@Override
	public void delete(ProductImage entity) {
		productImageRepository.delete(entity);
	}

	@Override
	public Page<ProductImage> page(int pageNumber, int pageSize, Map<String, Object> map) {
		return null;
	}

	@Override
	public Optional<ProductImage> findById(int productImageId) {
		return productImageRepository.findById(productImageId);
	}

	@Override
	public List<ProductImage> findListAfterDelete(int displayOrder, int productId) {
		return productImageRepository.findListAfterDelete(displayOrder, productId);
	}

	@Override
	public List<ProductImage> findByProductId(int productId) {
		return productImageRepository.findByProductId(productId);
	}

}
