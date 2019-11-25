package pl.angler.service;

import pl.angler.dto.FishingTripDto;
import pl.angler.entity.FishingTrip;

import java.util.List;

public interface FishingTripService {

    List<FishingTripDto> getTrips(String email);

    void saveNewFishingTrip(String email, FishingTrip newFishingTrip);
}
