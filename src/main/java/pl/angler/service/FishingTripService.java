package pl.angler.service;

import pl.angler.dto.FishingTripDto;
import pl.angler.entity.FishingTrip;

import java.util.List;

public interface FishingTripService {

    List<FishingTripDto> getTrips(String email);

    void saveNewFishingTrip(String email, FishingTripDto newFishingTrip);

    FishingTrip findTripById(String name, Long id);

    void updateFishingTrip(String email, FishingTripDto updateFishingTrip);

    void removeFishingTrip(Long id);
}
