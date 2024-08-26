package com.mysite.suggestion.answer;

import java.time.LocalDateTime;

import com.mysite.suggestion.proposal.Proposal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String content;
	
	private LocalDateTime create_date;
	
	private LocalDateTime update_date;
	
	@ManyToOne
	private Proposal proposal;
	
	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
		if (proposal != null) {
			proposal.setIsAnswered(true);
		}
	}
}
