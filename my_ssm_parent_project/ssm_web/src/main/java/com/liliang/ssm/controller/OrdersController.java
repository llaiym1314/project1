package com.liliang.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.liliang.domain.Orders;
import com.liliang.ssm.service.IOrdersService;

@Controller
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
	private IOrdersService OrdersService;
	
	//分页查询的方法
	@RequestMapping("/findAll")
	public ModelAndView findAll(@RequestParam(value = "page",defaultValue = "1",required = true)Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "5",required = true)Integer pageSize) {
		//列表数据
		List<Orders> ordersList = OrdersService.findAll(pageNum, pageSize);
		ModelAndView mav = new ModelAndView();
		//创建pageinfo对象，将前台需要的数据组装起来
		PageInfo pageInfo = new PageInfo(ordersList); 
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("orders-list");
		return mav;
	} 
	@RequestMapping("/findById")
	public ModelAndView findById(String id) {
		Orders orders = OrdersService.findById(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("orders",orders);
		mav.setViewName("orders-show");
		return mav;
	}

}
