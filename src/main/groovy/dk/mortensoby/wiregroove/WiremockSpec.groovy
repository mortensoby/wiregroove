package dk.mortensoby.wiregroove

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.junit.After
import org.junit.Before

trait WiremockSpec {

    WireMockServer wireMockServer = new WireMockServer(new WireMockConfiguration(portNumber: port()))

    @Before
    void startWiremock() {
        wireMockServer.start()
        WireMock.configureFor("localhost", port());
    }

    @After
    void stopWiremock() {
        wireMockServer.stop()
    }


    int port() {
        return 8081
    }


}