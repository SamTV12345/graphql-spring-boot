package com.samtv.editor.playground.webflux;

import com.samtv.editor.playground.PlaygroundTestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(classes = PlaygroundWebFluxTestConfig.class)
@AutoConfigureWebTestClient
@ActiveProfiles("playground")
@TestPropertySource("classpath:application-playground-mapping-test.properties")
class PlaygroundWebFluxCustomMappingTest {

  @Autowired private WebTestClient webTestClient;

  @Test
  void shouldUseTheConfiguredRequestMapping() {
    webTestClient
        .get()
        .uri(PlaygroundTestHelper.CUSTOM_MAPPING)
        .exchange()
        .expectStatus()
        .isOk()
        .expectHeader()
        .contentTypeCompatibleWith(MediaType.TEXT_HTML);
  }
}
