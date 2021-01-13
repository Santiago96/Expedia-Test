package com.globant.flights.repositories;

import com.globant.flights.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    /*
    Using the JPA's capability to create queries based on the named method, there was defined a method con query all the flight
    based on a greater or less time, those params are passed in the method definition as a LocalTime that is the same
    type of the column that I want to compare.
     */
    Optional<List<Flight>> getAllByDepartureTimeGreaterThanEqualAndDepartureTimeLessThanEqual(LocalTime greater, LocalTime less);
}
