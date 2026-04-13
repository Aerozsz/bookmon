package com.vfs.italyglobal.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import bc.n;
import be.d;
import be.f;
import com.google.gson.j;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.activities.AppointmentGetEarliestDates;
import com.vfs.italyglobal.activities.AppointmentLogin;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.pojo.ConfirmAppointmentResponse;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.c;
import com.vfs.italyglobal.utilities.g;
import java.util.HashMap;
import java.util.Objects;
import nb.h;
import nb.i;
import nb.o;
import ua.l3;
import ua.m3;
import ua.n3;
import ua.o3;
import ua.p3;
import ua.q3;
import ua.r3;
import ua.s3;
import ua.t3;
import va.x;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class OnlinePaymentActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9413f0 = i.a(new l3(this));

    /* renamed from: g0  reason: collision with root package name */
    public String f9414g0;

    /* renamed from: h0  reason: collision with root package name */
    public String f9415h0;

    /* renamed from: i0  reason: collision with root package name */
    public String f9416i0;

    /* renamed from: j0  reason: collision with root package name */
    public String f9417j0;

    /* renamed from: k0  reason: collision with root package name */
    public String f9418k0;

    /* renamed from: l0  reason: collision with root package name */
    public String f9419l0;

    /* renamed from: m0  reason: collision with root package name */
    private final String f9420m0 = "Log_Tag";

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ OnlinePaymentActivity f9421a;

        a(OnlinePaymentActivity onlinePaymentActivity) {
            this.f9421a = onlinePaymentActivity;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(OnlinePaymentActivity onlinePaymentActivity, int i10, String str) {
            n.e(str, "errorMsg");
            AppointmentLogin.a.c(AppointmentLogin.f9338h0, onlinePaymentActivity.c1(), i10, false, 4, (Object) null);
            return nb.x.f15721a;
        }

        public void a(d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            try {
                Utility.f9497a.c();
                ConfirmAppointmentResponse confirmAppointmentResponse = (ConfirmAppointmentResponse) xVar.a();
                Objects.toString(confirmAppointmentResponse);
                if (confirmAppointmentResponse == null) {
                    c.h(xVar, this.f9421a.c1(), new t3(this.f9421a));
                } else if (n.a(confirmAppointmentResponse.isAppointmentBooked(), Boolean.TRUE)) {
                    this.f9421a.h2();
                } else if (confirmAppointmentResponse.getError() != null) {
                    try {
                        String valueOf = String.valueOf(confirmAppointmentResponse.getError());
                        if (!kc.n.Q(valueOf, "description", false, 2, (Object) null) || !kc.n.Q(valueOf, "code", false, 2, (Object) null)) {
                            OnlinePaymentActivity onlinePaymentActivity = this.f9421a;
                            String string = onlinePaymentActivity.getString(R.string.generic_error);
                            n.d(string, "getString(...)");
                            onlinePaymentActivity.j2(string);
                            return;
                        }
                        String substring = valueOf.substring(kc.n.c0(valueOf, "description", 0, false, 6, (Object) null) + 12, valueOf.length() - 1);
                        n.d(substring, "substring(...)");
                        this.f9421a.j2(substring);
                    } catch (Exception e10) {
                        Exception exc = e10;
                        OnlinePaymentActivity onlinePaymentActivity2 = this.f9421a;
                        String string2 = onlinePaymentActivity2.getString(R.string.generic_error);
                        n.d(string2, "getString(...)");
                        onlinePaymentActivity2.j2(string2);
                        exc.toString();
                    }
                } else {
                    OnlinePaymentActivity onlinePaymentActivity3 = this.f9421a;
                    String string3 = onlinePaymentActivity3.getString(R.string.generic_error);
                    n.d(string3, "getString(...)");
                    onlinePaymentActivity3.j2(string3);
                }
            } catch (Exception e11) {
                Exception exc2 = e11;
                OnlinePaymentActivity onlinePaymentActivity4 = this.f9421a;
                String string4 = onlinePaymentActivity4.getString(R.string.generic_error);
                n.d(string4, "getString(...)");
                onlinePaymentActivity4.j2(string4);
                exc2.toString();
            }
        }

        public void b(d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.f9497a.c();
            Objects.toString(th);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class b extends WebViewClient {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ OnlinePaymentActivity f9422a;

        b(OnlinePaymentActivity onlinePaymentActivity) {
            this.f9422a = onlinePaymentActivity;
        }

        public void onPageFinished(WebView webView, String str) {
            this.f9422a.S1().f20546g.setVisibility(0);
            this.f9422a.S1().f20542c.setVisibility(8);
            this.f9422a.S1().f20544e.setRefreshing(false);
            Utility.f9497a.c();
            super.onPageFinished(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            this.f9422a.S1().f20546g.setVisibility(8);
            super.onPageStarted(webView, str, bitmap);
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            Objects.toString(webResourceError);
            super.onReceivedError(webView, webResourceRequest, webResourceError);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            Objects.toString(sslError);
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!TextUtils.isEmpty(str)) {
                OnlinePaymentActivity onlinePaymentActivity = this.f9422a;
                n.b(str);
                onlinePaymentActivity.Z1(str);
                return true;
            }
            OnlinePaymentActivity onlinePaymentActivity2 = this.f9422a;
            String string = onlinePaymentActivity2.c1().getString(R.string.msg_payment_failed);
            n.d(string, "getString(...)");
            onlinePaymentActivity2.l2(string);
            return true;
        }
    }

    public OnlinePaymentActivity() {
        super(false, false, 3, (bc.h) null);
    }

    private final void L1() {
        String str;
        String str2;
        String appointmentCfmLift;
        AppointmentGetEarliestDates.a aVar = AppointmentGetEarliestDates.f9302x0;
        aVar.a().getAppointmentRequestRefNo();
        aVar.a().getAppointmentBankReferenceNo();
        j jVar = new j();
        g.a aVar2 = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.appmnt_login_authToken);
        n.d(string, "getString(...)");
        String str3 = "";
        String b10 = aVar2.b(c12, string, str3);
        Utility.Companion companion = Utility.f9497a;
        jVar.r("missionCode", companion.q());
        jVar.r("countryCode", companion.k(c1()));
        Activity c13 = c1();
        String string2 = getString(R.string.appmnt_login_loggedInUser);
        n.d(string2, "getString(...)");
        jVar.r("loginUser", aVar2.b(c13, string2, str3));
        Activity c14 = c1();
        String string3 = getString(R.string.appointment_URN);
        n.d(string3, "getString(...)");
        jVar.r("urn", aVar2.b(c14, string3, str3));
        jVar.r("bankReferenceNo", aVar.a().getAppointmentBankReferenceNo());
        jVar.r("requestReferenceNo", aVar.a().getAppointmentRequestRefNo());
        jVar.q("amount", Double.valueOf(Double.parseDouble(N1())));
        String e10 = companion.e(c1(), companion.l());
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
        f1().t(hashMap, jVar).A(new a(this));
    }

    /* access modifiers changed from: private */
    public final xa.n S1() {
        return (xa.n) this.f9413f0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void V1(OnlinePaymentActivity onlinePaymentActivity, View view) {
        String string = onlinePaymentActivity.getString(R.string.msg_go_back);
        n.d(string, "getString(...)");
        onlinePaymentActivity.n2(string);
    }

    /* access modifiers changed from: private */
    public static final void W1(OnlinePaymentActivity onlinePaymentActivity) {
        onlinePaymentActivity.M1();
    }

    /* access modifiers changed from: private */
    public static final xa.n Y1(OnlinePaymentActivity onlinePaymentActivity) {
        return xa.n.c(onlinePaymentActivity.getLayoutInflater());
    }

    /* access modifiers changed from: private */
    public final void Z1(String str) {
        try {
            Uri parse = Uri.parse(str);
            n.d(parse, "parse(...)");
            S1().f20546g.loadUrl(str);
            if (kc.n.Q(str, "confirmation", false, 2, (Object) null)) {
                String queryParameter = parse.getQueryParameter("TransactionId");
                AppointmentGetEarliestDates.a aVar = AppointmentGetEarliestDates.f9302x0;
                aVar.a().setAppointmentBankReferenceNo(queryParameter);
                aVar.a().getAppointmentBankReferenceNo();
                L1();
            }
        } catch (Exception e10) {
            e10.toString();
        }
    }

    private final void a2() {
        Intent intent = new Intent(c1(), AppointmentListActivity.class);
        intent.setFlags(268468224);
        Activity c12 = c1();
        n.c(c12, "null cannot be cast to non-null type android.app.Activity");
        c12.startActivity(intent);
        finish();
    }

    /* access modifiers changed from: private */
    public static final void i2(AlertDialog alertDialog, OnlinePaymentActivity onlinePaymentActivity, View view) {
        alertDialog.dismiss();
        onlinePaymentActivity.a2();
    }

    /* access modifiers changed from: private */
    public static final nb.x k2(OnlinePaymentActivity onlinePaymentActivity) {
        onlinePaymentActivity.a2();
        return nb.x.f15721a;
    }

    /* access modifiers changed from: private */
    public final void l2(String str) {
        try {
            o d10 = c.d(this, R.layout.custom_alert_goback);
            AlertDialog alertDialog = (AlertDialog) d10.c();
            ((AppCompatTextView) ((View) d10.d()).findViewById(R.id.txtDesc)).setText(str);
            AppCompatButton appCompatButton = (AppCompatButton) ((View) d10.d()).findViewById(R.id.btnGoBack);
            appCompatButton.setText(c1().getText(R.string.lbl_go_back));
            ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btnCancel)).setVisibility(8);
            appCompatButton.setOnClickListener(new q3(alertDialog, this));
            alertDialog.show();
        } catch (Exception e10) {
            e10.toString();
        }
    }

    /* access modifiers changed from: private */
    public static final void m2(AlertDialog alertDialog, OnlinePaymentActivity onlinePaymentActivity, View view) {
        alertDialog.dismiss();
        onlinePaymentActivity.c1().startActivity(new Intent(onlinePaymentActivity.c1(), AppointmentListActivity.class));
        onlinePaymentActivity.finish();
    }

    private final void n2(String str) {
        try {
            o d10 = c.d(this, R.layout.custom_alert_goback);
            AlertDialog alertDialog = (AlertDialog) d10.c();
            ((AppCompatTextView) ((View) d10.d()).findViewById(R.id.txtDesc)).setText(str);
            ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btnGoBack)).setOnClickListener(new o3(alertDialog, this));
            ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btnCancel)).setOnClickListener(new p3(alertDialog));
            alertDialog.show();
        } catch (Exception e10) {
            e10.toString();
        }
    }

    /* access modifiers changed from: private */
    public static final void o2(AlertDialog alertDialog, OnlinePaymentActivity onlinePaymentActivity, View view) {
        alertDialog.dismiss();
        onlinePaymentActivity.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        onlinePaymentActivity.finish();
    }

    /* access modifiers changed from: private */
    public static final void p2(AlertDialog alertDialog, View view) {
        alertDialog.dismiss();
    }

    public final void M1() {
        try {
            S1().f20544e.setRefreshing(false);
            if (c.j(this)) {
                T1();
                X1();
                return;
            }
            S1().f20546g.setVisibility(8);
            Utility.f9497a.c();
            S1().f20542c.setVisibility(0);
        } catch (Exception e10) {
            e10.toString();
        }
    }

    public final String N1() {
        String str = this.f9419l0;
        if (str != null) {
            return str;
        }
        n.o("appAmount");
        return null;
    }

    public final String O1() {
        String str = this.f9415h0;
        if (str != null) {
            return str;
        }
        n.o("appDate");
        return null;
    }

    public final String P1() {
        String str = this.f9417j0;
        if (str != null) {
            return str;
        }
        n.o("appNoOfApp");
        return null;
    }

    public final String Q1() {
        String str = this.f9414g0;
        if (str != null) {
            return str;
        }
        n.o("appRefNo");
        return null;
    }

    public final String R1() {
        String str = this.f9416i0;
        if (str != null) {
            return str;
        }
        n.o("appTime");
        return null;
    }

    public final String T1() {
        String str = this.f9418k0;
        if (str != null) {
            return str;
        }
        n.o("paymentUrl");
        return null;
    }

    public final void U1() {
        S1().f20541b.setOnClickListener(new m3(this));
        M1();
        S1().f20544e.setOnRefreshListener(new n3(this));
    }

    public final void X1() {
        try {
            Utility.f9497a.y(c1());
            WebSettings settings = S1().f20546g.getSettings();
            n.d(settings, "getSettings(...)");
            settings.setJavaScriptEnabled(true);
            settings.setUseWideViewPort(true);
            settings.setLoadWithOverviewMode(true);
            settings.setDomStorageEnabled(true);
            settings.setBuiltInZoomControls(true);
            S1().f20546g.setWebViewClient(new b(this));
            S1().f20546g.loadUrl(T1());
        } catch (Exception e10) {
            e10.toString();
        }
    }

    public final void b2(String str) {
        n.e(str, "<set-?>");
        this.f9419l0 = str;
    }

    public final void c2(String str) {
        n.e(str, "<set-?>");
        this.f9415h0 = str;
    }

    public final void d2(String str) {
        n.e(str, "<set-?>");
        this.f9417j0 = str;
    }

    public final void e2(String str) {
        n.e(str, "<set-?>");
        this.f9414g0 = str;
    }

    public final void f2(String str) {
        n.e(str, "<set-?>");
        this.f9416i0 = str;
    }

    public final void g2(String str) {
        n.e(str, "<set-?>");
        this.f9418k0 = str;
    }

    public final void h2() {
        try {
            o d10 = c.d(this, R.layout.custom_alert_dialog_appointment_successfull);
            AlertDialog alertDialog = (AlertDialog) d10.c();
            ((TextView) ((View) d10.d()).findViewById(R.id.txt_centerText)).setText(c1().getString(R.string.appmnt_booked_successfully));
            ((TextView) ((View) d10.d()).findViewById(R.id.txt_dialog_ref_no)).setText(Q1());
            ((TextView) ((View) d10.d()).findViewById(R.id.txt_dialog_dateTime)).setText(O1() + getString(R.string.at) + R1());
            ((TextView) ((View) d10.d()).findViewById(R.id.txt_dialog_no_of_applicant)).setText(P1());
            ((TextView) ((View) d10.d()).findViewById(R.id.txtTotalAmount)).setText(N1());
            ((TextView) ((View) d10.d()).findViewById(R.id.txtBankRef)).setText(AppointmentGetEarliestDates.f9302x0.a().getAppointmentBankReferenceNo());
            ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btn_dialog)).setOnClickListener(new s3(alertDialog, this));
            alertDialog.show();
        } catch (Exception e10) {
            e10.toString();
        }
    }

    public final void j2(String str) {
        n.e(str, "message");
        if (!isFinishing()) {
            c.e(this, str, (String) null, new r3(this), false, (String) null, (ac.a) null, false, 122, (Object) null).show();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        super.onCreate(bundle);
        setContentView((View) S1().b());
        Bundle extras = getIntent().getExtras();
        String str6 = null;
        if (extras != null) {
            str = extras.getString("appRefNo");
        } else {
            str = null;
        }
        e2(String.valueOf(str));
        if (extras != null) {
            str2 = extras.getString("appDate");
        } else {
            str2 = null;
        }
        c2(String.valueOf(str2));
        if (extras != null) {
            str3 = extras.getString("appTime");
        } else {
            str3 = null;
        }
        f2(String.valueOf(str3));
        if (extras != null) {
            str4 = extras.getString("appNoOfApp");
        } else {
            str4 = null;
        }
        d2(String.valueOf(str4));
        if (extras != null) {
            str5 = extras.getString("paymentUrl");
        } else {
            str5 = null;
        }
        g2(String.valueOf(str5));
        if (extras != null) {
            str6 = extras.getString("appAmount");
        }
        b2(String.valueOf(str6));
        U1();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Utility.Companion companion = Utility.f9497a;
        companion.c();
        companion.d();
    }

    public void onBackPressed() {
    }
}
