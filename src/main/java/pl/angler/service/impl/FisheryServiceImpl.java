package pl.angler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.angler.dto.FisheryDto;
import pl.angler.repository.FisheryRepository;
import pl.angler.service.FisheryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FisheryServiceImpl implements FisheryService {

    @Autowired
    private FisheryRepository fisheryRepository;

    @Override
    public List<FisheryDto> getAllUserFisheries(String email) {
        return this.fisheryRepository.findDistinctByUser_emailOrPrivateFisheryFalse(email).stream().map(fishery -> new FisheryDto(
                fishery.getId(),
                fishery.getName(),
                fishery.getAltitude(),
                fishery.getLatitude(),
                fishery.getDescription()
        )).collect(Collectors.toList());
    }

    @Override
    public List<FisheryDto> getAllPublicFisheries(String userNick) {
        return this.fisheryRepository.findByUser_nickAndPrivateFisheryFalse(userNick).stream().map(fishery -> new FisheryDto(
                fishery.getId(),
                fishery.getName(),
                fishery.getAltitude(),
                fishery.getLatitude(),
                fishery.getDescription()
        )).collect(Collectors.toList());
    }
}
