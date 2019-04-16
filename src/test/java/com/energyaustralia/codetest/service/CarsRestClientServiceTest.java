package com.energyaustralia.codetest.service;

import com.energyaustralia.codetest.config.CodeTestConfig;
import com.energyaustralia.codetest.model.CarShow;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;

import java.util.List;

import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created with IntelliJ IDEA.
 * User: fahadumarkhan
 * Date: 2019-04-15
 * Time: 13:19
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CodeTestConfig.class, CarsRestClientService.class})
@RestClientTest
@WebAppConfiguration
public class CarsRestClientServiceTest {

    @Value("${api.cars.path}")
    private String apiPath;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private CarsRestClientService carsRestClientService;

    @Before
    public void setUp() {
        RestGatewaySupport gateway = new RestGatewaySupport();
        gateway.setRestTemplate(restTemplate);
        mockRestServiceServer = MockRestServiceServer.createServer(gateway);
    }

    /**
     * Test with Content Returned From API
     */
    @Test
    public void testWithContent() {

        mockRestServiceServer
                .expect(once(), requestTo(apiPath))
                .andRespond(
                        withSuccess("[{\"name\" : \"Show 1\", \"cars\" : [{\"make\" : \"Make 1\", \"model\" : \"Model 1\"}]}]", MediaType.APPLICATION_JSON));

        List<CarShow> response = carsRestClientService.getCarShowList();

        Assert.assertEquals(response.size(), 1);
        Assert.assertEquals(response.get(0).getName(), "Show 1");
        Assert.assertEquals(response.get(0).getCars().size(), 1);
        Assert.assertEquals(response.get(0).getCars().get(0).getMake(), "Make 1");
        Assert.assertEquals(response.get(0).getCars().get(0).getModel(), "Model 1");

    }

    /**
     * Test when empty string returned
     */
    @Test
    public void testEmptyContent() {

        mockRestServiceServer
                .expect(once(), requestTo(apiPath))
                .andRespond(
                        withSuccess("", MediaType.APPLICATION_JSON));

        List<CarShow> response = carsRestClientService.getCarShowList();

        Assert.assertEquals(response.size(), 0);

    }

    /**
     * Test when 400 Bad request Returned
     */
    @Test(expected = HttpClientErrorException.class)
    public void testWithBadRequest400() {

        this.mockRestServiceServer
                .expect(once(), requestTo(apiPath))
                .andRespond(
                        withStatus(HttpStatus.BAD_REQUEST)
                );

        carsRestClientService.getCarShowList();
    }
}
