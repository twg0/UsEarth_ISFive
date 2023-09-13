package com.isfive.usearth.domain.funding.entity;

import com.isfive.usearth.domain.funding.dto.DeliveryRegister;
import com.isfive.usearth.exception.BusinessException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.isfive.usearth.domain.funding.entity.DeliveryStatus.*;
import static com.isfive.usearth.exception.ErrorCode.ALREADY_DELIVERY_COMPLETED;
import static com.isfive.usearth.exception.ErrorCode.ALREADY_START_DELIVERY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;

	@Embedded
	private Address address;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, length = 13)
	private String phone;

	@Builder
	private Delivery(DeliveryStatus status, Address address, String name, String phone) {
		this.status = status;
		this.address = address;
		this.name = name;
		this.phone = phone;
	}

	public static Delivery createDelivery(DeliveryRegister deliveryRegister) {
		return Delivery.builder()
			.status(PRODUCT_PREPARING)
			.address(deliveryRegister.getAddress())
			.name(deliveryRegister.getName())
			.phone(deliveryRegister.getPhone())
			.build();
	}

	public void verifyCancelable() {
		if (status == DELIVERY_PREPARING || status == DELIVERING) {
			throw new BusinessException(ALREADY_START_DELIVERY);
		}

		if (status == DELIVERY_COMPLETED) {
			throw new BusinessException(ALREADY_DELIVERY_COMPLETED);
		}
	}
}
