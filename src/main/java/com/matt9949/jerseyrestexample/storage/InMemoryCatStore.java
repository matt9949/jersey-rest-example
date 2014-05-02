package com.matt9949.jerseyrestexample.storage;

import com.matt9949.jerseyrestexample.bean.Cat;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryCatStore implements ICatStore{

    private volatile static Map<String, Cat> store;

    public InMemoryCatStore() {
        if (store == null) {
            synchronized (HashMap.class) {
                if (store == null) {
                    store = new HashMap<>();
                }
            }
        }
    }

    @Override
    public String createCat(Cat cat) {
        String catId = UUID.randomUUID().toString();
        store.put(catId, cat);
        return catId;
    }

    @Override
    public Cat retrieveCat(String catId) {
        return null;
    }

    @Override
    public void updateCat(Cat cat) {
    }

    @Override
    public void deleteCat(String catId) {
    }
}
