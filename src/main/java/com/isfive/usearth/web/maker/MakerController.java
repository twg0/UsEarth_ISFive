package com.isfive.usearth.web.maker;

import com.isfive.usearth.domain.common.FileImageService;
import com.isfive.usearth.web.maker.dto.IndividualRegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.isfive.usearth.domain.maker.dto.MakerResponse;
import com.isfive.usearth.domain.maker.service.MakerService;
import com.isfive.usearth.web.maker.dto.MakerRegister;
import com.isfive.usearth.web.maker.dto.MakerRegisterRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/makers")
@RequiredArgsConstructor
public class MakerController {

    private final MakerService makerService;
    private final FileImageService fileImageService;
    @PostMapping
    public ResponseEntity<String> createMaker(
            @ModelAttribute IndividualRegisterRequest individualRegisterRequest) {

        MakerRegister makerRegister = new MakerRegister(individualRegisterRequest);
        makerService.createMakerBy(makerRegister);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/{makerId}")
    public ResponseEntity<MakerResponse> getMaker(
            @PathVariable("makerId") Long id
    ) {
        MakerResponse response = makerService.readMakerById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }





}
