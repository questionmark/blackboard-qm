package com.questionmark;

import java.util.*;
import java.text.*;
import com.questionmark.*;
import com.questionmark.QMWISe.*;

public class ResultComparator implements Comparator {
	public int compare(Object a, Object b) {
		return -latest((Result) a).compareTo(latest((Result) b));
	}
	public boolean equals(Object a, Object b) {
		return latest((Result) a).equals(latest((Result) b));
	}

	private Date latest(Result r) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date latest;
		try {
			latest = df.parse(r.getWhen_Started());
			if(!r.isStill_Going()) {
				latest = df.parse(r.getWhen_Finished());
			}
			return latest;
		} catch(ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
