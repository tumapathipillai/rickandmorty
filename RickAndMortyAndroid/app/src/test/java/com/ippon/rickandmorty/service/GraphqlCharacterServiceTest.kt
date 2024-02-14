package com.ippon.rickandmorty.service

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.annotations.ApolloExperimental
import com.apollographql.apollo3.api.Error as ApolloError
import com.apollographql.apollo3.testing.QueueTestNetworkTransport
import com.apollographql.apollo3.testing.enqueueTestResponse
import com.ippon.rickandmorty.api.ApiResponse
import com.ippon.rickandmorty.api.ListResponse
import com.ippon.rickandmorty.apollo.DetailCharacterQuery
import com.ippon.rickandmorty.apollo.ListCharacterQuery
import com.ippon.rickandmorty.apollo.type.FilterCharacter
import com.ippon.rickandmorty.models.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class GraphqlCharacterServiceTest {
    @OptIn(ApolloExperimental::class)
    val fakeApolloClient: ApolloClient =
        ApolloClient.Builder().networkTransport(QueueTestNetworkTransport()).build()

    private val characterService = GraphqlCharacterService(fakeApolloClient)

    @OptIn(ApolloExperimental::class)
    @Test
    fun getCharacterById_ReturnsError() {
        runBlocking {
            fakeApolloClient.enqueueTestResponse(
                operation = DetailCharacterQuery("1"),
                errors = listOf(
                    ApolloError(
                        message = "An unknown error occurred",
                        extensions = null,
                        locations = null,
                        nonStandardFields = null,
                        path = null,
                    )
                )
            )

            val response = characterService.getCharacterById(1)

            assert(response is ApiResponse.Error)
            assertEquals(
                (response as ApiResponse.Error).error,
                "Error caught when querying : An unknown error occurred"
            )
        }
    }

    @OptIn(ApolloExperimental::class)
    @Test
    fun getCharacterById_ReturnsData() {
        runBlocking {
            fakeApolloClient.enqueueTestResponse(
                operation = DetailCharacterQuery("1"),
                data = DetailCharacterQuery.Data(
                    character = DetailCharacterQuery.Character(
                        id = "1",
                        name = "Character1",
                        image = "Character Image",
                        status = "alive",
                        gender = "male",
                        episode = listOf(),
                    )
                )
            )

            val response = characterService.getCharacterById(1)

            assert(response is ApiResponse.Data)
            assertEquals(
                (response as ApiResponse.Data).data,
                DetailCharacter(
                    id = 1,
                    name = "Character1",
                    image = "Character Image",
                    status = Status.ALIVE,
                    gender = Gender.MALE,
                    episodes = listOf()
                )
            )
        }
    }

    @OptIn(ApolloExperimental::class)
    @Test
    fun getCharacterById_ReturnsNull() {
        runBlocking {
            fakeApolloClient.enqueueTestResponse(
                operation = DetailCharacterQuery("1"),
                data = null
            )

            val response = characterService.getCharacterById(1)

            assert(response is ApiResponse.Error)
            assertEquals(
                (response as ApiResponse.Error).error,
                "Could not get the character with id: 1"
            )
        }

        runBlocking {
            fakeApolloClient.enqueueTestResponse(
                operation = DetailCharacterQuery("1"),
                data = DetailCharacterQuery.Data(
                    character = null
                )
            )

            val response = characterService.getCharacterById(1)

            assert(response is ApiResponse.Error)
            assertEquals(
                (response as ApiResponse.Error).error,
                "Could not get the character with id: 1"
            )
        }

        runBlocking {
            fakeApolloClient.enqueueTestResponse(
                operation = DetailCharacterQuery("1"),
                data = DetailCharacterQuery.Data(
                    character = DetailCharacterQuery.Character(
                        id = null,
                        name = null,
                        image = null,
                        status = null,
                        gender = null,
                        episode = listOf(),
                    )
                )
            )

            val response = characterService.getCharacterById(1)

            assert(response is ApiResponse.Error)
            assertEquals(
                (response as ApiResponse.Error).error,
                "Could not get the character with id: 1"
            )
        }
    }

    @OptIn(ApolloExperimental::class)
    @Test
    fun getCharacterById_ThrowParsingError() {
        runBlocking {
            fakeApolloClient.enqueueTestResponse(
                operation = DetailCharacterQuery("1"),
                data = DetailCharacterQuery.Data(
                    character = DetailCharacterQuery.Character(
                        id = "error",
                        name = "name",
                        image = "image",
                        status = "alive",
                        gender = "male",
                        episode = listOf(),
                    )
                )
            )

            val response = characterService.getCharacterById(1)

            assert(response is ApiResponse.Error)
            assertEquals(
                (response as ApiResponse.Error).error,
                "java.lang.NumberFormatException: For input string: \"error\""
            )
        }
    }

    @OptIn(ApolloExperimental::class)
    @Test
    fun listCharacters_ReturnsError() {
        runBlocking {
            fakeApolloClient.enqueueTestResponse(
                operation = ListCharacterQuery(
                    page = 1,
                    filter = FilterCharacter()
                ),
                errors = listOf(
                    ApolloError(
                        message = "An unknown error occurred",
                        extensions = null,
                        locations = null,
                        nonStandardFields = null,
                        path = null,
                    )
                )
            )

            val response = characterService.listCharacters(1, Filter(gender = null, status = null))

            assert(response is ApiResponse.Error)
            assertEquals(
                (response as ApiResponse.Error).error,
                "Error caught when querying : An unknown error occurred"
            )
        }
    }

    @OptIn(ApolloExperimental::class)
    @Test
    fun listCharacters_ReturnNull() {
        runBlocking {
            fakeApolloClient.enqueueTestResponse(
                operation = ListCharacterQuery(
                    page = 1,
                    filter = FilterCharacter()
                ),
            )

            val response = characterService.listCharacters(1, Filter(gender = null, status = null))

            assert(response is ApiResponse.Data)
            assertEquals(
                (response as ApiResponse.Data).data,
                ListResponse(emptyList())
            )
        }

        runBlocking {
            fakeApolloClient.enqueueTestResponse(
                operation = ListCharacterQuery(
                    page = 1,
                    filter = FilterCharacter()
                ),
            )

            val response = characterService.listCharacters(1, Filter(gender = null, status = null))

            assert(response is ApiResponse.Data)
            assertEquals(
                (response as ApiResponse.Data).data,
                ListResponse(emptyList())
            )
        }

        runBlocking {
            fakeApolloClient.enqueueTestResponse(
                operation = ListCharacterQuery(
                    page = 1,
                    filter = FilterCharacter()
                ),
                data = ListCharacterQuery.Data(
                    characters = null
                )
            )

            val response = characterService.listCharacters(1, Filter(gender = null, status = null))

            assert(response is ApiResponse.Data)
            assertEquals(
                (response as ApiResponse.Data).data,
                ListResponse(emptyList())
            )
        }
    }

    @OptIn(ApolloExperimental::class)
    @Test
    fun listCharacters_ReturnsData() {
        runBlocking {
            fakeApolloClient.enqueueTestResponse(
                operation = ListCharacterQuery(
                    page = 1,
                    filter = FilterCharacter()
                ),
                data = ListCharacterQuery.Data(
                    characters = ListCharacterQuery.Characters(
                        info = ListCharacterQuery.Info(next = 1),
                        results = listOf(
                            null,
                            ListCharacterQuery.Result(id = null, name = "null", image = "null"),
                            ListCharacterQuery.Result(id = "null", name = "null", image = null),
                            ListCharacterQuery.Result(id = "1", name = null, image = "null"),
                            ListCharacterQuery.Result(
                                id = "2",
                                name = "CharacterName",
                                image = "CharacterImage"
                            )
                        )
                    )
                )
            )

            val response = characterService.listCharacters(0, Filter(gender = null, status = null))

            assert(response is ApiResponse.Data)
            assertEquals(
                (response as ApiResponse.Data).data,
                ListResponse(
                    characters = listOf(
                        SimpleCharacter(
                            id = 2,
                            name = "CharacterName",
                            image = "CharacterImage"
                        )
                    ),
                    nextPage = 1
                )
            )
        }
    }
}