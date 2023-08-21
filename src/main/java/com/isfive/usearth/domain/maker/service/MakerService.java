package com.isfive.usearth.domain.maker.service;

import java.io.File;
import java.util.UUID;

import com.isfive.usearth.web.maker.dto.register.CorporateRegister;
import com.isfive.usearth.web.maker.dto.register.IndividualRegister;
import com.isfive.usearth.web.maker.dto.register.PersonalRegister;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.isfive.usearth.domain.maker.dto.MakerResponse;
import com.isfive.usearth.domain.maker.entity.BusinessInformation;
import com.isfive.usearth.domain.maker.entity.CorporateBusiness;
import com.isfive.usearth.domain.maker.entity.Individual;
import com.isfive.usearth.domain.maker.entity.Maker;
import com.isfive.usearth.domain.maker.entity.PersonalBusiness;
import com.isfive.usearth.domain.maker.repository.CorporateBusinessRepository;
import com.isfive.usearth.domain.maker.repository.IndividualRepository;
import com.isfive.usearth.domain.maker.repository.MakerRepository;
import com.isfive.usearth.domain.maker.repository.PersonalBusinessRepository;
import com.isfive.usearth.web.maker.dto.register.MakerRegister;
import com.isfive.usearth.web.maker.dto.MakerUpdate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MakerService {
    private final MakerRepository makerRepository;

    public void createIndividualBy(
            IndividualRegister individualRegister) {

        makerRepository.save(individualRegister.toEntity());
    }

    public void createCorporateBusinessBy(
            CorporateRegister corporateRegister) {

        makerRepository.save(corporateRegister.toEntity());
    }

    public void createPersonalBusinessBy(
            PersonalRegister personalRegister) {

        makerRepository.save(personalRegister.toEntity());
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


}
