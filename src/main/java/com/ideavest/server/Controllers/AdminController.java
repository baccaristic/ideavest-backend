package com.ideavest.server.Controllers;

import com.ideavest.server.Repositories.IdeaRepository;
import com.ideavest.server.Services.NotificationService;
import com.ideavest.server.dtos.IdeaUpdateAssignedToDTO;
import com.ideavest.server.models.*;
import com.ideavest.server.Services.IdeaService;
import com.ideavest.server.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')") // ✅ Only admins can access these endpoints
public class AdminController {

    private final UserService userService;
    private final IdeaService ideaService;
    private final IdeaRepository ideaRepository;
    private final NotificationService notificationService;

    // ✅ Get all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // ✅ Update user role
    @PutMapping("/users/{userId}/role")
    public ResponseEntity<User> updateUserRole(@PathVariable UUID userId, @RequestBody Map<String, String> body) {
        String  role = body.get("role");
        return ResponseEntity.ok(userService.updateUserRole(userId,UserRole.valueOf(role)));
    }

    @GetMapping("/experts")
    public ResponseEntity<List<User>> getExperts() {
        return ResponseEntity.ok(userService.getAllUsersByRole(UserRole.EXPERT));
    }

    // ✅ Get all ideas
    @GetMapping("/ideas")
    public ResponseEntity<List<Idea>> getAllIdeas() {
        return ResponseEntity.ok(ideaService.getAllIdeas());
    }

    // ✅ Update idea status
    @PutMapping("/ideas/{ideaId}/status")
    public ResponseEntity<Idea> updateIdeaStatus(@PathVariable UUID ideaId, @RequestBody Map<String, String> body) {
        IdeaStatus status = IdeaStatus.valueOf(body.get("status"));
        Idea idea = ideaService.updateIdeaStatus(ideaId, status);
        notificationService.sendNotificationToUser(
                String.valueOf(idea.getOwner().getId()), new Notification(idea.getOwner().getId(), "Your idea has been updated", "SUCCESS", false, "/ideas")
        );
        return ResponseEntity.ok(idea);
    }

    // ✅ Delete idea
    @DeleteMapping("/ideas/{ideaId}")
    public ResponseEntity<Void> deleteIdea(@PathVariable UUID ideaId) {
        ideaService.deleteIdea(ideaId);
        return ResponseEntity.noContent().build();
    }

    // ✅ Get dashboard stats
    @GetMapping("/dashboard/stats")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        return ResponseEntity.ok(userService.getDashboardStats());
    }
    @PostMapping("/experts")
    public ResponseEntity<User> addExpert(@RequestBody User expertData) {
        return ResponseEntity.ok(userService.addExpert(expertData));
    }


    @PostMapping("/assign")
    public ResponseEntity<Idea> assignIdea(@RequestBody IdeaUpdateAssignedToDTO dto) {
        Optional<Idea> ideaOpt = ideaRepository.findById(dto.getIdeaId());
        Optional<User> userOpt = userService.findById(dto.getUserId());

        if (ideaOpt.isEmpty() || userOpt.isEmpty()) return ResponseEntity.badRequest().build();

        Idea idea = ideaOpt.get();
        idea.setAssignedTo(userOpt.get());

        return ResponseEntity.ok(ideaRepository.save(idea));
    }

}

