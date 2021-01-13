package com.globant.flights.services;


import com.globant.flights.dto.FlightDTO;
import com.globant.flights.entities.Flight;
import com.globant.flights.repositories.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FlightService {

    private FlightRepository flightRepository;

    /*
    Method that will consult from the repository and based on the result, it will send an Array of FlightDTO or an empty List
     */

    public List<FlightDTO> getFlightsByDepartureTime(LocalTime departureTime) {
        Optional<List<Flight>> flightList = flightRepository.getAllByDepartureTimeGreaterThanEqualAndDepartureTimeLessThanEqual(departureTime, departureTime.plusHours(6));
        return flightList.isPresent() ? transformEntityToDTO(flightList.get()) : Collections.emptyList();
    }

    /*
    Method that will build the final List<FlightDTO> that will be the response in the request.
     */
    private List<FlightDTO> transformEntityToDTO(List<Flight> flights) {
        List<FlightDTO> flightDTOList = new ArrayList<>();

        flights.forEach(
                flight -> flightDTOList.add(
                        new FlightDTO(getFlightDisplay(flight.getAirlineName(), flight.getFlightNumber()), getTimeFormat(flight.getDepartureTime()))
                )
        );
        return flightDTOList;
    }

    /*
    Method to give format to time with AM or PM value also with a 12H format
     */
    private String getTimeFormat(LocalTime departureTime) {
        return departureTime.format(DateTimeFormatter.ofPattern("hh:mm a")).toUpperCase();
    }
    /*
    Method to give format for the flightDTO attribute flight which will be displayed in the response
     */
    private String getFlightDisplay(String airlineName, String flightNumber) {
        return  airlineName + " " + flightNumber;
    }
}
