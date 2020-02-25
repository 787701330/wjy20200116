package com.wjy.space.service;

import java.io.IOException;
import java.util.List;

import org.csource.common.MyException;

import com.github.pagehelper.PageInfo;
import com.wjy.space.pojo.Storage;
import com.wjy.space.pojo.StorageExample;

public interface StorageService {


	int delectByPrimaryKey(Long id);

	int insert(Storage storage);
	
	Storage selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Storage storage);

	int rename(Long id, String name) throws Exception;

	int seeName(String name, Long userId,Long parent);

	int addFolder(Long userId, String name,Long parent);
	
	void deleteOverdue();

	PageInfo<Storage> selectList(Long userId, String keyword, Long parent, int state,Integer pageNum, Integer pageSize);

	List<Storage> selectByExample(StorageExample example);

	int move2(String name, Long parent, Long spaceId,Long userId);

	int seeName2(String name, Long userId, Long parent, Long spaceId);

	int delete(Long[] id) throws IOException, MyException;
	
	int SetStateAll(Long id,int state);

	int setState(Long[] id,int state);

}
