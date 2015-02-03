package com.parse;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AverageScore {

	/**
	 * 解析XML文件，求平均成绩
	 * @param XN	学年
	 * @param XQ	学期
	 * @return
	 * @throws Exception
	 */
	public static float getAverageScore(String XN, String XQ) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse("src/data.xml");
		NodeList nodeList = document.getElementsByTagName("Table");
		float cjSum = 0;// 成绩*学分的总和
		float xfSum = 0;// 学分总和
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			NodeList childList = element.getChildNodes();
			float cj = 0;// 成绩*学分
			float xf = 0;// 学分
			for (int j = 0; j < childList.getLength(); j++) {
				Node node = childList.item(j);
				if (node.getNodeName().trim().equals("XN")
						&& (node.getTextContent().trim().equals(XN) == false)) {
					break;
				}
				if (node.getNodeName().trim().equals("XQ")
						&& (node.getTextContent().trim().equals(XQ) == false)) {
					break;
				}
				if (node.getNodeName().equals("#text")) {
					continue;
				} 
				if (node.getNodeName().trim().equals("XF")) {
					xf = Float.parseFloat(node.getTextContent().trim());
				}
				if (node.getNodeName().trim().equals("CJ")) {
					try {
						cj = Integer.parseInt(node.getTextContent().trim());
					} catch (Exception e) {
						String score = node.getTextContent().trim();
						if (score.equals("优秀")) {
							cj = 90;
						} else if (score.equals("中等")) {
							cj = 80;
						} else if (score.equals("良好")) {
							cj = 60;
						}
					}
				}

			}
			cjSum = cjSum + cj * xf;
			xfSum = xfSum + xf;
		}
		return cjSum / xfSum;
	}

	/**
	 * @param XN	学年
	 * @param XQ	续期
	 * @return	平均成绩
	 * @throws Exception
	 */
	public static float getSemesterAverageScore(String XN, String XQ)
			throws Exception {
		return getAverageScore(XN, XQ);
	}
}
