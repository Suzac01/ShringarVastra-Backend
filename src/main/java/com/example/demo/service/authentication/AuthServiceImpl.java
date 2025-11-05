//package com.example.demo.service.authentication;
//
//import com.example.demo.dto.auth.googleRegister.Google;
//import com.example.demo.model.Role;
//import com.example.demo.model.User;
//import com.example.demo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthServiceImpl implements AuthService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public User saveUser(User user) {
//        System.out.println("Saving user: " + user);
//        return userRepository.save(user);
//    }
//
//    @Override
//    public User login(String username, String password) {
//        return userRepository.findByEmailAndPassword(username, password);
//    }
//
//    @Override
//    public User loginOrRegister(Google google) {
//        User user = userRepository.findByEmail(google.getEmail()).orElse(null);
//
//        if (user == null) {
//            user = new User();
//            user.setName(google.getName());
//            user.setEmail(google.getEmail());
//            user.setAppwriteId(google.getAppwriteId());
//            user.setRole(Role.ROLE_CLIENT);
//            user = userRepository.save(user);
//        }
//
//        return user;
//    }
//
//}


package com.example.demo.service.authentication;

import com.example.demo.dto.auth.googleRegister.Google;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    // Use BCrypt for hashing
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User saveUser(User user) {
        System.out.println("Saving user: " + user);

        // 🔹 Hash the password before saving
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        // 🔹 Find the user by email
        User user = userRepository.findByEmail(username).orElse(null);

        if (user == null) {
            System.out.println("User not found for email: " + username);
            return null;
        }

        // 🔹 Compare raw password with hashed password
        boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());

        if (!isPasswordMatch) {
            System.out.println("Password mismatch for user: " + username);
            return null;
        }

        return user;
    }

    @Override
    public User loginOrRegister(Google google) {
        // Find if the user already exists
        User user = userRepository.findByEmail(google.getEmail()).orElse(null);

        if (user == null) {
            // New Google user → save details
            user = new User();
            user.setName(google.getName());
            user.setEmail(google.getEmail());
            user.setAppwriteId(google.getAppwriteId()); // Or google.getSub()
            user.setRole(Role.ROLE_CLIENT);
            user.setProvider("GOOGLE");
            userRepository.save(user);

            System.out.println("✅ New Google user saved: " + google.getEmail());
        } else {
            System.out.println("🔁 Existing Google user logged in: " + google.getEmail());
        }

        return user;
    }
}
