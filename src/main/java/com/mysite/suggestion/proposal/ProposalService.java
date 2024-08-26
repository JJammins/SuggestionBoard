package com.mysite.suggestion.proposal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mysite.suggestion.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProposalService {
	
	private final ProposalRepository proposalRepository;
	
	public List<Proposal> getList(){
		return this.proposalRepository.findAll();
	}
	
	public Proposal getProposal(Long id) {
		Optional<Proposal> proposal = this.proposalRepository.findById(id);
		if (proposal.isPresent()) {
			return proposal.get();
		} else {
			throw new DataNotFoundException("proposal not found");
		}
	}
	
	public void createProposal(String classification, String subject, String content) {
		Proposal p = new Proposal();
		p.setClassification(classification);
		p.setSubject(subject);
		p.setContent(content);
		p.setCreateDate(LocalDateTime.now());
		this.proposalRepository.save(p);
	}
	
	public List<Proposal> getAnsweredList(){
		return this.proposalRepository.findByIsAnswered(true);
	}
	
	public List<Proposal> getUnansweredList(){
		return this.proposalRepository.findByIsAnswered(false);
	}
	
	public List<Proposal> getClassificationList(String classification){
		return this.proposalRepository.findByClassification(classification);
	}
	
	public Page<Proposal> getList(int page){
		Pageable pageable = PageRequest.of(page, 1);
		return this.proposalRepository.findAll(pageable);
	}
}
