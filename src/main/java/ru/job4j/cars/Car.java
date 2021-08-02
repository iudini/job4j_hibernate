package ru.job4j.cars;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner",
            joinColumns = {@JoinColumn(
                    name = "car_id", nullable = false, updatable = false,
                    foreignKey = @ForeignKey(name = "CAR_ID_FK"))
            },
            inverseJoinColumns = {@JoinColumn(
                    name = "driver_id", nullable = false, updatable = false,
                    foreignKey = @ForeignKey(name = "DRIVER_ID_FK"))
            }
    )
    private Set<Driver> drivers;

    public Car() {
    }

    public Car(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String model) {
        this.name = model;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}