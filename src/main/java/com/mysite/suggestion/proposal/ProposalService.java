package com.mysite.suggestion.proposal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysite.suggestion.DataNotFoundException;
import com.mysite.suggestion.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProposalService {
	
	private final ProposalRepository proposalRepository;
	
	public List<Proposal> getList() {
	    return this.proposalRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
	}
	
	public Proposal getProposal(Long id) {
		Optional<Proposal> proposal = this.proposalRepository.findById(id);
		if (proposal.isPresent()) {
			return proposal.get();
		} else {
			throw new DataNotFoundException("proposal not found");
		}
	}
	
	public void createProposal(String classification, String subject, String content, SiteUser user) {
		Proposal p = new Proposal();
		p.setClassification(classification);
		p.setSubject(subject);
		p.setContent(content);
		p.setCreateDate(LocalDateTime.now());
		p.setAuthor(user);
		this.proposalRepository.save(p);
	}
	
	public List<Proposal> getAnsweredList(){
		return this.proposalRepository.findByIsAnswered(true);
	}
	
	public List<Proposal> getUnansweredList(){
		return this.proposalRepository.findByIsAnswered(false);
	}
	
    public List<Proposal> getClassificationList(String classification) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");
        return this.proposalRepository.findByClassification(classification, sort);
    }
	
	// 수정
	public void updateProposal(Proposal proposal, String subject, String content) {
		proposal.setSubject(subject);
		proposal.setContent(content);
		proposal.setUpdateDate(LocalDateTime.now());
		this.proposalRepository.save(proposal);
	}
	
	// 삭제
	public void delete(Proposal proposal) {
		this.proposalRepository.delete(proposal);
	}
	
//	public Page<Proposal> getList(int page){
////		List<Sort.Order> sorts = new ArrayList<>();
////		sorts.add(Sort.Order.desc("createDate"));
//		//Sort.by(sorts)
//		Pageable pageable = PageRequest.of(page, 5);
//		return this.proposalRepository.findAll(pageable);
//	}
}
