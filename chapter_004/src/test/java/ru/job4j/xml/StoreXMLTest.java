package ru.job4j.xml;
/**
 * StoreXMLTest.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreXMLTest {
/*    @Test
    public void whenSaveReadNumbersFromXmlConvertAndSumNumbers() throws SQLException, JAXBException, TransformerException, IOException, SAXException, ParserConfigurationException {
        StoreSQL sql = new StoreSQL();
        StoreXML xml = new StoreXML(new File("C:\\projects\\job4j\\chapter_004\\c4654.xml"));
        xml.save(sql.getList());
        xml.convert(new File("C:\\projects\\job4j\\chapter_004\\sasdf.xml"), new File("C:\\projects\\job4j\\chapter_004\\file.xml"), new File("C:\\projects\\job4j\\chapter_004\\2.xml"));
        System.out.println();
        assertThat(xml.sum(new File("C:\\projects\\job4j\\chapter_004\\2.xml")), is(6));
    }
*/
}