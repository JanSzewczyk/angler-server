package pl.angler.service;


import pl.angler.entity.User;

public interface SignUpService {

    void createNewUser(User newUser);
    void confirmNewUser(String userEmail);

}