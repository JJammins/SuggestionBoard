package com.mysite.suggestion.answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.mysite.suggestion.proposal.Proposal;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
	
	private final AnswerRepository answerRepository;
	
	public void createAnswer(Proposal proposal, String content) {
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreate_date(LocalDateTime.now());
		answer.setProposal(proposal);
		answer.setUpdate_date(LocalDateTime.now());
		this.answerRepository.save(answer);
		
		
	}
}
