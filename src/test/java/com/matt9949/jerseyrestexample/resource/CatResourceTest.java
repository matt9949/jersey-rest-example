package com.matt9949.jerseyrestexample.resource;

import com.matt9949.jerseyrestexample.bean.Cat;
import com.matt9949.jerseyrestexample.service.CatService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CatResourceTest {

    @Mock
    private CatService mockService;
    @Mock
    private UriInfo mockUriInfo;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();


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
        verify(mockService, times(1)).createCat(validCat);
    }

    @Test
    public void shouldThrowValidationExceptionOnPostInvalidCat() throws URISyntaxException {
        Cat invalidCat = new Cat("mattcat", null, 8, "tabby");
        CatResource testResource = new CatResource(mockService, mockUriInfo);
        when(mockService.createCat(invalidCat)).thenThrow(new RuntimeException());

        //TODO: Change to ValidationException
        expectedException.expect(RuntimeException.class);

        testResource.postCat(invalidCat);
    }

    @Test
    public void shouldGetCatWith200ResponseCode() throws URISyntaxException {
        Cat expectedCat = new Cat("mattcat", "male", 8, "tabby");
        CatResource testResource = new CatResource(mockService, mockUriInfo);
        when(mockService.retrieveCat("123")).thenReturn(expectedCat);

        Response assertResponse = testResource.getCat("123");

        assertThat(assertResponse.getStatus(), is(200));
        assertThat((Cat)assertResponse.getEntity(), is(expectedCat));
        verify(mockService, times(1)).retrieveCat("123");
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenCatNotFound() throws URISyntaxException {
        CatResource testResource = new CatResource(mockService, mockUriInfo);
        when(mockService.retrieveCat("idontexist")).thenThrow(new RuntimeException());

        //TODO: Change to NotFoundException
        expectedException.expect(RuntimeException.class);

        testResource.getCat("idontexist");
    }

    @Test
    public void shouldUpdateCatWith204ResponseCode() throws URISyntaxException {
        Cat expectedCat = new Cat("mattcatupdated", "male", 8, "tabby");
        CatResource testResource = new CatResource(mockService, mockUriInfo);
        when(mockUriInfo.getAbsolutePath()).thenReturn(new URI("test"));

        Response assertResponse = testResource.updateCat("123", expectedCat);

        verify(mockService, times(1)).updateCat("123", expectedCat);
        assertThat(assertResponse.getStatus(), is(204));
        assertThat(assertResponse.getMetadata().get("location").get(0).toString(), is("test/123"));
    }

    @Test
    public void shouldDeleteCatWith204ResponseCode() throws URISyntaxException {
        CatResource testResource = new CatResource(mockService, mockUriInfo);

        Response assertResponse = testResource.deleteCat("123");

        verify(mockService, times(1)).deleteCat("123");
        assertThat(assertResponse.getStatus(), is(204));
    }

}
