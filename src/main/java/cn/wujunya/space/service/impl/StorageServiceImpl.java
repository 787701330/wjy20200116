package cn.wujunya.space.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.wujunya.space.mapper.StorageMapper;
import cn.wujunya.space.pojo.Storage;
import cn.wujunya.space.pojo.StorageExample;
import cn.wujunya.space.pojo.StorageExample.Criteria;
import cn.wujunya.space.service.StorageService;
import cn.wujunya.space.util.FastDfsUtil;

@Service
public class StorageServiceImpl implements StorageService {

	@Autowired
	private StorageMapper storageMapper;

	@Override
	public PageInfo<Storage> selectList(Long userId, String keyword, Long parent, int state, Integer pageNum,
			Integer pageSize) {
		StorageExample example = new StorageExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andStateEqualTo(state);
		criteria.andParentEqualTo(parent);
		if (StringUtils.isNotBlank(keyword)) {
			criteria.andNameLike("%" + keyword + "%");
		}
		PageHelper.startPage(pageNum, pageSize);
		List<Storage> list = storageMapper.selectByExample(example);
		if (parent != 1) {
			Storage storage = new Storage();
			storage.setName("...");
			Storage storage2 = this.selectByPrimaryKey(parent);
			Long parent2 = storage2.getParent();
			storage.setId(parent2);
			storage.setIsFolder(1);
			list.add(0, storage);
		}
		PageInfo<Storage> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public int delectByPrimaryKey(@RequestParam("id") Long id) {
		Storage storage = storageMapper.selectByPrimaryKey(id);
		String adress = storage.getAdress();
		int result = -1;
		try {
			result = FastDfsUtil.fdfsDeleteFile1(adress);
		} catch (IOException | MyException e) {
			e.printStackTrace();
		}
		if (result == 0) {
			return storageMapper.deleteByPrimaryKey(id);
		} else {
			return 0;
		}
	}

	@Override
	public int insert(Storage storage) {
		return storageMapper.insert(storage);
	}

	@Override
	public Storage selectByPrimaryKey(Long id) {
		return storageMapper.selectByPrimaryKey(id);
	}
	

	@Override
	public int updateByPrimaryKeySelective(Storage storage) {
		return storageMapper.updateByPrimaryKeySelective(storage);
	}

	@Override
	public int rename(Long id, String name) throws Exception {
		Storage storage = this.selectByPrimaryKey(id);
		storage.setId(id);
		name = name.trim();
		Long parent = storage.getParent();
		if (name.lastIndexOf(".") == -1 && storage.getIsFolder() != 1) {
			String name2 = storage.getName();
			name2 = name2.substring(name2.lastIndexOf("."));
			name = name + name2;
		}
		int i = this.seeName(name, id, parent);
		if (i == 1) {
			return 2;
		}
		storage.setName(name);
		storage.setUpdated(new Date());
		int result = this.updateByPrimaryKeySelective(storage);
		return result;
	}

	@Override
	public int seeName(String name, Long userId, Long parent) {
		StorageExample example = new StorageExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andParentEqualTo(parent);
		criteria.andNameEqualTo(name);
		List<Storage> list = storageMapper.selectByExample(example);
		if (list.size() > 0) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public int seeName2(String name, Long userId, Long parent, Long spaceId) {
		StorageExample example = new StorageExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andParentEqualTo(parent);
		criteria.andNameEqualTo(name);
		criteria.andIdNotEqualTo(spaceId);
		List<Storage> list = storageMapper.selectByExample(example);
		if (list.size() > 0) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public int addFolder(Long userId, String name, Long parent) {
		if (seeName(name, userId, parent) == 1) {
			return 2;
		} else {
			Storage storage = new Storage();
			storage.setName(name);
			storage.setCreated(new Date());
			storage.setUpdated(storage.getCreated());
			storage.setUserId(userId);
			storage.setState(1);
			storage.setParent(parent);
			storage.setIsFolder(1);
			int result = storageMapper.insert(storage);
			return result;
		}
	}

	@Override
	public List<Storage> selectByExample(StorageExample example) {
		return storageMapper.selectByExample(example);
	}

	@Override
	public int move2(String name, Long parent, Long spaceId, Long userId) {
		Storage storage = this.selectByPrimaryKey(spaceId);
		name = name.trim();
		if (name.lastIndexOf(".") == -1 && storage.getIsFolder() != 1) {
			String name2 = storage.getName();
			name2 = name2.substring(name2.lastIndexOf("."));
			name = name + name2;
		}
		int i = this.seeName2(name, userId, parent, spaceId);
		if (i == 1) {
			return 2;
		}
		storage.setName(name);
		storage.setUpdated(new Date());
		storage.setParent(parent);
		return storageMapper.updateByPrimaryKeySelective(storage);
	}

	@Override
	public int setState(Long[] id, int state) {
		for (int i = 0; i < id.length; i++) {
			Storage storage = storageMapper.selectByPrimaryKey(id[i]);
			Integer isFolder = storage.getIsFolder();
			if (isFolder == 1) {
				int result = SetStateAll(id[i], state);
				if (result == 0) {
					return 0;
				}
			}
			storage.setUpdated(new Date());
			storage.setState(state);
			Long parent = storage.getParent();
			Storage s = storageMapper.selectByPrimaryKey(parent);
			Integer state2 = s.getState();
			if (state2 != state) {
				storage.setParent((long) 1);
			}
			int r = storageMapper.updateByPrimaryKeySelective(storage);
			if (r == 0) {
				return 0;
			}
		}
		return 1;
	}

	@Override
	public int SetStateAll(Long id, int state) {
		StorageExample example = new StorageExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentEqualTo(id);
		List<Storage> list = storageMapper.selectByExample(example);
		for (Storage s : list) {
			if (s.getIsFolder() == 1) {
				int result = this.SetStateAll(s.getId(), state);
				if (result == 0) {
					return 0;
				}
			}
			s.setState(state);
			s.setUpdated(new Date());
			Long parent = s.getParent();
			Storage s2 = storageMapper.selectByPrimaryKey(parent);
			Integer state2 = s2.getState();
			if (state2 != state) {
				s.setParent((long) 1);
			}
			int r = storageMapper.updateByPrimaryKeySelective(s);
			if (r == 0) {
				return 0;
			}
		}
		return 1;
	}

	@Override
	public int delete(Long[] id) throws IOException, MyException {
		for (int i = 0; i < id.length; i++) {
			Storage storage = storageMapper.selectByPrimaryKey(id[i]);
			Integer isFolder = storage.getIsFolder();
			if (isFolder == 1) {
				int result = this.delete(id[i]);
				if (result == 0) {
					return 0;
				}
			} else {
				String adress = storage.getAdress();
				int r1 = FastDfsUtil.fdfsDeleteFile1(adress);
				if (r1 != 0) {
					return 0;
				}
			}
			int r = storageMapper.deleteByPrimaryKey(id[i]);
			if (r == 0) {
				return 0;
			}
		}
		return 1;
	}

	public int delete(Long id) throws IOException, MyException {
		StorageExample example = new StorageExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentEqualTo(id);
		List<Storage> list = storageMapper.selectByExample(example);
		for (Storage s : list) {
			if (s.getIsFolder() == 1) {
				int result = this.delete(s.getId());
				if (result == 0) {
					return 0;
				}
			} else {
				String adress = s.getAdress();
				int r = FastDfsUtil.fdfsDeleteFile1(adress);
				if (r != 0) {
					return 0;
				}
			}
			int i = storageMapper.deleteByPrimaryKey(s.getId());
			if (i == 0) {
				return 0;
			}
		}
		return 1;
	}

	@Override
	public void deleteOverdue() {
		StorageExample example = new StorageExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(2);
		List<Storage> list = storageMapper.selectByExample(example);
		Date date = new Date();
		try {
			for (Storage s : list) {
				Date date2 = s.getUpdated();
				int day = (int) ((date.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
				if (day > 15) {
					String adress = s.getAdress();
					FastDfsUtil.fdfsDeleteFile1(adress);
					storageMapper.deleteByPrimaryKey(s.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public PageInfo<Storage> selectMusicList(Long userId, String keyword, int state, Integer pageNum,
			Integer pageSize) {

		Storage storage = new Storage();
		storage.setState(state);
		storage.setUserId(userId);
		if (StringUtils.isNotBlank(keyword)) {
			storage.setName(keyword);
		}
		PageHelper.startPage(pageNum, pageSize);
		List<Storage> list = storageMapper.selectMusicList(storage);
		PageInfo<Storage> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<Storage> selectPictureList(Long userId, String keyword, int state, Integer pageNum,
			Integer pageSize) {
		Storage storage = new Storage();
		storage.setState(state);
		storage.setUserId(userId);
		if (StringUtils.isNotBlank(keyword)) {
			storage.setName(keyword);
		}
		PageHelper.startPage(pageNum, pageSize);
		List<Storage> list = storageMapper.selectPictureList(storage);
		PageInfo<Storage> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<Storage> selectDocumentList(Long userId, String keyword, int state, Integer pageNum,
			Integer pageSize) {
		Storage storage = new Storage();
		storage.setState(state);
		storage.setUserId(userId);
		if (StringUtils.isNotBlank(keyword)) {
			storage.setName(keyword);
		}
		PageHelper.startPage(pageNum, pageSize);
		List<Storage> list = storageMapper.selectDocumentList(storage);
		PageInfo<Storage> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<Storage> selectVideoList(Long userId, String keyword, int state, Integer pageNum,
			Integer pageSize) {
		Storage storage = new Storage();
		storage.setState(state);
		storage.setUserId(userId);
		if (StringUtils.isNotBlank(keyword)) {
			storage.setName(keyword);
		}

		PageHelper.startPage(pageNum, pageSize);
		List<Storage> list = storageMapper.selectVideoList(storage);
		PageInfo<Storage> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<Storage> selectTorrentList(Long userId, String keyword, int state, Integer pageNum,
			Integer pageSize) {
		Storage storage = new Storage();
		storage.setState(state);
		storage.setUserId(userId);
		if (StringUtils.isNotBlank(keyword)) {
			storage.setName(keyword);
		}
		PageHelper.startPage(pageNum, pageSize);
		List<Storage> list = storageMapper.selectTorrentList(storage);
		PageInfo<Storage> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<Storage> selectOtherList(Long userId, String keyword, int state, Integer pageNum,
			Integer pageSize) {
		Storage storage = new Storage();
		storage.setState(state);
		storage.setUserId(userId);
		if (StringUtils.isNotBlank(keyword)) {
			storage.setName(keyword);
		}
		
		PageHelper.startPage(pageNum, pageSize);
		List<Storage> list = storageMapper.selectOtherList(storage);
		PageInfo<Storage> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
}
