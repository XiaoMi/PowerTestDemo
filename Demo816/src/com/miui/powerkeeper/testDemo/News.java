package com.miui.powerkeeper.testDemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.miui.powerkeeper.testDemo.tools.Automator;
import com.miui.powerkeeper.testDemo.tools.Utils;

public class News
{
	private Automator am;

	News(Automator automator, long time)
	{
		this.am = automator;
		Utils.log(new SimpleDateFormat("MMddHHmm").format(new Date()) + "......News");
		long startTime = System.currentTimeMillis();
		long runningTime = time;
		
		while (System.currentTimeMillis() - startTime < runningTime)
		{
			try
			{
				Utils.excuteCMD("am start -n com.netease.newsreader.activity/com.netease.nr.biz.ad.AdActivity");
				Utils.sleepMs(10 * 1000);

				new UiObject(new UiSelector().text("新闻"))
						.clickAndWaitForNewWindow();
				Utils.sleepMs(2 * 1000);
				swipeDown(3);
				Utils.sleepMs(1 * 1000);
				swipeUp(3);
				Utils.sleepMs(1 * 1000);

				new UiObject(new UiSelector().text("阅读"))
						.clickAndWaitForNewWindow();
				Utils.sleepMs(2 * 1000);
				swipeDown(3);
				Utils.sleepMs(1 * 1000);
				swipeUp(3);
				Utils.sleepMs(1 * 1000);
				
				new UiObject(new UiSelector().text("视听"))
						.clickAndWaitForNewWindow();
				Utils.sleepMs(2 * 1000);
				swipeDown(3);
				Utils.sleepMs(1 * 1000);
				swipeUp(3);
				Utils.sleepMs(1 * 1000);
				
				new UiObject(new UiSelector().text("发现"))
						.clickAndWaitForNewWindow();
				Utils.sleepMs(2 * 1000);
				swipeDown(3);
				Utils.sleepMs(1 * 1000);
				swipeUp(3);
				Utils.sleepMs(1 * 1000);
			} catch (Exception e)
			{
				Utils.catchException(e, "News");
				am.back(4);
				am.home(2);
			}
		}
		am.home(2);
	}

	private void swipeDown(int times)
	{
		for (int i = 0; i < times; i++)
		{
			am.swipe(am.mDeviceDisplayWidth / 2, am.mDeviceDisplayHeight / 2,
					am.mDeviceDisplayWidth / 2, am.mDeviceDisplayHeight / 4);
			Utils.sleepMs(2 * 1000);
		}
	}
	
	private void swipeUp(int times)
	{
		for (int i = 0; i < times; i++)
		{
			am.swipe(am.mDeviceDisplayWidth / 2, am.mDeviceDisplayHeight / 2,
					am.mDeviceDisplayWidth / 2, am.mDeviceDisplayHeight * 3 / 4);
			Utils.sleepMs(2 * 1000);
		}
	}
}
