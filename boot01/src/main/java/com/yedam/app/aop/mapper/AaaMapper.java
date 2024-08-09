package com.yedam.app.aop.mapper;

import org.apache.ibatis.annotations.Insert;

public interface AaaMapper {
	@Insert("INSERT INTO AAA VALUES(#{value})")
	public int aaaInsert(String value);
}
