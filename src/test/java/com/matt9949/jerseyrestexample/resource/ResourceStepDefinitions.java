package com.matt9949.jerseyrestexample.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matt9949.jerseyrestexample.bean.Cat;
import com.matt9949.jerseyrestexample.service.CatService;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.IOUtils;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.google.common.io.Resources;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class ResourceStepDefinitions {

    private Cat cat;
    private Response response;
    private CatResource catResource;
    private ObjectMapper mapper = new ObjectMapper();

    @Mock
    private CatService mockService;
    @Mock
    private UriInfo mockUriInfo;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.catResource = new CatResource(mockService, mockUriInfo);
    }

    @Given("^a create cat request \"([^\"]*)\"$")
    public void a_cat(String catFileName) throws IOException {
        String jsonCat = IOUtils.toString(getFile(catFileName).toURI());
        this.cat = mapper.readValue(jsonCat, Cat.class);
    }

    @And("^the create cat request is valid$")
    public void the_create_cat_request_is_valid() throws URISyntaxException {
        when(mockService.createCat(cat)).thenReturn("123");
        when(mockUriInfo.getAbsolutePath()).thenReturn(new URI("test"));
    }

    @When("^the client requests to create a cat$")
    public void the_client_requests_to_create_a_cat() {
        this.response = catResource.postCat(cat);
    }

    @Then("^the response code should be '(\\d+)'$")
    public void the_response_code_should_be_(int expectedStatus) throws Throwable {
        assertThat(response.getStatus(), is(expectedStatus));
    }

    public File getFile(String fileName) throws IOException {
        URL url = Resources.getResource("com/matt9949/jerseyrestexample/requests/" + fileName + ".json");
        return new File(url.getFile());
    }
}
