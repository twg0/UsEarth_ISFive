package com.isfive.usearth.domain.funding.entity;

import static com.isfive.usearth.domain.funding.entity.DeliveryStatus.*;

import com.isfive.usearth.domain.funding.dto.DeliveryRegister;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Funding funding;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Embedded
    private Address address;

    private String name;

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
}
