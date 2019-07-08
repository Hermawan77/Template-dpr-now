package com.example.template_dpr_now;

public class ImageUpload {
    private String mName, mImageUrl;

    public ImageUpload(){

    }

    public ImageUpload(String name, String imageurl){
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
}
