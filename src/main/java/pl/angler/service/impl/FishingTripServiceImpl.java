package pl.angler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
                    return new FishingTripDto(fishingTrip.getId(), fishingTrip.getTitle(), fishingTrip.getTripDate(), fishingTrip.getDescription(), fishingTrip.getFishery());
                })
                .collect(Collectors.toList());
    }

    @Override
    public void saveNewFishingTrip(String email, FishingTrip newFishingTrip) {
        Optional<User> findUser = this.userRepository.findByEmail(email);
        if (!findUser.isPresent()) {
            throw new NotFoundException("The user with this email not exists.");
        }
        newFishingTrip.setUser(findUser.get());
        newFishingTrip.getFishery().setUser(findUser.get());
        this.fishingTripRepository.save(newFishingTrip);
    }

    @Override
    public FishingTrip findTripById(String email, Long id) {
        Optional<FishingTrip> findFishingTrip = this.fishingTripRepository.findByUser_emailAndId(email, id);
        if (!findFishingTrip.isPresent()) {
            throw new NotFoundException("No resources found");
        }
        return findFishingTrip.get();
    }

    @Override
    public void updateFishingTrip(String email, FishingTrip updateFishingTrip) {
        Optional<User> findUser = this.userRepository.findByEmail(email);

        if (!findUser.isPresent()) {
            throw new NotFoundException("The user with this email not exists.");
        }

        if (!this.fishingTripRepository.existsByUser_emailAndId(email, updateFishingTrip.getId())) {
            throw new NotFoundException("No resources found.");
        }
        updateFishingTrip.setUser(findUser.get());
        this.fishingTripRepository.save(updateFishingTrip);
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