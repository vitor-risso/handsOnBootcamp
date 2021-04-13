/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.pokedex.persistence

import com.skydoves.pokedex.utils.MockUtil.mockPokemon
import com.skydoves.pokedex.utils.MockUtil.mockPokemonList
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class PokemonDaoTest : LocalDatabase() {

  private lateinit var pokemonDao: PokemonDao

  @Before
  fun init() {
    pokemonDao = db.pokemonDao()
  }

  @Test
  fun insertAndLoadPokemonListTest() = runBlocking {
    val mockDataList = mockPokemonList()
    pokemonDao.insertPokemonList(mockDataList)

    val loadFromDB = pokemonDao.getPokemonList(page_ = 0)
    assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

    val mockData = mockPokemon()
    assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
  }
}
