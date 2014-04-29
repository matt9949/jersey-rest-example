package com.matt9949.jerseyrestexample.resource;

import com.matt9949.jerseyrestexample.bean.Cat;
import com.matt9949.jerseyrestexample.service.CatService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class CatResourceTest {

    @Mock
    private CatService mockService;
    @Mock
    private UriInfo mockUriInfo;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPostValidCatWith201ResponseCodeAndLocation() throws URISyntaxException {
        Cat validCat = new Cat("mattcat", "male", 8, "tabby");
        CatResource testResource = new CatResource(mockService, mockUriInfo);
        when(mockService.createCat(validCat)).thenReturn("123");
        when(mockUriInfo.getAbsolutePath()).thenReturn(new URI("test"));

        Response assertResponse = testResource.postCat(validCat);

        assertThat(assertResponse.getStatus(), is(201));
        assertThat(assertResponse.getMetadata().get("location").get(0).toString(), is("test/123"));
    }



}
