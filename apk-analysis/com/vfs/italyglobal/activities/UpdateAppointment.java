package com.vfs.italyglobal.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import bc.n;
import be.d;
import be.f;
import com.google.gson.j;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.activities.AppointmentLogin;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.pojo.ErrorMessage;
import com.vfs.italyglobal.pojo.SearchAppointmentDataResponse;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.c;
import com.vfs.italyglobal.utilities.g;
import java.util.HashMap;
import nb.h;
import nb.i;
import ua.b5;
import ua.c5;
import ua.d5;
import ua.e5;
import va.x;
import xa.u;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class UpdateAppointment extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9460f0 = i.a(new d5(this));

    /* renamed from: g0  reason: collision with root package name */
    private boolean f9461g0;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ UpdateAppointment f9462a;

        a(UpdateAppointment updateAppointment) {
            this.f9462a = updateAppointment;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(UpdateAppointment updateAppointment, int i10, String str) {
            n.e(str, "errorMsg");
            AppointmentLogin.a.c(AppointmentLogin.f9338h0, updateAppointment.c1(), i10, false, 4, (Object) null);
            return nb.x.f15721a;
        }

        public void a(d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            SearchAppointmentDataResponse searchAppointmentDataResponse = (SearchAppointmentDataResponse) xVar.a();
            if (searchAppointmentDataResponse == null) {
                c.h(xVar, this.f9462a.c1(), new e5(this.f9462a));
            } else if (searchAppointmentDataResponse.getData() != null) {
                this.f9462a.K1(searchAppointmentDataResponse);
            } else if (searchAppointmentDataResponse.getError() != null) {
                Activity c12 = this.f9462a.c1();
                ErrorMessage error = searchAppointmentDataResponse.getError();
                n.b(error);
                companion.w(c12, String.valueOf(error.getDescription()));
            }
        }

        public void b(d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            companion.w(this.f9462a.c1(), th.getLocalizedMessage().toString());
        }
    }

    public UpdateAppointment() {
        super(false, false, 3, (bc.h) null);
    }

    private final void D1() {
        String str;
        String str2;
        String appointmentCfmLift;
        Utility.Companion companion = Utility.f9497a;
        companion.y(c1());
        j jVar = new j();
        String valueOf = String.valueOf(F1().f20678f.getText());
        String valueOf2 = String.valueOf(F1().f20677e.getText());
        String valueOf3 = String.valueOf(F1().f20676d.getText());
        String valueOf4 = String.valueOf(F1().f20675c.getText());
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
        jVar.r("aurn", valueOf);
        jVar.r("passportNumber", valueOf2);
        jVar.r("emailId", valueOf3);
        jVar.r("contactNumber", valueOf4);
        Activity c14 = c1();
        String string3 = getString(R.string.appmnt_login_authToken);
        n.d(string3, "getString(...)");
        String b10 = aVar.b(c14, string3, str3);
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
        f1().H(hashMap, jVar).A(new a(this));
    }

    private final void E1() {
        if (String.valueOf(F1().f20678f.getText()).length() == 0 && String.valueOf(F1().f20677e.getText()).length() == 0 && String.valueOf(F1().f20676d.getText()).length() == 0 && String.valueOf(F1().f20675c.getText()).length() == 0) {
            Utility.Companion companion = Utility.f9497a;
            Activity c12 = c1();
            String string = getString(R.string.reschdule_appmnt_validation);
            n.d(string, "getString(...)");
            companion.w(c12, string);
            return;
        }
        D1();
    }

    private final u F1() {
        return (u) this.f9460f0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void H1(UpdateAppointment updateAppointment, View view) {
        updateAppointment.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final void I1(UpdateAppointment updateAppointment, View view) {
        if (c.j(updateAppointment)) {
            updateAppointment.E1();
            return;
        }
        Utility.Companion companion = Utility.f9497a;
        Activity c12 = updateAppointment.c1();
        String string = updateAppointment.getString(R.string.network_error);
        n.d(string, "getString(...)");
        companion.w(c12, string);
    }

    /* access modifiers changed from: private */
    public static final u J1(UpdateAppointment updateAppointment) {
        return u.c(updateAppointment.getLayoutInflater());
    }

    public final void G1() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Object obj = extras.get(getString(R.string.isToReschedule));
            n.c(obj, "null cannot be cast to non-null type kotlin.Boolean");
            this.f9461g0 = ((Boolean) obj).booleanValue();
        }
        if (!this.f9461g0) {
            F1().f20679g.setImageResource(R.drawable.cancle_appointment_vector);
            F1().f20681i.setText(R.string.cancel_appointment);
        }
        F1().f20680h.setOnClickListener(new b5(this));
        F1().f20674b.setOnClickListener(new c5(this));
    }

    public final void K1(SearchAppointmentDataResponse searchAppointmentDataResponse) {
        n.e(searchAppointmentDataResponse, "searchAppmntResponse");
        Intent intent = new Intent(this, BookedAppointmentListActivity.class);
        intent.putExtra(getString(R.string.search_appmnt_list), searchAppointmentDataResponse);
        intent.putExtra(getString(R.string.isToReschedule), this.f9461g0);
        startActivity(intent);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) F1().b());
        G1();
    }
}
