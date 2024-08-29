package com.mysite.suggestion.answer;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.mysite.suggestion.proposal.Proposal;
import com.mysite.suggestion.proposal.ProposalService;
import com.mysite.suggestion.user.SiteUser;
import com.mysite.suggestion.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final ProposalService proposalService;
	private final AnswerService answerService;
	private final UserService userService;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Long id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
		Proposal proposal = this.proposalService.getProposal(id);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		if (bindingResult.hasErrors()) {
			model.addAttribute("proposal", proposal);
			return "proposal_detail";
		}
		this.answerService.createAnswer(proposal, answerForm.getContent(), siteUser);
		return String.format("redirect:/proposal/detail/%s", id);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("update/{id}")
	public String answerUpdate(AnswerForm answerForm, @PathVariable("id") Long id, Principal principal) {
		Answer answer = this.answerService.getAnswer(id);
		if (!answer.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		answerForm.setContent(answer.getContent());
		return "answer_form";
	}
	
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update/{id}")
    public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult,
            @PathVariable("id") Long id, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "answer_form";
        }
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.answerService.update(answer, answerForm.getContent());
        return String.format("redirect:/proposal/detail/%s", answer.getProposal().getId());
    }
	
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String answerDelete(Principal principal, @PathVariable("id") Long id) {
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.answerService.delete(answer);
        return String.format("redirect:/proposal/detail/%s", answer.getProposal().getId());
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update/ajax/{id}")
    public ResponseEntity<Map<String, Object>> answerModifyAjax(
            @PathVariable("id") Long id, 
            @RequestBody Map<String, String> requestBody, 
            Principal principal) {

        // 요청된 답변 내용을 추출합니다.
        String updatedContent = requestBody.get("content");

        // 해당 ID의 답변을 가져옵니다.
        Answer answer = this.answerService.getAnswer(id);
        
        // 답변 작성자가 현재 로그인한 사용자와 일치하는지 확인합니다.
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        // 답변 내용을 업데이트합니다.
        this.answerService.update(answer, updatedContent);

        // 성공적으로 수정된 경우, 성공 상태와 메시지를 반환합니다.
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "답변이 성공적으로 수정되었습니다.");
        
        return ResponseEntity.ok(response);
    }
}
