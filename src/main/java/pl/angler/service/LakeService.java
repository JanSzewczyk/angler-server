package pl.angler.service;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface LakeService {
    void downloadFishery() throws IOException, SAXException, ParserConfigurationException;
}
