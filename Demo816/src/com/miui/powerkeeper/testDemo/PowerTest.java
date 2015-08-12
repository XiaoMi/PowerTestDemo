package com.miui.powerkeeper.testDemo;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.powerkeeper.testDemo.tools.Automator;
import com.miui.powerkeeper.testDemo.tools.Utils;

public class PowerTest extends UiAutomatorTestCase
{

	public void test_PowerConsumption()
	{
		Utils.START_TIME = System.currentTimeMillis();
		Automator automator = new Automator(this.getUiDevice());
		Utils.log("816Demo");

		Utils.sleepMs(30 * 1000);
		new QQTextSendNew(automator, 1 * 60 * 1000);
		new Video(automator, 1 * 60 * 1000);
		new Taobao(automator, 1 * 60 * 1000);
		new Map(automator, 1 * 60 * 1000);
		new News(automator, 1 * 60 * 1000);
//		new Sleep(automator);
		
		int loop = Integer.parseInt(this.getParams().getString("loop", "10"));
		for (int iterator = 0; iterator < loop; iterator++)
		{
			Utils.sleepMs(30 * 1000);
			new QQTextSendNew(automator, 30 * 60 * 1000);//30min
			new LaunchWeibo(automator, 30 * 1000);
			new Video(automator, 20 * 60 * 1000);//20min
			new LaunchMoji(automator, 30 * 1000);
			new Taobao(automator, 20 * 60 * 1000);//20min
			new LaunchYouku(automator, 30 * 1000);
			new Map(automator, 30 * 60 * 1000);//30min
			new LaunchTouTiao(automator, 30 * 1000);
			new News(automator, 20 * 60 * 1000);//20min
			new Sleep(automator);
		}
	}
}
