package dk.mortensoby.wiregroove


import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.Unirest
import spock.lang.Specification

class MockBuilderSpec extends Specification implements WiremockSpec {

    def "match any url"() {
        given:
            MockBuilder.mockResponse {
                withHeader('test-header', 'some-value')
                    .withBody('Hey there')
            }
        when:
            HttpResponse<String> request = Unirest.get('http://localhost:8081/someUrl').asString()
        then:
            request.body == 'Hey there'
            request.headers.get('test-header').get(0) == 'some-value'
    }

    def "match specific url"() {
        given:
            MockBuilder.mockResponse('/expectedUrl') {
                withHeader('test-header', 'some-value')
                    .withBody('Hey there')
            }
        when:
            HttpResponse<String> request = Unirest.get('http://localhost:8081/expectedUrl').asString()
        then:
            request.body == 'Hey there'
            request.headers.get('test-header').get(0) == 'some-value'
        when:
            HttpResponse<String> requestForUnexpectedUrl = Unirest.get('http://localhost:8081/unexpectedUrl').asString()
        then:
            requestForUnexpectedUrl.status == 404

    }

}
