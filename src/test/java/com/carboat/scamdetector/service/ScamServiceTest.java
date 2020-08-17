package com.carboat.scamdetector.service;

import com.carboat.scamdetector.model.ScamReport;
import com.carboat.scamdetector.rule.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.carboat.scamdetector.model.advert.Advert.anAdvert;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class ScamServiceTest {
    @Mock
    Rule mockRule1;

    @Mock
    Rule mockRule2;

    @Mock
    Rule mockRule3;

    private ScamService underTest;

    @BeforeEach
    void setup(){
        underTest = new ScamService(Set.of(mockRule1, mockRule2, mockRule3));
    }

    @Test
    void testSuccessCase(){
        var advert = anAdvert().withReference("ref").build();
        Stream.of(mockRule1, mockRule2, mockRule3)
                .forEach(mock -> when(mock.isPotentialScam(advert)).thenReturn(false));

        var expectedResult = new ScamReport("ref", false, emptyList());

        var result = underTest.checkAdvert(advert);

        assertEquals(expectedResult, result);

        Stream.of(mockRule1, mockRule2, mockRule3)
                .forEach(mock ->  verify(mock).isPotentialScam(advert));
    }

    @Test
    void testFailureCase(){
        var advert = anAdvert().withReference("ref").build();
        Stream.of(mockRule1, mockRule2)
                .forEach(mock -> when(mock.isPotentialScam(advert)).thenReturn(false));

        when(mockRule3.isPotentialScam(advert)).thenReturn(true);
        when(mockRule3.getName()).thenReturn("rule name");

        var expectedResult = new ScamReport("ref", true, List.of("rule name"));

        var result = underTest.checkAdvert(advert);

        assertEquals(expectedResult, result);

        Stream.of(mockRule1, mockRule2, mockRule3)
                .forEach(mock ->  verify(mock).isPotentialScam(advert));
    }

}