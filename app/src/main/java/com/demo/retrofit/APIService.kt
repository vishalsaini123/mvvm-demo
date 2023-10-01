package com.demo.retrofit
import com.demo.common_helper.InputParams
import com.demo.pojo.*
import io.reactivex.rxjava3.core.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import retrofit2.http.Body

interface APIService {

    @POST("login")
    fun userLogin(@Body inputParams: InputParams): Observable<UserLoginModel>

    @POST("sendOtpRegister")
    fun userSignUp(@Body inputParams: InputParams): Observable<UserSignUpModel>

    @POST("registeruser")
    fun userRegistration(@Body inputParams: InputParams): Observable<UserRegistrationExtraModel>

    @POST("registerVerifyOtp")
    fun userVerifyOtp(@Body inputParams: InputParams): Observable<UserVerifyOtpModel>

    @POST("ReportProblem")
    fun reportProblem(@Body inputParams: InputParams): Observable<CommonModel>

    @POST("logout")
    fun logout(@Body inputParams: InputParams): Observable<CommonModel>

    @POST("getListenerDetails")
    fun getListenerDetails(@Body inputParams: InputParams): Observable<CommonModel>

    @Multipart
    @POST("registerlistener")
    fun registerlistener(
        @Part file: MultipartBody.Part?,
        @Part("mobile") mobile: RequestBody?,
        @Part("name") name: RequestBody?,
        @Part("email") email: RequestBody?,
        @Part("qualification") qualification: RequestBody?,
        @Part("gender") gender: RequestBody?,
        @Part("dob") dob: RequestBody?,
        @Part("categoryid") categoryid: RequestBody?,
        @Part("user_type") user_type: RequestBody?,
        @Part("informationcorrect") informationcorrect: RequestBody?,
        @Part("availablefor3hours") availablefor3hours: RequestBody?,
        @Part("termsconditions") termsconditions: RequestBody?
    ): Observable<UserRegistrationExtraModel>

    @GET("getCategory")
    fun getCategories(): Observable<CategoriesModel>

    @GET("getListenerListing")
    fun getListenerListing(): Observable<GetListenerListingModel>

    @GET("getPrivacyPolicy")
    fun getPrivacyPolicy(): Observable<PrivacyPolicyModel>

    @GET("getTermAndConditions")
    fun getTermsConditions(): Observable<TermsConditionsModel>

    @GET("getRaisedTickets")
    fun getRaisedTickets(): Observable<GetRaisedTicketModel>

   /* @GET
    fun getIPAddress(@Url url: String): Call<IpAddressModel>

    @POST("get-otp")
    fun getOtp(@Body loginParams: InputParams): Call<GetOtpModel>



    @POST("get-offers")
    fun getOffers(@Body loginParams: InputParams): Call<OfferModel>

    @GET("get-categories")
    fun getCategories(): Call<GetCategoriesModel>

    @POST("leaderboard")
    fun getLeaderBoard(): Call<LeaderBoardResponse>


    @GET("get-user-profile")
    fun getProfileData(): Call<GetProfileModel>

    @Multipart
    @POST("update-user-profile")
    fun updateProfile(
        @Part file: MultipartBody.Part?,
        @Part("firstname") firstName: RequestBody?,
        @Part("dob") dob: RequestBody?,
        @Part("gender") gender: RequestBody?,
        @Part("email") email: RequestBody?
    ): Call<ProfileModel>

    @POST("send-email-verification-link")
    fun verifyEmail(@Body loginParams: InputParams): Call<VerifyEmailModel>

    @POST("change-language")
    fun changeLanguage(@Body inputParams: InputParams): Call<LanguageAPIModel>

    @GET("faqs")
    fun getFAQ(): Call<FAQModels>

    @POST("redeem-now")
    fun redeemAmount(@Body inputParams: InputParams): Call<RedeemModel>

    @POST("claim-offer")
    fun claimOffer(@Body inputParams: InputParams): Call<ClaimOfferModel>

    @POST("get-completed-offers")
    fun getCompletedOffers(@Body inputParams: InputParams): Call<CompletedOfferModel>

    @POST("redeem-history")
    fun getRedeemHistory(@Body inputParams: InputParams): Call<RedeemHistoryModel>*/

}