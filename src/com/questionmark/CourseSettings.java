package com.questionmark;

import java.io.*;
import java.util.*;
import blackboard.platform.plugin.*;

public class CourseSettings extends Properties {

	private static String vendorId = "qm";
	private static String applicationHandle = "qmpp";
	private static String settingsSuffix = ".settings";
	private static String versionKey = "version";
	private static String courseIDKey = "courseID";
	private static String settingsFileHeader = "Questionmark Perception Connector course settings file";
	
	private File settingsFile = null;
	private String courseID;

	public CourseSettings(String courseID) throws PlugInException {
		File dir = PlugInUtil.getConfigDirectory(vendorId,applicationHandle);
		settingsFile = new File(dir,courseID+settingsSuffix);
		this.courseID = courseID;
		if (settingsFile.exists()) {
			loadSettingsFile();
		}
		else {
			// if there are no settings, prime our settings file with a version for future compatibility
			setProperty(versionKey,"1");
			// and store the course ID for information
			setProperty(courseIDKey,courseID);
		}	
	}

	private void loadSettingsFile() {
		try {
			FileInputStream is = new FileInputStream(settingsFile);
			load(is);
			is.close();
		}
		catch(IOException e) {
			System.out.println("Couldn't load qmp course settings from "+settingsFile.toString()+" due to an IOException");
		}
	}

	
	public void saveSettingsFile() {
		try {
			FileOutputStream os = new FileOutputStream(settingsFile);
			store(os,settingsFileHeader);
			os.close();	
		}
		catch(IOException e) {
			System.out.println("Couldn't save qmp course settings to "+settingsFile.toString()+" due to an IOException");
		}
	}
	
	
}
