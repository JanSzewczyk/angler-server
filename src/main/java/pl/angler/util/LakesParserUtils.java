package pl.angler.util;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import pl.angler.entity.Lake;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class LakesParserUtils {

    public List<Lake> parseXmlToObjects(String fisheryData) throws ParserConfigurationException, IOException, SAXException {

        List<Lake> lakes = new ArrayList<>();

        // Create xml object
        Document document = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new InputSource(new StringReader(fisheryData)));

        document.getDocumentElement().normalize();

        NodeList provinceList = document.getElementsByTagName("Folder");

        for (int temp = 0; temp < provinceList.getLength(); temp++) {
            // Province newProvince = new
            Node provinceNode = provinceList.item(temp);

            if (provinceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element provinceElement = (Element) provinceNode;

                NodeList lakeNodeList = provinceElement.getElementsByTagName("Placemark");
                for (int j = 0; j < lakeNodeList.getLength(); j++) {

                    Node leakNode = lakeNodeList.item(j);
                    if (leakNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element lakeElement = (Element) leakNode;

                        String[] coordinates = lakeElement.getElementsByTagName("coordinates").item(0).getTextContent().split(",");
                        lakes.add(this.mapToObjectLake(lakeElement.getElementsByTagName("name").item(0).getTextContent(), coordinates[0], coordinates[1]));
                    }
                }
            }
        }

        return lakes;
    }

    private Lake mapToObjectLake(String name, String alt, String lat) {
        Lake lake = new Lake();

        Double altitude = Double.parseDouble(alt);
        Double latitude = Double.parseDouble(lat);

        lake.setName(name);
        lake.setAltitude(altitude);
        lake.setLatitude(latitude);

        return lake;
    }
}
