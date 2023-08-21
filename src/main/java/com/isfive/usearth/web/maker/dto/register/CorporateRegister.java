package com.isfive.usearth.web.maker.dto.register;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.web.maker.dto.register_request.CorporateRegisterRequest;

public class CorporateRegister extends MakerRegister {
    private String registrationNumber;
    private String corporateName;
    private FileImage registration;
    private FileImage corporateSealCertificate;

    private CorporateRegister(CorporateRegisterRequest request,
                              FileImage profileImage,
                              FileImage submitFile,
                              FileImage registration,
                              FileImage corporateSealCertificate) {
        super(request, profileImage, submitFile);
        this.registrationNumber = request.getRegistrationNumber();
        this.corporateName = request.getCorporateName();
        this.registration = registration;
        this.corporateSealCertificate = corporateSealCertificate;
    }

    public static CorporateRegister createCorporateRegister(CorporateRegisterRequest request, FileImage profileImage, FileImage submitFile, FileImage registration, FileImage corporateSealCertificate) {
        return new CorporateRegister(request, profileImage, submitFile, registration, corporateSealCertificate);
    }
}
