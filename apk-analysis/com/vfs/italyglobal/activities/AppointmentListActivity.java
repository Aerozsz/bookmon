package com.vfs.italyglobal.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import androidx.activity.s;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import bc.n;
import be.f;
import com.google.gson.j;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.activities.AppointmentLogin;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.pojo.SearchAppointmentDataResponse;
import com.vfs.italyglobal.pojo.SearchAppointmentDetails;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.g;
import com.vfs.italyglobal.utilities.q;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nb.h;
import nb.i;
import nb.o;
import ua.a1;
import ua.b1;
import ua.w0;
import ua.x0;
import ua.y0;
import ua.z0;
import va.x;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class AppointmentListActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9331f0 = i.a(new y0(this));

    /* renamed from: g0  reason: collision with root package name */
    private final String f9332g0 = "Log_Tag";

    /* renamed from: h0  reason: collision with root package name */
    private q f9333h0;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentListActivity f9334a;

        a(AppointmentListActivity appointmentListActivity) {
            this.f9334a = appointmentListActivity;
        }

        public void a(be.d dVar, be.x xVar) {
            Utility.f9497a.c();
            this.f9334a.V1();
            this.f9334a.b().k();
        }

        public void b(be.d dVar, Throwable th) {
            Utility.f9497a.c();
            this.f9334a.V1();
            this.f9334a.b().k();
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class b implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentListActivity f9335a;

        b(AppointmentListActivity appointmentListActivity) {
            this.f9335a = appointmentListActivity;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(AppointmentListActivity appointmentListActivity, int i10, String str) {
            n.e(str, "errorMsg");
            AppointmentLogin.a.c(AppointmentLogin.f9338h0, appointmentListActivity.c1(), i10, false, 4, (Object) null);
            return nb.x.f15721a;
        }

        public void a(be.d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            try {
                Utility.f9497a.c();
                SearchAppointmentDataResponse searchAppointmentDataResponse = (SearchAppointmentDataResponse) xVar.a();
                if (searchAppointmentDataResponse == null) {
                    com.vfs.italyglobal.utilities.c.h(xVar, this.f9335a.c1(), new b1(this.f9335a));
                } else if (searchAppointmentDataResponse.getData() != null) {
                    this.f9335a.J1().f20317d.setVisibility(8);
                    this.f9335a.J1().f20319f.setVisibility(0);
                    this.f9335a.Q1(searchAppointmentDataResponse);
                } else if (searchAppointmentDataResponse.getError() != null) {
                    this.f9335a.J1().f20317d.setVisibility(0);
                    this.f9335a.J1().f20319f.setVisibility(8);
                }
            } catch (Exception unused) {
                Utility.f9497a.c();
            }
        }

        public void b(be.d dVar, Throwable th) {
            String str;
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            Activity c12 = this.f9335a.c1();
            String localizedMessage = th.getLocalizedMessage();
            if (localizedMessage == null || (str = localizedMessage.toString()) == null) {
                str = this.f9335a.getString(R.string.generic_error);
                n.d(str, "getString(...)");
            }
            companion.w(c12, str);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class c extends s {

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ AppointmentListActivity f9336d;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        c(AppointmentListActivity appointmentListActivity) {
            super(false);
            this.f9336d = appointmentListActivity;
        }

        public void d() {
            this.f9336d.finish();
            this.f9336d.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class d implements za.b {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentListActivity f9337a;

        d(AppointmentListActivity appointmentListActivity) {
            this.f9337a = appointmentListActivity;
        }

        public void a(SearchAppointmentDetails searchAppointmentDetails) {
            n.e(searchAppointmentDetails, "searchAppointmentDetails");
            g.a aVar = g.f9512a;
            Activity c12 = this.f9337a.c1();
            String string = this.f9337a.getString(R.string.appointment_URN);
            n.d(string, "getString(...)");
            aVar.g(c12, string, String.valueOf(searchAppointmentDetails.getUrn()));
            this.f9337a.O1(searchAppointmentDetails);
        }
    }

    public AppointmentListActivity() {
        super(false, false, 3, (bc.h) null);
    }

    private final void H1() {
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
        f1().G(hashMap, jVar).A(new a(this));
    }

    private final void I1() {
        String str;
        String str2;
        String appointmentCfmLift;
        Utility.Companion companion = Utility.f9497a;
        companion.y(c1());
        j jVar = new j();
        String q10 = companion.q();
        String k10 = companion.k(c1());
        jVar.r("missionCode", q10);
        jVar.r("languageCode", companion.o());
        jVar.r("countryCode", k10);
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.appmnt_login_loggedInUser);
        n.d(string, "getString(...)");
        String str3 = "";
        jVar.r("loginUser", aVar.b(c12, string, str3));
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
        f1().H(hashMap, jVar).A(new b(this));
    }

    /* access modifiers changed from: private */
    public final xa.c J1() {
        return (xa.c) this.f9331f0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void L1(AppointmentListActivity appointmentListActivity, View view) {
        appointmentListActivity.P1();
    }

    /* access modifiers changed from: private */
    public static final void M1(AppointmentListActivity appointmentListActivity, View view) {
        appointmentListActivity.R1();
    }

    /* access modifiers changed from: private */
    public static final xa.c N1(AppointmentListActivity appointmentListActivity) {
        return xa.c.c(appointmentListActivity.getLayoutInflater());
    }

    private final void P1() {
        startActivity(new Intent(this, AppointmentGetEarliestDates.class));
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    private final void R1() {
        o d10 = com.vfs.italyglobal.utilities.c.d(this, R.layout.custom_alert_book_download);
        AlertDialog alertDialog = (AlertDialog) d10.c();
        ((TextView) ((View) d10.d()).findViewById(R.id.txt_centerText)).setText(getString(R.string.lbl_logout_msg));
        AppCompatButton appCompatButton = (AppCompatButton) ((View) d10.d()).findViewById(R.id.btn_dialog_no_cancel);
        appCompatButton.setText(getString(R.string.lbl_logout));
        appCompatButton.setOnClickListener(new z0(alertDialog, this));
        AppCompatButton appCompatButton2 = (AppCompatButton) ((View) d10.d()).findViewById(R.id.btn_dialog_yes_cancel);
        appCompatButton2.setText(getString(R.string.lbl_cancel));
        appCompatButton2.setOnClickListener(new a1(alertDialog));
        alertDialog.show();
    }

    /* access modifiers changed from: private */
    public static final void S1(AlertDialog alertDialog, AppointmentListActivity appointmentListActivity, View view) {
        alertDialog.dismiss();
        appointmentListActivity.H1();
    }

    /* access modifiers changed from: private */
    public static final void T1(AlertDialog alertDialog, View view) {
        alertDialog.dismiss();
    }

    private final void U1() {
        q qVar = new q(c1());
        this.f9333h0 = qVar;
        n.b(qVar);
        qVar.start();
    }

    /* access modifiers changed from: private */
    public final void V1() {
        q qVar = this.f9333h0;
        if (qVar != null) {
            n.b(qVar);
            qVar.d();
        }
    }

    public final void K1() {
        J1().f20315b.setOnClickListener(new w0(this));
        J1().f20316c.setOnClickListener(new x0(this));
        b().h(this, new c(this));
        U1();
    }

    public final void O1(SearchAppointmentDetails searchAppointmentDetails) {
        n.e(searchAppointmentDetails, "searchAppointmentDetails");
        Intent intent = new Intent(this, ApplicantDetailsActivity.class);
        intent.putExtra(getString(R.string.booked_appmnt_applcnt_details), searchAppointmentDetails);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    public final void Q1(SearchAppointmentDataResponse searchAppointmentDataResponse) {
        n.b(searchAppointmentDataResponse);
        List<SearchAppointmentDetails> data = searchAppointmentDataResponse.getData();
        n.c(data, "null cannot be cast to non-null type java.util.ArrayList<com.vfs.italyglobal.pojo.SearchAppointmentDetails>");
        wa.g gVar = new wa.g((ArrayList) data, new d(this));
        J1().f20319f.setLayoutManager(new LinearLayoutManager(this));
        J1().f20319f.t0();
        J1().f20319f.setAdapter(gVar);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) J1().b());
        K1();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        V1();
        Utility.Companion companion = Utility.f9497a;
        companion.c();
        companion.d();
    }

    public boolean onKeyDown(int i10, KeyEvent keyEvent) {
        if (i10 != 4) {
            return false;
        }
        R1();
        return false;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        I1();
    }
}
