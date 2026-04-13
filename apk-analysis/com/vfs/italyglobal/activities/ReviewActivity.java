package com.vfs.italyglobal.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import bc.n;
import be.d;
import be.f;
import com.google.gson.j;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.activities.AppointmentGetEarliestDates;
import com.vfs.italyglobal.activities.AppointmentLogin;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.pojo.MasVasDetailResponseData;
import com.vfs.italyglobal.pojo.ScheduleResponse;
import com.vfs.italyglobal.pojo.VasCartDetails;
import com.vfs.italyglobal.pojo.VasDetails;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.g;
import com.vfs.italyglobal.utilities.n;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import nb.h;
import nb.i;
import nb.o;
import org.apache.http.HttpStatus;
import org.apache.http.message.TokenParser;
import ua.h4;
import ua.i4;
import ua.j4;
import ua.k4;
import ua.l4;
import ua.m4;
import ua.n4;
import ua.o4;
import va.x;
import wa.w;
import xa.q;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class ReviewActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9442f0 = i.a(new h4(this));

    /* renamed from: g0  reason: collision with root package name */
    public ArrayList f9443g0;

    /* renamed from: h0  reason: collision with root package name */
    private boolean f9444h0;

    /* renamed from: i0  reason: collision with root package name */
    public String f9445i0;

    /* renamed from: j0  reason: collision with root package name */
    public w f9446j0;

    /* renamed from: k0  reason: collision with root package name */
    public wa.x f9447k0;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ReviewActivity f9448a;

        a(ReviewActivity reviewActivity) {
            this.f9448a = reviewActivity;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(ReviewActivity reviewActivity, int i10, String str) {
            n.e(str, "errorMsg");
            AppointmentLogin.a.c(AppointmentLogin.f9338h0, reviewActivity.c1(), i10, false, 4, (Object) null);
            return nb.x.f15721a;
        }

        public void a(d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            try {
                Utility.f9497a.c();
                MasVasDetailResponseData masVasDetailResponseData = (MasVasDetailResponseData) xVar.a();
                if (masVasDetailResponseData != null) {
                    this.f9448a.L1();
                } else {
                    com.vfs.italyglobal.utilities.c.h(xVar, this.f9448a.c1(), new n4(this.f9448a));
                }
                Objects.toString(masVasDetailResponseData);
            } catch (Exception e10) {
                e10.toString();
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
    public static final class b implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ReviewActivity f9449a;

        b(ReviewActivity reviewActivity) {
            this.f9449a = reviewActivity;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(ReviewActivity reviewActivity, int i10, String str) {
            n.e(str, "errorMsg");
            AppointmentLogin.a.c(AppointmentLogin.f9338h0, reviewActivity.c1(), i10, false, 4, (Object) null);
            return nb.x.f15721a;
        }

        public void a(d dVar, be.x xVar) {
            be.x xVar2 = xVar;
            n.e(dVar, "call");
            n.e(xVar2, "response");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            ScheduleResponse scheduleResponse = (ScheduleResponse) xVar2.a();
            if (scheduleResponse != null) {
                scheduleResponse.isAppointmentBooked();
                if (scheduleResponse.isAppointmentBooked()) {
                    if (!n.a(this.f9449a.P1(), "PAYAtVAC")) {
                        AppointmentGetEarliestDates.a aVar = AppointmentGetEarliestDates.f9302x0;
                        aVar.a().setAppointmentRequestRefNo(String.valueOf(scheduleResponse.getRequestRefNo()));
                        VasCartDetails a10 = aVar.a();
                        String digitalSignature = scheduleResponse.getDigitalSignature();
                        if (digitalSignature == null) {
                            digitalSignature = "";
                        }
                        a10.setAppointmentDigitalSignature(digitalSignature);
                        aVar.a().setPaymentBaseUrl(scheduleResponse.getURL());
                        aVar.a().setPayload(scheduleResponse.getPayLoad());
                        String appointmentRequestRefNo = aVar.a().getAppointmentRequestRefNo();
                        String appointmentDate = aVar.a().getAppointmentDate();
                        String appointmentTime = aVar.a().getAppointmentTime();
                        g.a aVar2 = g.f9512a;
                        Activity c12 = this.f9449a.c1();
                        String string = this.f9449a.getString(R.string.applicants_count);
                        n.d(string, "getString(...)");
                        int a11 = aVar2.a(c12, string, 0);
                        Object G1 = this.f9449a.J1(aVar.a().getPaymentBaseUrl(), aVar.a().getAppointmentRequestRefNo(), aVar.a().getCurrency(), companion.o(), String.valueOf(aVar.a().getFinalAmount()), aVar.a().getAppointmentDigitalSignature());
                        if (G1 instanceof String) {
                            this.f9449a.U1(appointmentRequestRefNo, appointmentDate, appointmentTime, String.valueOf(a11), (String) G1, String.valueOf(aVar.a().getFinalAmount()));
                            return;
                        }
                        ReviewActivity reviewActivity = this.f9449a;
                        String string2 = reviewActivity.getString(R.string.msg_payment_failed);
                        n.d(string2, "getString(...)");
                        reviewActivity.h2(string2);
                    } else if (scheduleResponse.isAppointmentBooked()) {
                        this.f9449a.f2();
                    } else {
                        Activity c13 = this.f9449a.c1();
                        String string3 = this.f9449a.getString(R.string.generic_error);
                        n.d(string3, "getString(...)");
                        companion.w(c13, string3);
                    }
                } else if (scheduleResponse.getError() != null) {
                    try {
                        String scheduleResponseError = scheduleResponse.getError().toString();
                        if (!kc.n.Q(scheduleResponseError, "description", false, 2, (Object) null) || !kc.n.Q(scheduleResponseError, "code", false, 2, (Object) null)) {
                            Activity c14 = this.f9449a.c1();
                            String string4 = this.f9449a.getString(R.string.generic_error);
                            n.d(string4, "getString(...)");
                            companion.w(c14, string4);
                            return;
                        }
                        String substring = scheduleResponseError.substring(kc.n.c0(scheduleResponseError, "description", 0, false, 6, (Object) null) + 12, scheduleResponseError.length() - 1);
                        n.d(substring, "substring(...)");
                        companion.w(this.f9449a.c1(), substring);
                    } catch (Exception e10) {
                        Utility.Companion companion2 = Utility.f9497a;
                        Activity c15 = this.f9449a.c1();
                        String string5 = this.f9449a.getString(R.string.generic_error);
                        n.d(string5, "getString(...)");
                        companion2.w(c15, string5);
                        e10.toString();
                    }
                } else {
                    Activity c16 = this.f9449a.c1();
                    String string6 = this.f9449a.getString(R.string.generic_error);
                    n.d(string6, "getString(...)");
                    companion.w(c16, string6);
                }
            } else {
                com.vfs.italyglobal.utilities.c.h(xVar2, this.f9449a.c1(), new o4(this.f9449a));
            }
        }

        public void b(d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            String localizedMessage = th.getLocalizedMessage();
            if (localizedMessage != null) {
                Utility.f9497a.w(this.f9449a.c1(), localizedMessage);
            }
            Utility.f9497a.c();
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class c extends ClickableSpan {

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ ReviewActivity f9450b;

        c(ReviewActivity reviewActivity) {
            this.f9450b = reviewActivity;
        }

        public void onClick(View view) {
            n.e(view, "textView");
            this.f9450b.startActivity(new Intent(this.f9450b, TermsAndConditionReviewActivity.class));
        }

        public void updateDrawState(TextPaint textPaint) {
            n.e(textPaint, "ds");
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
        }
    }

    public ReviewActivity() {
        super(false, false, 3, (bc.h) null);
    }

    /* access modifiers changed from: private */
    public final Object J1(String str, String str2, String str3, String str4, String str5, String str6) {
        AppointmentGetEarliestDates.a aVar = AppointmentGetEarliestDates.f9302x0;
        if (!n.a(aVar.a().getAppointmentRequestRefNo(), str2) || !n.a(aVar.a().getCurrency(), str3) || !n.a(Utility.f9497a.o(), str4) || !n.a(String.valueOf(aVar.a().getFinalAmount()), str5) || !n.a(aVar.a().getAppointmentDigitalSignature(), str6)) {
            return "";
        }
        return str + "?RequestRefNo=" + str2 + "&Currency=" + str3 + "&Culture=" + str4 + "&Amount=" + str5 + "&DigitalSignature=" + str6 + "&RedirectURL=";
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00b9 A[Catch:{ Exception -> 0x0091 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void K1() {
        /*
            r8 = this;
            java.lang.String r0 = ""
            boolean r1 = com.vfs.italyglobal.utilities.c.j(r8)     // Catch:{ Exception -> 0x0091 }
            java.lang.String r2 = "getString(...)"
            if (r1 == 0) goto L_0x00da
            com.vfs.italyglobal.utilities.Utility$Companion r1 = com.vfs.italyglobal.utilities.Utility.f9497a     // Catch:{ Exception -> 0x0091 }
            android.app.Activity r3 = r8.c1()     // Catch:{ Exception -> 0x0091 }
            r1.y(r3)     // Catch:{ Exception -> 0x0091 }
            com.google.gson.j r3 = new com.google.gson.j     // Catch:{ Exception -> 0x0091 }
            r3.<init>()     // Catch:{ Exception -> 0x0091 }
            com.vfs.italyglobal.utilities.g$a r4 = com.vfs.italyglobal.utilities.g.f9512a     // Catch:{ Exception -> 0x0091 }
            r5 = 2131886165(0x7f120055, float:1.9406901E38)
            java.lang.String r5 = r8.getString(r5)     // Catch:{ Exception -> 0x0091 }
            bc.n.d(r5, r2)     // Catch:{ Exception -> 0x0091 }
            java.lang.String r5 = r4.b(r8, r5, r0)     // Catch:{ Exception -> 0x0091 }
            java.lang.String r6 = "loginUser"
            r7 = 2131886169(0x7f120059, float:1.940691E38)
            java.lang.String r7 = r8.getString(r7)     // Catch:{ Exception -> 0x0091 }
            bc.n.d(r7, r2)     // Catch:{ Exception -> 0x0091 }
            java.lang.String r7 = r4.b(r8, r7, r0)     // Catch:{ Exception -> 0x0091 }
            r3.r(r6, r7)     // Catch:{ Exception -> 0x0091 }
            java.lang.String r6 = "missionCode"
            java.lang.String r7 = r1.q()     // Catch:{ Exception -> 0x0091 }
            r3.r(r6, r7)     // Catch:{ Exception -> 0x0091 }
            java.lang.String r6 = "countryCode"
            java.lang.String r7 = r1.k(r8)     // Catch:{ Exception -> 0x0091 }
            r3.r(r6, r7)     // Catch:{ Exception -> 0x0091 }
            java.lang.String r6 = "urn"
            r7 = 2131886183(0x7f120067, float:1.9406938E38)
            java.lang.String r7 = r8.getString(r7)     // Catch:{ Exception -> 0x0091 }
            bc.n.d(r7, r2)     // Catch:{ Exception -> 0x0091 }
            java.lang.String r2 = r4.b(r8, r7, r0)     // Catch:{ Exception -> 0x0091 }
            r3.r(r6, r2)     // Catch:{ Exception -> 0x0091 }
            java.lang.String r2 = "applicants"
            com.google.gson.f r4 = r8.X1()     // Catch:{ Exception -> 0x0091 }
            r3.n(r2, r4)     // Catch:{ Exception -> 0x0091 }
            r3.toString()     // Catch:{ Exception -> 0x0091 }
            java.lang.String r2 = r1.l()     // Catch:{ Exception -> 0x0091 }
            java.lang.String r1 = r1.e(r8, r2)     // Catch:{ Exception -> 0x0091 }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ Exception -> 0x0091 }
            r2.<init>()     // Catch:{ Exception -> 0x0091 }
            java.lang.String r4 = "Authorize"
            r2.put(r4, r5)     // Catch:{ Exception -> 0x0091 }
            java.lang.String r4 = "referer"
            com.vfs.italyglobal.AppDelegate r5 = r8.d1()     // Catch:{ Exception -> 0x0091 }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x0091 }
            if (r5 == 0) goto L_0x0093
            java.lang.String r5 = r5.getAppointmentUrlOrigin()     // Catch:{ Exception -> 0x0091 }
            if (r5 != 0) goto L_0x0094
            goto L_0x0093
        L_0x0091:
            r0 = move-exception
            goto L_0x00ed
        L_0x0093:
            r5 = r0
        L_0x0094:
            r2.put(r4, r5)     // Catch:{ Exception -> 0x0091 }
            java.lang.String r4 = "origin"
            com.vfs.italyglobal.AppDelegate r5 = r8.d1()     // Catch:{ Exception -> 0x0091 }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x0091 }
            if (r5 == 0) goto L_0x00a9
            java.lang.String r5 = r5.getAppointmentUrlOrigin()     // Catch:{ Exception -> 0x0091 }
            if (r5 != 0) goto L_0x00aa
        L_0x00a9:
            r5 = r0
        L_0x00aa:
            r2.put(r4, r5)     // Catch:{ Exception -> 0x0091 }
            java.lang.String r4 = "cfmlift"
            com.vfs.italyglobal.AppDelegate r5 = r8.d1()     // Catch:{ Exception -> 0x0091 }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x0091 }
            if (r5 == 0) goto L_0x00c1
            java.lang.String r5 = r5.getAppointmentCfmLift()     // Catch:{ Exception -> 0x0091 }
            if (r5 != 0) goto L_0x00c0
            goto L_0x00c1
        L_0x00c0:
            r0 = r5
        L_0x00c1:
            r2.put(r4, r0)     // Catch:{ Exception -> 0x0091 }
            java.lang.String r0 = "ClientSource"
            r2.put(r0, r1)     // Catch:{ Exception -> 0x0091 }
            ab.b r0 = r8.f1()     // Catch:{ Exception -> 0x0091 }
            be.d r0 = r0.r(r2, r3)     // Catch:{ Exception -> 0x0091 }
            com.vfs.italyglobal.activities.ReviewActivity$a r1 = new com.vfs.italyglobal.activities.ReviewActivity$a     // Catch:{ Exception -> 0x0091 }
            r1.<init>(r8)     // Catch:{ Exception -> 0x0091 }
            r0.A(r1)     // Catch:{ Exception -> 0x0091 }
            return
        L_0x00da:
            com.vfs.italyglobal.utilities.Utility$Companion r0 = com.vfs.italyglobal.utilities.Utility.f9497a     // Catch:{ Exception -> 0x0091 }
            r0.c()     // Catch:{ Exception -> 0x0091 }
            r1 = 2131886591(0x7f1201ff, float:1.9407765E38)
            java.lang.String r1 = r8.getString(r1)     // Catch:{ Exception -> 0x0091 }
            bc.n.d(r1, r2)     // Catch:{ Exception -> 0x0091 }
            r0.w(r8, r1)     // Catch:{ Exception -> 0x0091 }
            return
        L_0x00ed:
            r0.toString()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vfs.italyglobal.activities.ReviewActivity.K1():void");
    }

    /* access modifiers changed from: private */
    public final void L1() {
        String str;
        String str2;
        String str3;
        String str4;
        try {
            if (com.vfs.italyglobal.utilities.c.j(this)) {
                Utility.Companion companion = Utility.f9497a;
                companion.y(c1());
                j jVar = new j();
                String q10 = companion.q();
                String k10 = companion.k(c1());
                Locale locale = Locale.ROOT;
                String lowerCase = q10.toLowerCase(locale);
                n.d(lowerCase, "toLowerCase(...)");
                jVar.r("missionCode", lowerCase);
                String lowerCase2 = k10.toLowerCase(locale);
                n.d(lowerCase2, "toLowerCase(...)");
                jVar.r("countryCode", lowerCase2);
                g.a aVar = g.f9512a;
                Activity c12 = c1();
                String string = getString(R.string.appmnt_center_code);
                n.d(string, "getString(...)");
                jVar.r("centerCode", aVar.b(c12, string, ""));
                Activity c13 = c1();
                String string2 = getString(R.string.appmnt_login_loggedInUser);
                n.d(string2, "getString(...)");
                jVar.r("loginUser", aVar.b(c13, string2, ""));
                Activity c14 = c1();
                String string3 = getString(R.string.appointment_URN);
                n.d(string3, "getString(...)");
                jVar.r("urn", aVar.b(c14, string3, ""));
                Activity c15 = c1();
                String string4 = getString(R.string.appmnt_allocation_Id);
                n.d(string4, "getString(...)");
                jVar.r("allocationId", aVar.b(c15, string4, ""));
                jVar.o("CanVFSReachoutToApplicant", Boolean.FALSE);
                jVar.r("notificationType", "none");
                j jVar2 = new j();
                jVar2.r("RequestRefNo", "");
                AppointmentGetEarliestDates.a aVar2 = AppointmentGetEarliestDates.f9302x0;
                jVar2.q("amount", Float.valueOf(aVar2.a().getFinalAmount()));
                jVar2.r("clientId", "");
                jVar2.r("merchantId", "");
                if (n.a(P1(), "PAYAtVAC")) {
                    str = "VAC";
                } else {
                    str = "online";
                }
                jVar2.r("paymentmode", str);
                jVar2.r("currency", aVar2.a().getCurrency());
                jVar.n("paymentdetails", jVar2);
                Activity c16 = c1();
                String string5 = getString(R.string.appmnt_login_authToken);
                n.d(string5, "getString(...)");
                String b10 = aVar.b(c16, string5, "");
                String e10 = companion.e(this, companion.l());
                HashMap hashMap = new HashMap();
                hashMap.put("Authorize", b10);
                hashMap.put("Content-Type", "application/json");
                AppConfigModel e11 = d1().e();
                if (e11 == null || (str2 = e11.getAppointmentUrlOrigin()) == null) {
                    str2 = "";
                }
                hashMap.put("referer", str2);
                AppConfigModel e12 = d1().e();
                if (e12 == null || (str3 = e12.getAppointmentUrlOrigin()) == null) {
                    str3 = "";
                }
                hashMap.put("origin", str3);
                AppConfigModel e13 = d1().e();
                if (e13 == null || (str4 = e13.getAppointmentCfmLift()) == null) {
                    str4 = "";
                }
                hashMap.put("cfmlift", str4);
                hashMap.put("ClientSource", e10);
                StringBuilder sb2 = new StringBuilder();
                String upperCase = k10.toUpperCase(locale);
                n.d(upperCase, "toUpperCase(...)");
                sb2.append(upperCase);
                sb2.append('/');
                Activity c17 = c1();
                String string6 = getString(R.string.appmnt_center_code);
                n.d(string6, "getString(...)");
                String upperCase2 = aVar.b(c17, string6, "").toUpperCase(locale);
                n.d(upperCase2, "toUpperCase(...)");
                sb2.append(upperCase2);
                sb2.append('/');
                String upperCase3 = q10.toUpperCase(locale);
                n.d(upperCase3, "toUpperCase(...)");
                sb2.append(upperCase3);
                hashMap.put("route", sb2.toString());
                f1().q(hashMap, jVar).A(new b(this));
                return;
            }
            Utility.Companion companion2 = Utility.f9497a;
            companion2.c();
            String string7 = getString(R.string.network_error);
            n.d(string7, "getString(...)");
            companion2.w(this, string7);
        } catch (Exception e14) {
            Utility.f9497a.c();
            e14.toString();
            AppointmentLogin.a.c(AppointmentLogin.f9338h0, c1(), HttpStatus.SC_UNAUTHORIZED, false, 4, (Object) null);
        }
    }

    private final q O1() {
        return (q) this.f9442f0.getValue();
    }

    private final void R1() {
        c cVar = new c(this);
        n.a aVar = com.vfs.italyglobal.utilities.n.f9525e;
        AppCompatTextView appCompatTextView = O1().f20624n;
        bc.n.d(appCompatTextView, "tvTermsAndCondition");
        String string = getString(R.string.lbl_terms_condition);
        bc.n.d(string, "getString(...)");
        com.vfs.italyglobal.utilities.n a10 = aVar.a(appCompatTextView, string);
        String string2 = getString(R.string.lbl_terms_condition_only);
        bc.n.d(string2, "getString(...)");
        a10.b(cVar, string2);
        String string3 = getString(R.string.lbl_terms_condition_only);
        bc.n.d(string3, "getString(...)");
        a10.c(R.color.colorOrange, string3);
        String string4 = getString(R.string.lbl_terms_condition_only);
        bc.n.d(string4, "getString(...)");
        a10.d(string4);
        a10.a();
    }

    private final void S1() {
        try {
            AppCompatTextView appCompatTextView = O1().f20633w;
            AppointmentGetEarliestDates.a aVar = AppointmentGetEarliestDates.f9302x0;
            appCompatTextView.setText(aVar.a().getFromCountryName());
            O1().f20634x.setText(aVar.a().getToCountryName());
            O1().V.setText(aVar.a().getVisaApplicationCenter());
            O1().f20627q.setText(aVar.a().getApplicationCategory());
            O1().f20630t.setText(aVar.a().getApplicationSubCategory());
            O1().f20632v.setText(aVar.a().getAppointmentType());
            O1().f20628r.setText(aVar.a().getAppointmentDate());
            O1().f20631u.setText(aVar.a().getAppointmentTime());
            AppCompatTextView appCompatTextView2 = O1().R;
            appCompatTextView2.setText(aVar.a().getCurrency() + TokenParser.SP + aVar.a().getSumOfAllVas());
            AppCompatTextView appCompatTextView3 = O1().f20636z;
            appCompatTextView3.setText(aVar.a().getCurrency() + TokenParser.SP + aVar.a().getVfsServiceFees());
            AppCompatTextView appCompatTextView4 = O1().P;
            appCompatTextView4.setText(aVar.a().getCurrency() + TokenParser.SP + aVar.a().getVfsServiceFees());
            AppCompatTextView appCompatTextView5 = O1().N;
            appCompatTextView5.setText(aVar.a().getCurrency() + TokenParser.SP + aVar.a().getVfsAdditionalFees());
            AppCompatTextView appCompatTextView6 = O1().U;
            appCompatTextView6.setText(aVar.a().getCurrency() + TokenParser.SP + aVar.a().getFinalAmount());
            O1().A.setText(aVar.a().getFeeName());
            if (bc.n.a(String.valueOf(aVar.a().getSumOfAllVas()), "0.0")) {
                O1().L.setVisibility(8);
                O1().R.setVisibility(8);
                O1().Q.setVisibility(8);
                O1().X.setVisibility(8);
            }
            if (bc.n.a(String.valueOf(aVar.a().getVfsAdditionalFees()), "0.0")) {
                O1().f20625o.setVisibility(8);
                O1().N.setVisibility(8);
                O1().f20615e.setVisibility(8);
            }
            a2();
            d2();
        } catch (Exception e10) {
            e10.toString();
        }
    }

    /* access modifiers changed from: private */
    public static final q T1(ReviewActivity reviewActivity) {
        return q.c(reviewActivity.getLayoutInflater());
    }

    /* access modifiers changed from: private */
    public final void U1(String str, String str2, String str3, String str4, String str5, String str6) {
        try {
            if (com.vfs.italyglobal.utilities.c.j(this)) {
                Intent intent = new Intent(this, OnlinePaymentActivity.class);
                intent.putExtra("appRefNo", str);
                intent.putExtra("appDate", str2);
                intent.putExtra("appTime", str3);
                intent.putExtra("appNoOfApp", str4);
                intent.putExtra("paymentUrl", str5);
                intent.putExtra("appAmount", str6);
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
                startActivity(intent);
                return;
            }
            Utility.Companion companion = Utility.f9497a;
            String string = getString(R.string.network_error);
            bc.n.d(string, "getString(...)");
            companion.w(this, string);
        } catch (Exception e10) {
            e10.toString();
        }
    }

    /* access modifiers changed from: private */
    public static final void V1(ReviewActivity reviewActivity, View view) {
        reviewActivity.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final void W1(ReviewActivity reviewActivity, View view) {
        if (reviewActivity.O1().f20614d.isChecked()) {
            reviewActivity.j2();
            return;
        }
        Utility.Companion companion = Utility.f9497a;
        Activity c12 = reviewActivity.c1();
        String string = reviewActivity.getString(R.string.msg_accept_tnc);
        bc.n.d(string, "getString(...)");
        companion.w(c12, string);
    }

    private final com.google.gson.f X1() {
        com.google.gson.f fVar = new com.google.gson.f();
        try {
            int size = N1().size();
            for (int i10 = 0; i10 < size; i10++) {
                int size2 = ((VasDetails) N1().get(i10)).getVasApplicantsList().size();
                for (int i11 = 0; i11 < size2; i11++) {
                    if (((VasDetails) N1().get(i10)).getVasApplicantsList().get(i11).isChecked()) {
                        j jVar = new j();
                        jVar.r("arn", ((VasDetails) N1().get(i10)).getVasApplicantsList().get(i11).getApplicantARN());
                        jVar.r("vasId", ((VasDetails) N1().get(i10)).getVasApplicantsList().get(i11).getVasID());
                        jVar.r("vasCode", ((VasDetails) N1().get(i10)).getVasApplicantsList().get(i11).getVasCode());
                        fVar.n(jVar);
                    }
                }
            }
            fVar.toString();
            return fVar;
        } catch (Exception e10) {
            e10.toString();
            return fVar;
        }
    }

    private final void Y1() {
        c1().startActivity(new Intent(this, AppointmentListActivity.class));
    }

    private final void a2() {
        try {
            Z1(new w(c1(), AddApplicantBookAppointmentActivity.f9247v0.a(), false));
            O1().f20622l.setLayoutManager(new LinearLayoutManager(c1()));
            O1().f20622l.t0();
            O1().f20622l.setAdapter(M1());
        } catch (Exception e10) {
            e10.toString();
        }
    }

    private final void d2() {
        try {
            e2(new wa.x(N1()));
            O1().f20623m.setLayoutManager(new LinearLayoutManager(c1()));
            O1().f20623m.t0();
            O1().f20623m.setAdapter(Q1());
        } catch (Exception e10) {
            e10.toString();
        }
    }

    /* access modifiers changed from: private */
    public static final void g2(AlertDialog alertDialog, ReviewActivity reviewActivity, View view) {
        alertDialog.dismiss();
        reviewActivity.Y1();
    }

    /* access modifiers changed from: private */
    public static final nb.x i2(ReviewActivity reviewActivity) {
        reviewActivity.Y1();
        return nb.x.f15721a;
    }

    /* access modifiers changed from: private */
    public static final void k2(AlertDialog alertDialog, ReviewActivity reviewActivity, View view) {
        alertDialog.dismiss();
        if (reviewActivity.f9444h0) {
            reviewActivity.K1();
        } else {
            reviewActivity.L1();
        }
    }

    public final w M1() {
        w wVar = this.f9446j0;
        if (wVar != null) {
            return wVar;
        }
        bc.n.o("applicantAdapter");
        return null;
    }

    public final ArrayList N1() {
        ArrayList arrayList = this.f9443g0;
        if (arrayList != null) {
            return arrayList;
        }
        bc.n.o("arrVFSList");
        return null;
    }

    public final String P1() {
        String str = this.f9445i0;
        if (str != null) {
            return str;
        }
        bc.n.o("paymentMode");
        return null;
    }

    public final wa.x Q1() {
        wa.x xVar = this.f9447k0;
        if (xVar != null) {
            return xVar;
        }
        bc.n.o("vasServiceAdapter");
        return null;
    }

    public final void Z1(w wVar) {
        bc.n.e(wVar, "<set-?>");
        this.f9446j0 = wVar;
    }

    public final void b2(ArrayList arrayList) {
        bc.n.e(arrayList, "<set-?>");
        this.f9443g0 = arrayList;
    }

    public final void c2(String str) {
        bc.n.e(str, "<set-?>");
        this.f9445i0 = str;
    }

    public final void e2(wa.x xVar) {
        bc.n.e(xVar, "<set-?>");
        this.f9447k0 = xVar;
    }

    public final void f2() {
        try {
            o d10 = com.vfs.italyglobal.utilities.c.d(this, R.layout.custom_alert_dialog_appointment_successfull);
            AlertDialog alertDialog = (AlertDialog) d10.c();
            ((TextView) ((View) d10.d()).findViewById(R.id.txt_centerText)).setText(c1().getString(R.string.appmnt_booked_successfully));
            ((LinearLayout) ((View) d10.d()).findViewById(R.id.llAppointmentAllFields)).setVisibility(8);
            ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btn_dialog)).setOnClickListener(new l4(alertDialog, this));
            alertDialog.show();
        } catch (Exception e10) {
            e10.toString();
        }
    }

    public final void h2(String str) {
        bc.n.e(str, "message");
        com.vfs.italyglobal.utilities.c.e(this, str, (String) null, new m4(this), false, (String) null, (ac.a) null, false, 122, (Object) null).show();
    }

    public final void j2() {
        String str;
        String str2;
        o d10 = com.vfs.italyglobal.utilities.c.d(this, R.layout.custom_dialog_go_for_payment);
        AlertDialog alertDialog = (AlertDialog) d10.c();
        AppCompatTextView appCompatTextView = (AppCompatTextView) ((View) d10.d()).findViewById(R.id.txtMessage);
        if (bc.n.a(P1(), "PAYAtVAC")) {
            str = getString(R.string.msg_go_payment_VAC);
        } else {
            str = getString(R.string.msg_go_payment_online);
        }
        appCompatTextView.setText(str);
        AppCompatButton appCompatButton = (AppCompatButton) ((View) d10.d()).findViewById(R.id.btnGoForPayment);
        if (bc.n.a(P1(), "PAYAtVAC")) {
            str2 = getString(R.string.lbl_pay_at_VAC);
        } else {
            str2 = getString(R.string.lbl_pay_online);
        }
        appCompatButton.setText(str2);
        appCompatButton.setOnClickListener(new k4(alertDialog, this));
        alertDialog.show();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Object obj;
        String str;
        super.onCreate(bundle);
        setContentView((View) O1().b());
        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                obj = extras.get(getString(R.string.bundle_vasList));
            } else {
                obj = null;
            }
            bc.n.c(obj, "null cannot be cast to non-null type java.util.ArrayList<com.vfs.italyglobal.pojo.VasDetails>");
            b2((ArrayList) obj);
            this.f9444h0 = extras.getBoolean("isVasAdded");
            R1();
            O1().f20621k.setOnClickListener(new i4(this));
            g.a aVar = g.f9512a;
            Activity c12 = c1();
            String string = getString(R.string.appmnt_payment_mode);
            bc.n.d(string, "getString(...)");
            c2(aVar.b(c12, string, ""));
            if (bc.n.a(Utility.f9497a.k(c1()), "DZA")) {
                O1().J.setText(getString(R.string.lbl_review_desc_algeria));
                Activity c13 = c1();
                String string2 = getString(R.string.appmnt_visa_sub_category_payment_mode);
                bc.n.d(string2, "getString(...)");
                String b10 = aVar.b(c13, string2, "");
                if (!bc.n.a(b10, "")) {
                    c2(b10);
                }
            }
            P1();
            AppCompatButton appCompatButton = O1().f20612b;
            if (bc.n.a(P1(), "PAYAtVAC")) {
                str = getString(R.string.lbl_pay_at_VAC);
            } else {
                str = getString(R.string.lbl_pay_online);
            }
            appCompatButton.setText(str);
            O1().f20612b.setOnClickListener(new j4(this));
            S1();
        } catch (Exception e10) {
            e10.toString();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Utility.Companion companion = Utility.f9497a;
        companion.c();
        companion.d();
    }
}
