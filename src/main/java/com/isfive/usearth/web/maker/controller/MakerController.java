package com.isfive.usearth.web.maker.controller;

import com.isfive.usearth.domain.maker.service.MakerService;
import com.isfive.usearth.web.maker.dto.MakerRegister;
import com.isfive.usearth.web.maker.dto.MakerRegisterRequest;
import com.isfive.usearth.web.maker.dto.MakerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
