package com.isfive.usearth.web.project.dto;

import com.isfive.usearth.domain.project.dto.RewardCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RewardRegister {

	@NotBlank(message = "리워드 제목을 입력해야 합니다.")
	private String title;

	@NotBlank(message = "리워드 설명을 입력해야 합니다.")
	private String description;

	@NotNull(message = "리워드 가격을 입력해야 합니다.")
	private Integer price;

	@NotBlank(message = "배송 예정 날짜를 입력해야 합니다.")
	private String expectedSendDate;

	@NotNull(message = "배송비를 입력해야 합니다.")
	private Integer deliveryFee;

	@NotBlank(message = "옵션명과 옵션 상세내용 입력해야 합니다. 예시) \"색상\": \"검정,흰색\", \"사이즈\": \"S,M,L\"")
	private Map<String, String> options;

	@NotBlank(message = "옵션상세내용과 초기 수량을 입력해야 합니다. 예시) \"검정,S\": 100, \"검정,M\": 100, \"검정,L\": 5 등")
	private Map<String, Integer> optionStocks;

	public RewardCreate toService() {
		return RewardCreate.builder()
			.title(title)
			.description(description)
			.price(price)
			.expectedSendDate(expectedSendDate)
			.deliveryFee(deliveryFee)
			.options(options)
			.optionStocks(optionStocks)
			.build();
	}
}
