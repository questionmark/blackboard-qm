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

public class PropertiesBean implements java.io.Serializable {

//	private static PropertiesBean propertiesBean;
	private static String vendorId = "qm";
	private static String applicationHandle = "qmpp";
	private static String propertiesFilename = "qmpp.properties";
	private Properties p=null;
	private static Object pCacheLock=new Object();
	private static Properties pCache=null;
	private static long pCacheTime=0;
	public static ConcurrentHashMap<String,String> idCache=new ConcurrentHashMap<String,String>(100);
	public PropertiesBean() {
		p = this.getProperties();
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
			System.out.println("Unexpected PlugInException: "+e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("Can't find properties file"+e.getMessage());
		} catch (IOException e) {
			System.out.println("Unexpected IOException: "+e.getMessage());
		}
	}

//	public static PropertiesBean getInstance() {
//		if ( propertiesBean == null ) {
//			propertiesBean = new PropertiesBean();
//		}
//		System.out.println( "PropertiesManager getInstance() Initialized" );
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
				} else
					_props=pCache;
			}
		} catch (PlugInException e) {
			System.out.println("Unexpected PlugInException: "+e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("Questionmark Connector: missing properties file, assuming first run");
		} catch (IOException e) {
			System.out.println("Unexpected IOException: "+e.getMessage());
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
