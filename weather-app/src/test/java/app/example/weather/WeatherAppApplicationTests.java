package app.example.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeatherAppApplicationTests {

  @LocalServerPort private int port;

  private RestTemplate restTemplate;

  @BeforeEach
  void setup() {
    restTemplate = new RestTemplate();
  }

  @Test
  @SuppressWarnings("unchecked")
  void get_api_weather() {
    Map<String, Object> result =
        restTemplate.getForObject("http://localhost:" + port + "/api/v1/weather", Map.class);
    assertEquals(
        Map.of(
            "temperature", "30 \u2103",
            "humidity", "45 %"),
        result);
  }
}
