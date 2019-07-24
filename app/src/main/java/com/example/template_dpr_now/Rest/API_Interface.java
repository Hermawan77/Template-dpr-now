package com.example.template_dpr_now.Rest;

//import com.example.template_dpr_now.Model.GetPengaduan;
//import com.example.root.kontak.Model.PostPutDelPengaduan;

import com.example.template_dpr_now.Model.GetPengaduan;
import com.example.template_dpr_now.Model.Pengaduan;
import com.example.template_dpr_now.Model.PostPutDelAkun;
import com.example.template_dpr_now.Model.PostPutDelPengaduan;
import com.example.template_dpr_now.YouTubeModel;

import java.security.Key;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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
import retrofit2.http.Url;

public interface API_Interface {
    @GET("/pengaduan/")
    Call<GetPengaduan> getPengaduan();

    //mengirim raw data, tanpa di FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("/pengaduan/")
    Call<PostPutDelPengaduan> postPengaduan(@Body  Map <String,String> option);


    @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("/login/")
    Call<ResponseBody> postAkun(@Body  @Field("nama") String nama,
                                @Field("password") String password);

    @Headers("Content-Type: application/json")
    @POST("/login/")
    Call<PostPutDelAkun> postAkunn(@Body Map <String,String> option);

    //get Youtube model punya babeh faiz
    @GET
    Call<YouTubeModel> getVideos(@Url String url);
//    @FormUrlEncoded
//    @PUT("kontak")
//    Call<PostPutDelPengaduan> putKontak(@Field("id") String id,
//                                     @Field("nama") String nama,
//                                     @Field("nomor") String nomor);
//    @FormUrlEncoded
//    @HTTP(method = "DELETE", path = "kontak", hasBody = true)
//    Call<PostPutDelPengaduan> deleteKontak(@Field("id") String id);
}
