package com.mysite.suggestion.answer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.suggestion.proposal.Proposal;
import com.mysite.suggestion.proposal.ProposalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final ProposalService proposalService;
	private final AnswerService answerService;
	
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Long id, @Valid AnswerForm answerForm, BindingResult bindingResult) {
		Proposal proposal = this.proposalService.getProposal(id);
		if (bindingResult.hasErrors()) {
			model.addAttribute("proposal", proposal);
			return "proposal_detail";
		}
		this.answerService.createAnswer(proposal, answerForm.getContent());
		return String.format("redirect:/proposal/detail/%s", id);
	}
}
