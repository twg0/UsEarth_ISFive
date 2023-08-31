package com.isfive.usearth.web.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isfive.usearth.domain.member.dto.MemberResponse;
import com.isfive.usearth.domain.member.service.MemberService;
import com.isfive.usearth.exception.AuthException;
import com.isfive.usearth.exception.ErrorCode;
import com.isfive.usearth.exception.InvalidValueException;
import com.isfive.usearth.web.auth.dto.SignUpRegister;
import com.isfive.usearth.web.auth.dto.SignUpRequest;
import com.isfive.usearth.web.common.dto.Message;
import com.isfive.usearth.web.member.dto.UpdateRequest;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	@GetMapping("/my-page")
	public ResponseEntity<MemberResponse> getJson(Authentication authentication) {
		String username = authentication.getName();
		MemberResponse memberResponse = memberService.readByUsername(username);
		return new ResponseEntity<>(memberResponse, HttpStatus.OK);
	}

	// TODO 회원가입 요청
	@ResponseBody
	@PostMapping
	public ResponseEntity<MemberResponse> registration(
		@RequestBody SignUpRequest request
	) {
		if (!request.pwCheck())
			throw new InvalidValueException(ErrorCode.INVALID_PASSWORD);

		MemberResponse memberResponse = memberService.createBy(SignUpRegister.fromRequest(request));
		return new ResponseEntity<>(memberResponse, HttpStatus.OK);
	}

	// TODO 회원 정보 수정
	@PutMapping("{memberId}")
	public ResponseEntity<MemberResponse> updateInfo(
		@RequestBody UpdateRequest request,
		@PathVariable("memberId") Long memberId,
		Authentication authentication
	) {
		String username = authentication.getName();
		if (!memberService.isUserAlright(memberId, username))
			throw new AuthException(ErrorCode.AUTHENTICATION_FAILED);

		MemberResponse memberResponse = memberService.updateUserInfoByUpdateRegister(username,request.toRegister());
		return new ResponseEntity<>(memberResponse, HttpStatus.OK);
	}

	// TODO 회원 삭제 요청
	@DeleteMapping("{memberId}")
	public ResponseEntity<Message> deleteMember(
		@PathVariable("memberId") Long memberId,
		Authentication authentication
	) {
		String username = authentication.getName();
		if (!memberService.isUserAlright(memberId, username))
			throw new AuthException(ErrorCode.AUTHENTICATION_FAILED);

		memberService.deleteBy(username);
		return new ResponseEntity<>(new Message("삭제가 완료되었습니다."), HttpStatus.OK);
	}
}
