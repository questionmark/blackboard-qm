package com.questionmark;

import org.apache.commons.pool.BasePoolableObjectFactory;

public class QMWISeFactory extends BasePoolableObjectFactory {

	@Override
	public Object makeObject() throws Exception {
		return new QMWise();
	}

}
