package com.carboat.scamdetector.model.advert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

@JsonDeserialize(builder = Vehicle.VehicleBuilder.class)
public class Vehicle {
    private final String make;
    private final String model;
    private final String version;
    private final String category;
    private final String registerNumber;
    private final long mileage;

    private Vehicle(String make, String model, String version, String category,
                   String registerNumber, long mileage) {
        this.make = make;
        this.model = model;
        this.version = version;
        this.category = category;
        this.registerNumber = registerNumber;
        this.mileage = mileage;
    }

    public static VehicleBuilder aVehicle(){
        return new VehicleBuilder();
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getVersion() {
        return version;
    }

    public String getCategory() {
        return category;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public long getMileage() {
        return mileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return mileage == vehicle.mileage &&
                Objects.equals(make, vehicle.make) &&
                Objects.equals(model, vehicle.model) &&
                Objects.equals(version, vehicle.version) &&
                Objects.equals(category, vehicle.category) &&
                Objects.equals(registerNumber, vehicle.registerNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, version, category, registerNumber, mileage);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", version='" + version + '\'' +
                ", category='" + category + '\'' +
                ", registerNumber='" + registerNumber + '\'' +
                ", mileage=" + mileage +
                '}';
    }

    @JsonPOJOBuilder
    public static class VehicleBuilder {
        private String make;
        private String model;
        private String version;
        private String category;
        private String registerNumber;
        private long mileage;

        private VehicleBuilder(){}

        public VehicleBuilder withMake(String make) {
            this.make = make;
            return this;
        }

        public VehicleBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public VehicleBuilder withVersion(String version) {
            this.version = version;
            return this;
        }

        public VehicleBuilder withCategory(String category) {
            this.category = category;
            return this;
        }

        public VehicleBuilder withRegisterNumber(String registerNumber) {
            this.registerNumber = registerNumber;
            return this;
        }

        public VehicleBuilder withMileage(long mileage) {
            this.mileage = mileage;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(make, model, version, category, registerNumber, mileage);
        }
    }
}
