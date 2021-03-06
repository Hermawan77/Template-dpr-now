package com.example.template_dpr_now.Images_Activity;

import com.google.firebase.database.Exclude;

public class ImagesUpload {
    private String mName, mImageUrl, mKey;

    public ImagesUpload(){

    }

    public ImagesUpload(String name, String imageurl){
        if(name.trim().equals(" ")){
            name = "No Name";
        }

        mName = name;
        mImageUrl = imageurl;
    }

    public String getName(){
        return mName;
    }

    public void setName(String name){
        mName = name;
    }

    public String getImageUrl(){
        return mImageUrl;
    }

    public void setImageUrl(String imageurl){
       mImageUrl = imageurl ;
    }

    @Exclude
    public String getKey(){
        return mKey;
    }

    @Exclude
    public void setKey(String key){
        mKey = key;
    }
}
