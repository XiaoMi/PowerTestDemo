package com.miui.powerkeeper.testDemo.tools;

import android.os.RemoteException;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiDevice;

public class Automator
{
	public UiDevice mDevice = null;
	public int mDeviceDisplayWidth = 0;
	public int mDeviceDisplayHeight = 0;

	public Automator(UiDevice device)
	{
		this.mDevice = device;
		this.mDeviceDisplayWidth = mDevice.getDisplayWidth();
		this.mDeviceDisplayHeight = mDevice.getDisplayHeight();
	}

	public void swipe(int startX, int startY, int endX, int endY)
	{
		mDevice.swipe(startX, startY, endX, endY, Utils.SWIPE_STEPS);
	}

	public void home(int times)
	{
		for (int i = 0; i < times; i++)
		{
			mDevice.pressHome();
		}
	}
	
	public void volumedown()
	{
		mDevice.pressKeyCode(KeyEvent.KEYCODE_VOLUME_DOWN);
	}

	public void back(int times)
	{
		for (int i = 0; i < times; i++)
		{
			mDevice.pressBack();
		}
	}

	public void home()
	{
		mDevice.pressHome();
	}

	public void back()
	{
		mDevice.pressBack();
	}

	public void click(int x, int y)
	{
		mDevice.click(x, y);
	}

	public void wakeUp()
	{
		try
		{
			mDevice.wakeUp();
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCurrentPackageName()
	{
		return mDevice.getCurrentPackageName();
	}

	public void screenOn()
	{
		try
		{
			if (mDevice.isScreenOn() == false)
			{
				mDevice.wakeUp();
			}
		} catch (RemoteException e)
		{
			Utils.log(e.toString());
		}
	}

	public String brandName()
	{
		String command = Utils.SHELL("getprop ro.product.brand");
		System.out.println(command);
		return command;
	}


}
