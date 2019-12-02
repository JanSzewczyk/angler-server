package pl.angler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.angler.dto.TrophyDto;
import pl.angler.entity.Fish;
import pl.angler.entity.FishingTrip;
import pl.angler.entity.Trophy;
import pl.angler.exception.NotFoundException;
import pl.angler.repository.FishRepository;
import pl.angler.repository.FishingTripRepository;
import pl.angler.repository.TrophyRepository;
import pl.angler.service.TrophyService;

import java.util.Optional;

@Service
public class TrophyServiceImpl implements TrophyService {

    @Autowired
    private TrophyRepository trophyRepository;
    @Autowired
    private FishingTripRepository fishingTripRepository;
    @Autowired
    private FishRepository fishRepository;

    @Override
    public void addNewTrophy(TrophyDto newTrophy) {
        Optional<Fish> findFish = this.fishRepository.findById(newTrophy.getFishId());
        Optional<FishingTrip> findTrip = this.fishingTripRepository.findById(newTrophy.getTripId());
        if (!findFish.isPresent())
            throw new NotFoundException("Problem finding resources.");

        if (!findTrip.isPresent())
            throw new NotFoundException("Problem finding resources.");

        this.trophyRepository.save(new Trophy(null, findFish.get(), findTrip.get(), newTrophy.getLength(), newTrophy.getWeight(), newTrophy.getTime()));
    }

    @Override
    public void updateTrophy(TrophyDto trophyDto) {
        Optional<Trophy> findTrophy = this.trophyRepository.findById(trophyDto.getId());
        if (!findTrophy.isPresent())
            throw new NotFoundException("Trophy not exists.");
        Optional<Fish> findFish = this.fishRepository.findById(trophyDto.getFishId());
        if (!findFish.isPresent())
            throw new NotFoundException("Problem finding resources.");

        Trophy trophy = findTrophy.get();
        trophy.setLength(trophyDto.getLength());
        trophy.setTime(trophyDto.getTime());
        trophy.setWeight(trophyDto.getWeight());
        trophy.setFish(findFish.get());

        this.trophyRepository.save(trophy);
    }

    @Override
    public void deleteTrophy(Long id) {
        this.trophyRepository.deleteById(id);
    }
}
