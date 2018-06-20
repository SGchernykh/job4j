package ru.job4j.xml;
/**
 * SAXPars.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXPars extends DefaultHandler {
    private String temp = "";
    private List<Integer> list = new ArrayList<>();

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        int len = atts.getLength();
        for (int i = 0; i < len; i++) {
             list.add(new Integer(atts.getValue(i)));
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        temp = "";
    }

    /**
     * Get list data from the xml file.
     * @return List number.
     */
    public List<Integer> getList() {
        return list;
    }
}
