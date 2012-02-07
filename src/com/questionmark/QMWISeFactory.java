package com.questionmark;

import org.apache.commons.pool.BasePoolableObjectFactory;

public class QMWISeFactory extends BasePoolableObjectFactory {

	@Override
	public Object makeObject() throws Exception {
		return new QMWise();
	}
	
	
	@Override
	public boolean validateObject(Object obj) {
		QMWise q=(QMWise)obj;
		if (q.failMsg==null && q.timestamp.equals(PropertiesBean.idCache.get("timestamp"))) { 
			return true;
		} else {
			System.out.println(PropertiesBean.applicationHandle+": invalidating connection, "+(q.failMsg!=null?q.failMsg:"expired"));
			return false;
		}
				
	}

}
