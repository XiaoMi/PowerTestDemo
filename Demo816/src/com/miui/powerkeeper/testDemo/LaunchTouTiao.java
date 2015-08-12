package com.miui.powerkeeper.testDemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.miui.powerkeeper.testDemo.tools.Automator;
import com.miui.powerkeeper.testDemo.tools.Utils;

public class LaunchTouTiao
{
	private Automator am;

	LaunchTouTiao(Automator automator, long time)
	{
		am = automator;
		Utils.log(new SimpleDateFormat("MMddHHmm").format(new Date()) + ".LaunchTouTiao");
		long startTime = System.currentTimeMillis();
		long runningTime = time;

		while (System.currentTimeMillis() - startTime < runningTime)
		{
			try
			{
				Utils.excuteCMD("am start -n com.ss.android.article.news/com.ss.android.article.news.activity.SplashActivity");
				System.out.println("launch toutiao");
				Utils.sleepMs(5 * 1000);
				if (new UiObject(
						new UiSelector()
								.resourceId("com.ss.android.article.news:id/close_introduction"))
						.exists())
				{
					new UiObject(
							new UiSelector()
									.resourceId("com.ss.android.article.news:id/close_introduction"))
							.click();
					System.out.println("toutiao skip login");
					Utils.sleepMs(2 * 1000);
				}
				Utils.sleepMs(3 * 1000);
				am.swipe(am.mDeviceDisplayWidth * 5 / 10,
						am.mDeviceDisplayHeight * 7 / 10,
						am.mDeviceDisplayWidth * 5 / 10,
						am.mDeviceDisplayHeight * 3 / 10);
				Utils.sleepMs(5 * 1000);
				am.swipe(am.mDeviceDisplayWidth * 5 / 10,
						am.mDeviceDisplayHeight * 3 / 10,
						am.mDeviceDisplayWidth * 5 / 10,
						am.mDeviceDisplayHeight * 7 / 10);
				am.back();
				Utils.sleepMs(500);
				am.back();
				Utils.sleepMs(500);
				am.back();
				am.home(2);
			} catch (Exception e)
			{
				Utils.catchException(e, "LaunchtouTiao");
				am.back();
				Utils.sleepMs(1000);
				am.back();
				Utils.sleepMs(1000);
				am.back();
				Utils.sleepMs(1000);
				am.back();
				Utils.sleepMs(1000);
				am.back(3);
			}
		}
		am.home(2);
	}
}