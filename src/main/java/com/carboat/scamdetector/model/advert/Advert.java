package com.carboat.scamdetector.model.advert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@JsonDeserialize(builder = Advert.AdvertBuilder.class)
public class Advert {
    private final Contacts contacts;
    private final OffsetDateTime creationDate;
    private final int price;
    private final List<String> publicationOptions;
    private final String reference;
    private final Vehicle vehicle;

    private Advert(Contacts contacts, OffsetDateTime creationDate, int price,
                  List<String> publicationOptions, String reference, Vehicle vehicle) {
        this.contacts = contacts;
        this.creationDate = creationDate;
        this.price = price;
        this.publicationOptions = publicationOptions;
        this.reference = reference;
        this.vehicle = vehicle;
    }

    public static AdvertBuilder anAdvert(){
        return new AdvertBuilder();
    }

    public Contacts getContacts() {
        return contacts;
    }

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public int getPrice() {
        return price;
    }

    public List<String> getPublicationOptions() {
        return publicationOptions;
    }

    public String getReference() {
        return reference;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advert advert = (Advert) o;
        return price == advert.price &&
                Objects.equals(contacts, advert.contacts) &&
                Objects.equals(creationDate, advert.creationDate) &&
                Objects.equals(publicationOptions, advert.publicationOptions) &&
                Objects.equals(reference, advert.reference) &&
                Objects.equals(vehicle, advert.vehicle);
    }

    @Override
    public String toString() {
        return "Advert{" +
                "contacts=" + contacts +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", publicationOptions=" + publicationOptions +
                ", reference='" + reference + '\'' +
                ", vehicle=" + vehicle +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(contacts, creationDate, price, publicationOptions, reference, vehicle);
    }

    @JsonPOJOBuilder
    public static class AdvertBuilder {
        private Contacts contacts;
        private OffsetDateTime creationDate;
        private int price;
        private List<String> publicationOptions;
        private String reference;
        private Vehicle vehicle;

        private AdvertBuilder(){}

        public AdvertBuilder withContacts(Contacts contacts) {
            this.contacts = contacts;
            return this;
        }

        public AdvertBuilder withCreationDate(OffsetDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public AdvertBuilder withPrice(int price) {
            this.price = price;
            return this;
        }

        public AdvertBuilder withPublicationOptions(List<String> publicationOptions) {
            this.publicationOptions = publicationOptions;
            return this;
        }

        public AdvertBuilder withReference(String reference) {
            this.reference = reference;
            return this;
        }

        public AdvertBuilder withVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        public Advert build() {
            return new Advert(contacts, creationDate, price, publicationOptions, reference, vehicle);
        }
    }
}