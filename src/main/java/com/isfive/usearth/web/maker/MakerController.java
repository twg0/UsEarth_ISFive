package com.isfive.usearth.web.maker;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.FileImageService;
import com.isfive.usearth.domain.maker.dto.MakerResponse;
import com.isfive.usearth.domain.maker.service.MakerService;
import com.isfive.usearth.web.maker.dto.MakerUpdate;
import com.isfive.usearth.web.maker.dto.MakerUpdateRequest;
import com.isfive.usearth.web.maker.dto.register.CorporateRegister;
import com.isfive.usearth.web.maker.dto.register.IndividualRegister;
import com.isfive.usearth.web.maker.dto.register.PersonalRegister;
import com.isfive.usearth.web.maker.dto.register_request.BusinessMakerRequest;
import com.isfive.usearth.web.maker.dto.register_request.MakerRegisterRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/makers")
@RequiredArgsConstructor
public class MakerController {

    private final MakerService makerService;
    private final FileImageService fileImageService;

    @PostMapping("/individual")
    public ResponseEntity<String> createIndividual(
            @RequestPart("MakerRegisterRequest") MakerRegisterRequest request,
            @RequestPart("profileImage") MultipartFile profileImage,
            @RequestPart("submitFile") MultipartFile submitFile,
            @RequestPart("idCard") MultipartFile idCard
            ) throws IOException {
        FileImage savedprofileImage = fileImageService.createFileImage(profileImage);
        FileImage savedSubmitFile = fileImageService.createFileImage(submitFile);
        FileImage savedIdCard = fileImageService.createFileImage(idCard);

        IndividualRegister register = IndividualRegister.createIndividualRegister(
                request,
                savedprofileImage,
                savedSubmitFile,
                savedIdCard);

        makerService.createIndividualBy(register);

        return ResponseEntity.ok("메이커 신청이 완료되었습니다.");
    }

    @PostMapping("/personal-business")
    public ResponseEntity<String> createPersonalBusiness(
            @RequestPart("BusinessMakerRequest") BusinessMakerRequest request,
            @RequestPart("profileImage") MultipartFile profileImage,
            @RequestPart("submitFile") MultipartFile submitFile,
            @RequestPart("registration") MultipartFile registration
    ) throws IOException {
        FileImage savedprofileImage = fileImageService.createFileImage(profileImage);
        FileImage savedSubmitFile = fileImageService.createFileImage(submitFile);
        FileImage savedRegistration = fileImageService.createFileImage(registration);

        PersonalRegister register = PersonalRegister.createPersonalRegister(
                request,
                savedprofileImage,
                savedSubmitFile,
                savedRegistration);
        makerService.createPersonalBusinessBy(register);

        return ResponseEntity.ok("메이커 신청이 완료되었습니다.");
    }

    @PostMapping("/corporate-business")
    public ResponseEntity<String> createCorporateBusiness(
            @RequestPart("BusinessMakerRequest") BusinessMakerRequest request,
            @RequestPart("profileImage") MultipartFile profileImage,
            @RequestPart("submitFile") MultipartFile submitFile,
            @RequestPart("registration") MultipartFile registration,
            @RequestPart("corporateSealCertificate") MultipartFile corporateSealCertificate
    ) throws IOException {
        FileImage savedprofileImage = fileImageService.createFileImage(profileImage);
        FileImage savedSubmitFile = fileImageService.createFileImage(submitFile);
        FileImage savedRegistration = fileImageService.createFileImage(registration);
        FileImage savedCorporateSealCertificate = fileImageService.createFileImage(corporateSealCertificate);

        CorporateRegister register = CorporateRegister.createCorporateRegister(
                request,
                savedprofileImage,
                savedSubmitFile,
                savedRegistration,
                savedCorporateSealCertificate);
        makerService.createCorporateBusinessBy(register);

        return ResponseEntity.ok("메이커 신청이 완료되었습니다.");
    }

    @GetMapping("/{makerId}")
    public ResponseEntity<MakerResponse> getMaker(
            @PathVariable("makerId") Long id
    ) {
        MakerResponse response = makerService.readMakerById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{makerId}")
    public ResponseEntity<MakerResponse> updateMaker(
            @PathVariable("makerId") Long id,
            @RequestBody MakerUpdateRequest request
            ) {
        MakerUpdate makerUpdate = MakerUpdate.toMakerUpdate(request);
       MakerResponse response = makerService.updateMakerById(id,makerUpdate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{makerId}")
    public ResponseEntity<String> deleteMaker(
            @PathVariable("makerId") Long id
    ) {
        makerService.removeMakerById(id);
        return ResponseEntity.ok("메이커가 삭제되었습니다.");
    }

}
