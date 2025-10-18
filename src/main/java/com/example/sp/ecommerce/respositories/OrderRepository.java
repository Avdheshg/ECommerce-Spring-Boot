package com.example.sp.ecommerce.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sp.ecommerce.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>
{

}
