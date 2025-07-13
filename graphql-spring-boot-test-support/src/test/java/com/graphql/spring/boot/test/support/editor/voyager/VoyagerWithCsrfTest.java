package com.graphql.spring.boot.test.support.editor.voyager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.graphql.spring.boot.test.support.annotations.TestApplication;
import graphql.kickstart.autoconfigure.editor.voyager.VoyagerAutoConfiguration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    classes = {VoyagerAutoConfiguration.class, SecurityAutoConfiguration.class, TestApplication.class, PermitAllWebSecurity.class},
    properties = {"graphql.voyager.enabled=true", "spring.main.web-application-type=servlet"})
@AutoConfigureMockMvc
@Disabled
@ActiveProfiles("voyager")
class VoyagerWithCsrfTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void shouldLoadCSRFData() throws Exception {
    final MvcResult mvcResult =
        mockMvc.perform(get("/voyager")).andExpect(status().isOk()).andReturn();

    final Document document = Jsoup.parse(mvcResult.getResponse().getContentAsString());
    final String script = document.body().select("body script").dataNodes().get(0).getWholeData();
    assertThat(script)
        .contains("let csrf = {\"")
        .contains("\"token\":\"")
        .contains("\"parameterName\":\"_csrf\"")
        .contains("\"headerName\":\"X-CSRF-TOKEN\"");
  }
}
