package pl.angler.service;

import pl.angler.dto.FisheryDto;

import java.util.List;

public interface FisheryService {
    List<FisheryDto> getAllUserFisheries(String email);
    List<FisheryDto> getAllPublicFisheries(String userNick);
}
