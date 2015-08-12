package com.miui.powerkeeper.testDemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.miui.powerkeeper.testDemo.tools.Automator;
import com.miui.powerkeeper.testDemo.tools.Utils;

public class Taobao
{
	private Automator am;

	Taobao(Automator automator, long time)
	{
		am = automator;
		Utils.log(new SimpleDateFormat("MMddHHmm").format(new Date()) + "......Taobao");
		long startTime = System.currentTimeMillis();
		long runningTime = time;

		while (System.currentTimeMillis() - startTime < runningTime)
		{
			try
			{
				Utils.excuteCMD("am start -n com.taobao.taobao/com.taobao.tao.homepage.MainActivity3");
				Utils.sleepMs(10 * 1000);
				mainPage();
			} catch (Exception e)
			{
				Utils.catchException(e, "Taobao");
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

	private void mainPage() throws UiObjectNotFoundException
	{
		if (!new UiObject(new UiSelector().text("扫一扫")).exists())
		{
			throw new UiObjectNotFoundException("not in main page");
		}
		String[] button = { "天猫", "天猫超市" };
		for (int i = 0; i < button.length; i++)
		{
			Utils.sleepMs(1 * 1000);
			new UiObject(new UiSelector().text(button[i]))
					.clickAndWaitForNewWindow();
			Utils.sleepMs(5 * 1000);
			am.swipe(am.mDeviceDisplayWidth / 2, am.mDeviceDisplayHeight / 2,
					am.mDeviceDisplayWidth / 2, am.mDeviceDisplayHeight * 3 / 4);
			Utils.sleepMs(5 * 1000);
			swipeDown(5);
			swipeUp(5);
			Utils.sleepMs(1 * 1000);
			am.back();
		}

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

	private void swipeDown(int times)
	{
		for (int i = 0; i < times; i++)
		{
			am.swipe(am.mDeviceDisplayWidth / 2, am.mDeviceDisplayHeight / 2,
					am.mDeviceDisplayWidth / 2, am.mDeviceDisplayHeight / 3);
			System.out.println("swipedown");
			Utils.sleepMs(1 * 1000);
		}
	}

	private void swipeUp(int times)
	{
		for (int i = 0; i < times; i++)
		{
			am.swipe(am.mDeviceDisplayWidth / 2, am.mDeviceDisplayHeight / 2,
					am.mDeviceDisplayWidth / 2, am.mDeviceDisplayHeight * 2 / 3);
			System.out.println("swipeUp");
			Utils.sleepMs(3 * 1000);
		}
	}
}