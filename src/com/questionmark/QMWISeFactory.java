package com.questionmark;

import org.apache.commons.pool.BasePoolableObjectFactory;

public class QMWISeFactory extends BasePoolableObjectFactory {

	static long nMakes=0;
	static long memMakes=0;
	
	public static String MemoryReport () {
		if (nMakes>0) {
			return "Average connection usage: "+new Double(memMakes/(nMakes*1024.0)).toString()+" KB";
		} else {
			return "Not enough data for connection pool memory report";
		}
	}
	
	@Override
	public Object makeObject() throws Exception {
		long m=Runtime.getRuntime().freeMemory();
		QMWise q=new QMWise();
		m=m-Runtime.getRuntime().freeMemory();
		if (m>0) {
			nMakes++;
			memMakes+=m;
		}
		return q;
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
