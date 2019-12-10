package pl.angler.service;

import org.xml.sax.SAXException;
import pl.angler.entity.Lake;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface LakeService {

    void downloadFishery() throws IOException, SAXException, ParserConfigurationException;
    List<Lake> getAllLakes();
}
