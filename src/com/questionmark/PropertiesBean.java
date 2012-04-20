package com.questionmark;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import blackboard.platform.plugin.PlugInException;
import blackboard.platform.plugin.PlugInUtil;

public class PropertiesBean  {

//	private static PropertiesBean propertiesBean;
	public static String vendorId = "qm";
	public static String applicationHandle = "qmpp";
	private static String propertiesFilename = "qmpp.properties";
	// constants for getting and setting properties
	public static final String protocol_key = "perception.protocol";
	public static final String host_key = "perception.host";
	public static final String port_key = "perception.port";
	public static final String directory_key = "perception.directory";
	public static final String security_key = "perception.security";
	public static final String username_key = "perception.username";
	public static final String checksum_key = "perception.checksum";
	public static final String version_key = "perception.version";
	public static final String syncusers_key = "perception.syncusers";
	public static final String syncgroups_key = "perception.syncgroups";
	public static final String syncmembers_key = "perception.syncmembers";
	public static final String syncfolders_key = "perception.syncfolders";
	public static final String accesslink_key = "perception.accesslink";
	public static final String syncperiod_key = "perception.syncperiod";
	public static final String singlesignon_key = "perception.singlesignon";
	public static final String logging_key="perception.logging";
	public static final String oldassessmentlist_key="perception.oldassessmentlist";	
	private Properties p=null;
	private static Object pCacheLock=new Object();
	private static Properties pCache=null;
	private static long pCacheTime=0;
	public static ConcurrentHashMap<String,String> idCache=new ConcurrentHashMap<String,String>(100);

	public PropertiesBean() {
		p = this.getProperties();
	}

	
	public static void SetDefaults(Properties pNew) {
		if (pNew != null) {
			pNew.setProperty(protocol_key,"https://");
			pNew.setProperty(host_key,"ondemand.questionmark.com");
			pNew.setProperty(port_key,"443");
			pNew.setProperty(directory_key,"qmwise/123456");
			pNew.setProperty(security_key,"Yes");
			pNew.setProperty(username_key,"Manager");
			pNew.setProperty(checksum_key,"434de524caad1f0bd4983c4cbf0cd0e9");
			pNew.setProperty(version_key,"1");			
		}
	}
	
	public void setProperties( HttpServletRequest request ) {
		Enumeration pNames = request.getParameterNames();
		p = new Properties();
		for ( ; pNames.hasMoreElements(); ) {
			String name = (String) pNames.nextElement();
			String value = request.getParameter( name );
			p.setProperty( name, value );
		writeProperties();
		}
	}

	private void writeProperties() {
		File dir = null;
		try {
			dir = PlugInUtil.getConfigDirectory( vendorId, applicationHandle );
			File configFile = new File( dir, propertiesFilename );
			FileOutputStream fos = null;
			if ( !configFile.exists() )
				configFile.createNewFile();
			fos = new FileOutputStream( configFile );
			p.store( fos, "#QMPP Properties File" );
			fos.close();
		} catch (PlugInException e) {
			System.out.println(applicationHandle+": Unexpected PlugInException: "+e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(applicationHandle+": Can't find properties file"+e.getMessage());
		} catch (IOException e) {
			System.out.println(applicationHandle+": Unexpected IOException: "+e.getMessage());
		}
	}

//	public static PropertiesBean getInstance() {
//		if ( propertiesBean == null ) {
//			propertiesBean = new PropertiesBean();
//		}
//		System.out.println(applicationHandle+": PropertiesManager getInstance() Initialized" );
//		return propertiesBean;
//	}

	public Properties getProperties() {
		File _dir = null;
		FileInputStream _in = null ;

		Properties _props = null;
		try {
			_props = new Properties();
			_dir = PlugInUtil.getConfigDirectory( vendorId, applicationHandle );
			File _configFile = new File( _dir, propertiesFilename );
			if ( !_configFile.exists() )
				throw new FileNotFoundException();
			synchronized (pCacheLock) {
				if (pCache!=null && _configFile.lastModified()>pCacheTime)
					pCache=null;
				if (pCache==null) {
					_in = new FileInputStream(_configFile);
					_props.load( _in );
					_in.close();
					pCache=_props;
					pCacheTime=_configFile.lastModified();
					idCache.clear();
					idCache.put("timestamp",new Long(pCacheTime).toString());
				} else
					_props=pCache;
			}
		} catch (PlugInException e) {
			System.out.println(applicationHandle+": Unexpected PlugInException: "+e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(applicationHandle+": Questionmark Connector: missing properties file, assuming first run");
			SetDefaults(_props);
		} catch (IOException e) {
			System.out.println(applicationHandle+": Unexpected IOException: "+e.getMessage());
		}
		return _props;
	}

	public String getProperty(String propertyName) {
		String _prop = "";
		if (p != null)
			_prop = p.getProperty(propertyName);
		return _prop;
	}

	public void setProperty(String key, String value) {
		p.setProperty(key, value);
		this.writeProperties();
	}
}
