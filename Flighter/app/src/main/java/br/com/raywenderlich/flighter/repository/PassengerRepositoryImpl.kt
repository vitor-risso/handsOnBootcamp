package br.com.raywenderlich.flighter.repository

import br.com.raywenderlich.flighter.dao.PassengerDAO
import br.com.raywenderlich.flighter.database.entity.Passenger

class PassengerRepositoryImpl : PassengerRepository {

    private lateinit var passengerDAO: PassengerDAO  //by lazy { FlighterApplication.database.passengerDAO() }

    fun startDao(dao: PassengerDAO){
        passengerDAO = dao
    }

    override fun insertPassenger(passenger: Passenger) =
        passengerDAO.insert(passenger)

    override fun getUserId(email: String, password: String): Long? =
        passengerDAO.getUserId(email, password)

    override fun deletePassengerData(passenger: Passenger) {
       passengerDAO.deletePassengerData(passenger)
    }
}