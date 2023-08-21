package com.isfive.usearth.web.maker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public ResponseEntity<String> createMaker(
            @RequestBody MakerRegisterRequest makerRegisterRequest) {

        MakerRegister makerRegister = new MakerRegister(makerRegisterRequest);
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
