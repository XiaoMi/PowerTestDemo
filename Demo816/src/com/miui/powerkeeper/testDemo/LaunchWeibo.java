package com.miui.powerkeeper.testDemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.miui.powerkeeper.testDemo.tools.Automator;
import com.miui.powerkeeper.testDemo.tools.Utils;

public class LaunchWeibo
{
	private Automator am;

	LaunchWeibo(Automator automator, long time)
	{
		am = automator;
		Utils.log(new SimpleDateFormat("MMddHHmm").format(new Date()) + ".LaunchWeibo");
		long startTime = System.currentTimeMillis();
		long runningTime = time;

		while (System.currentTimeMillis() - startTime < runningTime)
		{
			try
			{
				Utils.excuteCMD("am start -n com.sina.weibo/com.sina.weibo.SplashActivity");
				System.out.println("launch weibo");
				Utils.sleepMs(10 * 1000);
				am.back();
				Utils.sleepMs(500);
				am.back();
				Utils.sleepMs(300);
				am.back();
				am.home(2);
			} catch (Exception e)
			{
				Utils.catchException(e, "LaunchWeibo");
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