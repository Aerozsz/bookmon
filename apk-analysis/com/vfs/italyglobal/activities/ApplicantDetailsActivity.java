package com.vfs.italyglobal.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import bc.n;
import be.d;
import be.f;
import com.google.gson.j;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.activities.AppointmentLogin;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.pojo.AppointmentCancelledResponseData;
import com.vfs.italyglobal.pojo.ErrorMessage;
import com.vfs.italyglobal.pojo.SearchApplicantDetails;
import com.vfs.italyglobal.pojo.SearchAppointmentDetails;
import com.vfs.italyglobal.pojo.VasDetailsObj;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.c;
import com.vfs.italyglobal.utilities.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nb.h;
import nb.i;
import nb.o;
import org.apache.http.message.TokenParser;
import ua.a0;
import ua.b0;
import ua.u;
import ua.v;
import ua.y;
import ua.z;
import va.x;
import wa.b;
import wa.w;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class ApplicantDetailsActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9276f0 = i.a(new ua.x(this));

    /* renamed from: g0  reason: collision with root package name */
    public SearchAppointmentDetails f9277g0;

    /* renamed from: h0  reason: collision with root package name */
    public w f9278h0;

    /* renamed from: i0  reason: collision with root package name */
    public b f9279i0;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ApplicantDetailsActivity f9280a;

        a(ApplicantDetailsActivity applicantDetailsActivity) {
            this.f9280a = applicantDetailsActivity;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(ApplicantDetailsActivity applicantDetailsActivity, int i10, String str) {
            n.e(str, "errorMsg");
            AppointmentLogin.a.c(AppointmentLogin.f9338h0, applicantDetailsActivity.c1(), i10, false, 4, (Object) null);
            return nb.x.f15721a;
        }

        public void a(d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            AppointmentCancelledResponseData appointmentCancelledResponseData = (AppointmentCancelledResponseData) xVar.a();
            if (appointmentCancelledResponseData != null) {
                Boolean isAppointmentCancelled = appointmentCancelledResponseData.getIsAppointmentCancelled();
                n.b(isAppointmentCancelled);
                if (isAppointmentCancelled.booleanValue()) {
                    ApplicantDetailsActivity applicantDetailsActivity = this.f9280a;
                    String string = applicantDetailsActivity.getString(R.string.cancel_appointment_success);
                    n.d(string, "getString(...)");
                    applicantDetailsActivity.b2(string);
                    return;
                }
                companion.c();
                ErrorMessage error = appointmentCancelledResponseData.getError();
                n.b(error);
                if (error.getDescription() != null) {
                    Activity c12 = this.f9280a.c1();
                    ErrorMessage error2 = appointmentCancelledResponseData.getError();
                    n.b(error2);
                    companion.w(c12, String.valueOf(error2.getDescription()));
                    return;
                }
                Activity c13 = this.f9280a.c1();
                String string2 = this.f9280a.c1().getString(R.string.generic_error);
                n.d(string2, "getString(...)");
                companion.w(c13, string2);
                return;
            }
            c.h(xVar, this.f9280a.c1(), new b0(this.f9280a));
        }

        public void b(d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            String localizedMessage = th.getLocalizedMessage();
            if (localizedMessage != null) {
                companion.w(this.f9280a.c1(), localizedMessage);
            }
        }
    }

    public ApplicantDetailsActivity() {
        super(false, false, 3, (bc.h) null);
    }

    private final void H1() {
        String str;
        String str2;
        String appointmentCfmLift;
        j jVar = new j();
        Utility.Companion companion = Utility.f9497a;
        String q10 = companion.q();
        String k10 = companion.k(c1());
        jVar.r("missionCode", q10);
        jVar.r("countryCode", k10);
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.appmnt_center_code);
        n.d(string, "getString(...)");
        String str3 = "";
        jVar.r("centerCode", aVar.b(c12, string, str3));
        Activity c13 = c1();
        String string2 = getString(R.string.appmnt_login_loggedInUser);
        n.d(string2, "getString(...)");
        jVar.r("loginUser", aVar.b(c13, string2, str3));
        Activity c14 = c1();
        String string3 = getString(R.string.appointment_URN);
        n.d(string3, "getString(...)");
        jVar.r("urn", aVar.b(c14, string3, str3));
        Activity c15 = c1();
        String string4 = getString(R.string.appmnt_login_authToken);
        n.d(string4, "getString(...)");
        String b10 = aVar.b(c15, string4, str3);
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
        f1().D(hashMap, jVar).A(new a(this));
    }

    private final xa.a J1() {
        return (xa.a) this.f9276f0.getValue();
    }

    private final void M1() {
        try {
            U1();
            W1();
        } catch (Exception e10) {
            e10.toString();
        }
    }

    /* access modifiers changed from: private */
    public static final xa.a N1(ApplicantDetailsActivity applicantDetailsActivity) {
        return xa.a.c(applicantDetailsActivity.getLayoutInflater());
    }

    private final void O1() {
        finish();
    }

    private final void P1() {
        Intent intent = new Intent(this, AppointmentDateAndTimeListActivity.class);
        intent.putExtra(getString(R.string.isToReschedule), true);
        intent.putExtra(getString(R.string.appmnt_booked_data), K1());
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    /* access modifiers changed from: private */
    public static final void Q1(ApplicantDetailsActivity applicantDetailsActivity, View view) {
        applicantDetailsActivity.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final void R1(ApplicantDetailsActivity applicantDetailsActivity, View view) {
        applicantDetailsActivity.Y1();
    }

    /* access modifiers changed from: private */
    public static final void S1(ApplicantDetailsActivity applicantDetailsActivity, View view) {
        applicantDetailsActivity.P1();
    }

    private final void U1() {
        try {
            Activity c12 = c1();
            List<SearchApplicantDetails> applicants = K1().getApplicants();
            n.c(applicants, "null cannot be cast to non-null type java.util.ArrayList<com.vfs.italyglobal.pojo.SearchApplicantDetails>");
            T1(new w(c12, (ArrayList) applicants, true));
            J1().f20273g.setLayoutManager(new LinearLayoutManager(c1()));
            J1().f20273g.t0();
            J1().f20273g.setAdapter(I1());
        } catch (Exception e10) {
            e10.toString();
        }
    }

    private final void W1() {
        try {
            ArrayList arrayList = new ArrayList();
            List<SearchApplicantDetails> applicants = K1().getApplicants();
            if (applicants != null) {
                for (SearchApplicantDetails searchApplicantDetails : applicants) {
                    List<VasDetailsObj> vas = searchApplicantDetails.getVas();
                    if (vas != null) {
                        for (VasDetailsObj vasDetailsObj : vas) {
                            vasDetailsObj.setApplicantName(searchApplicantDetails.getFirstName() + TokenParser.SP + searchApplicantDetails.getLastName());
                            arrayList.add(vasDetailsObj);
                        }
                    }
                }
            }
            if (arrayList.isEmpty()) {
                J1().f20278l.setVisibility(8);
                J1().f20270d.setVisibility(8);
            }
            X1(new b(arrayList));
            J1().f20274h.setLayoutManager(new LinearLayoutManager(c1()));
            J1().f20274h.t0();
            J1().f20274h.setAdapter(L1());
        } catch (Exception e10) {
            e10.toString();
        }
    }

    private final void Y1() {
        o d10 = c.d(this, R.layout.custom_alert_cancel_appmnt_dialog);
        AlertDialog alertDialog = (AlertDialog) d10.c();
        ((TextView) ((View) d10.d()).findViewById(R.id.txt_centerText)).setText(getString(R.string.cancel_alert_appointment));
        ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btn_dialog_no_cancel)).setOnClickListener(new y(alertDialog));
        ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btn_dialog_yes_cancel)).setOnClickListener(new z(alertDialog, this));
        alertDialog.show();
    }

    /* access modifiers changed from: private */
    public static final void Z1(AlertDialog alertDialog, View view) {
        alertDialog.dismiss();
    }

    /* access modifiers changed from: private */
    public static final void a2(AlertDialog alertDialog, ApplicantDetailsActivity applicantDetailsActivity, View view) {
        alertDialog.dismiss();
        applicantDetailsActivity.H1();
        Utility.f9497a.y(applicantDetailsActivity.c1());
    }

    /* access modifiers changed from: private */
    public static final nb.x c2(ApplicantDetailsActivity applicantDetailsActivity) {
        applicantDetailsActivity.O1();
        Utility.f9497a.b(applicantDetailsActivity.c1());
        return nb.x.f15721a;
    }

    public final w I1() {
        w wVar = this.f9278h0;
        if (wVar != null) {
            return wVar;
        }
        n.o("applicantAdapter");
        return null;
    }

    public final SearchAppointmentDetails K1() {
        SearchAppointmentDetails searchAppointmentDetails = this.f9277g0;
        if (searchAppointmentDetails != null) {
            return searchAppointmentDetails;
        }
        n.o("searchApplicantDetails");
        return null;
    }

    public final b L1() {
        b bVar = this.f9279i0;
        if (bVar != null) {
            return bVar;
        }
        n.o("vasServiceAdapter");
        return null;
    }

    public final void T1(w wVar) {
        n.e(wVar, "<set-?>");
        this.f9278h0 = wVar;
    }

    public final void V1(SearchAppointmentDetails searchAppointmentDetails) {
        n.e(searchAppointmentDetails, "<set-?>");
        this.f9277g0 = searchAppointmentDetails;
    }

    public final void X1(b bVar) {
        n.e(bVar, "<set-?>");
        this.f9279i0 = bVar;
    }

    public final void b2(String str) {
        n.e(str, "message");
        c.e(this, str, (String) null, new a0(this), false, (String) null, (ac.a) null, false, 122, (Object) null).show();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Object obj;
        super.onCreate(bundle);
        setContentView((View) J1().b());
        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                obj = extras.get(getString(R.string.booked_appmnt_applcnt_details));
            } else {
                obj = null;
            }
            n.c(obj, "null cannot be cast to non-null type com.vfs.italyglobal.pojo.SearchAppointmentDetails");
            V1((SearchAppointmentDetails) obj);
            J1().f20271e.setOnClickListener(new u(this));
            J1().f20268b.setOnClickListener(new v(this));
            J1().f20269c.setOnClickListener(new ua.w(this));
            M1();
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
