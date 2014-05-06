package com.matt9949.jerseyrestexample.service;

import com.matt9949.jerseyrestexample.bean.Cat;
import com.matt9949.jerseyrestexample.storage.ICatStore;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CatServiceTest {

    @Mock
    private ICatStore mockCatStore;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnValidIdOnCreateValidCat(){
        Cat expectedCat = new Cat("testName", "testGender", "testAge", "testBreed");
        when(mockCatStore.createCat(expectedCat)).thenReturn("testId");

        String assertId = new CatService(mockCatStore).createCat(expectedCat);

        assertThat(assertId, notNullValue());
        verify(mockCatStore, times(1)).createCat(expectedCat);
    }

    @Test
    public void shouldReturnCatThatExistsOnRetrieveCat(){
        Cat expectedCat = new Cat("testName", "testGender", "testAge", "testBreed");
        String expectedCatId = "testId";
        when(mockCatStore.retrieveCat(expectedCatId)).thenReturn(expectedCat);

        Cat assertCat = new CatService(mockCatStore).retrieveCat(expectedCatId);

        assertThat(assertCat, is(expectedCat));
        verify(mockCatStore, times(1)).retrieveCat(expectedCatId);
    }
}
