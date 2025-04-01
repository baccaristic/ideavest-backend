package com.ideavest.server.Services;

import com.ideavest.server.Repositories.AgreementRepository;
import com.ideavest.server.dtos.IdeaEstimatedDto;
import com.ideavest.server.models.AgreementStatus;
import com.ideavest.server.models.Idea;
import com.ideavest.server.models.IdeaStatus;
import com.ideavest.server.Repositories.IdeaRepository;
import com.ideavest.server.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IdeaService {
    private final IdeaRepository ideaRepository;
    private final AgreementRepository agreementRepository;

    // ✅ Get all ideas
    public List<Idea> getAllIdeas() {
        return ideaRepository.findAll();
    }

    // ✅ Update idea status
    public Idea updateIdeaStatus(UUID ideaId, IdeaStatus status) {
        Idea idea = ideaRepository.findById(ideaId).orElseThrow(() -> new RuntimeException("Idea not found"));
        idea.setStatus(status);
        return ideaRepository.save(idea);
    }
    public List<IdeaEstimatedDto> getEstimatedIdeas(User investor) {
        List<Idea> estimatedIdeas = ideaRepository.findByStatus(IdeaStatus.ESTIMATED);

        // Get agreements signed by this investor
        Set<UUID> signedIdeaIds = agreementRepository.findByInvestorAndStatus(investor, AgreementStatus.SIGNED)
                .stream()
                .map(agreement -> agreement.getIdea().getId())
                .collect(Collectors.toSet());

        return estimatedIdeas.stream()
                .map(idea -> new IdeaEstimatedDto(idea.getId(), idea.getTitle(), idea.getDescription(), signedIdeaIds.contains(idea.getId())))
                .collect(Collectors.toList());
    }

    // ✅ Delete idea
    public void deleteIdea(UUID ideaId) {
        ideaRepository.deleteById(ideaId);
    }

    public Idea getIdeaById(UUID ideaId) {
        return ideaRepository.findById(ideaId)
                .orElseThrow(() -> new RuntimeException("Idea not found"));
    }

    public void likeIdea(UUID ideaId) {
        Idea idea = getIdeaById(ideaId);
        idea.setLikes(idea.getLikes() + 1);
        ideaRepository.save(idea);
    }
}

