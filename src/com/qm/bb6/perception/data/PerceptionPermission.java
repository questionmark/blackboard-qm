/*
 * PerceptionPermission.java
 *
 * Created on February 16, 2007, 4:18 PM
 *
 * Copyright 2006 VLE Genius Ltd. All rights reserved.
 */

package com.qm.bb6.perception.data;

/**
 *
 * @author Matt Elton
 */
public class PerceptionPermission {
	
	public static final int NO_ACCESS = 0;
	public static final int VIEW_ACCESS = 1;
	public static final int EDIT_ACCESS = 2;
	public static final int FULL_ACCESS = 3;
	
	private int permissions = NO_ACCESS;
	
	public int getPermissions(){
		return this.permissions;
	}
		
	public void setPermissions( int value ){
		if( value >= NO_ACCESS && value <= FULL_ACCESS )
			this.permissions = value;
	}
	
}
