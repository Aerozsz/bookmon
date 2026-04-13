package ab;

import be.d;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.pojo.ApplicantDeleteResponse;
import com.vfs.italyglobal.pojo.ApplicantListResponseData;
import com.vfs.italyglobal.pojo.ApplicantOTP;
import com.vfs.italyglobal.pojo.AppointmentCancelledResponseData;
import com.vfs.italyglobal.pojo.AppointmentLoginResponse;
import com.vfs.italyglobal.pojo.AppointmentRescheduleDataResponse;
import com.vfs.italyglobal.pojo.CalenderResponse;
import com.vfs.italyglobal.pojo.CenterCodeResponseData;
import com.vfs.italyglobal.pojo.ChatBotResponse;
import com.vfs.italyglobal.pojo.CheckIsSlotAvailableResModel;
import com.vfs.italyglobal.pojo.ConfigurationData;
import com.vfs.italyglobal.pojo.ConfirmAppointmentResponse;
import com.vfs.italyglobal.pojo.CountryData;
import com.vfs.italyglobal.pojo.ForgotPassword;
import com.vfs.italyglobal.pojo.GenerateUserOtpResponse;
import com.vfs.italyglobal.pojo.MasVasDetailResponseData;
import com.vfs.italyglobal.pojo.MasterGetCategory;
import com.vfs.italyglobal.pojo.MasterResponseData;
import com.vfs.italyglobal.pojo.Nationality;
import com.vfs.italyglobal.pojo.PaymentMode;
import com.vfs.italyglobal.pojo.RegistrationMainResponse;
import com.vfs.italyglobal.pojo.ScheduleResponse;
import com.vfs.italyglobal.pojo.SearchAppointmentDataResponse;
import com.vfs.italyglobal.pojo.TicketData;
import com.vfs.italyglobal.pojo.TimeSlotResponse;
import com.vfs.italyglobal.pojo.TokenData;
import com.vfs.italyglobal.pojo.TrackingResponse;
import com.vfs.italyglobal.pojo.VASFeesResponseData;
import com.vfs.italyglobal.pojo.VisaCategoryDataResponse;
import com.vfs.italyglobal.pojo.request.CheckIsSlotAvailableRequestParams;
import de.a;
import de.c;
import de.e;
import de.f;
import de.i;
import de.j;
import de.o;
import de.s;
import de.t;
import fd.i0;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public interface b {
    @f("configuration/fields/{mission}/{country}")
    d<ConfigurationData> A(@j Map<String, String> map, @s("mission") String str, @s("country") String str2);

    @f("vas/vasbyvac/{mission}/{country}/{center}")
    d<i0> B(@j Map<String, String> map, @s("mission") String str, @s("country") String str2, @s("center") String str3);

    @o("CheckAPIKEY")
    d<TokenData> C(@a com.google.gson.j jVar);

    @o("appointment/cancel")
    d<AppointmentCancelledResponseData> D(@j Map<String, String> map, @a com.google.gson.j jVar);

    @o("user/forgetpassword")
    d<ForgotPassword> E(@j Map<String, String> map, @a com.google.gson.j jVar);

    @o("user/generateuserotp")
    d<GenerateUserOtpResponse> F(@j Map<String, String> map, @a com.google.gson.j jVar);

    @o("user/logout")
    d<i0> G(@j Map<String, String> map, @a com.google.gson.j jVar);

    @o("appointment/application")
    d<SearchAppointmentDataResponse> H(@j Map<String, String> map, @a com.google.gson.j jVar);

    @f("Get")
    d<TrackingResponse> I(@j Map<String, String> map, @t("request") String str);

    @f("master/paymentmode/{missionCode}/{countryCode}/{centerCode}")
    d<List<PaymentMode>> J(@j Map<String, String> map, @s("missionCode") String str, @s("countryCode") String str2, @s("centerCode") String str3, @t("culturCode") String str4, @t("subVisaCode") String str5);

    @f("master/subvisacategory/{mission}/{country}/{center}/{visacategorycode}")
    d<i0> K(@j Map<String, String> map, @s("mission") String str, @s("country") String str2, @s("center") String str3, @s("visacategorycode") String str4);

    @o("user/login")
    @e
    d<AppointmentLoginResponse> a(@j Map<String, String> map, @c("username") String str, @c("password") String str2, @c("missioncode") String str3, @c("countrycode") String str4);

    @o("appointment/deleteapplicant")
    d<ApplicantDeleteResponse> b(@j Map<String, String> map, @a com.google.gson.j jVar);

    @o("PrintTicket")
    d<TicketData> c(@a com.google.gson.j jVar);

    @o("appointment/timeslot")
    d<TimeSlotResponse> d(@j Map<String, String> map, @a com.google.gson.j jVar);

    @f("master/visacategory/{mission}/{country}/{center}")
    d<List<VisaCategoryDataResponse>> e(@j Map<String, String> map, @s("mission") String str, @s("country") String str2, @s("center") String str3);

    @f("master/center/{mission}/{country}")
    d<List<CenterCodeResponseData>> f(@j Map<String, String> map, @s("mission") String str, @s("country") String str2);

    @o("appointment/reschedule")
    d<AppointmentRescheduleDataResponse> g(@j Map<String, String> map, @a com.google.gson.j jVar);

    @o("appointment/applicantotp")
    d<ApplicantOTP> h(@j Map<String, String> map, @a com.google.gson.j jVar);

    @o("appointment/calendar")
    d<CalenderResponse> i(@j Map<String, String> map, @a com.google.gson.j jVar);

    @f("master/GetCategoryList")
    d<MasterGetCategory> j();

    @o("user/login")
    @e
    d<AppointmentLoginResponse> k(@j Map<String, String> map, @c("username") String str, @c("password") String str2, @c("missioncode") String str3, @c("countrycode") String str4, @c("otp") String str5);

    @o("appointment/fees")
    d<VASFeesResponseData> l(@j Map<String, String> map, @a com.google.gson.j jVar);

    @o("GenerateAPIKEY")
    d<TokenData> m(@a com.google.gson.j jVar);

    @o("api/map/")
    d<ChatBotResponse> n(@i("api_key") String str, @a AppConfigModel.CountryConfig.ChatbotRequestBody chatbotRequestBody);

    @f("master/nationality/{languageCode}")
    d<List<Nationality>> o(@j Map<String, String> map, @s("languageCode") String str);

    @f("Country/MobileCountryList")
    d<CountryData> p();

    @o("appointment/schedule")
    d<ScheduleResponse> q(@j Map<String, String> map, @a com.google.gson.j jVar);

    @o("vas/mapvas")
    d<MasVasDetailResponseData> r(@j Map<String, String> map, @a com.google.gson.j jVar);

    @f("master/visatype/{embassyLoc}")
    d<MasterResponseData> s(@s("embassyLoc") String str);

    @o("payments/confirmappointment")
    d<ConfirmAppointmentResponse> t(@j Map<String, String> map, @a com.google.gson.j jVar);

    @o("appointment/applicants")
    d<ApplicantListResponseData> u(@j Map<String, String> map, @a com.google.gson.j jVar);

    @f("master/visasubcategory/{visaType}")
    d<MasterResponseData> v(@s("visaType") String str);

    @f("master/embassylocation/{countryId}")
    d<MasterResponseData> w(@s("countryId") int i10);

    @o("{apiEndPoint}")
    d<i0> x(@j Map<String, String> map, @s("apiEndPoint") String str);

    @o("user/registration")
    d<RegistrationMainResponse> y(@j Map<String, String> map, @a com.google.gson.j jVar);

    @o("appointment/CheckIsSlotAvailable")
    d<CheckIsSlotAvailableResModel> z(@j Map<String, String> map, @a CheckIsSlotAvailableRequestParams checkIsSlotAvailableRequestParams);
}
