package com.yuu.yit.education.job;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yuu.yit.education.course.feign.IBossCourseVideo;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.config.SystemUtil;
import com.xiaoleilu.hutool.io.FileUtil;

/**
 * 定时任务-视频处理
 * 
 * @author Yuu
 */
@Component
public class VideoCrontab extends BaseController {

	private static final Object KEY = new Object();
	private static boolean taskFlag = false;

	@Autowired
	private IBossCourseVideo bossCourseVideo;

	/**
	 * 定时任务每分钟执行一次 <br/>
	 * 注意：每个course服务都必须要对应有一个定时任务，针对服务器
	 */
	@Scheduled(fixedRate = 60000)
	public void orderCancel() {
		synchronized (KEY) {
			if (VideoCrontab.taskFlag) {
				logger.warn("视频处理-任务已经启动");
				return;
			}
			VideoCrontab.taskFlag = true;
		}

		int videoSum = 0;

		File file = new File(SystemUtil.PERIOD_VIDEO_PATH);
		if (file.isDirectory()) {// isDirectory是否文件夹
			File[] files = file.listFiles();// listFiles是获取该目录下所有文件和目录的绝对路径
			for (File targetFile : files) {
				
				if (targetFile.isFile() && targetFile.exists()) {
					if (FileUtil.newerThan(targetFile, (System.currentTimeMillis() - 7200000))) {// 上传两个小时内
						
						try {
							bossCourseVideo.handleScheduledTasks(targetFile);
							videoSum = videoSum + 1;
						} catch (Exception e) {
							logger.error("视频定时任务处理失败", e);
						}
						
					}
				}
			}
		}

		VideoCrontab.taskFlag = false;
		
		logger.warn("视频处理-定时任务完成，处理视频数={}", videoSum);
	}

}
