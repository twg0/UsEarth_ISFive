package com.isfive.usearth.domain.maker.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MakerRepositoryTest {
    @Autowired
    MakerRepository makerRepository;
    @Test
    void test() {
        makerRepository.findMakerWithCorporate(1L);
    }

}