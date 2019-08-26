package com.curtcaldwell.sofichallenge;



import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.curtcaldwell.sofichallenge.model.CustomDisplayItem;
import com.curtcaldwell.sofichallenge.model.Datum;
import com.curtcaldwell.sofichallenge.model.Image;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class ImgurDataSource extends PageKeyedDataSource<Integer, CustomDisplayItem> {

    RetroFitService retroFitService;
    String input;

    public ImgurDataSource(RetroFitService r, String input) {
        retroFitService = r;
        this.input = input;


    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, CustomDisplayItem> callback) {
        List<CustomDisplayItem> displayItems = new ArrayList<>();
        try {
            List<Datum> items = retroFitService.getPictureData(input).execute().body().getData();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getImages() != null && items.get(i).getImages().size() != 0) {
                    String title = items.get(i).getTitle();
                    List<Image> images = items.get(i).getImages();
                    for (int j = 0; j < images.size(); j++) {
                        if (images.get(j).getType().equals("image/jpeg") || images.get(j).getType().equals("image/png")) {

                            CustomDisplayItem item = new CustomDisplayItem();
                            item.setTitle(title);
                            item.setLink(images.get(j).getLink());
                            item.setDescription(images.get(j).getDescription());


                            displayItems.add(item);
                        }
                    }


                }
            }

            } catch(IOException e){
                e.printStackTrace();
            }
            callback.onResult(displayItems, null, params.requestedLoadSize);

        }
    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, CustomDisplayItem> callback) {



    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, CustomDisplayItem> callback) {

        List<CustomDisplayItem> displayItems = new ArrayList<>();
        try {
            List<Datum> items = retroFitService.getPictureData(input).execute().body().getData();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getImages() != null && items.get(i).getImages().size() != 0) {
                    String title = items.get(i).getTitle();
                    List<Image> images = items.get(i).getImages();
                    for (int j = 0; j < images.size(); j++) {
                        if (images.get(j).getType().equals("image/jpeg") || images.get(j).getType().equals("image/png")) {

                            CustomDisplayItem item = new CustomDisplayItem();
                            item.setTitle(title);
                            item.setLink(images.get(j).getLink());
                            item.setDescription(images.get(j).getDescription());


                            displayItems.add(item);
                        }
                    }


                }
            }

        } catch(IOException e){
            e.printStackTrace();
        }
        callback.onResult(displayItems, params.requestedLoadSize + 1);


    }


}
