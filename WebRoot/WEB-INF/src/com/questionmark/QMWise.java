package com.questionmark;

import com.questionmark.*;
import com.questionmark.QMWISe.*;
import java.net.URL;
import org.apache.axis.*;
import org.apache.axis.message.SOAPHeaderElement;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;
import javax.xml.soap.SOAPException;

public class QMWise {
	static private QMWISeSoapStub stub = null;

	public QMWise() throws Exception {
		try {
			connect();
		} catch (Exception e) {
			throw e;
		}
	}

	public QMWISeSoapStub getStub() {
		return stub;
	}

	static public void reset() {
		stub = null;
	}

	private void connect() throws Exception {
		if(stub == null) {
			try {
				PropertiesBean pb = new PropertiesBean();
				java.util.Properties p = pb.getProperties();
				String protocol = p.getProperty("perception.protocol");
				String host = p.getProperty("perception.host");
				String port = p.getProperty("perception.port");
				String directory = p.getProperty("perception.directory");
				String username = p.getProperty("perception.username");
				String checksum = p.getProperty("perception.checksum");
				String security = p.getProperty("perception.security");

				stub = new QMWISeSoapStub(
					new URL(protocol+host+":"+port+"/"+directory+"/qmwise.asmx"),
					new QMWISeLocator()
				);

				//set security header
				if(security != null) {
					stub.setHeader("http://questionmark.com/QMWISe/", "Security", null);
					stub.getHeader("http://questionmark.com/QMWISe/", "Security").addChild(
						new SOAPHeaderElement("http://questionmark.com/QMWISe/", "ClientID", username)
					);
					stub.getHeader("http://questionmark.com/QMWISe/", "Security").addChild(
						new SOAPHeaderElement("http://questionmark.com/QMWISe/", "Checksum", checksum)
					);
				}

				//get version information
				Version2 version = stub.getAbout2();
				int major=version.getMajorVersion();
				int minor=version.getMinorVersion();
				int build=version.getBuildVersion();
				if (major<4 || (major==4 && (minor<4 || (minor==4 && build<101)))) {
					throw new Exception("Your version of Perception is older than 4.4.101. You must be running Perception with at least service pack 1 to use the Blackboard connector.");
				}

				return;

			} catch(MalformedURLException e) {
				throw new Exception("The URL of the Perception server is invalid. Ensure the domain, port and path are correctly set.", e);
			} catch(AxisFault e) {
				throw new Exception(
					"There was a problem while connecting to the server. "
					+ new QMWiseException(e).getMessage()
					, e
				);
			} catch(SOAPException e) {
				throw new Exception("A Soap exception was caught: " + e, e);
			} catch(RemoteException e) {
				throw new Exception("A remote exception was caught: " + e, e);
			} catch(Exception e) {
				throw new Exception("An exception was caught: " + e, e);
			}
		}
	}
}
