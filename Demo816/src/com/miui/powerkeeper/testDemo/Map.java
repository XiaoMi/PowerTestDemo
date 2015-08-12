package com.miui.powerkeeper.testDemo;

import java.text.SimpleDateFormat;
import java.util.Date;

//import android.os.RemoteException;

import android.graphics.Rect;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.powerkeeper.testDemo.tools.Automator;
import com.miui.powerkeeper.testDemo.tools.Utils;

public class Map extends UiAutomatorTestCase
{
	private Automator am;

	Map(Automator automator, long time)
	{
		this.am = automator;
		Utils.log(new SimpleDateFormat("MMddHHmm").format(new Date()) + "......Map");
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < time)
		{
			try
			{
				navigation(time - (System.currentTimeMillis() - startTime));
			} catch (Exception e)
			{
				Utils.catchException(e, "Map");
				am.back(4);
				Utils.sleepMs(1 * 1000);
				am.back(4);
				Utils.sleepMs(1 * 1000);
				am.home(2);
			}
		}

		am.back(4);
		am.home(2);
	}

	private void navigation(long waittime) throws UiObjectNotFoundException
	{
		Utils.excuteCMD("am start -n com.autonavi.minimap/com.autonavi.map.activity.NewMapActivity");
		Utils.sleepMs(6 * 1000);

		new UiObject(new UiSelector().text("路线")).click();
		Utils.sleepMs(2 * 1000);
		new UiObject(new UiSelector().description("自驾车路线规划")).click();
		Utils.sleepMs(2 * 1000);
		new UiObject(new UiSelector().text("输入终点")).clickAndWaitForNewWindow();
		Utils.sleepMs(1 * 1000);

		UiObject destination = new UiObject(new UiSelector().text("天津站"));
		destination.clickAndWaitForNewWindow();
		Utils.sleepMs(5 * 1000);
		UiObject navigationButton = new UiObject(new UiSelector().className(
				"android.widget.Button").text("导航"));
		Rect bound = navigationButton.getBounds();
		am.click(am.mDeviceDisplayWidth / 3, bound.centerY());
		Utils.sleepMs(3 * 1000);

		UiObject simulationNavigation = new UiObject(
				new UiSelector().text("模拟导航"));
		UiObject cancelButton = new UiObject(new UiSelector().text("取消"));

		for (int i = 0; i < 5; i++)
		{
			if (simulationNavigation.exists())
			{
				break;
			}
			if (cancelButton.exists())
			{
				cancelButton.click();
				Utils.sleepMs(1 * 1000);
			}

			am.swipe(am.mDeviceDisplayWidth / 2, am.mDeviceDisplayHeight / 2,
					am.mDeviceDisplayWidth / 2, am.mDeviceDisplayHeight / 4);
			Utils.sleepMs(2 * 1000);
		}
		
		Utils.sleepMs(2 * 1000);
		am.swipe(am.mDeviceDisplayWidth / 2, am.mDeviceDisplayHeight / 2,
				am.mDeviceDisplayWidth / 2, am.mDeviceDisplayHeight / 4);
		Utils.sleepMs(3 * 1000);
		simulationNavigation.clickAndWaitForNewWindow();
		Utils.sleepMs(2 * 1000);

		UiObject goingIcon = new UiObject(
				new UiSelector()
						.resourceId("com.autonavi.minimap:id/autonavi_continue_navi"));
		long sTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - sTime < waittime)
		{
			Utils.sleepMs(30 * 1000);// 检查间隔
			goingIcon.click();
		}

		am.back(4);
		Utils.sleepMs(1 * 1000);
		am.back(4);
		Utils.sleepMs(1 * 1000);
		am.home(2);
	}
}