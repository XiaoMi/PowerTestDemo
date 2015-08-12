package com.miui.powerkeeper.testDemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.miui.powerkeeper.testDemo.tools.Automator;
import com.miui.powerkeeper.testDemo.tools.Utils;

public class QQTextSendNew
{
	private Automator am;

	QQTextSendNew(Automator automator, long time)
	{
		this.am = automator;
		Utils.log(new SimpleDateFormat("MMddHHmm").format(new Date()) + "......QQTextSendNew");
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < time)
		{
			try
			{
				Utils.sleepMs(1000);
				System.out.println("starting chat");
				chat(time - (System.currentTimeMillis() - startTime));
			} catch (Exception e)
			{
				Utils.catchException(e, "QQTextSendNew");
				am.back(4);
				Utils.sleepMs(1 * 1000);
				am.back(4);
				Utils.sleepMs(1 * 1000);
				am.home(2);
				System.out.println("running catch");
			}
		}
		am.back(4);
		am.home(2);
	}

	private void chat(long waittime) throws UiObjectNotFoundException
	{
		Utils.excuteCMD("am start -n com.tencent.mobileqq/com.tencent.mobileqq.activity.SplashActivity");
		Utils.sleepMs(2 * 1000);

		relogin();
		System.out.println("chat(): relogin() is running");

		UiObject chatName = new UiObject(new UiSelector().text("demo"));
		chatName.clickAndWaitForNewWindow();
		System.out.println("chat(): select demo successfully");

		UiObject soundButton = new UiObject(new UiSelector().text("语音输入"));
		UiObject textButton = new UiObject(new UiSelector().text("文字输入"));
		UiObject inputText = new UiObject(
				new UiSelector().className("android.widget.EditText"));

		long sTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - sTime < waittime)
		{
			if (chatName.exists() && chatName.isSelected())
			{
				chatName.click();
				System.out.println("chat(): chatName is pressed");
			}

			System.out.println("chat(): press edit text");
			if (soundButton.exists())
			{
				Utils.sleepMs(1 * 1000);
			} else if (textButton.exists())
			{
				textButton.click();
			}
			System.out.println("chat(): prepare for input message");
			inputText.click();
			Utils.sleepMs(1 * 1000);
			inputText.clearTextField();
			Utils.sleepMs(1 * 1000);
			inputText.setText("816");
			Utils.sleepMs(1 * 1000);

			new UiObject(new UiSelector().text("发送")).click();
			Utils.sleepMs(16 * 1000);
			System.out.println("chat(): message is send");

			am.swipe(am.mDeviceDisplayWidth / 2, am.mDeviceDisplayHeight / 2,
					am.mDeviceDisplayWidth / 2, am.mDeviceDisplayHeight * 3 / 7);
			Utils.sleepMs(10 * 1000);
		}
	}

	private void relogin() throws UiObjectNotFoundException
	{
		UiObject reloginButton = new UiObject(new UiSelector().resourceId(
				"com.tencent.mobileqq:id/dialogRightBtn").text("重新登录"));
		UiObject loginButton = new UiObject(
				new UiSelector().description("登录QQ"));

		System.out.println("relogin(): is running");
		if (reloginButton.exists() && reloginButton.isClickable())
		{
			reloginButton.click();
			System.out.println("relogin(): reloginButton is clicked");
			Utils.sleepMs(1 * 1000);
			am.back(5);
		}
		if (loginButton.exists() && loginButton.isClickable())
		{
			System.out.println("relogin(): loginButton is clicked");
			loginButton.clickAndWaitForNewWindow();
		}
	}
}
