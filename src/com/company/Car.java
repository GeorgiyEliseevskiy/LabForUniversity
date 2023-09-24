package com.company;

import java.util.Objects;

public class Car {
    private int id;
    private String model;

    public Car(int id, String model) {
        this.id = id;
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model);
    }
}
