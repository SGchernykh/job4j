package ru.job4j.xml;

/**
 * StoreXML.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreXML {
    private final File file;

    /**
     * Constructor.
     * @param file
     */
    public StoreXML(File file) {
        this.file = file;
    }

    /**
     * Save data list in xml file.
     * @param list List data DB.
     * @throws JAXBException
     */
    public void save(List<Integer> list) throws JAXBException {
        List<Field> temp = new ArrayList<>();
        for (Integer num : list) {
            temp.add(new Field(num));
        }
        JAXBContext jaxbContext = JAXBContext.newInstance(Entrys.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(new Entrys(temp), this.file);
    }

    /**
     * Convert xml file in xsl format.
     * @param xmlFile Xml file.
     * @param xslFile Xsl format file.
     * @param result File in which reserveю
     * @throws TransformerException
     */
    public void convert(File xmlFile, File xslFile, File result) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(xslFile);
        Transformer transformer = factory.newTransformer(xslt);
        Source text = new StreamSource(xmlFile);
        transformer.transform(text, new StreamResult(result));
    }

    /**
     * Sum Element xml file.
     * @param xml Xml file.
     * @return Sum.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public Integer sum(File xml) throws ParserConfigurationException, SAXException, IOException {
        int sum = 0;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SAXPars saxp = new SAXPars();
        parser.parse(xml, saxp);
        for (Integer it : saxp.getList()) {
            sum += it;
        }
        return sum;
    }

    /**
     * Class Field designed for transportation
     */
    @XmlRootElement
    private static class Field {
        private int field;

        public Field() {
        }

        public Field(int field) {
            this.field = field;
        }

        public int getField() {
            return field;
        }

        public void setField(int field) {
            this.field = field;
        }
    }

    /**
     * Class Entrys designed for transportation
     */
    @XmlRootElement
    private static class Entrys {
        private List<Field> entry;

        public Entrys() {
        }

        public Entrys(List<Field> entry) {
            this.entry = entry;
        }

        public List<Field> getEntry() {
            return entry;
        }

        public void setEntry(List<Field> entry) {
            this.entry = entry;
        }
    }
}
