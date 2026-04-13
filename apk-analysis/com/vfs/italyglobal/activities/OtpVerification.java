package com.vfs.italyglobal.activities;

import ac.p;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import bc.n;
import be.d;
import be.f;
import com.google.gson.e;
import com.google.gson.j;
import com.google.gson.l;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.pojo.ApplicantOTP;
import com.vfs.italyglobal.pojo.AppointmentLoginResponse;
import com.vfs.italyglobal.pojo.Error;
import com.vfs.italyglobal.pojo.ErrorMessage;
import com.vfs.italyglobal.pojo.RegistrationMainResponse;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.g;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Objects;
import nb.h;
import nb.i;
import ua.u3;
import ua.v3;
import ua.w3;
import ua.x3;
import ua.y3;
import ua.z3;
import va.x;
import xa.z;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class OtpVerification extends x {

    /* renamed from: f0  reason: collision with root package name */
    private int f9423f0;
    /* access modifiers changed from: private */

    /* renamed from: g0  reason: collision with root package name */
    public String f9424g0;

    /* renamed from: h0  reason: collision with root package name */
    private String f9425h0;

    /* renamed from: i0  reason: collision with root package name */
    private String f9426i0;

    /* renamed from: j0  reason: collision with root package name */
    private j f9427j0;

    /* renamed from: k0  reason: collision with root package name */
    private boolean f9428k0;

    /* renamed from: l0  reason: collision with root package name */
    private boolean f9429l0;

    /* renamed from: m0  reason: collision with root package name */
    private int f9430m0;

    /* renamed from: n0  reason: collision with root package name */
    private final h f9431n0 = i.a(new u3(this));

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ String f9432a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ OtpVerification f9433b;

        a(String str, OtpVerification otpVerification) {
            this.f9432a = str;
            this.f9433b = otpVerification;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(OtpVerification otpVerification, int i10, String str) {
            n.e(str, "errorMsg");
            if (!(i10 == 403 || i10 == 429)) {
                Utility.Companion companion = Utility.f9497a;
                Activity c12 = otpVerification.c1();
                String string = otpVerification.getString(R.string.generic_error);
                n.d(string, "getString(...)");
                companion.w(c12, string);
            }
            return nb.x.f15721a;
        }

        public void a(d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            ApplicantOTP applicantOTP = (ApplicantOTP) xVar.a();
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            if (applicantOTP == null) {
                com.vfs.italyglobal.utilities.c.h(xVar, this.f9433b.c1(), new y3(this.f9433b));
            } else if (applicantOTP.isOTPGenerated() == null || applicantOTP.isOTPValidated() == null) {
                Activity c12 = this.f9433b.c1();
                Error error = applicantOTP.getError();
                n.b(error);
                companion.w(c12, String.valueOf(error.getDescription()));
            } else if (n.a(this.f9432a, "VALIDATE")) {
                Boolean isOTPValidated = applicantOTP.isOTPValidated();
                n.b(isOTPValidated);
                if (isOTPValidated.booleanValue()) {
                    this.f9433b.R1();
                    return;
                }
                Activity c13 = this.f9433b.c1();
                Error error2 = applicantOTP.getError();
                n.b(error2);
                companion.w(c13, String.valueOf(error2.getDescription()));
            } else {
                Boolean isOTPGenerated = applicantOTP.isOTPGenerated();
                n.b(isOTPGenerated);
                isOTPGenerated.booleanValue();
            }
        }

        public void b(d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            if (th instanceof SocketTimeoutException) {
                companion.z(this.f9433b.c1());
                return;
            }
            Activity c12 = this.f9433b.c1();
            String localizedMessage = th.getLocalizedMessage();
            n.b(localizedMessage);
            companion.w(c12, localizedMessage.toString());
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class b implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ OtpVerification f9434a;

        b(OtpVerification otpVerification) {
            this.f9434a = otpVerification;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(OtpVerification otpVerification, int i10, String str) {
            n.e(str, "errorMsg");
            if (!(i10 == 403 || i10 == 429)) {
                Utility.Companion companion = Utility.f9497a;
                Activity c12 = otpVerification.c1();
                String string = otpVerification.getString(R.string.generic_error);
                n.d(string, "getString(...)");
                companion.w(c12, string);
            }
            return nb.x.f15721a;
        }

        public void a(d dVar, be.x xVar) {
            String str;
            n.e(dVar, "call");
            n.e(xVar, "response");
            AppointmentLoginResponse appointmentLoginResponse = (AppointmentLoginResponse) xVar.a();
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            if (appointmentLoginResponse != null) {
                Boolean isAuthenticated = appointmentLoginResponse.isAuthenticated();
                n.b(isAuthenticated);
                if (isAuthenticated.booleanValue()) {
                    g.a aVar = g.f9512a;
                    Activity c12 = this.f9434a.c1();
                    String string = this.f9434a.getString(R.string.appmnt_login_authToken);
                    n.d(string, "getString(...)");
                    aVar.g(c12, string, String.valueOf(appointmentLoginResponse.getAccessToken()));
                    Activity c13 = this.f9434a.c1();
                    String string2 = this.f9434a.getString(R.string.appmnt_login_loggedInUser);
                    n.d(string2, "getString(...)");
                    String E1 = this.f9434a.f9424g0;
                    if (E1 == null) {
                        n.o("email");
                        E1 = null;
                    }
                    aVar.g(c13, string2, E1);
                    Activity c14 = this.f9434a.c1();
                    String string3 = this.f9434a.getString(R.string.appmnt_login_contactNumber);
                    n.d(string3, "getString(...)");
                    aVar.g(c14, string3, String.valueOf(appointmentLoginResponse.getContactNumber()));
                    Activity c15 = this.f9434a.c1();
                    String string4 = this.f9434a.getString(R.string.appmnt_login_dialCode);
                    n.d(string4, "getString(...)");
                    String dialCode = appointmentLoginResponse.getDialCode();
                    if (dialCode == null || dialCode.length() == 0) {
                        str = "0";
                    } else {
                        str = appointmentLoginResponse.getDialCode().toString();
                    }
                    aVar.g(c15, string4, str);
                    String roleName = appointmentLoginResponse.getRoleName();
                    if (roleName != null) {
                        OtpVerification otpVerification = this.f9434a;
                        Activity c16 = otpVerification.c1();
                        String string5 = otpVerification.getString(R.string.appmnt_login_roleName);
                        n.d(string5, "getString(...)");
                        aVar.g(c16, string5, roleName);
                    }
                    this.f9434a.P1();
                    return;
                }
                Activity c17 = this.f9434a.c1();
                ErrorMessage error = appointmentLoginResponse.getError();
                n.b(error);
                companion.w(c17, String.valueOf(error.getDescription()));
                return;
            }
            com.vfs.italyglobal.utilities.c.h(xVar, this.f9434a.c1(), new z3(this.f9434a));
        }

        public void b(d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            if (th instanceof SocketTimeoutException) {
                companion.z(this.f9434a.c1());
                return;
            }
            Activity c12 = this.f9434a.c1();
            String localizedMessage = th.getLocalizedMessage();
            n.b(localizedMessage);
            companion.w(c12, localizedMessage.toString());
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class c implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ OtpVerification f9435a;

        c(OtpVerification otpVerification) {
            this.f9435a = otpVerification;
        }

        public void a(d dVar, be.x xVar) {
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            n.b(xVar);
            RegistrationMainResponse registrationMainResponse = (RegistrationMainResponse) xVar.a();
            if (registrationMainResponse == null) {
                com.vfs.italyglobal.utilities.c.i(xVar, this.f9435a.c1(), (p) null, 2, (Object) null);
            } else if (registrationMainResponse.getCode() == null) {
                Activity c12 = this.f9435a.c1();
                ErrorMessage error = registrationMainResponse.getError();
                n.b(error);
                companion.w(c12, String.valueOf(error.getDescription()));
            } else if (!kc.n.z(registrationMainResponse.getCode(), "200", false, 2, (Object) null)) {
                Activity c13 = this.f9435a.c1();
                String string = this.f9435a.getString(R.string.generic_error);
                n.d(string, "getString(...)");
                companion.w(c13, string);
            } else if (n.a(String.valueOf(registrationMainResponse.getMessage()), "")) {
                OtpVerification otpVerification = this.f9435a;
                String string2 = otpVerification.getString(R.string.registration_successfully);
                n.d(string2, "getString(...)");
                otpVerification.S1(string2);
            } else {
                this.f9435a.S1(String.valueOf(registrationMainResponse.getMessage()));
            }
        }

        public void b(d dVar, Throwable th) {
            Utility.Companion companion = Utility.f9497a;
            Activity c12 = this.f9435a.c1();
            n.b(th);
            String localizedMessage = th.getLocalizedMessage();
            n.d(localizedMessage, "getLocalizedMessage(...)");
            companion.w(c12, localizedMessage);
        }
    }

    public OtpVerification() {
        super(false, false, 3, (bc.h) null);
    }

    private final void F1(String str, String str2) {
        String str3;
        String str4;
        String appointmentCfmLift;
        Utility.Companion companion = Utility.f9497a;
        companion.y(c1());
        String q10 = companion.q();
        String k10 = companion.k(c1());
        j jVar = new j();
        String str5 = this.f9426i0;
        if (str5 == null) {
            n.o("urnNumber");
            str5 = null;
        }
        jVar.r("urn", str5);
        jVar.r("missionCode", q10);
        jVar.r("countryCode", k10);
        jVar.r("languageCode", "en");
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.appmnt_center_code);
        n.d(string, "getString(...)");
        String str6 = "";
        jVar.r("centerCode", aVar.b(c12, string, str6));
        Activity c13 = c1();
        String string2 = getString(R.string.appmnt_login_loggedInUser);
        n.d(string2, "getString(...)");
        jVar.r("loginUser", aVar.b(c13, string2, str6));
        jVar.r("captcha_version", str6);
        jVar.r("captcha_api_key", str6);
        jVar.r("OTP", str2);
        jVar.r("otpAction", str);
        Activity c14 = c1();
        String string3 = getString(R.string.appmnt_login_authToken);
        n.d(string3, "getString(...)");
        String b10 = aVar.b(c14, string3, str6);
        String e10 = companion.e(this, companion.l());
        HashMap hashMap = new HashMap();
        hashMap.put("Authorize", b10);
        AppConfigModel e11 = d1().e();
        if (e11 == null || (str3 = e11.getAppointmentUrlOrigin()) == null) {
            str3 = str6;
        }
        hashMap.put("referer", str3);
        AppConfigModel e12 = d1().e();
        if (e12 == null || (str4 = e12.getAppointmentUrlOrigin()) == null) {
            str4 = str6;
        }
        hashMap.put("origin", str4);
        AppConfigModel e13 = d1().e();
        if (!(e13 == null || (appointmentCfmLift = e13.getAppointmentCfmLift()) == null)) {
            str6 = appointmentCfmLift;
        }
        hashMap.put("cfmlift", str6);
        hashMap.put("ClientSource", e10);
        n.b(new e().r(jVar));
        f1().h(hashMap, jVar).A(new a(str, this));
    }

    static /* synthetic */ void G1(OtpVerification otpVerification, String str, String str2, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str2 = "";
        }
        otpVerification.F1(str, str2);
    }

    private final void H1() {
        String str;
        String str2;
        String str3;
        String appointmentCfmLift;
        Utility.Companion companion = Utility.f9497a;
        companion.y(c1());
        Activity c12 = c1();
        String str4 = this.f9425h0;
        if (str4 == null) {
            n.o("password");
            str4 = null;
        }
        String e10 = companion.e(c12, str4);
        String q10 = companion.q();
        String k10 = companion.k(c1());
        String e11 = companion.e(this, companion.l());
        HashMap hashMap = new HashMap();
        AppConfigModel e12 = d1().e();
        String str5 = "";
        if (e12 == null || (str = e12.getAppointmentUrlOrigin()) == null) {
            str = str5;
        }
        hashMap.put("referer", str);
        AppConfigModel e13 = d1().e();
        if (e13 == null || (str2 = e13.getAppointmentUrlOrigin()) == null) {
            str2 = str5;
        }
        hashMap.put("origin", str2);
        AppConfigModel e14 = d1().e();
        if (!(e14 == null || (appointmentCfmLift = e14.getAppointmentCfmLift()) == null)) {
            str5 = appointmentCfmLift;
        }
        hashMap.put("cfmlift", str5);
        hashMap.put("ClientSource", e11);
        ab.b f12 = f1();
        String str6 = this.f9424g0;
        if (str6 == null) {
            n.o("email");
            str3 = null;
        } else {
            str3 = str6;
        }
        f12.k(hashMap, str3, e10, q10, k10, String.valueOf(K1().f20734e.getText())).A(new b(this));
    }

    private final void I1() {
        String str;
        String str2;
        String appointmentCfmLift;
        j jVar = this.f9427j0;
        j jVar2 = null;
        if (jVar == null) {
            n.o("registrationRequestParams");
            jVar = null;
        }
        jVar.r("otp", String.valueOf(K1().f20734e.getText()));
        Utility.Companion companion = Utility.f9497a;
        String e10 = companion.e(this, companion.l());
        HashMap hashMap = new HashMap();
        AppConfigModel e11 = d1().e();
        String str3 = "";
        if (e11 == null || (str = e11.getAppointmentUrlOrigin()) == null) {
            str = str3;
        }
        hashMap.put("referer", str);
        AppConfigModel e12 = d1().e();
        if (e12 == null || (str2 = e12.getAppointmentUrlOrigin()) == null) {
            str2 = str3;
        }
        hashMap.put("origin", str2);
        AppConfigModel e13 = d1().e();
        if (!(e13 == null || (appointmentCfmLift = e13.getAppointmentCfmLift()) == null)) {
            str3 = appointmentCfmLift;
        }
        hashMap.put("cfmlift", str3);
        hashMap.put("ClientSource", e10);
        ab.b f12 = f1();
        j jVar3 = this.f9427j0;
        if (jVar3 == null) {
            n.o("registrationRequestParams");
        } else {
            jVar2 = jVar3;
        }
        f12.y(hashMap, jVar2).A(new c(this));
    }

    private final void J1() {
        if (com.vfs.italyglobal.utilities.c.j(this)) {
            Editable text = K1().f20734e.getText();
            n.b(text);
            int length = text.length();
            this.f9423f0 = length;
            if (length != 6) {
                Utility.Companion companion = Utility.f9497a;
                Activity c12 = c1();
                String string = getString(R.string.enter_OTP);
                n.d(string, "getString(...)");
                companion.w(c12, string);
                companion.c();
            } else if (this.f9428k0) {
                F1("VALIDATE", String.valueOf(K1().f20734e.getText()));
            } else if (this.f9429l0) {
                I1();
            } else {
                H1();
            }
        } else {
            Utility.Companion companion2 = Utility.f9497a;
            String string2 = getString(R.string.network_error);
            n.d(string2, "getString(...)");
            companion2.w(this, string2);
        }
    }

    private final z K1() {
        return (z) this.f9431n0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void M1(OtpVerification otpVerification, View view) {
        otpVerification.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final void N1(OtpVerification otpVerification, View view) {
        otpVerification.J1();
    }

    /* access modifiers changed from: private */
    public static final z O1(OtpVerification otpVerification) {
        return z.c(otpVerification.getLayoutInflater());
    }

    private final void Q1() {
        startActivity(new Intent(this, Dashboard.class));
    }

    /* access modifiers changed from: private */
    public static final nb.x T1(OtpVerification otpVerification) {
        otpVerification.Q1();
        return nb.x.f15721a;
    }

    public final void L1() {
        Bundle extras = getIntent().getExtras();
        n.b(extras);
        this.f9424g0 = String.valueOf(extras.getString("emailID"));
        this.f9425h0 = String.valueOf(extras.getString("password"));
        this.f9426i0 = String.valueOf(extras.getString(getString(R.string.bundle_urnNumber)));
        this.f9428k0 = extras.getBoolean(getString(R.string.bundle_isFromApplicant));
        this.f9429l0 = extras.getBoolean(getString(R.string.bundle_isFromRegister));
        String string = extras.getString(getString(R.string.bundle_registration_request_data));
        if (!(string == null || string.length() == 0)) {
            j d10 = l.c(string).d();
            n.d(d10, "getAsJsonObject(...)");
            this.f9427j0 = d10;
            j jVar = this.f9427j0;
            if (jVar == null) {
                n.o("registrationRequestParams");
                jVar = null;
            }
            Objects.toString(jVar);
        }
        K1().f20732c.setOnClickListener(new v3(this));
        K1().f20731b.setOnClickListener(new w3(this));
        if (this.f9428k0) {
            this.f9430m0 = extras.getInt(getString(R.string.no_of_applicants));
            if (this.f9426i0 == null) {
                n.o("urnNumber");
            }
            if (com.vfs.italyglobal.utilities.c.j(this)) {
                G1(this, "GENERATE", (String) null, 2, (Object) null);
                return;
            }
            Utility.Companion companion = Utility.f9497a;
            String string2 = getString(R.string.network_error);
            n.d(string2, "getString(...)");
            companion.w(this, string2);
        }
    }

    public final void P1() {
        startActivity(new Intent(this, AppointmentListActivity.class));
        finish();
    }

    public final void R1() {
        Intent intent = new Intent(this, AppointmentDateAndTimeListActivity.class);
        intent.putExtra(getString(R.string.isToReschedule), false);
        intent.putExtra(getString(R.string.no_of_applicants), this.f9430m0);
        finish();
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    public final void S1(String str) {
        n.e(str, "message");
        com.vfs.italyglobal.utilities.c.e(this, str, (String) null, new x3(this), false, (String) null, (ac.a) null, false, 122, (Object) null).show();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) K1().b());
        L1();
    }
}
