package com.liliang.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.liliang.domain.Product;

@Repository
public interface IProductDao {
	
	@Select("select * from product")
	public List<Product> findAll();
	
	@Insert("insert into product(id,productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
	public void addProduct(Product product);
	
	@Select("select * from product where id = #{productId}")
	public Product findById(String productId);

	
	@Delete("delete from product where id = #{id}")
	public void deleteById(String id);
	
	//delete 
//	t1,t2 
//	from 
//	dishes as t1 
//	LEFT JOIN publish as t2 
//	ON t1.id = t2.dishes_id 
//	where 
//	t1.id = 13
	
	//级联删除
	//delete product,orders from product left join orders on product.id = orders.productId  where product.id = '111111';
	@Delete("delete product,orders from product left join orders on procduct.id = orders.id where product.id = #{id} ")
	public void deleteById2(String id);
}
