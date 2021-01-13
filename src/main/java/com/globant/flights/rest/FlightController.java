package com.globant.flights.rest;


import com.globant.flights.dto.FlightDTO;
import com.globant.flights.services.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/")
public class FlightController {

    private FlightService flightService;

    /*
    I had to consult how to pass DataTime as a RequestParam due to I was having issues in my request since it threw an exception
    with a conversion type

    As an assumption the RequestParam will be sent with HH:mm format, e.g 06:00, 15:00, etc. For that reason there was not created
    a handled exception for conversion type.

    It will return and ResponseEntity Object with status 200 and as a body the flightDTO with the presentation values required.

    */
    @GetMapping(value = "flights-by-departure-time", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FlightDTO>> getFlightsByDepartureTime(@RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime departureTime) {
        return new ResponseEntity<>(flightService.getFlightsByDepartureTime(departureTime), HttpStatus.OK);
    }
}
