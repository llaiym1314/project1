package com.liliang.ssm.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.liliang.domain.Product;
import com.liliang.ssm.service.IProductService;





@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	IProductService productService;
	
	
	@RequestMapping("/findAll")
	public ModelAndView findAll() {
		List<Product> productList = productService.findAll();
		ModelAndView mvc = new ModelAndView();
		mvc.addObject("productList",productList);
		mvc.setViewName("product-list");
		return mvc;
	}
	
	@RequestMapping("/save")
	public String save(Product product) {		
		productService.addProduct(product);
		return "redirect:/product/findAll";
	}

	@RequestMapping("/findById")
	@RolesAllowed("USER")
	public ModelAndView findById(String id) {
		Product product = productService.findById(id);
		ModelAndView mvc = new ModelAndView();
		mvc.addObject("product",product);
		mvc.setViewName("product-update");
		return mvc;
	}
	
	@RequestMapping("/update")
	public String updateProduct(Product product) {
		productService.editProduct(product);
		return "redirect:/product/findAll";
	}

		
	
}
