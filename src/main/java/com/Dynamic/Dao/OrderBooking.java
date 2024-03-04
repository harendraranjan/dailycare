package com.Dynamic.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderBooking extends JpaRepository<OrderBooking, Long>{
	public boolean existsByEmail(String email);
}
