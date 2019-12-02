package pl.angler.service;

import pl.angler.dto.TrophyDto;
import pl.angler.entity.Trophy;

public interface TrophyService {
    void addNewTrophy(TrophyDto newTrophy);
    void updateTrophy(TrophyDto trophyDto);
    void deleteTrophy(Long id);
}
