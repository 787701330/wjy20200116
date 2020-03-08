package com.wjy.space.mapper;

import com.wjy.space.pojo.Storage;
import com.wjy.space.pojo.StorageExample;
import java.util.List;

public interface StorageMapper {
    long countByExample(StorageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Storage record);

    int insertSelective(Storage record);

    List<Storage> selectByExampleWithBLOBs(StorageExample example);

    List<Storage> selectByExample(StorageExample example);

    Storage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKeyWithBLOBs(Storage record);

    int updateByPrimaryKey(Storage record);
    
    List<Storage> selectMusicList(Storage storage);
    
    List<Storage> selectPictureList(Storage storage);

	List<Storage> selectDocumentList(Storage storage);

	List<Storage> selectVideoList(Storage storage);

	List<Storage> selectTorrentList(Storage storage);

	List<Storage> selectOtherList(Storage storage);
}