package com.isfive.usearth.web.member;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isfive.usearth.domain.member.dto.MemberResponse;
import com.isfive.usearth.domain.member.service.MemberService;
import com.isfive.usearth.domain.utils.mail.MailService;
import com.isfive.usearth.exception.AuthException;
import com.isfive.usearth.exception.ErrorCode;
import com.isfive.usearth.exception.InvalidValueException;
import com.isfive.usearth.web.auth.dto.SignUpRegister;
import com.isfive.usearth.web.auth.dto.SignUpRequest;
import com.isfive.usearth.web.common.dto.Message;
import com.isfive.usearth.web.member.dto.MailAuthenticationRequest;
import com.isfive.usearth.web.member.dto.UpdateRequest;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final StringRedisTemplate redisTemplate;
	private final MailService mailService;
	private final ObjectMapper mapper;

	private HashOperations<String, Object, Object> stringObjectObjectHashOperations;
	private ValueOperations<String, String> stringStringValueOperations;

	private @Value("${spring.data.redis.email-key}") String key;

	@PostConstruct
	public void init() {
		stringObjectObjectHashOperations = redisTemplate.opsForHash();
		stringStringValueOperations = redisTemplate.opsForValue();
		redisTemplate.expire(key, Duration.ofMinutes(5)); // 5분만 저장
	}

	@GetMapping("/my-page")
	public ResponseEntity<MemberResponse> getJson(Authentication authentication) {
		String username = authentication.getName();
		MemberResponse memberResponse = memberService.readByUsername(username);
		return new ResponseEntity<>(memberResponse, HttpStatus.OK);
	}

	// TODO 회원가입 요청
	@ResponseBody
	@PostMapping
	public ResponseEntity<Message> registration(
		@RequestBody SignUpRequest request
	) throws JsonProcessingException {
		if (!request.pwCheck())
			throw new InvalidValueException(ErrorCode.INVALID_PASSWORD);

		stringObjectObjectHashOperations = redisTemplate.opsForHash();
		stringStringValueOperations = redisTemplate.opsForValue();

		redisTemplate.expire(request.getEmail(), Duration.ofMinutes(5));

		stringObjectObjectHashOperations.put(key, request.getEmail(), mapper.writeValueAsString(request));
		stringStringValueOperations.set(request.getEmail(), Integer.toString(MailService.createNumber()));

		return new ResponseEntity<>(new Message("이메일 인증을 완료하세요."), HttpStatus.OK);
	}

	// TODO email 인증
	@ResponseBody
	@PostMapping("email")
	public ResponseEntity<Message> emailAuth(
		@RequestBody MailAuthenticationRequest request
	) {
		try {
			Message message = null;
			if (stringStringValueOperations.get(request.getEmail()).equals(request.getCode())) {

				String stringRequest = (String)stringObjectObjectHashOperations.get(key, request.getEmail());

				SignUpRequest signUpRequest = mapper.readValue(stringRequest, SignUpRequest.class);

				if(signUpRequest != null) {
					memberService.createBy(SignUpRegister.fromRequest(signUpRequest));
					message = new Message("가입이 완료 되었습니다.");
					redisTemplate.delete(request.getEmail());
				}
				else {
					message = new Message("인증 시간이 초과하였습니다.");
				}
			} else {
				message = new Message("인증 번호가 일치하지 않습니다.");
			}
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(); // 추후 Exception 처리 필요
		}
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
