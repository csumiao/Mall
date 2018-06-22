package com.miao.sellergoods.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.miao.mapper.TbSpecificationMapper;
import com.miao.mapper.TbSpecificationOptionMapper;
import com.miao.pojo.TbSpecification;
import com.miao.pojo.TbSpecificationExample;
import com.miao.pojo.TbSpecificationExample.Criteria;
import com.miao.pojo.TbSpecificationOption;
import com.miao.pojo.TbSpecificationOptionExample;
import com.miao.pojogroup.Specification;
import com.miao.sellergoods.service.SpecificationService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;
	
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
		//获取规格实体
		TbSpecification tbSpecification = specification.getSpecification();
		specificationMapper.insert(tbSpecification);		
		//获取规格选项集合
		List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
		for(TbSpecificationOption option : specificationOptionList){
			option.setSpecId(tbSpecification.getId() );//设置规格id
			specificationOptionMapper.insert(option);//新增规格
		}
		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification){

		//获取规格实体，保存修改的规格
		specificationMapper.updateByPrimaryKey(specification.getSpecification());
		//删除原有的规格选项		
		TbSpecificationOptionExample example=new TbSpecificationOptionExample();
		com.miao.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
		criteria.andSpecIdEqualTo(specification.getSpecification().getId());//指定规格ID为条件
		specificationOptionMapper.deleteByExample(example);//删除		
		//循环插入规格选项
		for(TbSpecificationOption specificationOption:specification.getSpecificationOptionList()){			
			specificationOption.setSpecId(specification.getSpecification().getId());
			specificationOptionMapper.insert(specificationOption);		
		}	
		
		
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){		
		//查询规格
		TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
		//查询规格选项列表
		TbSpecificationOptionExample example=new TbSpecificationOptionExample();
		com.miao.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
		criteria.andSpecIdEqualTo(id);//根据规格ID查询	
		
		List<TbSpecificationOption> optionList = specificationOptionMapper.selectByExample(example);
		//构建组合实体类返回结果
		Specification spec=new Specification();
		spec.setSpecification(tbSpecification); 
		spec.setSpecificationOptionList(optionList);		
		return spec;		
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			//删除规格表
			specificationMapper.deleteByPrimaryKey(id);
			
			//删除规格选项表
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			com.miao.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
			criteria.andSpecIdEqualTo(id);
			specificationOptionMapper.deleteByExample(example);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

}