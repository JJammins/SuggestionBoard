package com.mysite.suggestion.proposal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository <Proposal, Long> {
	List<Proposal> findByIsAnswered(boolean isAnswered);
	List<Proposal> findByClassification(String classification);
	Page<Proposal> findAll(Pageable pageable);
}
