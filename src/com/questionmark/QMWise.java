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
	
	public QMWise() {
		// We used to connect and check the version string straight away but this
		// just adds another SOAP round-trip to all pages unnecessarily
	}

	public QMWISeSoapStub getStub() throws QMWiseException {
		String failMsg = null;
		String url="";
		if (stub == null || PropertiesBean.idCache.get("qmwisestub")==null) {
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
				url=protocol+host+":"+port+"/"+directory+"/qmwise.asmx";
				
				stub = new QMWISeSoapStub(
						new URL(url),
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
				// This ensures QMWISe stub is shared across all pages using this node
				//	...until the next time the properties are reloaded from disk!
				PropertiesBean.idCache.put("qmwisestub", "");
				}
			} catch (AxisFault e) {
				failMsg=e.getMessage();
			} catch (SOAPException e) {
				failMsg=e.getMessage();
			} catch (MalformedURLException e) {
				failMsg="QMWISe URL is badly formed: "+url;
			}
			if (failMsg != null) {
				throw new QMWiseException(failMsg);
			}
		}
		return stub;
	}

	static public void reset() {
		stub = null;
	}

	public Version2 getVersion() throws QMWiseException {
		Version2 version = null;
		try {
			QMWISeSoapStub q=getStub();
			//get version information
			version = q.getAbout2();
			int major=version.getMajorVersion();
			int minor=version.getMinorVersion();
			int build=version.getBuildVersion();
			if (major<4 || (major==4 && (minor<4 || (minor==4 && build<101)))) {
				throw new QMWiseException("Perception version too old ("+version.getBuildString()+".  Perception 4.4 SP1 or higher required (4.4.101)");
			}
		} catch (RemoteException e) {
			throw new QMWiseException(e);
		}
		return version;		
	}
	
	private void connect() throws QMWiseException {
		QMWISeSoapStub q=getStub();
	}
}
