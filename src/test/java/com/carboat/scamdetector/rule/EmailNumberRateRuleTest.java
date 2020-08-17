package com.carboat.scamdetector.rule;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.carboat.scamdetector.model.advert.Advert.anAdvert;
import static com.carboat.scamdetector.model.advert.Contacts.aContacts;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class EmailNumberRateRuleTest {
    private final EmailNumberRateRule underTest = new EmailNumberRateRule();

    @ParameterizedTest
    @MethodSource
    void testRule(String email, boolean expectedResult){
        var advert = anAdvert().withContacts(aContacts().withEmail(email).build()).build();

        var result = underTest.isPotentialScam(advert);

        assertEquals(expectedResult, result);
    }

    static Stream<Arguments> testRule(){
        return Stream.of(
                arguments("", true),
                arguments(null, true),
                arguments("1@test.com", true),
                arguments("1234abchef@test.com", true),
                arguments("123abchefh@test.com", false),
                arguments("test@test.com", false)
        );
    }
}