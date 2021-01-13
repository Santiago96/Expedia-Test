package com.globant.flights.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
This class was created to define the structure to display in the requests,
the flight class is the entity that will be mapped with the db entity but not
the final class for presenting or exposing in the requests

As the requirements asked; there were defined just two attributes, flight will be the concatenation of
airlineName and flightNumber.

 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {

    private String flight;

    private String departure;
}
