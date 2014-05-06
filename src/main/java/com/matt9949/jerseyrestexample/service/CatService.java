package com.matt9949.jerseyrestexample.service;

import com.google.inject.Inject;
import com.matt9949.jerseyrestexample.bean.Cat;
import com.matt9949.jerseyrestexample.storage.ICatStore;

public class CatService implements ICatService{

    @Inject
    private ICatStore catStore;

    CatService(ICatStore catStore){
        this.catStore = catStore;
    }

    public String createCat(Cat cat) {
        return catStore.createCat(cat);
    }

    public Cat retrieveCat(String catId) {
        Cat cat = catStore.retrieveCat(catId);
        if ( cat != null){
            return cat;
        }
        else{
            return null;
        }
    }

    public void updateCat(String catId, Cat cat) {
        //update the cat!
    }

    public void deleteCat(String catId) {
        //delete the cat!
    }
}
