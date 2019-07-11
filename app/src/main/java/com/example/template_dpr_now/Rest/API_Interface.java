package com.example.template_dpr_now.Rest;

//import com.example.template_dpr_now.Model.GetPengaduan;
//import com.example.root.kontak.Model.PostPutDelPengaduan;

import com.example.template_dpr_now.Model.GetPengaduan;
import com.example.template_dpr_now.Model.Pengaduan;
import com.example.template_dpr_now.Model.PostPutDelPengaduan;

import java.security.Key;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface API_Interface {
    static final String JSON_CONTENT_TYPE = "application/json";
    @GET("kontak_android")
    Call<GetPengaduan> getPengaduan();
    @Headers("Content-Type: application/json")
    @POST("/pengaduan/")

   // Call<PostPutDelPengaduan> postPengaduan(@Body Pengaduan p);

    Call<PostPutDelPengaduan> postPengaduan(@Body  Map <String,String> option);
//    @FormUrlEncoded
//    @PUT("kontak")
//    Call<PostPutDelPengaduan> putKontak(@Field("id") String id,
//                                     @Field("nama") String nama,
//                                     @Field("nomor") String nomor);
//    @FormUrlEncoded
//    @HTTP(method = "DELETE", path = "kontak", hasBody = true)
//    Call<PostPutDelPengaduan> deleteKontak(@Field("id") String id);
}
