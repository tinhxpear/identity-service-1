package dev.tinhxpear.identity_service.service;

import dev.tinhxpear.identity_service.dto.request.UserCreationRequest;
import dev.tinhxpear.identity_service.dto.request.UserUpdateRequest;
import dev.tinhxpear.identity_service.dto.response.UserResponse;
import dev.tinhxpear.identity_service.entity.User;
import dev.tinhxpear.identity_service.enums.Role;
import dev.tinhxpear.identity_service.exception.AppException;
import dev.tinhxpear.identity_service.exception.ErrorCode;
import dev.tinhxpear.identity_service.mapper.UserMapper;
import dev.tinhxpear.identity_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
        User userSave = userRepository.save(user);
        return userMapper.toUserResponse(userSave);
    }

    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUserById(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUserById(String userId) {
        userRepository.deleteById(userId);
    }
}
