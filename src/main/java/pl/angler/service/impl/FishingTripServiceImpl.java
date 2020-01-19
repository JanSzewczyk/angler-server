package pl.angler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.angler.dto.FisheryDto;
import pl.angler.dto.FishingTripDto;
import pl.angler.entity.Fishery;
import pl.angler.entity.FishingTrip;
import pl.angler.entity.User;
import pl.angler.exception.NotFoundException;
import pl.angler.repository.FisheryRepository;
import pl.angler.repository.FishingTripRepository;
import pl.angler.repository.UserRepository;
import pl.angler.service.FishingTripService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FishingTripServiceImpl implements FishingTripService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FishingTripRepository fishingTripRepository;
    @Autowired
    private FisheryRepository fisheryRepository;

    @Override
    public List<FishingTripDto> getTrips(String email) {
        return this.fishingTripRepository.findByUser_emailOrderByTripDateDesc(email)
                .stream()
                .map(fishingTrip -> {
                    Fishery fishery = fishingTrip.getFishery();
                    return new FishingTripDto(
                            fishingTrip.getId(),
                            fishingTrip.getTitle(),
                            fishingTrip.getTripDate(),
                            fishingTrip.getDescription(),
                            new FisheryDto(
                                    fishery.getId(),
                                    fishery.getName(),
                                    fishery.getAltitude(),
                                    fishery.getLatitude(),
                                    fishery.getDescription(),
                                    fishery.getPost() != null
                            ),
                            null,
                            fishingTrip.getPost() != null);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void saveNewFishingTrip(String email, FishingTripDto newFishingTrip) {
        Optional<User> findUser = this.userRepository.findByEmail(email);
        if (!findUser.isPresent()) {
            throw new NotFoundException("The user with this email not exists.");
        }

        FishingTrip fishingTrip = new FishingTrip();
        fishingTrip.setTitle(newFishingTrip.getTitle());
        fishingTrip.setDescription(newFishingTrip.getDescription());
        fishingTrip.setTripDate(newFishingTrip.getTripDate());
        fishingTrip.setUser(findUser.get());

        Fishery fishery = new Fishery();
        if (newFishingTrip.getFishery().getId() != null) {
            Optional<Fishery> findFishery = this.fisheryRepository.findById(newFishingTrip.getFishery().getId());
            if (!findFishery.isPresent()) {
                throw new NotFoundException("Problem with finding fishery.");
            }
            fishery = findFishery.get();
        }else {
            FisheryDto data = newFishingTrip.getFishery();

            fishery.setUser(findUser.get());
            fishery.setName(data.getName());
            fishery.setDescription(data.getDescription());
            fishery.setLatitude(data.getLatitude());
            fishery.setAltitude(data.getAltitude());

            this.fisheryRepository.save(fishery);
        }

        fishingTrip.setFishery(fishery);
        this.fishingTripRepository.save(fishingTrip);
    }

    @Override
    public FishingTripDto findTripById(String email, Long id) {
        Optional<FishingTrip> findFishingTrip = this.fishingTripRepository.findByUser_emailAndId(email, id);
        if (!findFishingTrip.isPresent())
            throw new NotFoundException("Not found resources.");

        FishingTrip fishingTrip = findFishingTrip.get();

        Fishery fishery = fishingTrip.getFishery();
        return new FishingTripDto(
                fishingTrip.getId(),
                fishingTrip.getTitle(),
                fishingTrip.getTripDate(),
                fishingTrip.getDescription(),
                new FisheryDto(
                        fishery.getId(),
                        fishery.getName(),
                        fishery.getAltitude(),
                        fishery.getLatitude(),
                        fishery.getDescription(),
                        fishery.getPost() != null
                ),
                fishingTrip.getTrophies(),
                fishingTrip.getPost() != null);
    }

    @Override
    public void updateFishingTrip(String email, FishingTripDto updateFishingTrip) {
        Optional<User> findUser = this.userRepository.findByEmail(email);
        if (!findUser.isPresent())
            throw new NotFoundException("The user with this email not exists.");

        Optional<FishingTrip> findTrip = this.fishingTripRepository.findById(updateFishingTrip.getId());
        if (!findTrip.isPresent())
            throw new NotFoundException("Not found resources.");

        FishingTrip fishingTrip = findTrip.get();
        fishingTrip.setTitle(updateFishingTrip.getTitle());
        fishingTrip.setDescription(updateFishingTrip.getDescription());
        fishingTrip.setTripDate(updateFishingTrip.getTripDate());

        this.fishingTripRepository.save(fishingTrip);
    }

    @Override
    public void removeFishingTrip(Long id) {
        Optional<FishingTrip> findFishingTrip = this.fishingTripRepository.findById(id);
        if (!findFishingTrip.isPresent()) {
            throw new NotFoundException("No resources found");
        }
        FishingTrip fishingTrip = findFishingTrip.get();
        this.fishingTripRepository.delete(fishingTrip);
    }
}




