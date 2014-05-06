package com.matt9949.jerseyrestexample.storage;

import com.matt9949.jerseyrestexample.bean.Cat;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class InMemoryCatStoreTest {

    @Test
    public void shouldReturnValidIdOnCreateCatRequest(){
        InMemoryCatStore catStore = new InMemoryCatStore();
        String actualCatId = catStore.createCat(new Cat("testName", "testGender", "testAge", "testBreed"));
        assertThat(actualCatId, notNullValue());
    }

    @Test
    public void shouldReturnNullWhenCatDoesNotExist(){
        InMemoryCatStore catStore = new InMemoryCatStore();
        Cat assertCat = catStore.retrieveCat("iDontExist");
        assertThat(assertCat, nullValue());
    }

    @Test
    public void shouldReturnCatWhenCatDoesExist(){
        InMemoryCatStore catStore = new InMemoryCatStore();
        Cat expectedCat = new Cat("testName", "testGender", "testAge", "testBreed");
        String catId = catStore.createCat(expectedCat);
        Cat assertCat = catStore.retrieveCat(catId);
        assertThat(assertCat, is(expectedCat));
    }

}
