package com.liliang.ssm.service;

import java.util.List;

import com.liliang.domain.Orders;

public interface IOrdersService {
	
	public List<Orders> findAll(int page,int pageSize);

	public Orders findById(String id);
}
