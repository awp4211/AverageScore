package com.parse;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * ���ð༶�ĳɼ��ļ��� ��list��ʽ ���룬����� �ð༶ �����˵��ۺϳɼ���
 * @author Administrator
 *
 */
public class AverageScore {
	private String XN;
	private String XQ;
	private ArrayList<String> XMLFileName;
	/**
	 * ���ǵ�����������ֱ��ʹ��static �����ʹ��static �ͱ��뿼�ǵ��������ڴ�����⡣ ��Ϊ���ϣ�ֱ��ʹ��new �µ��ڴ�
	 * @param XN
	 * @param XQ
	 * @param XMLFileName
	 */
	public AverageScore(String XN,String XQ,ArrayList<String> XMLFileName){
		this.XN = XN;
		this.XQ = XQ;
		this.XMLFileName = XMLFileName;
	}
	/**
	 * prase xml ,AverageScore
	 * @param XN	
	 * @param XQ	
	 * @param XMLFileName    xml's name
	 * @return  Through xn .xq and xh calculate averagescore.
	 * @throws Exception
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private   ArrayList getAverageScore() throws Exception {
        ArrayList<Float> averageScoreList = new ArrayList<Float>();
        
        for (String xmlFileName : XMLFileName) {//read list xml
			averageScoreList.add(getAverageScore(xmlFileName));
		}
		
		return averageScoreList;
	}
	
	private  float getAverageScore(String XMLFileName){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document document = null;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(XMLFileName);
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		NodeList nodeList = document.getElementsByTagName("Table");
		float cjSum = 0;// �ɼ�*ѧ�ֵ��ܺ�
		float xfSum = 0;// ѧ���ܺ�
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			NodeList childList = element.getChildNodes();
			float cj = 0;// �ɼ�*ѧ��
			float xf = 0;// ѧ��
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
						if (score.equals("����")) {
							cj = 90;
						} else if (score.equals("�е�")) {
							cj = 80;
						} else if (score.equals("����")) {
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

	
}
