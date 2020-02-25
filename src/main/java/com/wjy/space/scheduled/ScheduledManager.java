package com.wjy.space.scheduled;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wjy.space.service.StorageService;

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
