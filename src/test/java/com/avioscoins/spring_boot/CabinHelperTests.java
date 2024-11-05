package com.avioscoins.spring_boot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.avioscoins.spring_boot.Components.CabinHelper;

@SpringBootTest
@AutoConfigureMockMvc
public class CabinHelperTests {

    @Autowired
    private CabinHelper cabinHelper;
    
    Map<String, Double> testValue = new HashMap<String, Double>();
    {
        testValue.put("W", 120.0);
    }

    @Test
    void returnsCabinCoinsForCabin() throws Exception {
        Map<String, Double> coins = cabinHelper.getCabinBonus("W", 100.0);
        assertEquals(coins, testValue);
    }

    @Test
    void errorsWithWrongCabinCode() throws Exception {
        InvalidParameterException exception = assertThrows(InvalidParameterException.class, () -> cabinHelper.getCabinBonus("D", 100.0));
        assertEquals("Invalid cabin code input: D", exception.getMessage());
    }

}
