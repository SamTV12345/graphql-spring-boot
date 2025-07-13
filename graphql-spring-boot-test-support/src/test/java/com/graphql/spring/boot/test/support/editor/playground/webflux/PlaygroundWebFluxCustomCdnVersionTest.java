package com.graphql.spring.boot.test.support.editor.playground.webflux;


import com.graphql.spring.boot.test.support.editor.playground.PlaygroundTestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = PlaygroundWebFluxTestConfig.class)
@AutoConfigureWebTestClient
@ActiveProfiles("playground")
@TestPropertySource("classpath:application-playground-cdn-custom-version-test.properties")
class PlaygroundWebFluxCustomCdnVersionTest extends PlaygroundWebFluxResourcesTestBase {

  @Test
  void shouldLoadSpecifiedVersionFromCdn() {
    testPlaygroundResources(
        PlaygroundTestHelper.CUSTOM_VERSION_CSS_CDN_PATH,
        PlaygroundTestHelper.CUSTOM_VERSION_SCRIPT_CDN_PATH,
        PlaygroundTestHelper.CUSTOM_VERSION_FAVICON_CDN_PATH,
        PlaygroundTestHelper.CUSTOM_VERSION_LOGO_CDN_PATH);
  }
}
