package com.ideavest.server.Services;

import com.ideavest.server.Controllers.NotificationController;
import com.ideavest.server.models.*;
import com.ideavest.server.Repositories.AgreementRepository;
import com.ideavest.server.Repositories.IdeaRepository;
import com.ideavest.server.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgreementService {
    private final AgreementRepository agreementRepository;
    private final IdeaRepository ideaRepository;
    private final UserRepository userRepository;
    private final NotificationController notificationController;

    public Agreement createAgreement(UUID ideaId, User investor) {
        Idea idea = ideaRepository.findById(ideaId)
                .orElseThrow(() -> new RuntimeException("Idea not found"));

        Agreement agreement = new Agreement();
        agreement.setIdea(idea);
        agreement.setInvestor(investor);
        agreement.setStatus(AgreementStatus.PENDING);
        return agreementRepository.save(agreement);
    }

    public List<Agreement> getAgreements(User investor) {
        return agreementRepository.findByInvestor(investor);
    }

    public Agreement submitSignedAgreement(String agreementId, String signatureData, MultipartFile file) {
        Agreement agreement = agreementRepository.findById(UUID.fromString(agreementId))
                .orElseThrow(() -> new RuntimeException("Agreement not found"));

        agreement.setSignatureData(signatureData);
        agreement.setStatus(AgreementStatus.SIGNED);
        agreement.setSignedAt(LocalDateTime.now());


        return agreementRepository.save(agreement);
    }
    public Agreement getAgreementById(UUID agreementId, User investor) {
        return agreementRepository.findById(agreementId)
                .filter(agreement -> agreement.getInvestor().equals(investor)) // Ensure the user owns the agreement
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agreement not found"));
    }

}

