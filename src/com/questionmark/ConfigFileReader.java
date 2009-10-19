package com.questionmark;

import java.io.*;
import java.text.DateFormat;
import java.util.*;
import blackboard.platform.plugin.PlugInUtil;

public class ConfigFileReader {

	private Hashtable<String, Long> resultHash;
	private String courseID;

	/* constructor initialises the results Hashtable, and assigns the current 
	 * course ID to the courseID instance variable.
	 */

	public ConfigFileReader(String courseID) {
		resultHash = new Hashtable<String, Long>();
		this.courseID = courseID;
	}

	/* getCourseSyncDate() returns the last synchronization date for the current 
	 * courseID, or zero if the course has no entry in the schedule file, or if 
	 * the file doesn't exist.
	 */

	public Long getCourseSyncDate() {
		Long result;
		if(readConfigFile()) {
			Long timestamp = resultHash.get(courseID);
			if(timestamp != null)
				result = timestamp;
			else
				result = new Long(1);
		}
		else
			result = new Long(0);

		return result;
	}

	/* setCourseSyncDate() is a public method for accessing the private 
	 * "writeConfigFile()" method.
	 */

	public void setCourseSyncDate() {
		writeConfigFile();
	}

	/* readConfigFile() reads the contents of the scheduling file into a 
	 * hashtable, returning true if the file exists, and false if it doesn't.
	 */

	private boolean readConfigFile() {

		try {
			File dir = PlugInUtil.getConfigDirectory("qm", "qmpp");
			File configFile = new File(dir, "qmpp.schedule");

			if (!configFile.exists())
				return false;
			else {
				FileInputStream is = new FileInputStream(configFile);
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);

				String tempFileLine;
				while((tempFileLine = br.readLine()) != null) {
					String[] tempTokens = tempFileLine.split(":");
					resultHash.put(tempTokens[0], new Long(tempTokens[1]));
				}
				is.close();
			}
		}
		catch(Exception e) {
			System.out.println("PlugInUtil couldn't find the required Config directory.");
		}

		return true;
	}

	/* writeConfigFile() updates the resultHash with the current time for the 
	 * current courseID, and writes the Hashtable contents back to the sync 
	 * schedule config file.
	 */

	private void writeConfigFile() {
		if(resultHash.isEmpty())	// make sure our hashtable is up-to-date, so we don't overwrite current values
			readConfigFile();

		Long timestamp = new Date().getTime();
		resultHash.put(courseID, timestamp);	// update the timestamp for the current course

		try {
			File dir = PlugInUtil.getConfigDirectory("qm", "qmpp");
			File configFile = new File(dir, "qmpp.schedule");
			FileWriter fw = new FileWriter(configFile);
			BufferedWriter bw = new BufferedWriter(fw);

			Enumeration ctr = resultHash.keys();
			while(ctr.hasMoreElements()) {
				String tempKey = (String)ctr.nextElement();
				String tempFileLine = tempKey + ":" + resultHash.get(tempKey);
				bw.write(tempFileLine);
				bw.newLine();
			}

			bw.close();
			fw.close();
		}
		catch(Exception e) {
			System.out.println("PlugInUtil couldn't find the required Config directory.");
		}
	}
}
