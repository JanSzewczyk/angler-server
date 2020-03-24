package pl.angler.service;

import pl.angler.dto.StatisticsDto;

public interface StatisticService {
    StatisticsDto getUserStatistics(String userEmail);
}
