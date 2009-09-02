package com.questionmark;

import com.questionmark.QMWISe.*;
import java.net.URL;
import org.apache.axis.*;
import org.apache.axis.message.SOAPHeaderElement;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;
import javax.xml.soap.SOAPException;

public class QMWiseException extends AxisFault {
	private String qmmessage = null;
	private Integer qmerrorcode = null;

	public QMWiseException(Exception e) {
		if(e instanceof AxisFault) {
			try {
				qmerrorcode = Integer.parseInt(((AxisFault) e).lookupFaultDetail(new QName("error")).getFirstChild().getNodeValue());
				qmmessage =
					"QMWise threw error "
					+ qmerrorcode + ": "
					+ ((AxisFault) e).lookupFaultDetail(new QName("message")).getFirstChild().getNodeValue()
				;
			} catch(NullPointerException npe) {
				qmmessage = e.getMessage();
			}
		} else {
			qmmessage = e.getMessage();
		}
	}

	public String getMessage() {
		return qmmessage;
	}

	public Integer getQMErrorCode() {
		return qmerrorcode;
	}
}
