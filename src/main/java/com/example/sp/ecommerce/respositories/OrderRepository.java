package com.example.sp.ecommerce.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sp.ecommerce.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>
{

}
