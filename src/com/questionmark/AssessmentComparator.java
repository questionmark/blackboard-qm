package com.questionmark;

import java.util.*;
import com.questionmark.QMWISe.*;

public class AssessmentComparator implements Comparator<Object> {
	public int compare(Object a, Object b) {
		return ((Assessment) a).getSession_Name().compareTo(((Assessment) b)
				.getSession_Name()) ;
	}
	public boolean equals(Object a, Object b) {
		return ((Assessment)a).getSession_Name().equals(((Assessment)b)
				.getSession_Name());
	}
}

