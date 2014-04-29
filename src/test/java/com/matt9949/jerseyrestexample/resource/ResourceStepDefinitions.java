package com.matt9949.jerseyrestexample;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.IOUtils;

import javax.ws.rs.core.MediaType;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StepDefinitions extends JerseyTest {

    public StepDefinitions(){
        super(new WebAppDescriptor.Builder("com.matt9949.jerseyrestexample.resource")
                .initParam("com.sun.jersey.api.json.POJOMappingFeature", "true")
                .build()
        );
    }

    private WebResource webResource = resource();
    private String requestBody;
    private ClientResponse response;

    @Given("^a cat \"([^\"]*)\"$")
    public void a_cat(String catFilePath) throws IOException {
        this.requestBody = IOUtils.toString(getFile(catFilePath).toURI());
    }

    @When("^the client requests to create a cat$")
    public void the_client_requests_to_create_a_cat() {
        this.response = webResource.path("v1/cats")
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, requestBody);
    }

    @Then("^the response code should be '(\\d+)'$")
    public void the_response_code_should_be_(int expectedStatus) throws Throwable {
        assertThat(response.getStatus(), is(expectedStatus));
    }

    public File getFile(String fileName) throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("com/matt9949/jerseyrestexample/features" + fileName);
        return new File(url.getFile());
    }
}
