package com.CyberMallBackEnd.CyberMallBackEnd.service;


import com.CyberMallBackEnd.CyberMallBackEnd.Entity.User;
import com.CyberMallBackEnd.CyberMallBackEnd.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void signup(User user) throws Exception {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new Exception("Email already registered");
        }
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new Exception("Username already taken");
        }
        userRepository.save(user);
    }

    public Optional<User> login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public Optional<User> getUserByEmail(String email) {
        System.out.println("Fetching user from database for email: " + email);
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //    public Optional<User> updateUser(String email, User updatedUser) {
//     // return userRepository.findByEmail(email).map(existingUser -> {
////            existingUser.setEmail(updatedUser.getEmail());
////            existingUser.setCustomerName(updatedUser.getCustomerName());
////            existingUser.setUserName(updatedUser.getUserName());
////            existingUser.setAddress(updatedUser.getAddress());
////            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
////            existingUser.setNic(updatedUser.getNic());
////            existingUser.setPassword(updatedUser.getPassword());
//
////            return
////                    userRepository.save(existingUser);
////
////        });
//        for(User user:getAllUsers()){
//            if(user.getEmail().equals(email)){
//                userRepository.save(updatedUser);
//
//                return Optional.of(updatedUser);
//            }
//        }
//        return null;
//    }
    public Optional<User> updateUser(String email, User updatedUser) {
        return userRepository.findByEmail(email).map(existingUser -> {
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setCustomerName(updatedUser.getCustomerName());
            existingUser.setUserName(updatedUser.getUserName());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            existingUser.setNic(updatedUser.getNic());
            existingUser.setPassword(updatedUser.getPassword());

            return userRepository.save(existingUser);
        });
    }

    @Transactional
    public boolean deleteUserByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            userRepository.deleteByEmail(email);
            return true;
        }
        return false;
    }

}
