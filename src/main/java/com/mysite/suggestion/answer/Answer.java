package com.mysite.suggestion.answer;

import java.time.LocalDateTime;

import com.mysite.suggestion.proposal.Proposal;
import com.mysite.suggestion.user.SiteUser;

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
	
	private LocalDateTime createDate;
	
	private LocalDateTime updateDate;
	
	@ManyToOne
	private Proposal proposal;
	
	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
		if (proposal != null) {
			proposal.setIsAnswered(true);
		}
	}
	
	@ManyToOne
	private SiteUser author;
}
