package com.parse;

public class Text {
	public static void main(String[] args) throws Exception {
		String XN = "2012-2013";	//ѧ��
		String XQ = "1";	//ѧ��
		//��ƽ���ɼ�
		float averageScore = AverageScore.getSemesterAverageScore(XN, XQ);
		System.out.println(XN + "ѧ��,�� " + XQ + "ѧ��,ƽ���ɼ�Ϊ��" + averageScore);
	}
}
