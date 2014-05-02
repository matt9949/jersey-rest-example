package com.matt9949.jerseyrestexample.storage;

import com.matt9949.jerseyrestexample.bean.Cat;

public interface ICatStore {

    String createCat(Cat cat);

    Cat retrieveCat(String catId);

    void updateCat(Cat cat);

    void deleteCat(String catId);
}
