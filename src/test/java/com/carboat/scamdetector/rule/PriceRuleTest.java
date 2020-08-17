package com.carboat.scamdetector.rule;

import com.carboat.scamdetector.model.advert.Advert;
import com.carboat.scamdetector.model.advert.Vehicle;
import com.carboat.scamdetector.service.QuotationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.lang.Integer.MAX_VALUE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceRuleTest {
    @InjectMocks
    private PriceRule underTest;

    @Mock
    private QuotationService quotationService;

    @ParameterizedTest
    @ValueSource(ints = {0, 79, 121, MAX_VALUE})
    @DisplayName("It should return scam for prices outside of range")
    void testOutsideOfRange(int price){
        var advert = Advert.anAdvert().withPrice(price).withVehicle(Vehicle.aVehicle().build()).build();

        when(quotationService.getValue(advert.getVehicle()))
                .thenReturn(100);

        var result = underTest.isPotentialScam(advert);

        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {80, 100, 120})
    @DisplayName("It should return non scam for prices inside of range")
    void testInsideOfRange(int price){
        var advert = Advert.anAdvert().withPrice(price).withVehicle(Vehicle.aVehicle().build()).build();

        when(quotationService.getValue(advert.getVehicle()))
                .thenReturn(100);

        var result = underTest.isPotentialScam(advert);

        assertFalse(result);
    }
}