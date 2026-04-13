package com.vfs.italyglobal.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import bc.n;
import be.d;
import be.f;
import com.google.gson.j;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.activities.AppointmentLogin;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.pojo.AppointmentCancelledResponseData;
import com.vfs.italyglobal.pojo.AppointmentDetails;
import com.vfs.italyglobal.pojo.SearchApplicantDetails;
import com.vfs.italyglobal.pojo.SearchAppointmentDetails;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.c;
import com.vfs.italyglobal.utilities.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nb.h;
import nb.i;
import nb.o;
import ua.o1;
import ua.p1;
import ua.q1;
import ua.r1;
import ua.s1;
import ua.t1;
import ua.u1;
import va.x;
import wa.v;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class BookedAppointmentDetails extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9352f0 = i.a(new q1(this));

    /* renamed from: g0  reason: collision with root package name */
    private ArrayList f9353g0 = new ArrayList();

    /* renamed from: h0  reason: collision with root package name */
    private boolean f9354h0;

    /* renamed from: i0  reason: collision with root package name */
    private SearchAppointmentDetails f9355i0 = new SearchAppointmentDetails();

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ BookedAppointmentDetails f9356a;

        a(BookedAppointmentDetails bookedAppointmentDetails) {
            this.f9356a = bookedAppointmentDetails;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(BookedAppointmentDetails bookedAppointmentDetails, int i10, String str) {
            n.e(str, "<unused var>");
            AppointmentLogin.a.c(AppointmentLogin.f9338h0, bookedAppointmentDetails.c1(), i10, false, 4, (Object) null);
            return nb.x.f15721a;
        }

        public void a(d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            AppointmentCancelledResponseData appointmentCancelledResponseData = (AppointmentCancelledResponseData) xVar.a();
            if (appointmentCancelledResponseData != null) {
                Boolean isAppointmentCancelled = appointmentCancelledResponseData.getIsAppointmentCancelled();
                n.b(isAppointmentCancelled);
                if (isAppointmentCancelled.booleanValue()) {
                    BookedAppointmentDetails bookedAppointmentDetails = this.f9356a;
                    String string = bookedAppointmentDetails.getString(R.string.cancel_appointment_success);
                    n.d(string, "getString(...)");
                    bookedAppointmentDetails.P1(string);
                    return;
                }
                return;
            }
            c.h(xVar, this.f9356a.c1(), new u1(this.f9356a));
        }

        public void b(d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            String localizedMessage = th.getLocalizedMessage();
            if (localizedMessage != null) {
                companion.w(this.f9356a.c1(), localizedMessage);
            }
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class b implements za.d {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ BookedAppointmentDetails f9357a;

        b(BookedAppointmentDetails bookedAppointmentDetails) {
            this.f9357a = bookedAppointmentDetails;
        }

        public void a(SearchApplicantDetails searchApplicantDetails, boolean z10) {
            n.e(searchApplicantDetails, "applicantList");
            Toast.makeText(this.f9357a.c1(), "You cannot edit the applicant", 1).show();
        }
    }

    public BookedAppointmentDetails() {
        super(false, false, 3, (bc.h) null);
    }

    private final void G1() {
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

    private final xa.f H1() {
        return (xa.f) this.f9352f0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void J1(BookedAppointmentDetails bookedAppointmentDetails, View view) {
        bookedAppointmentDetails.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final void K1(BookedAppointmentDetails bookedAppointmentDetails, View view) {
        if (bookedAppointmentDetails.f9354h0) {
            bookedAppointmentDetails.N1();
            return;
        }
        String string = bookedAppointmentDetails.getString(R.string.cancel_alert_appointment);
        n.d(string, "getString(...)");
        bookedAppointmentDetails.R1(string);
    }

    /* access modifiers changed from: private */
    public static final xa.f L1(BookedAppointmentDetails bookedAppointmentDetails) {
        return xa.f.c(bookedAppointmentDetails.getLayoutInflater());
    }

    private final void M1() {
        Intent intent = new Intent(this, ManageAppointmentActivity.class);
        intent.setFlags(67108864);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        finish();
    }

    private final void N1() {
        Intent intent = new Intent(this, AppointmentGetEarliestDates.class);
        intent.putExtra(getString(R.string.appmnt_booked_data), this.f9355i0);
        startActivity(intent);
    }

    private final void O1(SearchAppointmentDetails searchAppointmentDetails) {
        H1().f20385l.setText(searchAppointmentDetails.getUrn());
        H1().f20382i.setText(searchAppointmentDetails.getMissionCode());
        List<SearchApplicantDetails> applicants = searchAppointmentDetails.getApplicants();
        n.b(applicants);
        AppointmentDetails appointment = applicants.get(0).getAppointment();
        n.b(appointment);
        H1().f20381h.setText(Utility.f9497a.u(String.valueOf(appointment.getAppoinmentDate())));
        AppCompatTextView appCompatTextView = H1().f20384k;
        List<SearchApplicantDetails> applicants2 = searchAppointmentDetails.getApplicants();
        n.b(applicants2);
        AppointmentDetails appointment2 = applicants2.get(0).getAppointment();
        n.b(appointment2);
        appCompatTextView.setText(appointment2.getAppointmentTime());
        AppCompatTextView appCompatTextView2 = H1().f20383j;
        List<SearchApplicantDetails> applicants3 = searchAppointmentDetails.getApplicants();
        n.b(applicants3);
        appCompatTextView2.setText(String.valueOf(applicants3.size()));
        H1().f20386m.setText(searchAppointmentDetails.getVisaCategoryCode());
        List<SearchApplicantDetails> applicants4 = searchAppointmentDetails.getApplicants();
        n.b(applicants4);
        int size = applicants4.size();
        for (int i10 = 0; i10 < size; i10++) {
            ArrayList arrayList = this.f9353g0;
            List<SearchApplicantDetails> applicants5 = searchAppointmentDetails.getApplicants();
            n.b(applicants5);
            arrayList.add(applicants5.get(i10));
        }
        U1(this.f9353g0);
    }

    /* access modifiers changed from: private */
    public static final nb.x Q1(BookedAppointmentDetails bookedAppointmentDetails) {
        bookedAppointmentDetails.M1();
        Utility.f9497a.b(bookedAppointmentDetails.c1());
        return nb.x.f15721a;
    }

    private final void R1(String str) {
        o d10 = c.d(this, R.layout.custom_alert_cancel_appmnt_dialog);
        AlertDialog alertDialog = (AlertDialog) d10.c();
        ((TextView) ((View) d10.d()).findViewById(R.id.txt_centerText)).setText(str);
        ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btn_dialog_no_cancel)).setOnClickListener(new r1(alertDialog));
        ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btn_dialog_yes_cancel)).setOnClickListener(new s1(this, alertDialog));
        alertDialog.show();
    }

    /* access modifiers changed from: private */
    public static final void S1(AlertDialog alertDialog, View view) {
        alertDialog.dismiss();
    }

    /* access modifiers changed from: private */
    public static final void T1(BookedAppointmentDetails bookedAppointmentDetails, AlertDialog alertDialog, View view) {
        bookedAppointmentDetails.G1();
        Utility.f9497a.y(bookedAppointmentDetails.c1());
        alertDialog.dismiss();
    }

    private final void U1(ArrayList arrayList) {
        v vVar = new v(arrayList, false, new b(this));
        H1().f20379f.setLayoutManager(new LinearLayoutManager(this));
        H1().f20379f.t0();
        H1().f20379f.setAdapter(vVar);
        if (!arrayList.isEmpty()) {
            H1().f20378e.setVisibility(8);
            H1().f20379f.setVisibility(0);
            return;
        }
        H1().f20378e.setVisibility(0);
        H1().f20379f.setVisibility(8);
    }

    public final void I1() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Object obj = extras.get(getString(R.string.booked_appmnt_applcnt_details));
            n.c(obj, "null cannot be cast to non-null type com.vfs.italyglobal.pojo.SearchAppointmentDetails");
            this.f9355i0 = (SearchAppointmentDetails) obj;
            g.a aVar = g.f9512a;
            Activity c12 = c1();
            String string = getString(R.string.appointment_URN);
            n.d(string, "getString(...)");
            aVar.g(c12, string, String.valueOf(this.f9355i0.getUrn()));
            O1(this.f9355i0);
            Object obj2 = extras.get(getString(R.string.isToReschedule));
            n.c(obj2, "null cannot be cast to non-null type kotlin.Boolean");
            this.f9354h0 = ((Boolean) obj2).booleanValue();
        }
        if (!this.f9354h0) {
            H1().f20376c.setText(getString(R.string.cancel_appointment));
            H1().f20380g.setText(getString(R.string.cancel_appointment));
        }
        H1().f20377d.setOnClickListener(new o1(this));
        H1().f20376c.setOnClickListener(new p1(this));
    }

    public final void P1(String str) {
        n.e(str, "message");
        c.e(this, str, (String) null, new t1(this), false, (String) null, (ac.a) null, false, 122, (Object) null).show();
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) H1().b());
        I1();
    }
}
