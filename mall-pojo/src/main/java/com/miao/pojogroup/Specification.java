package com.miao.pojogroup;

import java.io.Serializable;
import java.util.List;

import com.miao.pojo.TbSpecification;
import com.miao.pojo.TbSpecificationOption;
/**
 * 规格组合实体类
 * @author USER
 *
 */

public class Specification implements Serializable{
	
	private TbSpecification specification;
	
	private List<TbSpecificationOption> specificationOptionList;

	public TbSpecification getSpecification() {
		return specification;
	}

	public void setSpecification(TbSpecification specification) {
		this.specification = specification;
	}

	public List<TbSpecificationOption> getSpecificationOptionList() {
		return specificationOptionList;
	}

	public void setSpecificationOptionList(List<TbSpecificationOption> specificationOptionList) {
		this.specificationOptionList = specificationOptionList;
	}
	
	
}
