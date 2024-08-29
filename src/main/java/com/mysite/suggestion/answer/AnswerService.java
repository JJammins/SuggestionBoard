package com.mysite.suggestion.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.suggestion.DataNotFoundException;
import com.mysite.suggestion.proposal.Proposal;
import com.mysite.suggestion.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
	
	private final AnswerRepository answerRepository;
	
	public Answer createAnswer(Proposal proposal, String content, SiteUser author) {
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setProposal(proposal);
		answer.setAuthor(author);
		answer.setUpdateDate(LocalDateTime.now());
		this.answerRepository.save(answer);
		return answer;
	}
	
	public Answer getAnswer(Long id) {
		Optional<Answer> answer = this.answerRepository.findById(id);
		if (answer.isPresent()) {
			return answer.get();
		} else {
			throw new DataNotFoundException("answer not found");
		}
	}
	
	public void update(Answer answer, String content) {
		answer.setContent(content);
		answer.setUpdateDate(LocalDateTime.now());
		this.answerRepository.save(answer);
	}
	
	public void delete(Answer answer) {
		this.answerRepository.delete(answer);
	}
}
