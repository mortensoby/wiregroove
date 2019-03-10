package dk.mortensoby.wiregroove

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static com.github.tomakehurst.wiremock.client.WireMock.any
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching

class MockBuilder {

    static void mockResponse(@DelegatesTo(ResponseDefinitionBuilder) Closure<ResponseDefinitionBuilder> respondsWith) {
        mockResponse null, respondsWith
    }

    static void mockResponse(String url, @DelegatesTo(ResponseDefinitionBuilder) Closure<ResponseDefinitionBuilder> respondsWith) {
        ResponseDefinitionBuilder response = aResponse()
        givenThat(
            any(url ? urlPathMatching(url): anyUrl())
                .willReturn(
                response.with(respondsWith)
            )
        )
    }
}
