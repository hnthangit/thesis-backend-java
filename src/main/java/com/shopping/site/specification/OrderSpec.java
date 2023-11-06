package com.shopping.site.specification;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

import com.shopping.site.entity.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class OrderSpec {
	
	public static Specification<Order> hasAddress(String address) {
		return (root, query, builder) -> builder.like(root.get("address"), "%" + address + "%");
	}
	
	public static Specification<Order> hasPaymentMethod(String paymentMethod) {
		return (root, query, builder) -> builder.equal(root.get("paymentMethod"), paymentMethod);
	}
	
	public static Specification<Order> hasDeliveryStatus(String deliveryStatus) {
		return (root, query, builder) -> builder.equal(root.get("deliveryStatus"), deliveryStatus);
	}
	
	public static Specification<Order> hasPaymentStatus(String paymentStatus) {
		return (root, query, builder) -> builder.equal(root.get("paymentStatus"), paymentStatus);
	}

	public static Specification<Order> search(String address, String paymentMethod, String deliveryStatus, String paymentStatus) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (address != null) {
				predicates.add(builder.like(root.get("address"), "%" + address + "%"));
			}
			if (paymentMethod != null) {
				predicates.add(builder.equal(root.get("paymentMethod"), paymentMethod));
			}
			if (deliveryStatus != null) {
				predicates.add(builder.equal(root.get("deliveryStatus"), deliveryStatus));
			}
			if (paymentStatus != null) {
				predicates.add(builder.equal(root.get("paymentStatus"), paymentStatus));
			}
			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
}
