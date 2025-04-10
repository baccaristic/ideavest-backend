
package com.ideavest.server.Controllers;

import com.ideavest.server.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.ideavest.server.models.*;
import com.ideavest.server.dtos.EstimationDTO;

import java.util.Optional;

@RestController
@RequestMapping("/idea/expert")
@RequiredArgsConstructor
public class EstimationController {

    private final EstimationRepository estimationRepository;
    private final IdeaRepository ideaRepository;
    private final UserRepository userRepository;

    @PostMapping("/estimate")
    public ResponseEntity<Estimation> estimate(Authentication auth, @RequestBody EstimationDTO dto) {
        Optional<User> expert = userRepository.findByEmail(auth.getName());
        Optional<Idea> ideaOpt = ideaRepository.findById(dto.getIdeaId());

        if (expert.isEmpty() || ideaOpt.isEmpty()) return ResponseEntity.badRequest().build();

        Estimation estimation = Estimation.builder()
                .price(dto.getPrice())
                .notes(dto.getNotes())
                .estimatedBy(expert.get())
                .idea(ideaOpt.get())
                .build();
        estimationRepository.save(estimation);
        ideaOpt.get().setEstimation(estimation);
        ideaOpt.get().setStatus(IdeaStatus.ESTIMATED);
        return ResponseEntity.ok(ideaRepository.save(ideaOpt.get()).getEstimation());
    }
}
