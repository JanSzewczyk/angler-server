package pl.angler.service.impl;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import pl.angler.exception.ServerErrorException;
import pl.angler.service.FisheryService;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

@Service
public class FisheryServiceImpl implements FisheryService  {

    private CloseableHttpClient httpClient;

    @Scheduled(fixedDelayString = "66000")
    @Override
    public void downloadFishery() throws IOException, SAXException, ParserConfigurationException {
        String fisheryData;
        System.out.println("START download fishery");

        try {
            fisheryData = this.getFishery();
        } catch (IOException e) {
            throw new ServerErrorException(e.getMessage());
        }

       // System.out.println(fisheryData);
        this.getFisheryData(fisheryData);

    }

    private String getFishery() throws IOException {
        this.httpClient = HttpClients.createDefault();

        try {
            return this.sendGetRequest();
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        } finally {
            this.httpClient.close();
        }
    }

    private String sendGetRequest() throws Exception {
        String uri = "http://www.google.com/maps/d/kml?forcekml=1&mid=1YgHOx5i8E3r6GvuhFalTnCUk4Rw";
        HttpGet request = new HttpGet(uri);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            return EntityUtils.toString(entity);
        }
    }

    private void getFisheryData(String fisheryData) throws ParserConfigurationException, IOException, SAXException {
        Document document = DocumentBuilderFactory.newInstance()
                                                .newDocumentBuilder()
                                                .parse(new InputSource(new StringReader(fisheryData)));

//
//        System.out.println(fisheryNodes);
//
//        if (fisheryNodes.getLength() > 0) {
//            System.out.println("fisheryNodes :" + fisheryNodes.getLength());
////            Element err = (Element) fisheryNodes.item(0);
////            System.out.println(err.getElementsByTagName("Name")
////                    .item(0)
////                    .getTextContent());
//        }

        document.getDocumentElement().normalize();
        System.out.println("Root element :" + document.getDocumentElement().getNodeName());
        System.out.println("----------------------------");

        NodeList fisheryNode = document.getElementsByTagName("Folder");

//        Node nNode = nList.item(temp);
//
//        System.out.println("\nCurrent Element :" + nNode.getNodeName());
        for (int temp = 0; temp < fisheryNode.getLength(); temp++) {

            Node nNode = fisheryNode.item(temp);

            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                System.out.println("Name : " + eElement.getElementsByTagName("name").item(0).getTextContent());

            }
        }
    }
}
