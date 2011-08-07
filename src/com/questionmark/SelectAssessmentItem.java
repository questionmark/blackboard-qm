package com.questionmark;

import java.util.Comparator;

import org.apache.commons.lang.StringEscapeUtils;


public class SelectAssessmentItem {

	public String label = null;
	public String assessmentID = null;
	public SelectAssessmentItem[] children = null;
	
	public SelectAssessmentItem(String name, String assessmentID) {
		label=name;
		this.assessmentID=assessmentID;
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder(1024); // 1K chunk size
		if (children == null) {
			sb.append("<option value=\"");
			sb.append(StringEscapeUtils.escapeHtml(assessmentID));
			sb.append("\">");
			sb.append(StringEscapeUtils.escapeHtml(label));
			sb.append("</option>\n");
		} else {
			sb.append("<optgroup label=\"");
			sb.append(StringEscapeUtils.escapeHtml(label));
			sb.append("\">\n");
			for (SelectAssessmentItem child: children) {
				sb.append(child.toString());
			}
			sb.append("</optgroup>\n");
		}
		return sb.toString();
	}

	
	public static class SortComparator implements Comparator<SelectAssessmentItem> {
		public int compare(SelectAssessmentItem a, SelectAssessmentItem b) {
			return a.label.compareTo(b.label);
		}

		public boolean equals(SelectAssessmentItem a, SelectAssessmentItem b) {
			return a.label.equals(b.label);
		}
	}
	
}
