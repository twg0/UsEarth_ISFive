package com.isfive.usearth.domain.maker.service;

import com.isfive.usearth.domain.maker.dto.MakerResponse;
import com.isfive.usearth.domain.maker.entity.Maker;
import com.isfive.usearth.domain.maker.repository.MakerRepository;
import com.isfive.usearth.web.maker.dto.MakerUpdate;
import com.isfive.usearth.web.maker.dto.register.CorporateRegister;
import com.isfive.usearth.web.maker.dto.register.IndividualRegister;
import com.isfive.usearth.web.maker.dto.register.PersonalRegister;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MakerService {
    private final MakerRepository makerRepository;

    @Transactional
    public void createIndividualBy(IndividualRegister individualRegister) {
        makerRepository.save(individualRegister.toEntity());
    }

    @Transactional
    public void createCorporateBusinessBy(CorporateRegister corporateRegister) {
        makerRepository.save(corporateRegister.toEntity());
    }

    @Transactional
    public void createPersonalBusinessBy(PersonalRegister personalRegister) {
        makerRepository.save(personalRegister.toEntity());
    }

    public MakerResponse readMakerById(Long id) {
        Maker maker = makerRepository.findByIdOrThrow(id);
        if (maker.getDeletedAt() != null) {
            throw new RuntimeException();
        }
        return MakerResponse.fromEntity(maker);
    }

    @Transactional
    public MakerResponse updateMakerById(Long id, MakerUpdate makerUpdate) {
        Maker maker = makerRepository.findByIdOrThrow(id);
        maker.update(makerUpdate);
        return MakerResponse.fromEntity(makerRepository.save(maker));
    }

    @Transactional
    public void removeMakerById(Long id) {
        Maker maker = makerRepository.findByIdOrThrow(id);
        maker.delete();
    }
}
