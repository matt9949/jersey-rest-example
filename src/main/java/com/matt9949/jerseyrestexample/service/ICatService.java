package com.matt9949.jerseyrestexample.service;

import com.matt9949.jerseyrestexample.bean.Cat;

public interface ICatService {

    public String createCat(Cat cat);

    public Cat retrieveCat(String catId);

    public String updateCat(String catId, Cat cat);

    public void deleteCat(String catId);

}
