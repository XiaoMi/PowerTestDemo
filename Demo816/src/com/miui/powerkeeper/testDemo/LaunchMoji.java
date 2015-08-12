package com.miui.powerkeeper.testDemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.miui.powerkeeper.testDemo.tools.Automator;
import com.miui.powerkeeper.testDemo.tools.Utils;

public class LaunchMoji
{
	private Automator am;

	LaunchMoji(Automator automator, long time)
	{
		am = automator;
		Utils.log(new SimpleDateFormat("MMddHHmm").format(new Date()) + ".LaunchMoji");
		long startTime = System.currentTimeMillis();
		long runningTime = time;

		while (System.currentTimeMillis() - startTime < runningTime)
		{
			try
			{
				Utils.excuteCMD("am start -n com.moji.mjweather/com.moji.mjweather.activity.main.MainActivity");
				Utils.sleepMs(10 * 1000);
				System.out.println("launch Moji");
				location();
				System.out.println("Moji location finished");
				Utils.sleepMs(10 * 1000);
				System.out.println("Moji wait 10");
				am.back();
				Utils.sleepMs(600);
				am.back();
				Utils.sleepMs(600);
				am.back();
				am.home(2);
			} catch (Exception e)
			{
				Utils.catchException(e, "LaunchMoji");
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

	private void location() throws UiObjectNotFoundException
	{
		UiObject startMoji = new UiObject(new UiSelector().text("开启墨迹之旅"));

		while (new UiObject(
				new UiSelector()
						.resourceId("com.moji.mjweather:id/ImageView_content"))
				.exists())
		{
			System.out.println("Moji first start");
			if (!startMoji.exists())
			{
				am.swipe(am.mDeviceDisplayWidth * 7 / 10,
						am.mDeviceDisplayHeight / 2,
						am.mDeviceDisplayWidth * 2 / 10,
						am.mDeviceDisplayHeight / 2);
				Utils.sleepMs(1 * 1000);
				System.out.println("Moji no moji button");
			}
			if (startMoji.exists())
			{
				System.out.println("Moji moji button");
				Utils.sleepMs(1000);
				startMoji.click();
				System.out.println("moji button clicked");
				Utils.sleepMs(1000);

				for (int i = 0; i < 3; i++)
				{
					am.swipe(am.mDeviceDisplayWidth * 5 / 10,
							am.mDeviceDisplayHeight * 3 / 10,
							am.mDeviceDisplayWidth * 5 / 10,
							am.mDeviceDisplayHeight * 7 / 10);
					System.out.println("Moji swipe 1");
					Utils.sleepMs(1000);
				}
			}
		}
	}
}