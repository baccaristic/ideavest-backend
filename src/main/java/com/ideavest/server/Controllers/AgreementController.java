package com.ideavest.server.Controllers;

import com.ideavest.server.Repositories.UserRepository;
import com.ideavest.server.Services.NotificationService;
import com.ideavest.server.Services.UserService;
import com.ideavest.server.dtos.CreateAgreementRequest;
import com.ideavest.server.models.Agreement;
import com.ideavest.server.models.Notification;
import com.ideavest.server.models.User;
import com.ideavest.server.Services.AgreementService;
import com.ideavest.server.models.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/investor/agreements")
@RequiredArgsConstructor
public class AgreementController {
    private final AgreementService agreementService;
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Agreement> createAgreement(@RequestBody CreateAgreementRequest request, Authentication authentication) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (user.isEmpty() ||  user.get().getRole() != UserRole.INVESTOR) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(agreementService.createAgreement(UUID.fromString(request.getIdeaId()), user.get()));
    }

    @GetMapping
    public ResponseEntity<List<Agreement>> getMyAgreements(Authentication authentication) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (user.isEmpty() ||  user.get().getRole() != UserRole.INVESTOR) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(agreementService.getAgreements(user.get()));
    }

    @PostMapping("/{agreementId}/sign")
    public ResponseEntity<Agreement> submitSignedAgreement(
            @PathVariable String agreementId,
            @RequestParam("signatureData") String signatureData,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        Agreement agreement = agreementService.submitSignedAgreement(agreementId, signatureData, file);
        notificationService.sendGlobalNotification(
                new Notification(agreement.getIdea().getOwner().getId(), "Your agreement has been signed!", "SUCCESS", false, "/dashboard")
        );
        return ResponseEntity.ok(agreement);
    }

    @GetMapping("/{agreementId}")
    public ResponseEntity<Agreement> getAgreementById(
            @PathVariable UUID agreementId,
            Authentication authentication
    ) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (user.isEmpty() ||  user.get().getRole() != UserRole.INVESTOR) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(agreementService.getAgreementById(agreementId, user.get()));
    }

}

