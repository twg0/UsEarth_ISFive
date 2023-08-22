package com.isfive.usearth.web.maker.dto.register;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.maker.entity.Individual;
import com.isfive.usearth.web.maker.dto.register_request.IndividualRegisterRequest;

public class IndividualRegister extends MakerRegister {
    private FileImage idCard;

    private IndividualRegister(IndividualRegisterRequest request,
                              FileImage profileImage,
                              FileImage submitFile,
                              FileImage idCard) {
        super(request, profileImage, submitFile);
        this.idCard = idCard;
    }

    public static IndividualRegister createIndividualRegister(IndividualRegisterRequest request,
                                                              FileImage profileImage,
                                                              FileImage submitFile,
                                                              FileImage idCard) {
        return new IndividualRegister(request, profileImage, submitFile, idCard);
    }

    public Individual toEntity(){
        return Individual.builder()
                .name(this.getName())
                .profileImage(this.getProfileImage().getStoredName())
                .phone(this.getPhone())
                .email(this.getEmail())
                .submitFile(this.getSubmitFile().getStoredName())
                .idCard(this.idCard.getStoredName())
                .build();

    }
}
