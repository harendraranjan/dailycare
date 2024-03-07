package com.Dynamic.service;

import com.Dynamic.Entity.OrderBooking;

public interface OrderBookingService {
	public OrderBooking saveOrderBooking(OrderBooking orderBooking );
	public boolean existEmailCheck(String email);
	
}	
