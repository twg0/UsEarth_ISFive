package com.isfive.usearth.web.funding.dto;

import com.isfive.usearth.domain.funding.dto.DeliveryRegister;
import com.isfive.usearth.domain.funding.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRequest {

    private String name;
    private String phone;
    private Address address;

    public DeliveryRegister toServiceDto() {
        return new DeliveryRegister(name, phone, address);
    }
}
