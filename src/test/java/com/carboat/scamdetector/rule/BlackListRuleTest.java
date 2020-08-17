package com.carboat.scamdetector.rule;

import com.carboat.scamdetector.model.advert.Advert;
import com.carboat.scamdetector.model.advert.Vehicle;
import com.carboat.scamdetector.service.BlackListService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlackListRuleTest {
    @InjectMocks
    private BlackListRule underTest;

    @Mock
    private BlackListService blackListService;

    @Test
    @DisplayName("It should return scam for blacklisted car")
    void testBlackListed(){
        var advert = Advert.anAdvert().withVehicle(Vehicle.aVehicle().build()).build();

        when(blackListService.isBlackListed(advert.getVehicle()))
                .thenReturn(true);

        var result = underTest.isPotentialScam(advert);

        assertTrue(result);
    }

    @Test
    @DisplayName("It should return non scam for non blacklisted car")
    void testNonBlackListed(){
        var advert = Advert.anAdvert().withVehicle(Vehicle.aVehicle().build()).build();

        when(blackListService.isBlackListed(advert.getVehicle()))
                .thenReturn(false);

        var result = underTest.isPotentialScam(advert);

        assertFalse(result);
    }


}