package com.ideavest.server.Services;

import com.ideavest.server.models.User;
import com.ideavest.server.Repositories.UserRepository;
import com.ideavest.server.models.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addExpert(User expertData) {
        expertData.setRole(UserRole.EXPERT);
        expertData.setPassword(passwordEncoder.encode(expertData.getPassword()));
        return userRepository.save(expertData);
    }
    public List<User> getAllUsersByRole(UserRole role) {
        return userRepository.findByRole(role);
    }

    // ✅ Update user role
    public User updateUserRole(UUID userId, UserRole role) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(role);
        return userRepository.save(user);
    }

    // ✅ Get dashboard stats
    public Map<String, Object> getDashboardStats() {
        long totalUsers = userRepository.count();
        long ideaHolders = userRepository.countByRole(UserRole.IDEA_HOLDER);
        long investors = userRepository.countByRole(UserRole.INVESTOR);
        long experts = userRepository.countByRole(UserRole.EXPERT);
        long admins = userRepository.countByRole(UserRole.ADMIN);

        return Map.of(
                "userCount", totalUsers,
                "ideaHolderCount", ideaHolders,
                "investorCount", investors,
                "expertCount", experts,
                "adminCount", admins
        );
    }

    public Optional<User> findById(UUID userId) {
        return this.userRepository.findById(userId);
    }
}

