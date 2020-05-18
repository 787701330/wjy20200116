package cn.wujunya.space.scheduled;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.wujunya.space.service.StorageService;

@Component("ScheduledManager")
public class ScheduledManager {
	
	@Autowired
	private StorageService storageService;

	@Scheduled(cron="0 0 0 * * *")
	public void task() {
		System.out.println("启动删除！");
		storageService.deleteOverdue();
	}
}
