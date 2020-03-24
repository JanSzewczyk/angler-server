package pl.angler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.angler.dto.StatisticsDto;
import pl.angler.entity.Fish;
import pl.angler.repository.FishRepository;
import pl.angler.service.StatisticService;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private FishRepository fishRepository;

    @Override
    public StatisticsDto getUserStatistics(String userEmail) {
        List<Fish> species = this.fishRepository.findDistinctByTrophies_FishingTrip_User_Email(userEmail);
//        System.out.println(species);
        System.out.println("elooooo");
        return null;
    }
}
