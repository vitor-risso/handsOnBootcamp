package br.com.raywenderlich.flighter.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.raywenderlich.flighter.database.DatabaseConstants.FLIGHT_TABLE_NAME
import br.com.raywenderlich.flighter.database.entity.Flight

@Dao
interface FlightDAO {

    @Insert
    fun insertFlight(flight: Flight)

    @Query("SELECT * FROM $FLIGHT_TABLE_NAME WHERE departure_city = :departureCity AND arrival_city = :arrivalCity")
    fun getFlightResults(departureCity: String, arrivalCity: String): List<Flight>

    @Delete
    fun deleteFlight(flight: Flight)

//    @Query("SELECT * FROM $FLIGHT_TABLE_NAME WHERE pass")
//    fun checkAvailability(passengers: Int, flightId: Long)

    @Query("SELECT * FROM $FLIGHT_TABLE_NAME WHERE id = :id")
    fun get(id: Long): Flight?
}