package com.ideavest.server.Controllers;

import com.ideavest.server.Repositories.IdeaRepository;
import com.ideavest.server.Repositories.UserRepository;
import com.ideavest.server.dtos.IdeaDTO;
import com.ideavest.server.models.Idea;
import com.ideavest.server.models.IdeaStatus;
import com.ideavest.server.models.User;
import com.ideavest.server.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ideas")
@CrossOrigin(origins = "*")
public class IdeaController {

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private UserRepository userRepository;

    // 🔹 Admin - Retrieve all ideas
    @GetMapping("/all")
    public ResponseEntity<List<Idea>> getAllIdeas(Authentication authentication) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (user.isEmpty() || user.get().getRole() != UserRole.ADMIN) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(ideaRepository.findAll());
    }

    // 🔹 Expert - Retrieve ideas that need estimation
    @GetMapping("/estimate")
    public ResponseEntity<List<Idea>> getIdeasForEstimation(Authentication authentication) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (user.isEmpty() || user.get().getRole() != UserRole.EXPERT) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(ideaRepository.findByStatus(IdeaStatus.APPROVED)); // Only approved ideas need estimation
    }

    // 🔹 Idea Holder - Retrieve their own ideas
    @GetMapping("/my-ideas")
    public ResponseEntity<List<Idea>> getMyIdeas(Authentication authentication) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (user.isEmpty() || user.get().getRole() != UserRole.IDEA_HOLDER) {
            return ResponseEntity.status(408).build();
        }
        return ResponseEntity.ok(ideaRepository.findByOwnerId(user.get().getId()));
    }

    @PostMapping("/new")
    public ResponseEntity<Idea> createIdea(Authentication authentication, @RequestBody IdeaDTO idea) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (user.isEmpty() || user.get().getRole() != UserRole.IDEA_HOLDER) {
            return ResponseEntity.status(403).build();
        }
        Idea newIdea = new Idea();
        newIdea.setOwner(user.get());
        newIdea.setTitle(idea.getTitle());
        newIdea.setDescription(idea.getDescription());
        newIdea.setCategory(idea.getCategory());
        newIdea.setStatus(IdeaStatus.AWAITING_APPROVAL);
        newIdea.setEstimatedBudget(idea.getEstimatedBudget());
        return ResponseEntity.ok(ideaRepository.save(newIdea));
    }
}
