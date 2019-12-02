package pl.angler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.angler.entity.Fish;
import pl.angler.repository.FishRepository;
import pl.angler.service.FishService;

import java.util.List;

@Service
public class FishServiceImpl implements FishService {

    @Autowired
    private FishRepository fishRepository;

    @Override
    public List<Fish> getAllFishes() {
        return this.fishRepository.findAll();
    }
}
