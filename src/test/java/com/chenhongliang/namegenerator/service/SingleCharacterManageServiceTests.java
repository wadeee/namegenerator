package com.chenhongliang.namegenerator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;

@SpringBootTest
public class SingleCharacterManageServiceTests {

    @Autowired
    SingleCharacterManageService singleCharacterManageService;

    @Test
    void getCharacterAmountTest() {
        assertNotNull(singleCharacterManageService.getCharacterAmount());
        assertEquals(singleCharacterManageService.getCharacterAmount(), singleCharacterManageService.getCharacterAmount());
        assertTrue(singleCharacterManageService.getCharacterAmount().getClass().equals(Integer.class));
        assertFalse(!singleCharacterManageService.getCharacterAmount().getClass().equals(Integer.class));
        assertNull(null);
        assertSame(String.class, String.class);
        assertNotSame(singleCharacterManageService.getCharacterAmount(), singleCharacterManageService.getCharacterAmount());
        assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3});
    }
}
