package com.miui.powerkeeper.testDemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.miui.powerkeeper.testDemo.tools.Automator;
import com.miui.powerkeeper.testDemo.tools.Utils;

public class LaunchYouku
{
	private Automator am;

	LaunchYouku(Automator automator, long time)
	{
		am = automator;
		Utils.log(new SimpleDateFormat("MMddHHmm").format(new Date()) + ".LaunchYouku");
		long startTime = System.currentTimeMillis();
		long runningTime = time;

		while (System.currentTimeMillis() - startTime < runningTime)
		{
			try
			{
				Utils.excuteCMD("am start -n com.youku.phone/com.youku.phone.ActivityWelcome");
				System.out.println("launch youku");
				Utils.sleepMs(15 * 1000);
				System.out.print(am.mDevice.getCurrentPackageName());
				swipe();
				am.back();
				Utils.sleepMs(500);
				am.back();
				Utils.sleepMs(300);
				am.back();
				am.home(2);
			} catch (Exception e)
			{
				Utils.catchException(e, "LaunchYouku");
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

	private void swipe() throws UiObjectNotFoundException
	{
		if (new UiObject(new UiSelector().text("同意")).exists())
		{
			new UiObject(new UiSelector().text("同意")).click();
			System.out.println("优酷Youku软件最终用户使用许可协议");
		}

		am.swipe(am.mDeviceDisplayWidth * 5 / 10,
				am.mDeviceDisplayHeight * 7 / 10,
				am.mDeviceDisplayWidth * 5 / 10,
				am.mDeviceDisplayHeight * 3 / 10);
		System.out.println("Youku swipe");
		Utils.sleepMs(2 * 1000);
	}
}