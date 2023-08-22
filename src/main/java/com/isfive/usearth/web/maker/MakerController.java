package com.isfive.usearth.web.maker;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.FileImageService;
import com.isfive.usearth.domain.maker.dto.MakerResponse;
import com.isfive.usearth.domain.maker.service.MakerService;
import com.isfive.usearth.web.maker.dto.MakerUpdate;
import com.isfive.usearth.web.maker.dto.MakerUpdateRequest;
import com.isfive.usearth.web.maker.dto.register.CorporateRegister;
import com.isfive.usearth.web.maker.dto.register.IndividualRegister;
import com.isfive.usearth.web.maker.dto.register.PersonalRegister;
import com.isfive.usearth.web.maker.dto.register_request.CorporateRegisterRequest;
import com.isfive.usearth.web.maker.dto.register_request.IndividualRegisterRequest;
import com.isfive.usearth.web.maker.dto.register_request.PersonalRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/makers")
@RequiredArgsConstructor
public class MakerController {

    private final MakerService makerService;
    private final FileImageService fileImageService;

    @PostMapping("/individual")
    public ResponseEntity<String> createIndividual(@ModelAttribute IndividualRegisterRequest request) {
        FileImage profileImage = fileImageService.createFileImage(request.getProfileImage());
        FileImage submitFile = fileImageService.createFileImage(request.getSubmitFile());
        FileImage idCard = fileImageService.createFileImage(request.getIdCard());

        IndividualRegister register = IndividualRegister.createIndividualRegister(
                request,
                profileImage,
                submitFile,
                idCard);

        makerService.createIndividualBy(register);

        return ResponseEntity.ok("Success");
    }

    @PostMapping("/personal-business")
    public ResponseEntity<String> createPersonalBusiness(@ModelAttribute PersonalRegisterRequest request) {
        FileImage profileImage = fileImageService.createFileImage(request.getProfileImage());
        FileImage submitFile = fileImageService.createFileImage(request.getSubmitFile());
        FileImage registration = fileImageService.createFileImage(request.getRegistration());

        PersonalRegister register = PersonalRegister.createPersonalRegister(
                request,
                profileImage,
                submitFile,
                registration);
        makerService.createPersonalBusinessBy(register);

        return ResponseEntity.ok("Success");
    }

    @PostMapping("/corporate-business")
    public ResponseEntity<String> createCorporateBusiness(@ModelAttribute CorporateRegisterRequest request) {
        FileImage profileImage = fileImageService.createFileImage(request.getProfileImage());
        FileImage submitFile = fileImageService.createFileImage(request.getSubmitFile());
        FileImage registration = fileImageService.createFileImage(request.getRegistration());
        FileImage corporateSealCertificate = fileImageService.createFileImage(request.getCorporateSealCertificate());

        CorporateRegister register = CorporateRegister.createCorporateRegister(
                request,
                profileImage,
                submitFile,
                registration,
                corporateSealCertificate);
        makerService.createCorporateBusinessBy(register);

        return ResponseEntity.ok("Success");
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
    public void deleteMaker(
            @PathVariable("makerId") Long id
    ) {
        makerService.removeMakerById(id);
    }

}
