package com.globant.flights.entities;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/*
I decided to create the entity with
a departureDate and departureTime in different attributes or columns
based in the requirements that dictates the filter by departureTime

Similar situation with airlineName and flightNumber due to those are not values
to mix or combine in a same column

 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "flight")
public class Flight {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    @Column
    private String airlineName;

    @NotNull
    @Column
    private String flightNumber;

    @Column
    private LocalDate departureDate;

    @Column
    private LocalTime departureTime;
}
