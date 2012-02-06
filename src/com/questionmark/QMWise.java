package com.questionmark;

import com.questionmark.QMWISe.*;
import java.net.URL;
import org.apache.axis.*;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.StackObjectPool;

import java.rmi.RemoteException;
import java.net.MalformedURLException;
import javax.xml.soap.SOAPException;

public class QMWise {
	
	public static final ObjectPool pool = new StackObjectPool(new QMWISeFactory());
	
	public static QMWise connect() throws QMWiseException {
		QMWise q=null;
		try {
			System.out.println("Pool info: active="+pool.getNumActive()+"; idle="+pool.getNumIdle());
			q=(QMWise) pool.borrowObject();
		} catch (Exception e) {
			// nasty to catch everything, but needs must
			throw new QMWiseException(e);
		}
		if (q.failMsg==null)
			return q;
		else
			throw new QMWiseException(q.failMsg);
	}

	public static void close(QMWise q) {
		if (q!=null) {
			try {
				if (q.failMsg!=null ||  !q.timestamp.equals(PropertiesBean.idCache.get("timestamp")))
					pool.invalidateObject(q);
				else
					pool.returnObject(q);
			} catch (Exception e) {
				;
			}
		}
	}
	
	public String failMsg = null;
	public QMWISeSoapStub stub = null;
	public final String timestamp = PropertiesBean.idCache.get("timestamp");;
	
	public QMWise() {
		String url="";
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
			}
		} catch (AxisFault e) {
			failMsg=e.getMessage();
		} catch (SOAPException e) {
			failMsg=e.getMessage();
		} catch (MalformedURLException e) {
			failMsg="QMWISe URL is badly formed: "+url;
		}
	}

	
	public Version2 getVersion() throws QMWiseException {
		Version2 version = null;
		try {
			//get version information
			version = stub.getAbout2();
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
	
}
