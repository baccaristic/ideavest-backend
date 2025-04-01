package com.ideavest.server.Repositories;

import com.ideavest.server.models.Agreement;
import com.ideavest.server.models.AgreementStatus;
import com.ideavest.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, UUID> {
    List<Agreement> findByInvestor(User investor);
    List<Agreement> findByInvestorAndStatus(User investor, AgreementStatus status);
}

