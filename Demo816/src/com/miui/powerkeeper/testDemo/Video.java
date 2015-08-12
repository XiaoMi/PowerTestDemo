package com.miui.powerkeeper.testDemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.miui.powerkeeper.testDemo.tools.Automator;
import com.miui.powerkeeper.testDemo.tools.Utils;

public class Video
{
	private Automator am;

	Video (Automator automator, long time)
	{
		this.am = automator;
		Utils.log(new SimpleDateFormat("MMddHHmm").format(new Date()) + "......Video");
		long startTime = System.currentTimeMillis();
		long runningTime = time;

		while (System.currentTimeMillis() - startTime < runningTime)
		{
			try
			{
				run();
			} catch (UiObjectNotFoundException e)
			{
				Utils.catchException(e, "Video");
				am.back(5);
			}
		}
		am.home(2);
	}

	private void run() throws UiObjectNotFoundException
	{
		UiObject folder = new UiObject(new UiSelector().text("DemoVideo"));
		UiObject video = new UiObject(new UiSelector().text("XiaomiPhone"));
		UiObject popNote = new UiObject(
				new UiSelector().text("是否从上次停止的位置恢复播放？"));

		Utils.excuteCMD("am start -n com.mxtech.videoplayer.ad/.ActivityMediaList");
		Utils.sleepMs(3 * 1000);
		folder.click();
		video.click();
		if(popNote.exists()) {
			new UiObject(new UiSelector().text("重新开始")).click();
		}
		Utils.sleepMs(58 * 1000);
		am.back(3);
		Utils.sleepMs(1000);
		am.back();
	}
}
