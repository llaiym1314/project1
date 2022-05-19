package com.liliang.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.liliang.domain.Orders;
import com.liliang.ssm.dao.IOrdersDao;
import com.liliang.ssm.service.IOrdersService;

@Service
@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
public class OrdersServiceImpl implements IOrdersService{
	
	@Autowired
	private IOrdersDao ordersDao;

	//分页的查询方法
	@Override
	public List<Orders> findAll(int page, int pageSize) {//page--页码 pagesize--每页显示条数
		//在发起查询之前的第一行设置查询的页码以及显示条数
		PageHelper.startPage(page, pageSize);
		return ordersDao.findAll();
	}

	@Override
	public Orders findById(String id) {		
		return ordersDao.findById(id);
	}




}
