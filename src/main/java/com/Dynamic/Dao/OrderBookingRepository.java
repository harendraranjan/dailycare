package com.Dynamic.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Dynamic.Entity.OrderBooking;

public interface OrderBookingRepository extends JpaRepository<OrderBooking, Integer>{
	public boolean existsByEmail(String email);
}
