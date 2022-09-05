package com.example.ryzencarrent;

import com.example.ryzencarrent.Modals.AddBookings;
import com.example.ryzencarrent.Modals.Addcarrating;
import com.example.ryzencarrent.Modals.BookingHistoryModal;
import com.example.ryzencarrent.Modals.BrandModal;
import com.example.ryzencarrent.Modals.CarModal;
import com.example.ryzencarrent.Modals.CompanyModal;
import com.example.ryzencarrent.Modals.DriverModal;
import com.example.ryzencarrent.Modals.GetPageDataModal;
import com.example.ryzencarrent.Modals.GetUser;
import com.example.ryzencarrent.Modals.GetUserCarRating;
import com.example.ryzencarrent.Modals.GetUserReviewModal;
import com.example.ryzencarrent.Modals.Getcarrating;
import com.example.ryzencarrent.Modals.InquiryModal;
import com.example.ryzencarrent.Modals.KYCModal;
import com.example.ryzencarrent.Modals.LoginData;
import com.example.ryzencarrent.Modals.PayAtBranch;
import com.example.ryzencarrent.Modals.PayUPIModal;
import com.example.ryzencarrent.Modals.RatingModal;
import com.example.ryzencarrent.Modals.ReviewModal;
import com.example.ryzencarrent.Modals.SubmitReview;
import com.example.ryzencarrent.Modals.Subscribe;
import com.example.ryzencarrent.Modals.TotalDays;
import com.example.ryzencarrent.Modals.VisitorSubscribeadd;
import com.example.ryzencarrent.adapters.UpdatePassword;
import com.example.ryzencarrent.Modals.UpdateProfile;
import com.example.ryzencarrent.adapters.usermailchangedata;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitAPI {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginData> postLogin(@Field("email") String email,
                              @Field("password") String password);


    @FormUrlEncoded
    @POST("emailck.php")
    Call<RecyclerData> postEmail(@Field("email") String email);



 @FormUrlEncoded
    @POST("updateemail.php")
    Call<usermailchangedata> updateemail(@Field("mail") String mail,
                                        @Field("uid") int uid);


    @FormUrlEncoded
    @POST("signup.php")
    Call<RecyclerData> postRegister(@Field("username") String username,
                                    @Field("email") String email,
                                    @Field("password") String confirmpass,
                                    @Field("mobileno") String mobileno,
                                    @Field("dob") String date,
                                    @Field("address") String address,
                                    @Field("country") String country,
                                    @Field("state") String state,
                                    @Field("city") String city);

    @FormUrlEncoded
    @POST("forgetotp.php")
    Call<RecyclerData> PostForgetpassemail(@Field("email") String forgetpassemail);

    @FormUrlEncoded
    @POST("forgotpass.php")
    Call<RecyclerData> PostForgetpassword(@Field("email") String Forgot_Mail,
                                          @Field("password") String confirmpass
    );

    @GET("getreview.php")
    Call<List<ReviewModal>> getReviews();

   @FormUrlEncoded
   @POST("vehiclelist.php")
    Call<List<CarModal>> getVehicleList(@Field("date") String date);

    @GET("getcontactinfo.php")
    Call<List<CompanyModal>> getcontactinfo();


    @GET("brandlist.php")
    Call<List<BrandModal>> getBrandname();

    @GET("chkdriver.php")
    Call<List<DriverModal>> getDriverDetail();

    @FormUrlEncoded
    @POST("totalday.php")
    Call<TotalDays> postDate(@Field("fdate") String fdate,
                             @Field("tdate") String tdate);

    @FormUrlEncoded
    @POST("addbooking.php")
    Call<AddBookings> addbooking(
            @Field("uid") int uid,
            @Field("vid") int vid,
            @Field("did") int did,
            @Field("fdate") String fdate,
            @Field("tdate") String tdate,
            @Field("msg") String msg
    );

    @FormUrlEncoded
    @POST("getusercarrating.php")
    Call<GetUserCarRating> getusercarrating(
            @Field("bno") int bno
    );

    @FormUrlEncoded
    @POST("removecarrating.php")
    Call<GetUserCarRating> removecarrating(
            @Field("bno") int bno
    );


    @FormUrlEncoded
    @POST("getcarrating.php")
    Call<Getcarrating> getcarrating(
            @Field("vid") int vid
    );


    @FormUrlEncoded
    @POST("addcarrating.php")
    Call<Addcarrating> addcarrating(
            @Field("bno") int bno,@Field("rating") int rating

    );

    @FormUrlEncoded
    @POST("payatbranch.php")
    Call<PayAtBranch> payatbranch(
            @Field("bno") int bno, @Field("apaid") int apaid
    );

    @FormUrlEncoded
    @POST("payment.php")
    Call<PayUPIModal> paywithUPI(
            @Field("apaid") int apaid,
            @Field("bno") int bno
    );

    @FormUrlEncoded
    @POST("getuser.php")
    Call<List<GetUser>> getuser(
            @Field("uid") int uid
    );

    @FormUrlEncoded
    @POST("updateprofile.php")
    Call<UpdateProfile> updateprofile(
            @Field("uid") int uid,
            @Field("mobileno") String mobileno,
            @Field("dob") String dob,
            @Field("address") String address,
            @Field("country") String country,
            @Field("state") String state,
            @Field("city") String city
    );

    @FormUrlEncoded
    @POST("updatepassword.php")
    Call<UpdatePassword> updatepassword(
            @Field("uid") int uid,
            @Field("currentpass") String currentpass,
            @Field("newpass") String newpass

    );

    @FormUrlEncoded
    @POST("chksubscribed.php")
    Call<Subscribe> chksubscribed(
            @Field("umail") String umail

    );

    @FormUrlEncoded
    @POST("addsubscriber.php")
    Call<Subscribe> addsubscriber(
            @Field("submail") String submail

    );

    @FormUrlEncoded
    @POST("removesub.php")
    Call<Subscribe> removesub(
            @Field("submail") String submail

    );

    @FormUrlEncoded
    @POST("addvisitorsub.php")
    Call<VisitorSubscribeadd> addvisitorsub(
            @Field("umail") String umail

    );
    @FormUrlEncoded
    @POST("chkisbooked.php")
    Call<RatingModal> chkisbooked(
            @Field("uid") int uid

    );

    @FormUrlEncoded
    @POST("removereviewrating.php")
    Call<RatingModal> removereviewrating(
            @Field("uid") int uid

    );

    @FormUrlEncoded
    @POST("chkreviewraring.php")
    Call<RatingModal> chkreviewraring(
            @Field("uid") int uid

    );



    @FormUrlEncoded
    @POST("addreviewrating.php")
    Call<SubmitReview> addreviewrating(
            @Field("uid") int uid,
            @Field("review") String review,
            @Field("rating") int rating

    );

    @FormUrlEncoded
    @POST("getuserreviewraring.php")
    Call<List<GetUserReviewModal>> getuserreviewraring(
            @Field("uid") int uid

    );

    @FormUrlEncoded
    @POST("chkiskyc.php")
    Call<GetUserReviewModal> chkiskyc(
            @Field("uid") int uid

    );


    @FormUrlEncoded
    @POST("getkycstatus.php")
    Call<GetUserReviewModal> getkycstatus(
            @Field("uid") int uid

    );

    @FormUrlEncoded
    @POST("getpagedata.php")
    Call<List<GetPageDataModal>> getpagedata(
            @Field("ptype") String ptype

    );

    @FormUrlEncoded
    @POST("getbookinghistory.php")
    Call<List<BookingHistoryModal>> getBookHistoryBYUID(
            @Field("uid") int uid

    );

    @FormUrlEncoded
    @POST("addinquiry.php")
    Call<InquiryModal> addinquiry(
            @Field("username") String username,
            @Field("email") String email,
            @Field("mno") String mno,
            @Field("message") String message

    );

    @Multipart
    @POST("addkyc.php")
    Call<KYCModal> addkyc(
            @Part("uid") RequestBody uid,
            @Part("mail") RequestBody mail,
            @Part("fname") RequestBody fname,
            @Part("ano") RequestBody ano,
            @Part("pno") RequestBody pno,
            @Part("lno") RequestBody lno,
               @Part MultipartBody.Part  aimg1,
               @Part MultipartBody.Part  aimg2,
               @Part MultipartBody.Part  pimg,
               @Part MultipartBody.Part  limg1,
               @Part MultipartBody.Part  limg2


    );


}
