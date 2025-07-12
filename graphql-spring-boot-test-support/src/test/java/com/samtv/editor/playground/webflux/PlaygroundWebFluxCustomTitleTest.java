package com.samtv.editor.playground.webflux;

import static com.samtv.editor.playground.PlaygroundTestHelper.CUSTOM_TITLE;
import static org.assertj.core.api.Assertions.assertThat;

import com.samtv.editor.playground.PlaygroundTestHelper;
import java.nio.charset.StandardCharsets;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(classes = PlaygroundWebFluxTestConfig.class)
@AutoConfigureWebTestClient
@ActiveProfiles("playground")
@TestPropertySource("classpath:application-playground-custom-title.properties")
class PlaygroundWebFluxCustomTitleTest {

  @Autowired private WebTestClient webTestClient;

  @Test
  void shouldUseTheCustomPageTitle() {
    // WHEN
    final byte[] actual =
        webTestClient
            .get()
            .uri(PlaygroundTestHelper.DEFAULT_PLAYGROUND_ENDPOINT)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .returnResult()
            .getResponseBody();
    // THEN
    assertThat(actual).isNotNull();
    final Document document = Jsoup.parse(new String(actual, StandardCharsets.UTF_8));
    PlaygroundTestHelper.assertTitle(document, CUSTOM_TITLE);
  }
}
