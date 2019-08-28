package com.curtcaldwell.sofichallenge;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.curtcaldwell.sofichallenge.model.CustomDisplayItem;

public class ImgurDataSourceFactory extends DataSource.Factory<Integer, CustomDisplayItem> {

    private String input;

    private RetroFitService service;

    ImgurDataSourceFactory(RetroFitService service, String input) {
        this.service = service;
        this.input = input;
    }

    private MutableLiveData<PageKeyedDataSource<Integer, CustomDisplayItem>> liveData = new MutableLiveData();

    MutableLiveData<PageKeyedDataSource<Integer, CustomDisplayItem>> getLiveData() {
        return liveData;
    }

    @NonNull
    @Override
    public DataSource<Integer, CustomDisplayItem> create() {
        ImgurDataSource dataSource = new ImgurDataSource(service, input);
        liveData.postValue(dataSource);
        return dataSource;
    }
}
