package br.com.raywenderlich.flighter.repository

import androidx.lifecycle.LiveData
import br.com.raywenderlich.flighter.database.entity.Flight

interface FlightRepository {
    fun insertFlight(flight: Flight)

    fun getFlightResults(departureCity: String, arrivalCity: String): List<Flight>

    fun deleteFlight(flight: Flight)

    fun checkAvailability(passengers: Int, flightId:Long): Boolean
}