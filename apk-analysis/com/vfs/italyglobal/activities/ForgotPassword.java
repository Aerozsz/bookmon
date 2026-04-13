package com.vfs.italyglobal.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import bc.n;
import be.d;
import be.f;
import com.google.gson.j;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.c;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import nb.h;
import nb.i;
import ua.v2;
import ua.w2;
import ua.x2;
import ua.y2;
import ua.z2;
import va.x;
import xa.l;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class ForgotPassword extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9400f0 = i.a(new x2(this));

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ForgotPassword f9401a;

        a(ForgotPassword forgotPassword) {
            this.f9401a = forgotPassword;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(ForgotPassword forgotPassword, int i10, String str) {
            n.e(str, "errorMsg");
            if (!(i10 == 403 || i10 == 429)) {
                Utility.Companion companion = Utility.f9497a;
                Activity c12 = forgotPassword.c1();
                String string = forgotPassword.getString(R.string.generic_error);
                n.d(string, "getString(...)");
                companion.w(c12, string);
            }
            return nb.x.f15721a;
        }

        public void a(d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            com.vfs.italyglobal.pojo.ForgotPassword forgotPassword = (com.vfs.italyglobal.pojo.ForgotPassword) xVar.a();
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            if (forgotPassword == null) {
                c.h(xVar, this.f9401a.c1(), new z2(this.f9401a));
            } else if (forgotPassword.getCode() == null || !n.a(forgotPassword.getCode(), "200")) {
                Activity c12 = this.f9401a.c1();
                String string = this.f9401a.getString(R.string.forgot_password_error);
                n.d(string, "getString(...)");
                companion.w(c12, string);
            } else {
                ForgotPassword forgotPassword2 = this.f9401a;
                forgotPassword2.K1(forgotPassword2.c1(), String.valueOf(forgotPassword.getMessage()));
            }
        }

        public void b(d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            if (th instanceof SocketTimeoutException) {
                companion.z(this.f9401a.c1());
            } else {
                companion.w(this.f9401a.c1(), th.getLocalizedMessage().toString());
            }
        }
    }

    public ForgotPassword() {
        super(false, false, 3, (bc.h) null);
    }

    private final void E1() {
        String str;
        String str2;
        String str3;
        String appointmentUrlAuthority;
        String obj = kc.n.N0(String.valueOf(F1().f20504c.getText())).toString();
        Utility.Companion companion = Utility.f9497a;
        String q10 = companion.q();
        String k10 = companion.k(c1());
        String e10 = companion.e(this, companion.l());
        HashMap hashMap = new HashMap();
        AppConfigModel e11 = d1().e();
        String str4 = "";
        if (e11 == null || (str = e11.getAppointmentUrlOrigin()) == null) {
            str = str4;
        }
        hashMap.put("referer", str);
        AppConfigModel e12 = d1().e();
        if (e12 == null || (str2 = e12.getAppointmentUrlOrigin()) == null) {
            str2 = str4;
        }
        hashMap.put("origin", str2);
        AppConfigModel e13 = d1().e();
        if (e13 == null || (str3 = e13.getAppointmentCfmLift()) == null) {
            str3 = str4;
        }
        hashMap.put("cfmlift", str3);
        AppConfigModel e14 = d1().e();
        if (!(e14 == null || (appointmentUrlAuthority = e14.getAppointmentUrlAuthority()) == null)) {
            str4 = appointmentUrlAuthority;
        }
        hashMap.put("authority", str4);
        hashMap.put("ClientSource", e10);
        j jVar = new j();
        jVar.r("missionCode", q10);
        jVar.r("countryCode", k10);
        jVar.r("email", obj);
        jVar.r("languageCode", "en");
        jVar.r("cultureCode", "en-US");
        f1().E(hashMap, jVar).A(new a(this));
    }

    private final l F1() {
        return (l) this.f9400f0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void H1(ForgotPassword forgotPassword, View view) {
        forgotPassword.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final void I1(ForgotPassword forgotPassword, View view) {
        if (c.j(forgotPassword)) {
            Editable text = forgotPassword.F1().f20504c.getText();
            n.b(text);
            if (text.length() == 0) {
                Utility.Companion companion = Utility.f9497a;
                Activity c12 = forgotPassword.c1();
                String string = forgotPassword.getString(R.string.appmnt_email_required);
                n.d(string, "getString(...)");
                companion.w(c12, string);
                return;
            }
            Utility.f9497a.y(forgotPassword.c1());
            forgotPassword.E1();
            return;
        }
        Utility.Companion companion2 = Utility.f9497a;
        Activity c13 = forgotPassword.c1();
        String string2 = forgotPassword.getString(R.string.network_error);
        n.d(string2, "getString(...)");
        companion2.w(c13, string2);
    }

    /* access modifiers changed from: private */
    public static final l J1(ForgotPassword forgotPassword) {
        l c10 = l.c(forgotPassword.getLayoutInflater());
        n.d(c10, "inflate(...)");
        return c10;
    }

    /* access modifiers changed from: private */
    public static final nb.x L1(ForgotPassword forgotPassword) {
        forgotPassword.b().k();
        return nb.x.f15721a;
    }

    public final void G1() {
        F1().f20505d.setOnClickListener(new v2(this));
        F1().f20503b.setOnClickListener(new w2(this));
    }

    public final void K1(Context context, String str) {
        n.e(context, "mContext");
        n.e(str, "message");
        c.e(this, str, (String) null, new y2(this), false, (String) null, (ac.a) null, false, 122, (Object) null).show();
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) F1().b());
        G1();
    }
}
