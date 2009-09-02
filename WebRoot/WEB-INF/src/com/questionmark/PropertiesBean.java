package com.questionmark;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import blackboard.platform.plugin.PlugInUtil;

public class PropertiesBean implements java.io.Serializable {

	private static PropertiesBean propertiesBean;
	private static String vendorId = "qm";
	private static String applicationHandle = "qmpp";
	private static String propertiesFilename = "qmpp.properties";
	private Properties p;

	public PropertiesBean() {
	}

	public void setProperties( HttpServletRequest request ) throws Exception {
		File dir = null;
		Enumeration pNames = request.getParameterNames();
		try {
			dir = PlugInUtil.getConfigDirectory( vendorId, applicationHandle );
			File configFile = new File( dir, propertiesFilename );
			FileOutputStream fos = null;
			if ( !configFile.exists() )
				configFile.createNewFile();
			try {
				fos = new FileOutputStream( configFile );
			}
			catch(FileNotFoundException e) {
				System.out.println("Cant find properties file");
			}
			Properties prop = new Properties();
			for ( ; pNames.hasMoreElements(); ) {
				String name = (String) pNames.nextElement();
				String value = request.getParameter( name );
				prop.setProperty( name, value );

			}
			prop.store( fos, "#QMPP Properties File" );
			fos.close();

		}
		catch ( Exception e ) {
		}
	}

	private void writeProperties( Properties props ) throws Exception {
		File dir = null;
		try {
			dir = PlugInUtil.getConfigDirectory( vendorId, applicationHandle );
			File configFile = new File( dir, propertiesFilename );
			FileOutputStream fos = null;
			if ( !configFile.exists() )
				configFile.createNewFile();
			fos = new FileOutputStream( configFile );
			props.store( fos, "#QMPP Properties File" );
			fos.close();
		}
		catch ( Exception e ) {
		}
	}

	public static PropertiesBean getInstance() {
		if ( propertiesBean == null ) {
			propertiesBean = new PropertiesBean();
		}
		System.out.println( "PropertiesManager getInstance() Initialized" );
		return propertiesBean;
	}

	public Properties getProperties() throws Exception {
		File _dir = null;
		FileInputStream _in = null ;

		Properties _props = null;
		try {
			_dir = PlugInUtil.getConfigDirectory( vendorId, applicationHandle );
			File _configFile = new File( _dir, propertiesFilename );
			if ( !_configFile.exists() )
				throw new FileNotFoundException();
			else
				_in = new FileInputStream(_configFile);

			_props = new Properties();
			_props.load( _in );
			_in.close();
		}
		catch ( Exception e ) {
			_props = new Properties();
		}
		p = _props;
		return _props;
	}

	public String getProperty(String propertyName) {
		String _prop = "";

		try {
			Properties p = this.getProperties();
			_prop = p.getProperty(propertyName);
		}
		catch (Exception e) {
			System.out.println(e);
			_prop = "";
		}
		return _prop;
	}

	public void setProperty(String key, String value)
		throws Exception {
		p.setProperty(key, value);
		this.writeProperties(p);
	}
}
