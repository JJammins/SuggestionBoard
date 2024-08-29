package com.mysite.suggestion.proposal;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.mysite.suggestion.answer.Answer;
import com.mysite.suggestion.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime createDate;
	
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime updateDate;
	
	private boolean isAnswered;
	
	@OneToMany(mappedBy = "proposal", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Answer> answerList;
	
	public void setIsAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}
	
	@ManyToOne
	private SiteUser author;
	
    @Override
    public String toString() {
        return "Proposal{" +
               "id=" + id +
               ", classification='" + classification + '\'' +
               ", subject='" + subject + '\'' +
               ", content='" + content + '\'' +
               ", createDate=" + createDate +
               ", author=" + author +
               '}';
    }
}
