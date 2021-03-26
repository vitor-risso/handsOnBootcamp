package br.com.raywenderlich.flighter.repository

import androidx.lifecycle.LiveData
import br.com.raywenderlich.flighter.app.FlighterApplication
import br.com.raywenderlich.flighter.dao.FlightDAO
import br.com.raywenderlich.flighter.database.entity.Flight

class FlightRepositoryImpl : FlightRepository {

    private lateinit var flightDao: FlightDAO // by lazy { FlighterApplication.database.flightDAO() }

    fun startDao(dao:FlightDAO){
        flightDao = dao
    }

    override fun insertFlight(flight: Flight) {
        flightDao.insertFlight(flight)
    }

    override fun getFlightResults(
        departureCity: String,
        arrivalCity: String
    ): List<Flight> = flightDao.getFlightResults(departureCity, arrivalCity)

    override fun deleteFlight(flight: Flight) {
       flightDao.deleteFlight(flight)
    }

    override fun checkAvailability(passengers: Int, flightId: Long): Boolean {
        val flight = getFlight(flightId)

        if(flight == null){
            return false
        }

        return flight.totalPassengers + passengers <= flight.passengersLimit
    }

    private fun getFlight(id: Long): Flight?{
        return flightDao.get(id)

    }
}