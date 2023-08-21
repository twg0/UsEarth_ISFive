package com.isfive.usearth.domain.maker.dto;

import com.isfive.usearth.domain.maker.entity.Maker;

import lombok.Builder;


@Builder
public class MakerResponse {

    private String name;

    private String profileImage;

    private String phone;

    private String email;


    public static MakerResponse fromEntity(Maker maker){
        return MakerResponse.builder()
                .name(maker.getName())
                .profileImage(maker.getProfileImage())
                .phone(maker.getPhone())
                .email(maker.getEmail())
                .build();
    }
}
