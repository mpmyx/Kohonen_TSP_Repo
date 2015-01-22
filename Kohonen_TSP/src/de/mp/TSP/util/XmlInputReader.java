/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.mp.TSP.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import de.mp.TSP.domain.clustering.TownCluster;
import de.mp.TSP.domain.positions.Town;

/**
 * 
 * @author mp
 */
public class XmlInputReader

{
	private static XmlInputReader instance = null;

	private XmlInputReader() {
		// def. const.
	}

	public static XmlInputReader getInstance() {
		if (instance == null) {
			instance = new XmlInputReader();
		}
		return instance;
	}

	public void readXMLFile(File inputFile) 
	{
		Initializer.getInstance().reset();
		Document document = null;
		SAXBuilder builder = new SAXBuilder();
		try 
		{
			document = builder.build(inputFile);
			Element rootElement = document.getRootElement();
			List<?> cities = rootElement.getChildren();
			Iterator<?> cityIterator = cities.iterator();
			while ( cityIterator.hasNext() )
			{
				Element e = (Element) cityIterator.next();
				TownCluster.getInstance().getCluster().add(
						new Town((Integer.parseInt(e.getAttributeValue("X"))+100), 
								  Integer.parseInt(e.getAttributeValue("Y"))+100));
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
