package br.com.raywenderlich.flighter

import br.com.raywenderlich.flighter.dao.PassengerDAO
import br.com.raywenderlich.flighter.database.entity.Passenger
import br.com.raywenderlich.flighter.repository.PassengerRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.justRun
import io.mockk.verify
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class PassengerRepositoryUnitTest {

    @RelaxedMockK
    lateinit var passengerDAO: PassengerDAO

    @InjectMockKs(overrideValues = true)
    var passengerRepository: PassengerRepositoryImpl = PassengerRepositoryImpl()

    @MockK
    lateinit var passenger: Passenger

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should insert a passenger`() {

        justRun { passengerDAO.insert(passenger) }
        justRun { passengerRepository.insertPassenger(passenger) }

        passengerRepository.insertPassenger(passenger)

        verify(exactly = 1) { passengerDAO.insert(passenger) }
        verify(exactly = 1) { passengerRepository.insertPassenger(passenger) }
    }


    @Test
    fun `should delete passenger data`() {
        justRun { passengerDAO.deletePassengerData(passenger) }

        passengerRepository.deletePassengerData(passenger)

        verify(exactly = 1) { passengerDAO.deletePassengerData(passenger) }
    }

    @Test
    fun `should return user id from user email and user pwd`() {
        val email = "vitor@vitor.com"
        val pwd = "12344543"
        val id = 321L

        every { passengerDAO.getUserId(email, pwd) } returns id

        val res = passengerRepository.getUserId(email, pwd)

        TestCase.assertEquals(id, res)

        verify(exactly = 1) { passengerDAO.getUserId(email, pwd) }
    }

    @Test
    fun `should return null if user is invalid`(){
        every { passengerDAO.getUserId(any(),any()) } returns null

        val res = passengerRepository.getUserId("vitor", "123")

        TestCase.assertNull(res)

        verify { passengerDAO.getUserId(any(), any()) }
    }
}