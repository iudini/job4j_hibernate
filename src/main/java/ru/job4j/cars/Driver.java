package ru.job4j.cars;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner",
            joinColumns = {@JoinColumn(
                    name = "driver_id", nullable = false, updatable = false,
                    foreignKey = @ForeignKey(name = "CAR_ID_FK"))
            },
            inverseJoinColumns = {@JoinColumn(
                    name = "car_id", nullable = false, updatable = false,
                    foreignKey = @ForeignKey(name = "DRIVER_ID_FK"))
            }
    )
    private Set<Car> cars;

    public Driver(String name) {
        this.name = name;
    }

    public Driver() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id == driver.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}