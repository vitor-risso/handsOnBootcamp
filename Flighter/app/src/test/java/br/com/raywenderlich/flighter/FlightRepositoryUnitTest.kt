package br.com.raywenderlich.flighter

import br.com.raywenderlich.flighter.dao.FlightDAO
import br.com.raywenderlich.flighter.database.entity.Flight
import br.com.raywenderlich.flighter.repository.FlightRepositoryImpl
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test

class FlightRepositoryUnitTest {

    @MockK
    lateinit var flightDao: FlightDAO

    @InjectMockKs(overrideValues = true)
    var flightRepository: FlightRepositoryImpl = FlightRepositoryImpl()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        flightRepository.startDao(flightDao)
    }

    @Test
    fun `should  insert a flight on database`() {
        every { flightRepository.insertFlight(any()) }

        justRun { flightDao.insertFlight(any()) }

        val flight = mockk<Flight>()

        flightRepository.insertFlight(flight)

        verify(exactly = 1) { flightRepository.insertFlight(flight) }
    }

    @Test
    fun `should delete a flight from database`() {

        every { flightRepository.deleteFlight(any()) }

        justRun { flightDao.deleteFlight(any()) }

        val flight = mockk<Flight>()
        flightRepository.deleteFlight(flight)

        verify(exactly = 1) { flightRepository.deleteFlight(any()) }
        verify(exactly = 1) { flightDao.deleteFlight(any()) }
    }

    @Test
    fun `should get flight results`() {

        val departureCity = "RJ"
        val arrivalCity = "SP"

        every { flightDao.getFlightResults(departureCity, arrivalCity) } returns listOf<Flight>()

        flightRepository.getFlightResults(departureCity, arrivalCity)

        verify(exactly = 1) { flightDao.getFlightResults(departureCity, arrivalCity) }
    }

    @Test
    fun `shoul return null if has no flight results`(){
        every { flightDao.getFlightResults(any(),any()) } returns emptyList<Flight>()

        val res = flightRepository.getFlightResults("SP", "RJ")

        verify(exactly = 1) { flightDao.getFlightResults(any(),any()) }

        assertEquals(emptyList<Flight>(), res)
    }

    @Test
    fun `when check fligth availability should return true when us empty`(){
        val flightId = 123L
        val flight = mockk<Flight>(){
            every { passengersLimit } returns 100
            every { totalPassengers } returns  0
            every { id } returns flightId
        }

        every { flightDao.get(flightId) } returns flight

        val response = flightRepository.checkAvailability(10, flight.id)

        assertTrue(response)
    }

    @Test
    fun `when checko flight availability should return false when is full`(){
        val flightId = 123L
        val flight = mockk<Flight>(){
            every { passengersLimit } returns 100
            every { totalPassengers } returns  100
            every { id } returns flightId
        }

        every { flightDao.get(flightId) } returns flight

        val response = flightRepository.checkAvailability(10, flight.id)

        assertFalse(response)
    }

    @Test
    fun `when check fligth availabiity should return true with possible amount of passengers`(){
        val flightId = 123L
        val flight = mockk<Flight>(){
            every { passengersLimit } returns 100
            every { totalPassengers } returns  90
            every { id } returns flightId
        }

        every { flightDao.get(flightId) } returns flight

        val response = flightRepository.checkAvailability(10, flight.id)

        assertTrue(response)
    }

    @Test
    fun `when check flight availability should return false with passenger amout is overlimit`(){
        val flightId = 123L
        val flight = mockk<Flight>(){
            every { passengersLimit } returns 100
            every { totalPassengers } returns  93
            every { id } returns flightId
        }

        every { flightDao.get(flightId) } returns flight

        val response = flightRepository.checkAvailability(10, flight.id)

        assertFalse(response)
    }

    var defaultValue = slot<Long>()

    @Test
    fun `when check flight availabillity should return false when flight doesnt exists`(){
        val flight = mockk<Flight>(relaxed = true)

        every { flightDao.get(id = capture(defaultValue)) } returns null

        print(defaultValue.isCaptured)

        val response = flightRepository.checkAvailability(10, flight.id)

        assertFalse(response)
    }
}