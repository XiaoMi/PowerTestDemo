package com.miui.powerkeeper.testDemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.miui.powerkeeper.testDemo.tools.Automator;
import com.miui.powerkeeper.testDemo.tools.Utils;

public class Sleep
{
	private Automator am;

	Sleep(Automator automator)
	{
		this.am = automator;
		Utils.log(new SimpleDateFormat("MMddHHmm").format(new Date()) + "......Sleep");
		
		if (am.brandName().equals("Meizu"))
		{
			System.out.println("m1");
			Utils.SHELL("logcat -c");
			Utils.sleepMs(2000);
			
			int i = 0;
			while (true)
			{
				boolean result = Utils.SHELL1("logcat -d", "AlarmAlertFullScreen");
				System.out.println("_________\n" + (i++) + "_________\n");
				if (result)
				{
					break;
				}
				Utils.sleepMs(5000);
			}
			System.out.println("m2");
		}
		else{
			String packagename = "";
			if (am.brandName().equals("Huawei"))
			{
				packagename = "com.android.deskclock";
			}
	
			if (am.brandName().equals("samsung"))
			{
				packagename = "com.sec.android.app.clockpackage";
			}
			if (am.brandName().equals("Xiaomi"))
			{
				packagename = "com.android.deskclock";
			}
	
			while (true)
			{
				Utils.sleepMs(5000);
				if (am.getCurrentPackageName().equals(packagename))
				{
					System.out.println("packagename get");
					break;
				}
			}
		}

		wakeupPhone();
		am.home(1);
	}
	
	public void wakeupPhone()
	{
		if (am.brandName().equals("Xiaomi"))
		{
			Utils.sleepMs(1 * 1000);
			swipeLockscreenUp();
		} else if (am.brandName().equals("Huawei"))
		{
			am.volumedown();
			Utils.sleepMs(1 * 1000);
			am.home(2);			
		} else if (am.brandName().equals("Meizu"))
		{
			Utils.sleepMs(1 * 1000);
			am.swipe(am.mDeviceDisplayWidth * 5 / 10, am.mDeviceDisplayHeight * 8 / 10,
					am.mDeviceDisplayWidth * 5 / 10, 0);
			System.out.println("swipe creen up");
		} else if (am.brandName().equals("samsung"))
		{
			Utils.sleepMs(1 * 1000);
			am.swipe(540, 1450, 1080, 1450);
			Utils.sleepMs(1 * 1000);
			swipeLockscreenUp();
			am.home(2);
		}
	}

	public void swipeLockscreenUp()
	{
		am.wakeUp();
		am.swipe(am.mDeviceDisplayWidth * 5 / 10, am.mDeviceDisplayHeight * 8 / 10,
				am.mDeviceDisplayWidth * 5 / 10, am.mDeviceDisplayHeight * 3 / 10);
		Utils.sleepMs(1 * 1000);
		am.home(2);
	}

	public void swipeLockscreenRight()
	{
		am.wakeUp();
		am.swipe(am.mDeviceDisplayWidth * 4 / 10, am.mDeviceDisplayHeight * 4 / 10,
				am.mDeviceDisplayWidth * 9 / 10, am.mDeviceDisplayHeight * 4 / 10);
		Utils.sleepMs(1 * 1000);
		am.home(2);
	}
}
