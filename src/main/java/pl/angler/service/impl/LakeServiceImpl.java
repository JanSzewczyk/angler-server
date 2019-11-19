package pl.angler.service.impl;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import pl.angler.entity.Lake;
import pl.angler.exception.ServerErrorException;
import pl.angler.repository.LakeRepository;
import pl.angler.service.LakeService;
import pl.angler.util.LakeUtils;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.List;

@Service
public class LakeServiceImpl implements LakeService {

    @Autowired
    private LakeUtils lakeUtils;

    @Autowired
    private LakeRepository lakeRepository;

    private CloseableHttpClient httpClient;

    @Scheduled(fixedDelayString = "66000")
    @Override
    public void downloadFishery() throws IOException, SAXException, ParserConfigurationException {
        String fisheryData;

        try {
            fisheryData = this.getFishery();
            saveOrUpdateFishery(this.lakeUtils.parseXmlToObjects(fisheryData));
        } catch (IOException e) {
            throw new ServerErrorException(e.getMessage());
        }

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
            //System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            //System.out.println(headers);

            return EntityUtils.toString(entity);
        }
    }

    private void saveOrUpdateFishery(List<Lake> lakes) {
        System.out.println(lakes);

//        this.fisheryRepository.deleteAll();
//
//        this.fisheryRepository.saveAll(fisheryList);
    }
}
