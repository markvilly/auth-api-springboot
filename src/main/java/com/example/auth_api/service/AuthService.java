package com.example.auth_api.service;

import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth_api.dto.AuthResponse;
import com.example.auth_api.dto.RegisterRequest;
import com.example.auth_api.entity.Role;
import com.example.auth_api.entity.User;
import com.example.auth_api.repo.RolesRepository;
import com.example.auth_api.repo.UsersRepository;
import com.example.auth_api.security.JwtUtil;

@Service
public class AuthService {
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    


    public AuthService(AuthenticationManager authenticationManager, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil, RolesRepository rolesRepository, UsersRepository usersRepository) {
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
        this.rolesRepository = rolesRepository;
        this.usersRepository = usersRepository;
    }

    public AuthResponse register(RegisterRequest request)throws RuntimeException{
        if(usersRepository.findByUsername(request.getUsername()).isPresent()){
                    throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        Role role = rolesRepository.findByName("ROLE_USER")
            .orElseThrow(()-> new RuntimeException("Default role not found"));

        user.setRoles(Set.of(role));

        usersRepository.save(user);
        return new AuthResponse(jwtUtil.generateToken(user.getUsername()));

    }

    // public AuthResponse login(LoginRequest request){

    // }
}