package com.isfive.usearth.domain.utils.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.isfive.usearth.exception.BusinessException;
import com.isfive.usearth.exception.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService{
	private final JavaMailSender javaMailSender;

	public static int createNumber(){
		return (int)(Math.random() * (90000)) + 100000;// (int) Math.random() * (최댓값-최소값+1) + 최소값
	}

	public void sendEmail(
		String toEmail,
		String title,
		String text
	) {
		SimpleMailMessage emailForm = createEmailForm(toEmail, title, text);
		try {
			javaMailSender.send(emailForm);
		} catch (RuntimeException e) {
			log.error("MailService.sendEmail exception occur toEmail: {}, " +
				"title: {}, text: {}", toEmail, title, text);
			throw new BusinessException(ErrorCode.SEND_EMAIL_FAILED);
		}
	}

	private SimpleMailMessage createEmailForm(String toEmail, String title, String text) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject(title);
		message.setText(text);

		return message;
	}
}
