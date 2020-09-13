package com.raynigon.mco.groundstation

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import java.net.URI

@SpringBootTest(webEnvironment = RANDOM_PORT)
internal class ApplicationTests {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun contextLoads() {
    }

    @Test
    fun sendRecord() {
        val json = "{\"rtt\":236958,\"fuel\":{\"nto\":61,\"hydrazine\":212},\"sensors\":{\"gyroscope0\":{\"x\":0.0002,\"y\":-0.0001,\"z\":0.0006},\"gyroscope1\":{\"x\":-0.0001,\"y\":0.0,\"z\":0.0001},\"sunSensor0\":0.8947,\"sunSensor1\":0.1293},\"energy\":{\"battery\":15.432,\"solar\":489.2},\"computer\":{\"clockSpeed\":5000000,\"memory\":{\"free\":17401750,\"used\":1472618}}}"
        val request = objectMapper.readValue(json, HashMap::class.java)
        val response = restTemplate.postForEntity(URI.create("/api/telemetry/"), request, HashMap::class.java)
        assertTrue(response.statusCode.is2xxSuccessful)
    }

}
