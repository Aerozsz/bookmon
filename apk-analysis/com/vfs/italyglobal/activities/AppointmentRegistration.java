package com.vfs.italyglobal.activities;

import ac.p;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.q;
import bc.n;
import be.d;
import be.f;
import com.google.gson.j;
import com.hbb20.CountryCodePicker;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.pojo.ErrorMessage;
import com.vfs.italyglobal.pojo.GenerateUserOtpResponse;
import com.vfs.italyglobal.pojo.RegistrationMainResponse;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.g;
import com.vfs.italyglobal.utilities.n;
import java.util.HashMap;
import nb.h;
import nb.i;
import org.apache.http.HttpHeaders;
import ua.k1;
import ua.l1;
import ua.m1;
import ua.n1;
import va.x;
import xa.v;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class AppointmentRegistration extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9347f0 = i.a(new m1(this));

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentRegistration f9348a;

        a(AppointmentRegistration appointmentRegistration) {
            this.f9348a = appointmentRegistration;
        }

        public void a(d dVar, be.x xVar) {
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            n.b(xVar);
            RegistrationMainResponse registrationMainResponse = (RegistrationMainResponse) xVar.a();
            if (registrationMainResponse == null) {
                com.vfs.italyglobal.utilities.c.i(xVar, this.f9348a.c1(), (p) null, 2, (Object) null);
            } else if (registrationMainResponse.getCode() == null) {
                Activity c12 = this.f9348a.c1();
                ErrorMessage error = registrationMainResponse.getError();
                n.b(error);
                companion.w(c12, String.valueOf(error.getDescription()));
            } else if (!kc.n.z(registrationMainResponse.getCode(), "200", false, 2, (Object) null)) {
                Activity c13 = this.f9348a.c1();
                String string = this.f9348a.getString(R.string.generic_error);
                n.d(string, "getString(...)");
                companion.w(c13, string);
            } else if (n.a(String.valueOf(registrationMainResponse.getMessage()), "")) {
                AppointmentRegistration appointmentRegistration = this.f9348a;
                String string2 = appointmentRegistration.getString(R.string.registration_successfully);
                n.d(string2, "getString(...)");
                appointmentRegistration.Q1(string2);
            } else {
                this.f9348a.Q1(String.valueOf(registrationMainResponse.getMessage()));
            }
        }

        public void b(d dVar, Throwable th) {
            Utility.Companion companion = Utility.f9497a;
            Activity c12 = this.f9348a.c1();
            n.b(th);
            String localizedMessage = th.getLocalizedMessage();
            n.d(localizedMessage, "getLocalizedMessage(...)");
            companion.w(c12, localizedMessage);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class b implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentRegistration f9349a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ j f9350b;

        b(AppointmentRegistration appointmentRegistration, j jVar) {
            this.f9349a = appointmentRegistration;
            this.f9350b = jVar;
        }

        public void a(d dVar, be.x xVar) {
            Utility.f9497a.c();
            n.b(xVar);
            GenerateUserOtpResponse generateUserOtpResponse = (GenerateUserOtpResponse) xVar.a();
            if (generateUserOtpResponse == null) {
                com.vfs.italyglobal.utilities.c.i(xVar, this.f9349a.c1(), (p) null, 2, (Object) null);
            } else if (n.a(generateUserOtpResponse.isOTPSend(), Boolean.TRUE)) {
                this.f9349a.M1(this.f9350b);
            }
        }

        public void b(d dVar, Throwable th) {
            Utility.Companion companion = Utility.f9497a;
            Activity c12 = this.f9349a.c1();
            n.b(th);
            String localizedMessage = th.getLocalizedMessage();
            n.d(localizedMessage, "getLocalizedMessage(...)");
            companion.w(c12, localizedMessage);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class c extends ClickableSpan {

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ AppointmentRegistration f9351b;

        c(AppointmentRegistration appointmentRegistration) {
            this.f9351b = appointmentRegistration;
        }

        public void onClick(View view) {
            n.e(view, "textView");
            this.f9351b.startActivity(new Intent(this.f9351b.c1(), PrivacyPolicyActivity.class));
        }

        public void updateDrawState(TextPaint textPaint) {
            n.e(textPaint, "ds");
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
        }
    }

    public AppointmentRegistration() {
        super(false, false, 3, (bc.h) null);
    }

    private final void F1(j jVar, HashMap hashMap) {
        f1().y(hashMap, jVar).A(new a(this));
    }

    private final void G1() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String appointmentUrlAuthority;
        String obj;
        String obj2;
        String obj3;
        String obj4 = kc.n.N0(String.valueOf(J1().f20691g.getText())).toString();
        Editable text = J1().f20692h.getText();
        String str8 = "";
        if (text == null || (obj3 = text.toString()) == null || (str = kc.n.N0(obj3).toString()) == null) {
            str = str8;
        }
        Editable text2 = J1().f20693i.getText();
        if (text2 == null || (obj2 = text2.toString()) == null || (str2 = kc.n.N0(obj2).toString()) == null) {
            str2 = str8;
        }
        String obj5 = kc.n.N0(String.valueOf(J1().f20688d.getSelectedCountryCodeAsInt())).toString();
        Editable text3 = J1().f20690f.getText();
        if (text3 == null || (obj = text3.toString()) == null || (str3 = kc.n.N0(obj).toString()) == null) {
            str3 = str8;
        }
        String valueOf = String.valueOf(J1().f20694j.getText());
        Utility.Companion companion = Utility.f9497a;
        String e10 = companion.e(c1(), valueOf);
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.enableOtpLoginRegistration);
        n.d(string, "getString(...)");
        boolean c10 = aVar.c(c12, string, false);
        j jVar = new j();
        String q10 = companion.q();
        String k10 = companion.k(c1());
        jVar.r("missioncode", q10);
        jVar.r("countrycode", k10);
        jVar.r("emailid", obj4);
        jVar.r("password", e10);
        jVar.r("confirmPassword", e10);
        jVar.r("firstName", str);
        jVar.r("lastName", str2);
        jVar.r("dialCode", obj5);
        jVar.r("contact", str3);
        jVar.o("instructionAgreed", Boolean.TRUE);
        jVar.r("languageCode", "en");
        jVar.r("cultureCode", "en-US");
        String e11 = companion.e(this, companion.l());
        HashMap hashMap = new HashMap();
        AppConfigModel e12 = d1().e();
        if (e12 == null || (str4 = e12.getAppointmentUrlOrigin()) == null) {
            str4 = str8;
        }
        hashMap.put("referer", str4);
        AppConfigModel e13 = d1().e();
        if (e13 == null || (str5 = e13.getAppointmentUrlOrigin()) == null) {
            str5 = str8;
        }
        hashMap.put(HttpHeaders.REFERER, str5);
        AppConfigModel e14 = d1().e();
        if (e14 == null || (str6 = e14.getAppointmentUrlOrigin()) == null) {
            str6 = str8;
        }
        hashMap.put("origin", str6);
        AppConfigModel e15 = d1().e();
        if (e15 == null || (str7 = e15.getAppointmentCfmLift()) == null) {
            str7 = str8;
        }
        hashMap.put("cfmlift", str7);
        AppConfigModel e16 = d1().e();
        if (!(e16 == null || (appointmentUrlAuthority = e16.getAppointmentUrlAuthority()) == null)) {
            str8 = appointmentUrlAuthority;
        }
        hashMap.put("authority", str8);
        hashMap.put("ClientSource", e11);
        if (c10) {
            H1(P1(q10, k10, obj5, str3), hashMap, jVar);
        } else {
            F1(jVar, hashMap);
        }
    }

    private final void H1(j jVar, HashMap hashMap, j jVar2) {
        f1().F(hashMap, jVar).A(new b(this, jVar2));
    }

    private final void I1() {
        Editable text = J1().f20692h.getText();
        n.b(text);
        if (text.length() == 0) {
            Utility.Companion companion = Utility.f9497a;
            String string = getString(R.string.add_applicant_first_name_mandatory);
            n.d(string, "getString(...)");
            companion.w(this, string);
            return;
        }
        Editable text2 = J1().f20693i.getText();
        n.b(text2);
        if (text2.length() == 0) {
            Utility.Companion companion2 = Utility.f9497a;
            String string2 = getString(R.string.add_applicant_last_name_mandatory);
            n.d(string2, "getString(...)");
            companion2.w(this, string2);
            return;
        }
        Editable text3 = J1().f20691g.getText();
        n.b(text3);
        if (text3.length() == 0) {
            Utility.Companion companion3 = Utility.f9497a;
            String string3 = getString(R.string.appmnt_email_required);
            n.d(string3, "getString(...)");
            companion3.w(this, string3);
            return;
        }
        Editable text4 = J1().f20691g.getText();
        n.b(text4);
        if (text4.length() > 0) {
            Utility.Companion companion4 = Utility.f9497a;
            if (!companion4.s(String.valueOf(J1().f20691g.getText()))) {
                String string4 = getString(R.string.add_applicant_email_valid);
                n.d(string4, "getString(...)");
                companion4.w(this, string4);
                return;
            }
        }
        Editable text5 = J1().f20690f.getText();
        n.b(text5);
        if (text5.length() == 0) {
            Utility.Companion companion5 = Utility.f9497a;
            String string5 = getString(R.string.add_applicant_contact);
            n.d(string5, "getString(...)");
            companion5.w(this, string5);
            return;
        }
        Editable text6 = J1().f20690f.getText();
        n.b(text6);
        if (text6.length() < 7) {
            Utility.Companion companion6 = Utility.f9497a;
            String string6 = getString(R.string.add_applicant_valid_contact);
            n.d(string6, "getString(...)");
            companion6.w(this, string6);
            return;
        }
        Editable text7 = J1().f20694j.getText();
        n.b(text7);
        if (text7.length() == 0) {
            Utility.Companion companion7 = Utility.f9497a;
            String string7 = getString(R.string.appmnt_password_required);
            n.d(string7, "getString(...)");
            companion7.w(this, string7);
            return;
        }
        Editable text8 = J1().f20694j.getText();
        n.b(text8);
        if (text8.length() > 0) {
            Utility.Companion companion8 = Utility.f9497a;
            if (!companion8.t(String.valueOf(J1().f20694j.getText()))) {
                String string8 = getString(R.string.appmnt_password_valid);
                n.d(string8, "getString(...)");
                companion8.w(this, string8);
                return;
            }
        }
        Editable text9 = J1().f20689e.getText();
        n.b(text9);
        if (text9.length() == 0) {
            Utility.Companion companion9 = Utility.f9497a;
            String string9 = getString(R.string.appmnt_confirm_password_required);
            n.d(string9, "getString(...)");
            companion9.w(this, string9);
        } else if (!kc.n.y(String.valueOf(J1().f20694j.getText()), String.valueOf(J1().f20689e.getText()), false)) {
            Utility.Companion companion10 = Utility.f9497a;
            String string10 = getString(R.string.appmnt_password_not_matching);
            n.d(string10, "getString(...)");
            companion10.w(this, string10);
        } else if (!J1().f20687c.isChecked()) {
            Utility.Companion companion11 = Utility.f9497a;
            Activity c12 = c1();
            String string11 = getString(R.string.msg_accept_privacy_policy);
            n.d(string11, "getString(...)");
            companion11.w(c12, string11);
        } else {
            Utility.f9497a.y(c1());
            G1();
        }
    }

    private final v J1() {
        return (v) this.f9347f0.getValue();
    }

    /* access modifiers changed from: private */
    public static final v L1(AppointmentRegistration appointmentRegistration) {
        return v.c(appointmentRegistration.getLayoutInflater());
    }

    /* access modifiers changed from: private */
    public final void M1(j jVar) {
        Intent intent = new Intent(this, OtpVerification.class);
        intent.putExtra(getString(R.string.bundle_registration_request_data), jVar.toString());
        intent.putExtra(getString(R.string.bundle_isFromRegister), true);
        intent.putExtra(getString(R.string.bundle_isFromApplicant), false);
        startActivity(intent);
    }

    /* access modifiers changed from: private */
    public static final void N1(AppointmentRegistration appointmentRegistration, View view) {
        appointmentRegistration.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final void O1(AppointmentRegistration appointmentRegistration, View view) {
        if (com.vfs.italyglobal.utilities.c.j(appointmentRegistration)) {
            appointmentRegistration.I1();
            return;
        }
        Utility.Companion companion = Utility.f9497a;
        String string = appointmentRegistration.getString(R.string.network_error);
        n.d(string, "getString(...)");
        companion.w(appointmentRegistration, string);
    }

    private final j P1(String str, String str2, String str3, String str4) {
        j jVar = new j();
        jVar.r("missioncode", str);
        jVar.r("countrycode", str2);
        jVar.r("dialCode", str3);
        jVar.r("contactNo", str4);
        jVar.r("action", "Register");
        return jVar;
    }

    /* access modifiers changed from: private */
    public static final nb.x R1(AppointmentRegistration appointmentRegistration) {
        appointmentRegistration.onBackPressed();
        return nb.x.f15721a;
    }

    public final void K1() {
        c cVar = new c(this);
        CountryCodePicker countryCodePicker = J1().f20688d;
        q Y = Y();
        n.d(Y, "getSupportFragmentManager(...)");
        countryCodePicker.setFragmentManager(Y);
        Utility.Companion companion = Utility.f9497a;
        if (n.a(companion.k(c1()), "DZA")) {
            n.a aVar = com.vfs.italyglobal.utilities.n.f9525e;
            AppCompatCheckBox appCompatCheckBox = J1().f20687c;
            bc.n.d(appCompatCheckBox, "cbTermsAndCondition");
            String string = getString(R.string.lbl_terms_condition_registration_description_algeria);
            bc.n.d(string, "getString(...)");
            com.vfs.italyglobal.utilities.n a10 = aVar.a(appCompatCheckBox, string);
            String string2 = getString(R.string.privacy_policy);
            bc.n.d(string2, "getString(...)");
            a10.b(cVar, string2);
            String string3 = getString(R.string.privacy_policy);
            bc.n.d(string3, "getString(...)");
            a10.c(R.color.colorOrange, string3);
            String string4 = getString(R.string.privacy_policy);
            bc.n.d(string4, "getString(...)");
            a10.d(string4);
            a10.a();
            J1().f20688d.setCountryForNameCode("DZ");
        } else if (bc.n.a(companion.k(c1()), "GBR")) {
            n.a aVar2 = com.vfs.italyglobal.utilities.n.f9525e;
            AppCompatCheckBox appCompatCheckBox2 = J1().f20687c;
            bc.n.d(appCompatCheckBox2, "cbTermsAndCondition");
            String string5 = getString(R.string.lbl_terms_condition_registration_description_uk);
            bc.n.d(string5, "getString(...)");
            com.vfs.italyglobal.utilities.n a11 = aVar2.a(appCompatCheckBox2, string5);
            String string6 = getString(R.string.privacy_policy);
            bc.n.d(string6, "getString(...)");
            a11.b(cVar, string6);
            String string7 = getString(R.string.privacy_policy);
            bc.n.d(string7, "getString(...)");
            a11.c(R.color.colorOrange, string7);
            String string8 = getString(R.string.privacy_policy);
            bc.n.d(string8, "getString(...)");
            a11.d(string8);
            a11.a();
            J1().f20688d.setCountryForNameCode("GB");
        } else if (bc.n.a(companion.k(c1()), "GHA")) {
            n.a aVar3 = com.vfs.italyglobal.utilities.n.f9525e;
            AppCompatCheckBox appCompatCheckBox3 = J1().f20687c;
            bc.n.d(appCompatCheckBox3, "cbTermsAndCondition");
            String string9 = getString(R.string.lbl_terms_condition_registration_description);
            bc.n.d(string9, "getString(...)");
            com.vfs.italyglobal.utilities.n a12 = aVar3.a(appCompatCheckBox3, string9);
            String string10 = getString(R.string.privacy_policy);
            bc.n.d(string10, "getString(...)");
            a12.b(cVar, string10);
            String string11 = getString(R.string.privacy_policy);
            bc.n.d(string11, "getString(...)");
            a12.c(R.color.colorOrange, string11);
            String string12 = getString(R.string.privacy_policy);
            bc.n.d(string12, "getString(...)");
            a12.d(string12);
            a12.a();
            J1().f20688d.setCountryForNameCode("gh");
        } else {
            n.a aVar4 = com.vfs.italyglobal.utilities.n.f9525e;
            AppCompatCheckBox appCompatCheckBox4 = J1().f20687c;
            bc.n.d(appCompatCheckBox4, "cbTermsAndCondition");
            String string13 = getString(R.string.lbl_terms_condition_registration_description);
            bc.n.d(string13, "getString(...)");
            com.vfs.italyglobal.utilities.n a13 = aVar4.a(appCompatCheckBox4, string13);
            String string14 = getString(R.string.privacy_policy);
            bc.n.d(string14, "getString(...)");
            a13.b(cVar, string14);
            String string15 = getString(R.string.privacy_policy);
            bc.n.d(string15, "getString(...)");
            a13.c(R.color.colorOrange, string15);
            String string16 = getString(R.string.privacy_policy);
            bc.n.d(string16, "getString(...)");
            a13.d(string16);
            a13.a();
        }
    }

    public final void Q1(String str) {
        bc.n.e(str, "message");
        com.vfs.italyglobal.utilities.c.e(this, str, (String) null, new n1(this), false, (String) null, (ac.a) null, false, 122, (Object) null).show();
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) J1().b());
        K1();
        J1().f20695k.setOnClickListener(new k1(this));
        J1().f20686b.setOnClickListener(new l1(this));
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Utility.Companion companion = Utility.f9497a;
        companion.c();
        companion.d();
    }
}
