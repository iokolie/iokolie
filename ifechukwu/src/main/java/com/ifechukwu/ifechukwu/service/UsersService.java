package com.ifechukwu.ifechukwu.service;

import com.ifechukwu.ifechukwu.model.UsersModel;
import com.ifechukwu.ifechukwu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsersModel registerUser(String login, String password, String email){

        if(login !=null && password !=null){
            if(userRepository.findFirstByLogin(login).isPresent()){
                System.out.println("Duplicate login");
                return null;
            }
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            return userRepository.save(usersModel);
        }else {
            return null;
        }
    }
    public UsersModel authenticate(String login, String password){
        return userRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}
