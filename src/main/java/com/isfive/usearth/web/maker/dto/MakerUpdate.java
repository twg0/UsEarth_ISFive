package com.isfive.usearth.web.maker.dto;


import lombok.Getter;

@Getter
public class MakerUpdate {
    private String phone;
    private String email;

    MakerUpdate(MakerUpdateRequest makerUpdateRequest) {
        this.phone = makerUpdateRequest.getPhone();
        this.email = makerUpdateRequest.getEmail();
    }
}
