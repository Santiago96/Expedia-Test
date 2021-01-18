package com.globant.flights.services;

import com.globant.flights.dto.FlightDTO;
import com.globant.flights.entities.Flight;
import com.globant.flights.repositories.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class FlightServiceTest {

    @InjectMocks
    private FlightService flightService;

    @Mock
    private FlightRepository flightRepository;

    private List<FlightDTO> flightDTOList;

    private List<Flight> mockFlightDTOList;

    final static int PLUS_HOURS = 6;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockFlightDTOList = getMockFlightDTOList();

    }

    private List<Flight> getMockFlightDTOList() {
        Flight flight1 = new Flight(1L, "Air Canada", "8099", LocalDate.now(), LocalTime.parse("07:30"));
        Flight flight2 = new Flight(2L, "United Airline", "6115", LocalDate.now(), LocalTime.parse("10:30"));
        Flight flight3 = new Flight(3L, "WestJet", "6456", LocalDate.now(), LocalTime.parse("12:30"));
        Flight flight4 = new Flight(4L, "Delta", "8099", LocalDate.now(), LocalTime.parse("15:00"));


        return Arrays.asList(flight1, flight2, flight3, flight4);
    }

    @Test
    public void itShouldReturnListOfTwoFlightsWithInitialTimeOfSix() {

        LocalTime initialTime = LocalTime.of(6, 0);
        LocalTime limitTime = initialTime.plusHours(PLUS_HOURS);

        when(flightRepository.getAllByDepartureTimeGreaterThanEqualAndDepartureTimeLessThanEqual(initialTime, limitTime))
                .thenReturn(
                        Optional.of(mockFlightDTOList.stream().filter(flight -> flight.getDepartureTime().isBefore(limitTime)).collect(Collectors.toList()))
                );
        flightDTOList = flightService.getFlightsByDepartureTime(initialTime);

        assertEquals(2, flightDTOList.size());
    }

    @Test
    public void itShouldReturnEmptyListOfFlights() {

        when(flightRepository.getAllByDepartureTimeGreaterThanEqualAndDepartureTimeLessThanEqual(any(), any())).thenReturn(Optional.empty());
        flightDTOList = flightService.getFlightsByDepartureTime(LocalTime.now());

        assertEquals(Boolean.TRUE, flightDTOList.isEmpty());
    }
}