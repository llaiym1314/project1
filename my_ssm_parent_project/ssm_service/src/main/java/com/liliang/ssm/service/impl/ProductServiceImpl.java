package com.liliang.ssm.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.liliang.domain.Product;
import com.liliang.ssm.dao.IProductDao;
import com.liliang.ssm.service.IProductService;

@Service
@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	private IProductDao productDao;
	
	//查询所有的产品的方法
	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}
	
	//添加产品的方法
	@Override
	public void addProduct(Product product) {
		String idstr = UUID.randomUUID().toString();
		product.setId(idstr);
		productDao.addProduct(product);
	}

	//通过id查找产品
	@Override
	public Product findById(String id) {		
		return productDao.findById(id);
	}
	
	@Override
	public void editProduct(Product product) {		
		productDao.deleteById(product.getId());
		productDao.addProduct(product);
	}

}
