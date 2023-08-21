package com.isfive.usearth.domain.maker.service;

import com.isfive.usearth.domain.maker.entity.*;
import com.isfive.usearth.domain.maker.repository.CorporateBusinessRepository;
import com.isfive.usearth.domain.maker.repository.IndividualRepository;
import com.isfive.usearth.domain.maker.repository.MakerRepository;
import com.isfive.usearth.domain.maker.repository.PersonalBusinessRepository;
import com.isfive.usearth.web.maker.dto.MakerRegister;
import com.isfive.usearth.web.maker.dto.MakerResponse;
import com.isfive.usearth.web.maker.dto.MakerUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MakerService {
    private final MakerRepository makerRepository;
    private final IndividualRepository individualRepository;
    private final CorporateBusinessRepository corporateBusinessRepository;
    private final PersonalBusinessRepository personalBusinessRepository;

    @Value("${file.upload-dir}")
    private String uploadPath;

    public void createMakerBy(
            MakerRegister makerRegister) {
        String profileImg = saveImage(makerRegister.getProfileImage());
        String submitFile = saveImage(makerRegister.getSubmitFile());
        String idImg = saveImage(makerRegister.getIdCard());
        String registration = saveImage(makerRegister.getRegistration());
        String corporateSealCertificate = saveImage(makerRegister.getCorporateSealCertificate());


        if (makerRegister.getMakerType().equals("개인")) {
            Individual individual = Individual.builder()
                    .name(makerRegister.getName())
                    .email(makerRegister.getEmail())
                    .profileImage(profileImg)
                    .phone(makerRegister.getPhone())
                    .submitFile(submitFile)
                    .idCard(idImg)
                    .build();
            individualRepository.save(individual);
        } else if (makerRegister.getMakerType().equals("개인사업자")) {
            BusinessInformation businessInformation =
                    BusinessInformation.builder()
                            .registrationNumber(makerRegister.getRegistrationNumber())
                            .corporateName(makerRegister.getCorporateName())
                            .registration(registration)
                            .build();
            PersonalBusiness personalBusiness =
                    PersonalBusiness.builder()
                            .name(makerRegister.getName())
                            .email(makerRegister.getEmail())
                            .profileImage(profileImg)
                            .phone(makerRegister.getPhone())
                            .submitFile(submitFile)
                            .businessInformation(businessInformation)
                            .build();
            personalBusinessRepository.save(personalBusiness);

        } else if (makerRegister.getMakerType().equals("법인사업자")) {
            BusinessInformation businessInformation =
                    BusinessInformation.builder()
                            .registrationNumber(makerRegister.getRegistrationNumber())
                            .corporateName(makerRegister.getCorporateName())
                            .registration(registration)
                            .build();
            CorporateBusiness corporateBusiness =
                    CorporateBusiness.builder()
                            .name(makerRegister.getName())
                            .email(makerRegister.getEmail())
                            .profileImage(profileImg)
                            .phone(makerRegister.getPhone())
                            .submitFile(submitFile)
                            .businessInformation(businessInformation)
                            .corporateSealCertificate(corporateSealCertificate)
                            .build();
            corporateBusinessRepository.save(corporateBusiness);

        }
    }

    public MakerResponse readMakerById(Long id) {
        return MakerResponse.fromEntity(makerRepository.findByIdOrThrow(id));
    }

    public MakerResponse updateMakerById(Long id, MakerUpdate makerUpdate) {
        Maker maker = makerRepository.findByIdOrThrow(id);
        maker.update(makerUpdate);
        return MakerResponse.fromEntity(makerRepository.save(maker));
    }

    public void removeMakerById(Long id) {
        Maker maker = makerRepository.findByIdOrThrow(id);
        maker.delete();
    }





    public String saveImage(MultipartFile multipartFile) {
        String uuid = UUID.randomUUID().toString();
        String uploadFileName = uuid + "_" + multipartFile.getOriginalFilename();
        File file = new File(uploadPath + uploadFileName);
        String filePath = file.getPath();
        try {
            multipartFile.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }


}
