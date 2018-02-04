package com.epam.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Epam Automation - Utilities
 * @author Raghunandan Beepyata
 * 
 */

public class PropertyUtil{
	private static Logger logger = LogManager.getLogger();
	
	static Properties props = new Properties();
	String strFileName;
	String strValue;

	public String getProperty(String strKey) {
		try {
			File f = new File(strFileName);
			if (f.exists()) {
				FileInputStream in = new FileInputStream(f);
				props.load(in);
				strValue = props.getProperty(strKey);
				in.close();
			} else
				logger.error("File not found!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strValue;
	}

	public void setProperty(String strKey, String strValue) throws Throwable {
		try {
			File f = new File(strFileName);
			if (f.exists()) {
				FileInputStream in = new FileInputStream(f);
				props.load(in);
				props.setProperty(strKey, strValue);
				props.store(new FileOutputStream(strFileName), null);
				in.close();
			} else {
				logger.error("File not found!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeProperty(String strKey) {
		try {
			File f = new File(strFileName);
			if (f.exists()) {
				FileInputStream in = new FileInputStream(f);
				props.load(in);
				props.remove(strKey);
				props.store(new FileOutputStream(strFileName), null);
				in.close();
			} else
				logger.error("File not found!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public PropertyUtil(String strFileName) {
		this.strFileName = strFileName;
	}


	public static String getHostName() throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		String hostname = addr.getHostName();

		return hostname;
	}
}
