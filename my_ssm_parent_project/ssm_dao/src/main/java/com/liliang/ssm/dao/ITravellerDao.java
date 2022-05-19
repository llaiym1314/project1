package com.liliang.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.liliang.domain.Traveller;

@Repository
public interface ITravellerDao {

	@Select("select * from traveller where id in(select travellerId from order_traveller where orderId = #{orderId})")
	public List<Traveller> findByOrderId(String orderId);
	
}
