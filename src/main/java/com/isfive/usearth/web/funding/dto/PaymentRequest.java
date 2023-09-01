package com.isfive.usearth.web.funding.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    @Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}-\\d{4}", message = "카드 번호는 0000-0000-0000-0000 형식이어야 합니다.")
    private String cardNumber;

    @Pattern(regexp = "\\d{4}-\\d{2}", message = "카드 유효기간은 YYYY-MM 형식이어야 합니다.")
    private String expiry;

    @Size(min = 6, max = 6, message = "생년월일은 6글자여야 합니다.")
    private String birth;

    @Size(min = 2, max = 2, message = "비밀번호 앞자리 2글자여야 합니다.")
    private String pwd_2digit;
}
