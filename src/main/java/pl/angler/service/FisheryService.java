package pl.angler.service;

import pl.angler.entity.Fishery;
import pl.angler.service.impl.FisheryServiceImpl;

import java.util.List;

public interface FisheryService {
    List<Fishery> getAllFisheriesByUser(String email);
}
