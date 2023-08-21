package com.isfive.usearth.web.maker.dto.register;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.maker.entity.Individual;
import com.isfive.usearth.domain.maker.entity.PersonalBusiness;
import com.isfive.usearth.web.maker.dto.register_request.PersonalRegisterRequest;

public class PersonalRegister extends MakerRegister {
    private String registrationNumber;
    private String corporateName;
    private FileImage registration;

    public PersonalRegister(PersonalRegisterRequest request, FileImage profileImage, FileImage submitFile, FileImage registration) {
        super(request, profileImage, submitFile);
        this.registrationNumber = request.getRegistrationNumber();
        this.corporateName = request.getCorporateName();
        this.registration = registration;
    }

    public static PersonalRegister createPersonalRegister(
            PersonalRegisterRequest request,
            FileImage profileImage,
            FileImage submitFile,
            FileImage registration) {
        return new PersonalRegister(request, profileImage, submitFile, registration);
    }

    public PersonalBusiness toEntity() {
        return  null;
    }
}
