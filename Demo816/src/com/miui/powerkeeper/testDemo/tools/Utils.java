package com.miui.powerkeeper.testDemo.tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils
{
	public static String LOGTAG = "PT";
	public static String LOG_PATH = "/sdcard/Download/logDemo816.ALL."
			+ new SimpleDateFormat("MMddHHmm").format(new Date()) + ".txt";
	public static long WAIT_MS = 1000;
	public static long LAUNCH_WAIT_MS = 3000;
	public static int SWIPE_STEPS = 6;
	public static long START_TIME;

	// Save log in device
	public static void log(String msg)
	{
		FileWriter writer = null;
		try
		{
			File f = new File(LOG_PATH);
			if (!f.exists())
			{
				f.createNewFile();
			}
			writer = new FileWriter(LOG_PATH, true);
			writer.write(msg + "\n");
		} catch (IOException ee)
		{
			ee.printStackTrace();
		} finally
		{
			try
			{
				if (writer != null)
				{
					writer.close();
				}
			} catch (Exception e)
			{
				log(e.toString());
			}
		}
	}

	public static void excuteCMD(String command)
	{
		try
		{
			Runtime.getRuntime().exec(command);
		} catch (Exception e)
		{
			log("Exception in method 'excuteCMD()' " + command + ".");
		}
	}

	public static void sleepMs(long ms)
	{
		try
		{
			Thread.sleep(ms);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static void catchException(Exception e, String img)
	{
		Utils.log(e.toString());
		Utils.excuteCMD("screencap -p /sdcard/Download/" + img
				+ new SimpleDateFormat("MMddHHmm").format(new Date()) + ".png");
		Utils.sleepMs(2000);
	}

	public static String SHELL(String command)
	{
		Process p = null;
		int status = 0;
		String text = "";
		BufferedInputStream in = null;
		BufferedReader resultReader = null;

		try
		{
			p = Runtime.getRuntime().exec(command);
			status = p.waitFor();
			if (status != 0)
			{
				throw new RuntimeException(String.format(
						"Run shell command: %s,  status: %s", command, status));
			}

			in = new BufferedInputStream(p.getInputStream());
			resultReader = new BufferedReader(
					new InputStreamReader(in));//, "UTF-8"));
			String line;
			while ((line = resultReader.readLine()) != null)
			{
				text += line + "\n";
			}

			resultReader.close();
			in.close();

		} catch (Exception e)
		{
		} finally
		{
			if (resultReader != null)
			{
				try
				{
					resultReader.close();
					in.close();
				} catch (Exception e)
				{
				}
			}
			if (p != null)
			{
				p.destroy();
			}
		}

		return text.equals("") ? null : text.trim();
	}
	
	public static boolean SHELL1(String command, String kwd)
	{
		Process p = null;
		InputStream is = null;
		BufferedReader reader = null;
		boolean result = false;

		try
		{
			p = Runtime.getRuntime().exec(command);
			is = p.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = reader.readLine()) != null)
			{
				//System.out.println(line);
				if(line.contains(kwd)) {
					System.out.println(line);
					result = true;
					break;
				}
			}

			reader.close();
			is.close();

		} catch (Exception e)
		{
		} finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
					is.close();
				} catch (Exception e)
				{
				}
			}
			if (p != null)
			{
				p.destroy();
			}
		}

		return result;
	}
}
