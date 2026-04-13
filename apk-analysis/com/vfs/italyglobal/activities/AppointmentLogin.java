package com.vfs.italyglobal.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import bc.h;
import bc.n;
import be.f;
import com.google.gson.j;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.pojo.AppointmentLoginResponse;
import com.vfs.italyglobal.pojo.ErrorMessage;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.g;
import fd.i0;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import k3.c;
import nb.i;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import ua.c1;
import ua.d1;
import ua.e1;
import ua.f1;
import ua.g1;
import ua.h1;
import ua.i1;
import ua.j1;
import va.x;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class AppointmentLogin extends x {

    /* renamed from: h0  reason: collision with root package name */
    public static final a f9338h0 = new a((h) null);

    /* renamed from: f0  reason: collision with root package name */
    private final nb.h f9339f0 = i.a(new c1(this));

    /* renamed from: g0  reason: collision with root package name */
    private String f9340g0;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a {
        public /* synthetic */ a(h hVar) {
            this();
        }

        public static /* synthetic */ void c(a aVar, Activity activity, int i10, boolean z10, int i11, Object obj) {
            if ((i11 & 4) != 0) {
                z10 = false;
            }
            aVar.b(activity, i10, z10);
        }

        /* access modifiers changed from: private */
        public static final nb.x d(boolean z10, Activity activity) {
            if (!z10) {
                Intent intent = new Intent(activity, AppointmentLogin.class);
                intent.setFlags(268468224);
                activity.startActivity(intent);
            }
            return nb.x.f15721a;
        }

        public final void b(Activity activity, int i10, boolean z10) {
            n.e(activity, "mContext");
            if (i10 == 401) {
                String string = activity.getString(R.string.msg_session_expired);
                n.d(string, "getString(...)");
                com.vfs.italyglobal.utilities.c.e(activity, string, (String) null, new h1(z10, activity), false, (String) null, (ac.a) null, false, 122, (Object) null).show();
                return;
            }
            Activity activity2 = activity;
            Utility.Companion companion = Utility.f9497a;
            String string2 = activity2.getString(R.string.generic_error);
            n.d(string2, "getString(...)");
            companion.w(activity2, string2);
        }

        private a() {
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class b implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentLogin f9341a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ String f9342b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f9343c;

        b(AppointmentLogin appointmentLogin, String str, String str2) {
            this.f9341a = appointmentLogin;
            this.f9342b = str;
            this.f9343c = str2;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(AppointmentLogin appointmentLogin, int i10, String str) {
            n.e(str, "errorMsg");
            if (!(i10 == 403 || i10 == 429)) {
                Utility.Companion companion = Utility.f9497a;
                Activity c12 = appointmentLogin.c1();
                String string = appointmentLogin.getString(R.string.generic_error);
                n.d(string, "getString(...)");
                companion.w(c12, string);
            }
            return nb.x.f15721a;
        }

        public void a(be.d dVar, be.x xVar) {
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
                    Activity c12 = this.f9341a.c1();
                    String string = this.f9341a.getString(R.string.appmnt_login_authToken);
                    n.d(string, "getString(...)");
                    aVar.g(c12, string, String.valueOf(appointmentLoginResponse.getAccessToken()));
                    Activity c13 = this.f9341a.c1();
                    String string2 = this.f9341a.getString(R.string.appmnt_login_loggedInUser);
                    n.d(string2, "getString(...)");
                    aVar.g(c13, string2, this.f9342b);
                    Activity c14 = this.f9341a.c1();
                    String string3 = this.f9341a.getString(R.string.appmnt_login_contactNumber);
                    n.d(string3, "getString(...)");
                    aVar.g(c14, string3, String.valueOf(appointmentLoginResponse.getContactNumber()));
                    Activity c15 = this.f9341a.c1();
                    String string4 = this.f9341a.getString(R.string.appmnt_login_dialCode);
                    n.d(string4, "getString(...)");
                    String dialCode = appointmentLoginResponse.getDialCode();
                    if (dialCode == null || dialCode.length() == 0) {
                        str = "0";
                    } else {
                        str = appointmentLoginResponse.getDialCode();
                    }
                    aVar.g(c15, string4, str);
                    String roleName = appointmentLoginResponse.getRoleName();
                    if (roleName != null) {
                        AppointmentLogin appointmentLogin = this.f9341a;
                        Activity c16 = appointmentLogin.c1();
                        String string5 = appointmentLogin.getString(R.string.appmnt_login_roleName);
                        n.d(string5, "getString(...)");
                        aVar.g(c16, string5, roleName);
                    }
                    this.f9341a.P1();
                    return;
                }
                Boolean enableOTPAuthentication = appointmentLoginResponse.getEnableOTPAuthentication();
                n.b(enableOTPAuthentication);
                if (enableOTPAuthentication.booleanValue()) {
                    this.f9341a.R1(this.f9342b, this.f9343c);
                    return;
                }
                Activity c17 = this.f9341a.c1();
                ErrorMessage error = appointmentLoginResponse.getError();
                n.b(error);
                companion.w(c17, String.valueOf(error.getDescription()));
                return;
            }
            com.vfs.italyglobal.utilities.c.h(xVar, this.f9341a.c1(), new i1(this.f9341a));
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            if (th instanceof SocketTimeoutException) {
                companion.z(this.f9341a.c1());
                return;
            }
            String localizedMessage = th.getLocalizedMessage();
            if (localizedMessage != null) {
                companion.w(this.f9341a.c1(), localizedMessage);
            }
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class c implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentLogin f9344a;

        c(AppointmentLogin appointmentLogin) {
            this.f9344a = appointmentLogin;
        }

        public void a(be.d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            Utility.f9497a.c();
            AppointmentLogin.f9338h0.b(this.f9344a.c1(), HttpStatus.SC_UNAUTHORIZED, true);
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.f9497a.c();
            AppointmentLogin.f9338h0.b(this.f9344a.c1(), HttpStatus.SC_UNAUTHORIZED, true);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class d implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentLogin f9345a;

        d(AppointmentLogin appointmentLogin) {
            this.f9345a = appointmentLogin;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(AppointmentLogin appointmentLogin, int i10, String str) {
            n.e(str, "errorMsg");
            if (!(i10 == 403 || i10 == 429)) {
                Utility.Companion companion = Utility.f9497a;
                Activity c12 = appointmentLogin.c1();
                String string = appointmentLogin.getString(R.string.generic_error);
                n.d(string, "getString(...)");
                companion.w(c12, string);
            }
            return nb.x.f15721a;
        }

        public void a(be.d dVar, be.x xVar) {
            String str;
            n.e(dVar, "call");
            n.e(xVar, "response");
            Utility.f9497a.c();
            i0 i0Var = (i0) xVar.a();
            if (i0Var != null) {
                str = i0Var.o();
            } else {
                str = null;
            }
            if (str != null) {
                AppointmentLogin appointmentLogin = this.f9345a;
                String string = new JSONObject(str).getString("data");
                g.a aVar = g.f9512a;
                Activity c12 = appointmentLogin.c1();
                String string2 = appointmentLogin.getString(R.string.api_public_key);
                n.d(string2, "getString(...)");
                n.b(string);
                aVar.g(c12, string2, string);
                appointmentLogin.d1().i().d(appointmentLogin.c1(), string);
                appointmentLogin.J1();
                return;
            }
            AppointmentLogin appointmentLogin2 = this.f9345a;
            com.vfs.italyglobal.utilities.c.h(xVar, appointmentLogin2.c1(), new j1(appointmentLogin2));
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            String localizedMessage = th.getLocalizedMessage();
            if (localizedMessage != null) {
                companion.w(this.f9345a.c1(), localizedMessage);
            }
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class e extends WebChromeClient {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentLogin f9346a;

        e(AppointmentLogin appointmentLogin) {
            this.f9346a = appointmentLogin;
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String str;
            if (consoleMessage != null) {
                str = consoleMessage.message();
            } else {
                str = null;
            }
            n.b(str);
            if (kc.n.K(str, "captchatoken: ", false, 2, (Object) null)) {
                String substring = str.substring(14);
                n.d(substring, "substring(...)");
                this.f9346a.T1(substring);
            }
            if (n.a(str, "verification-expired")) {
                this.f9346a.T1((String) null);
            }
            return super.onConsoleMessage(consoleMessage);
        }
    }

    public AppointmentLogin() {
        super(false, false, 3, (h) null);
    }

    private final void F1() {
        String str;
        String str2;
        String appointmentCfmLift;
        String valueOf = String.valueOf(I1().f20337d.getText());
        String valueOf2 = String.valueOf(I1().f20338e.getText());
        Utility.Companion companion = Utility.f9497a;
        String q10 = companion.q();
        String k10 = companion.k(c1());
        String e10 = companion.e(c1(), valueOf2);
        String e11 = companion.e(this, companion.l());
        HashMap hashMap = new HashMap();
        AppConfigModel e12 = d1().e();
        String str3 = "";
        if (e12 == null || (str = e12.getAppointmentUrlOrigin()) == null) {
            str = str3;
        }
        hashMap.put("referer", str);
        AppConfigModel e13 = d1().e();
        if (e13 == null || (str2 = e13.getAppointmentUrlOrigin()) == null) {
            str2 = str3;
        }
        hashMap.put("origin", str2);
        AppConfigModel e14 = d1().e();
        if (!(e14 == null || (appointmentCfmLift = e14.getAppointmentCfmLift()) == null)) {
            str3 = appointmentCfmLift;
        }
        hashMap.put("cfmlift", str3);
        hashMap.put("ClientSource", e11);
        f1().a(hashMap, valueOf, e10, q10, k10).A(new b(this, valueOf, valueOf2));
    }

    private final void G1() {
        String str;
        String str2;
        String appointmentCfmLift;
        Utility.Companion companion = Utility.f9497a;
        companion.y(c1());
        j jVar = new j();
        String q10 = companion.q();
        String k10 = companion.k(c1());
        jVar.r("missionCode", q10);
        jVar.r("countryCode", k10);
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.appmnt_login_loggedInUser);
        n.d(string, "getString(...)");
        String str3 = "";
        jVar.r("username", aVar.b(c12, string, str3));
        Activity c13 = c1();
        String string2 = getString(R.string.appmnt_login_authToken);
        n.d(string2, "getString(...)");
        String b10 = aVar.b(c13, string2, str3);
        String e10 = companion.e(this, companion.l());
        HashMap hashMap = new HashMap();
        hashMap.put("Authorize", b10);
        AppConfigModel e11 = d1().e();
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
        f1().G(hashMap, jVar).A(new c(this));
    }

    private final void H1() {
        String str;
        String rsaPublicKeyEndPoint;
        if (com.vfs.italyglobal.utilities.c.j(this)) {
            Utility.f9497a.y(c1());
            HashMap hashMap = new HashMap();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Bearer ");
            AppConfigModel e10 = d1().e();
            String str2 = "";
            if (e10 == null || (str = e10.getRsaPublicHeaderAuthToken()) == null) {
                str = str2;
            }
            sb2.append(str);
            hashMap.put("Authorization", sb2.toString());
            ab.b i12 = i1();
            AppConfigModel e11 = d1().e();
            if (!(e11 == null || (rsaPublicKeyEndPoint = e11.getRsaPublicKeyEndPoint()) == null)) {
                str2 = rsaPublicKeyEndPoint;
            }
            i12.x(hashMap, str2).A(new d(this));
            return;
        }
        Utility.Companion companion = Utility.f9497a;
        String string = getString(R.string.network_error);
        n.d(string, "getString(...)");
        companion.w(this, string);
    }

    private final xa.d I1() {
        return (xa.d) this.f9339f0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void K1(AppointmentLogin appointmentLogin, View view) {
        appointmentLogin.S1();
    }

    /* access modifiers changed from: private */
    public static final void L1(AppointmentLogin appointmentLogin, View view) {
        appointmentLogin.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final void M1(AppointmentLogin appointmentLogin, View view) {
        if (com.vfs.italyglobal.utilities.c.j(appointmentLogin)) {
            Editable text = appointmentLogin.I1().f20337d.getText();
            n.b(text);
            if (text.length() == 0) {
                Utility.Companion companion = Utility.f9497a;
                String string = appointmentLogin.getString(R.string.appmnt_email_required);
                n.d(string, "getString(...)");
                companion.w(appointmentLogin, string);
                return;
            }
            Editable text2 = appointmentLogin.I1().f20338e.getText();
            n.b(text2);
            if (text2.length() == 0) {
                Utility.Companion companion2 = Utility.f9497a;
                String string2 = appointmentLogin.getString(R.string.appmnt_password_required);
                n.d(string2, "getString(...)");
                companion2.w(appointmentLogin, string2);
                return;
            }
            AppConfigModel.CountryConfig j10 = appointmentLogin.d1().j();
            if (j10 == null || !j10.getRecaptchaLoginAccess()) {
                Utility.f9497a.y(appointmentLogin.c1());
                appointmentLogin.F1();
                return;
            }
            String str = appointmentLogin.f9340g0;
            if (str == null || str.length() == 0) {
                Utility.Companion companion3 = Utility.f9497a;
                String string3 = appointmentLogin.getString(R.string.appmnt_login_reCaptcha_required);
                n.d(string3, "getString(...)");
                companion3.w(appointmentLogin, string3);
                return;
            }
            Utility.f9497a.y(appointmentLogin.c1());
            appointmentLogin.F1();
            return;
        }
        Utility.Companion companion4 = Utility.f9497a;
        String string4 = appointmentLogin.getString(R.string.network_error);
        n.d(string4, "getString(...)");
        companion4.w(appointmentLogin, string4);
    }

    /* access modifiers changed from: private */
    public static final void N1(AppointmentLogin appointmentLogin, View view) {
        appointmentLogin.Q1();
    }

    /* access modifiers changed from: private */
    public static final xa.d O1(AppointmentLogin appointmentLogin) {
        return xa.d.c(appointmentLogin.getLayoutInflater());
    }

    private final void Q1() {
        startActivity(new Intent(this, ForgotPassword.class));
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    private final void S1() {
        startActivity(new Intent(this, AppointmentRegistration.class));
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    public final void J1() {
        I1().f20336c.setOnClickListener(new d1(this));
        I1().f20339f.setOnClickListener(new e1(this));
        I1().f20335b.setOnClickListener(new f1(this));
        I1().f20342i.setOnClickListener(new g1(this));
        AppConfigModel.CountryConfig j10 = d1().j();
        if (j10 != null && j10.getRecaptchaLoginAccess()) {
            I1().f20340g.getLayoutParams().height = -1;
            I1().f20341h.setVisibility(0);
            I1().f20341h.getSettings().setJavaScriptEnabled(true);
            I1().f20341h.setVerticalScrollBarEnabled(true);
            I1().f20341h.setHorizontalScrollBarEnabled(true);
            k3.c b10 = new c.b().c("localhost").a("/assets/", new c.a(this)).b();
            n.d(b10, "build(...)");
            I1().f20341h.setWebViewClient(new com.vfs.italyglobal.utilities.f(b10));
            I1().f20341h.loadUrl("https://localhost/assets/reCaptcha.html");
            I1().f20341h.setWebChromeClient(new e(this));
        }
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            n.b(extras);
            if (extras.getBoolean("isFromSessionTimeout")) {
                G1();
            }
        }
    }

    public final void P1() {
        startActivity(new Intent(this, AppointmentListActivity.class));
    }

    public final void R1(String str, String str2) {
        n.e(str, "emailID");
        n.e(str2, "password");
        Intent intent = new Intent(this, OtpVerification.class);
        intent.putExtra("emailID", str);
        intent.putExtra("password", str2);
        startActivity(intent);
    }

    public final void T1(String str) {
        this.f9340g0 = str;
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) I1().b());
        H1();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Utility.Companion companion = Utility.f9497a;
        companion.c();
        companion.d();
    }
}
