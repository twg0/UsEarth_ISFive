package com.isfive.usearth.web.project.dto;

import java.util.Map;

import com.isfive.usearth.domain.project.dto.RewardCreate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RewardRegister {
	private String title;
	private String description;
	private Integer price;
	private String expectedSendDate;
	private Integer deliveryFee;
	private Map<String, String> options; // <옵션명, 옵션값>
	private Map<String, Integer> optionStocks; // <"검정,M,줄무늬", 5>

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
