package com.mysite.suggestion.proposal;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.suggestion.answer.Answer;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Proposal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String classification;
	
	private String subject;
	
	private String content;
	
	private LocalDateTime createDate;
	
	private LocalDateTime updateDate;
	
	private boolean isAnswered;
	
	@OneToMany(mappedBy = "proposal", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
	public void setIsAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}
}
