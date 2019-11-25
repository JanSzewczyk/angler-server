package pl.angler.service;

import pl.angler.dto.RetrieveAccountDto;

public interface RetrieveAccountService {

    void retrievePasswordMailSender(String email);
    void retrievePassword(RetrieveAccountDto retrieveAccount);
}
