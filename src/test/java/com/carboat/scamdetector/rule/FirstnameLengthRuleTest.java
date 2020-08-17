package com.carboat.scamdetector.rule;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.carboat.scamdetector.model.advert.Advert.anAdvert;
import static com.carboat.scamdetector.model.advert.Contacts.aContacts;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FirstnameLengthRuleTest {
    private final FirstnameLengthRule underTest = new FirstnameLengthRule();

    @ParameterizedTest
    @MethodSource
    void testRule(String testFirstName, boolean expectedResult){
        var advert = anAdvert().withContacts(aContacts().withFirstName(testFirstName).build()).build();

        var result = underTest.isPotentialScam(advert);

        assertEquals(expectedResult, result);
    }

    static Stream<Arguments> testRule(){
        return Stream.of(
                arguments(null, true),
                arguments("", true),
                arguments(" ", true),
                arguments("a", true),
                arguments("ab", false)
        );
    }

}