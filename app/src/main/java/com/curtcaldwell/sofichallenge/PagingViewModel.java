package com.curtcaldwell.sofichallenge;




import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.curtcaldwell.sofichallenge.model.CustomDisplayItem;


public class PagingViewModel extends ViewModel {

    private LiveData<PagedList<CustomDisplayItem>> liveData;
    public LiveData<PageKeyedDataSource<Integer, CustomDisplayItem>> dataSourceLiveData;
    private ImgurDataSourceFactory dataSourceFactory;

    public PagingViewModel(RetroFitService service, String input) {
        this.dataSourceFactory = new ImgurDataSourceFactory(service, input);
        this.dataSourceLiveData = this.dataSourceFactory.getLiveData();
        initializePaging();
    }

    private void initializePaging(){

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .build();
        liveData = new LivePagedListBuilder(dataSourceFactory, config).build();

    }

    public LiveData<PagedList<CustomDisplayItem>> getLiveData() {
        return liveData;
    }
}
