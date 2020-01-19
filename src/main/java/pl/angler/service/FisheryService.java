package pl.angler.service;

import pl.angler.dto.FisheryDto;
import pl.angler.entity.Fishery;

import java.util.List;

public interface FisheryService {
    List<FisheryDto> getAllUserFisheries(String email);
    List<FisheryDto> getAllPublicFisheries(String userNick);
    void setFisheryPublic(Fishery fishery);
    void setFisheryPrivate(Fishery fishery);
}
