package com.parse;

public class Text {
	public static void main(String[] args) throws Exception {
		String XN = "2012-2013";	//学年
		String XQ = "1";	//学期
		//求平均成绩
		float averageScore = AverageScore.getSemesterAverageScore(XN, XQ);
		System.out.println(XN + "学年,第 " + XQ + "学期,平均成绩为：" + averageScore);
	}
}
