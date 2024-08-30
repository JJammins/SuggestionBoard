package com.mysite.suggestion.proposal;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.mysite.suggestion.answer.AnswerForm;
import com.mysite.suggestion.user.SiteUser;
import com.mysite.suggestion.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/proposal")
@RequiredArgsConstructor
@Controller
public class ProposalController {
	
	private final ProposalService proposalService;
	private final UserService userService;
	
	// 목록
	@GetMapping("/list")
	public String list(Model model) {
		List<Proposal> proposalList = this.proposalService.getList();
		model.addAttribute("proposalList", proposalList);
		return "proposal_list";
	}
	
	// 상세페이지
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id, AnswerForm answerForm) {
		Proposal proposal = this.proposalService.getProposal(id);
		model.addAttribute("proposal", proposal);
		return "proposal_detail";
	}
	
	// 글 작성 get 요청
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String proposalCreate(ProposalForm proposalForm) {
        return "proposal_form";
    }
    
	// 글 작성 post 요청
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String proposalCreate(@Valid ProposalForm proposalForm, BindingResult bindingResult, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "proposal_form";
		}
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.proposalService.createProposal(proposalForm.getClassification(), proposalForm.getSubject(), proposalForm.getContent(), siteUser);
		return "redirect:/proposal/list";
	}
	
	// 글 수정
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/update/{id}")
    public String questionModify(ProposalForm proposalForm, @PathVariable("id") Long id, Principal principal) {
    	Proposal proposal = this.proposalService.getProposal(id);
        if(!proposal.getAuthor().getUserID().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        proposalForm.setSubject(proposal.getSubject());
        proposalForm.setContent(proposal.getContent());
        return "proposal_form";
    }
    
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/update/{id}")
	public String proposalUpdate(@Valid ProposalForm proposalForm, BindingResult bindingResult, @PathVariable("id") Long id, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "proposal_form";
		}
		Proposal proposal = this.proposalService.getProposal(id);
		if(!proposal.getAuthor().getUserID().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		this.proposalService.updateProposal(proposal, proposalForm.getSubject(), proposalForm.getContent());
		return String.format("redirect:/proposal/detail/%s", id);
	}
	
	// 삭제
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String proposalDelete(Principal principal, @PathVariable("id") Long id) {
		Proposal proposal = this.proposalService.getProposal(id);
		if(!proposal.getAuthor().getUserID().equals(principal.getName()) && !principal.getName().equals("admin")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
		}
		this.proposalService.delete(proposal);
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
