package com.ideavest.server.Repositories;

import com.ideavest.server.models.Idea;
import com.ideavest.server.models.IdeaStatus;
import com.ideavest.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, UUID> {
    List<Idea> findByStatus(IdeaStatus status); // Get ideas by status
    List<Idea> findByStatusAndAssignedToId(IdeaStatus status, UUID id);
    List<Idea> findByOwnerId(UUID id);
    Optional<Idea> findById(UUID ideaId);// Get ideas by user
}