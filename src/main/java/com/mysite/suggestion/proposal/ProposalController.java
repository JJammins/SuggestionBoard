package com.mysite.suggestion.proposal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.suggestion.answer.AnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/proposal")
@RequiredArgsConstructor
@Controller
public class ProposalController {
	
	private final ProposalService proposalService;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Proposal> proposalList = this.proposalService.getList();
		model.addAttribute("proposalList", proposalList);
		return "proposal_list";
	}
	
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id, AnswerForm answerForm) {
		Proposal proposal = this.proposalService.getProposal(id);
		model.addAttribute("proposal", proposal);
		return "proposal_detail";
	}
	
    @GetMapping("/create")
    public String proposalCreate(ProposalForm proposalForm) {
        return "proposal_form";
    }
    
	@PostMapping("/create")
	public String proposalCreate(@Valid ProposalForm proposalForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "proposal_form";
		}
		this.proposalService.createProposal(proposalForm.getClassification(), proposalForm.getSubject(), proposalForm.getContent());
		return "redirect:/proposal/list";
	}
	
	@GetMapping("/answered")
	public String answeredList(Model model) {
		List<Proposal> answeredList = proposalService.getAnsweredList();
		model.addAttribute("answeredList", answeredList);
		return "answered_list";
	}
	
	@GetMapping("/unanswered")
	public String unansweredList(Model model) {
		List<Proposal> unansweredList = proposalService.getUnansweredList();
		model.addAttribute("unansweredList", unansweredList);
		return "unanswered_list";
	}
	
	@GetMapping("/classification")
	public String classificationList(@RequestParam("classification") String classification, Model model) {
		List<Proposal> classificationList = proposalService.getClassificationList(classification);
		model.addAttribute("classificationList", classificationList);
		model.addAttribute("classification", classification);
		return "classification_list";
	}
	
}
