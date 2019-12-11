package pl.angler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.angler.entity.Fishery;
import pl.angler.repository.FisheryRepository;
import pl.angler.service.FisheryService;

import java.util.List;

@Service
public class FisheryServiceImpl implements FisheryService {

    @Autowired
    private FisheryRepository fisheryRepository;

    @Override
    public List<Fishery> getAllFisheriesByUser(String email) {
        return this.fisheryRepository.findDistinctByUser_emailOrPrivateFisheryFalse(email);
    }
}
