package com.ideavest.server.Controllers;

import com.ideavest.server.Repositories.IdeaRepository;
import com.ideavest.server.Repositories.UserRepository;
import com.ideavest.server.Services.IdeaService;
import com.ideavest.server.dtos.IdeaDTO;
import com.ideavest.server.dtos.IdeaEstimatedDto;
import com.ideavest.server.mappers.IdeaAdditionalDataMapper;
import com.ideavest.server.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/ideas")
@CrossOrigin(origins = "*")
public class IdeaController {

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired private IdeaService ideaService;

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
        return ResponseEntity.ok(ideaRepository.findByStatusAndAssignedToId(IdeaStatus.APPROVED, user.get().getId())); // Only approved ideas need estimation
    }
    @GetMapping("/estimated")
    public ResponseEntity<List<Idea>> getEstimatedIdeasInvestor(Authentication authentication) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (user.isEmpty() || user.get().getRole() == UserRole.IDEA_HOLDER) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(ideaRepository.findByStatus(IdeaStatus.ESTIMATED)); // Only approved ideas need estimation
    }

    @GetMapping("/{ideaId}")
    public ResponseEntity<Idea> getIdeaById(@PathVariable UUID ideaId) {
        return ResponseEntity.ok(ideaService.getIdeaById(ideaId));
    }

    @PostMapping("/{ideaId}/like")
    public ResponseEntity<Void> likeIdea(@PathVariable UUID ideaId) {
        ideaService.likeIdea(ideaId);
        return ResponseEntity.ok().build();
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
    public ResponseEntity<Idea> createIdea(Authentication authentication, @RequestBody IdeaDTO ideaDTO) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());

        if (user.isEmpty() || user.get().getRole() != UserRole.IDEA_HOLDER) {
            return ResponseEntity.status(403).build();
        }

        Idea newIdea = new Idea();
        newIdea.setOwner(user.get());
        newIdea.setTitle(ideaDTO.getTitle());
        newIdea.setDescription(ideaDTO.getDescription());
        newIdea.setCategory(ideaDTO.getCategory());
        newIdea.setStatus(IdeaStatus.AWAITING_APPROVAL);
        newIdea.setEstimatedBudget(ideaDTO.getEstimatedBudget());
        newIdea.setEstimatedPrice(ideaDTO.getEstimatedPrice());

        // Map AdditionalData
        if (ideaDTO.getAdditionalData() != null) {
            IdeaAdditionalData additionalData = IdeaAdditionalDataMapper.fromDto(ideaDTO.getAdditionalData());
            additionalData.setIdea(newIdea);
            newIdea.setAdditionalData(additionalData);
        }

        // Map Attachments (if any)

        return ResponseEntity.ok(ideaRepository.save(newIdea));
    }
    @GetMapping("/investor/estimated")
    public ResponseEntity<List<IdeaEstimatedDto>> getEstimatedIdeas(Authentication authentication) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (user.isEmpty() || user.get().getRole() != UserRole.INVESTOR) {
            return ResponseEntity.status(403).build();
        }
        List<IdeaEstimatedDto> ideas = ideaService.getEstimatedIdeas(user.get());
        return ResponseEntity.ok(ideas);
    }
}
