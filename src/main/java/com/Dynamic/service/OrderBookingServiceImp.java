package com.Dynamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dynamic.Dao.OrderBookingRepository;
import com.Dynamic.Entity.OrderBooking;

@Service
public class OrderBookingServiceImp implements OrderBookingService{
	
	@Autowired
	private OrderBookingRepository orderBookingRepository;

	@Override
	public OrderBooking saveOrderBooking(OrderBooking orderBooking) {
		
		return orderBookingRepository.save(orderBooking);
	}

	@Override
	public boolean existEmailCheck(String email) {
		
		return orderBookingRepository.existsByEmail(email);
	}

}
