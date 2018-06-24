package com.miao.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.miao.pojo.TbBrand;

import entity.PageResult;

/*
 *品牌接口
 */
public interface BrandService {
	public List<TbBrand> findAll();
	
	/**
	 * 品牌分页
	 * @param pageNum当前页
	 * @param pageSize记录数
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	//增加
	public void add(TbBrand brand);
	
	//根据id查询分类
	public TbBrand findOne(Long id);
	
	//修改
	public void update(TbBrand brand);
	
	//删除
	public void delete(Long[] ids);
	
	//查询
	public PageResult findPage(TbBrand brand, int pageNum, int pageSize);
	
	//返回下拉列表
	public List<Map> selectOptionList();
	
}
