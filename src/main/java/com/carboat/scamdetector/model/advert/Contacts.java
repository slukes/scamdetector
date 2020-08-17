package com.carboat.scamdetector.model.advert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

@JsonDeserialize(builder = Contacts.ContactsBuilder.class)
public class Contacts {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Phone phone1;

    private Contacts(String firstName, String lastName, String email, Phone phone1) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone1 = phone1;
    }

    public static ContactsBuilder aContacts(){
        return new ContactsBuilder();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Phone getPhone1() {
        return phone1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacts contacts = (Contacts) o;
        return Objects.equals(firstName, contacts.firstName) &&
                Objects.equals(lastName, contacts.lastName) &&
                Objects.equals(email, contacts.email) &&
                Objects.equals(phone1, contacts.phone1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, phone1);
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone1=" + phone1 +
                '}';
    }

    @JsonPOJOBuilder
    public static class ContactsBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private Phone phone1;

        public ContactsBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ContactsBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ContactsBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ContactsBuilder withPhone1(Phone phone1) {
            this.phone1 = phone1;
            return this;
        }

        public Contacts build() {
            return new Contacts(firstName, lastName, email, phone1);
        }
    }
}
