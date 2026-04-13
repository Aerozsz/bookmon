package com.vfs.italyglobal.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bc.n;
import be.d;
import be.f;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.pojo.MasterIdValue;
import com.vfs.italyglobal.pojo.MasterResponseData;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.c;
import com.vfs.italyglobal.utilities.g;
import java.util.ArrayList;
import java.util.List;
import nb.h;
import nb.i;
import nb.o;
import ua.a6;
import ua.b6;
import ua.c6;
import ua.d6;
import ua.e6;
import ua.f6;
import ua.x5;
import ua.y5;
import ua.z5;
import va.x;
import wa.z;
import xa.p1;
import za.e;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class VisaInformationActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9480f0 = i.a(new e6(this));

    /* renamed from: g0  reason: collision with root package name */
    private int f9481g0;

    /* renamed from: h0  reason: collision with root package name */
    private ArrayList f9482h0 = new ArrayList();

    /* renamed from: i0  reason: collision with root package name */
    private ArrayList f9483i0 = new ArrayList();

    /* renamed from: j0  reason: collision with root package name */
    private ArrayList f9484j0 = new ArrayList();

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ VisaInformationActivity f9485a;

        a(VisaInformationActivity visaInformationActivity) {
            this.f9485a = visaInformationActivity;
        }

        public void a(d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            MasterResponseData masterResponseData = (MasterResponseData) xVar.a();
            if (masterResponseData != null) {
                List<MasterIdValue> extraData = masterResponseData.getExtraData();
                if (extraData != null && !extraData.isEmpty()) {
                    this.f9485a.O1().clear();
                    ArrayList O1 = this.f9485a.O1();
                    List<MasterIdValue> extraData2 = masterResponseData.getExtraData();
                    n.b(extraData2);
                    O1.addAll(extraData2);
                    List<MasterIdValue> extraData3 = masterResponseData.getExtraData();
                    n.b(extraData3);
                    int size = extraData3.size();
                    for (int i10 = 0; i10 < size; i10++) {
                        ArrayList N1 = this.f9485a.N1();
                        List<MasterIdValue> extraData4 = masterResponseData.getExtraData();
                        n.b(extraData4);
                        String value = extraData4.get(i10).getValue();
                        n.b(value);
                        N1.add(value);
                        ArrayList M1 = this.f9485a.M1();
                        List<MasterIdValue> extraData5 = masterResponseData.getExtraData();
                        n.b(extraData5);
                        M1.add(String.valueOf(extraData5.get(i10).getId()));
                    }
                    this.f9485a.e2();
                } else if (masterResponseData.getMessage() != null && String.valueOf(masterResponseData.getMessage()).length() > 0) {
                    companion.w(this.f9485a.c1(), String.valueOf(masterResponseData.getMessage()));
                }
            }
        }

        public void b(d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            Activity c12 = this.f9485a.c1();
            String localizedMessage = th.getLocalizedMessage();
            n.b(localizedMessage);
            companion.w(c12, localizedMessage);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class b implements e {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ VisaInformationActivity f9486a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ o f9487b;

        b(VisaInformationActivity visaInformationActivity, o oVar) {
            this.f9486a = visaInformationActivity;
            this.f9487b = oVar;
        }

        public void a(MasterIdValue masterIdValue) {
            n.e(masterIdValue, "embassy");
            g.a aVar = g.f9512a;
            Activity c12 = this.f9486a.c1();
            String string = this.f9486a.getString(R.string.key_embassy_location);
            n.d(string, "getString(...)");
            aVar.g(c12, string, String.valueOf(masterIdValue.getValue()));
            Activity c13 = this.f9486a.c1();
            String string2 = this.f9486a.getString(R.string.embassy_location_Id);
            n.d(string2, "getString(...)");
            aVar.g(c13, string2, String.valueOf(masterIdValue.getId()));
            ((AlertDialog) this.f9487b.c()).dismiss();
            Activity c14 = this.f9486a.c1();
            String string3 = this.f9486a.getString(R.string.key_embassy_location);
            n.d(string3, "getString(...)");
            String b10 = aVar.b(c14, string3, "");
            VisaInformationActivity visaInformationActivity = this.f9486a;
            AppCompatTextView appCompatTextView = visaInformationActivity.P1().f20601e;
            if (kc.n.e0(b10)) {
                b10 = visaInformationActivity.getString(R.string.txt_select_city);
                n.d(b10, "getString(...)");
            }
            appCompatTextView.setText(b10);
        }
    }

    public VisaInformationActivity() {
        super(false, false, 3, (bc.h) null);
    }

    private final void K1() {
        g1().w(this.f9481g0).A(new a(this));
    }

    private final void L1() {
        if (!c.j(this)) {
            Utility.Companion companion = Utility.f9497a;
            String string = getString(R.string.network_error);
            n.d(string, "getString(...)");
            companion.w(this, string);
        } else if (this.f9482h0.isEmpty()) {
            Utility.f9497a.y(this);
            K1();
        } else {
            e2();
        }
    }

    /* access modifiers changed from: private */
    public final p1 P1() {
        return (p1) this.f9480f0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void R1(VisaInformationActivity visaInformationActivity, View view) {
        if (visaInformationActivity.Y1()) {
            visaInformationActivity.b2();
        } else {
            visaInformationActivity.P1().f20599c.performClick();
        }
    }

    /* access modifiers changed from: private */
    public static final void S1(VisaInformationActivity visaInformationActivity, View view) {
        if (visaInformationActivity.Y1()) {
            visaInformationActivity.c2();
        } else {
            visaInformationActivity.P1().f20599c.performClick();
        }
    }

    /* access modifiers changed from: private */
    public static final void T1(VisaInformationActivity visaInformationActivity, View view) {
        if (visaInformationActivity.Y1()) {
            visaInformationActivity.d2();
        } else {
            visaInformationActivity.P1().f20599c.performClick();
        }
    }

    /* access modifiers changed from: private */
    public static final void U1(VisaInformationActivity visaInformationActivity, View view) {
        if (visaInformationActivity.Y1()) {
            visaInformationActivity.a2(false);
        } else {
            visaInformationActivity.P1().f20599c.performClick();
        }
    }

    /* access modifiers changed from: private */
    public static final void V1(VisaInformationActivity visaInformationActivity, View view) {
        if (visaInformationActivity.Y1()) {
            visaInformationActivity.a2(true);
        } else {
            visaInformationActivity.P1().f20599c.performClick();
        }
    }

    /* access modifiers changed from: private */
    public static final void W1(VisaInformationActivity visaInformationActivity, View view) {
        visaInformationActivity.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final void X1(VisaInformationActivity visaInformationActivity, View view) {
        visaInformationActivity.L1();
    }

    private final boolean Y1() {
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.key_embassy_location);
        n.d(string, "getString(...)");
        if (g.a.d(aVar, c12, string, (String) null, 4, (Object) null).length() <= 0) {
            return false;
        }
        Activity c13 = c1();
        String string2 = getString(R.string.embassy_location_Id);
        n.d(string2, "getString(...)");
        if (g.a.d(aVar, c13, string2, (String) null, 4, (Object) null).length() > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static final p1 Z1(VisaInformationActivity visaInformationActivity) {
        return p1.c(visaInformationActivity.getLayoutInflater());
    }

    private final void a2(boolean z10) {
        Intent intent = new Intent(this, HowToApplyActivity.class);
        intent.putExtra(getString(R.string.isForAfterSubmission), z10);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    private final void b2() {
        startActivity(new Intent(this, KnowYourVisaType.class));
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    private final void c2() {
        startActivity(new Intent(this, VisaAppCenterActivity.class));
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    private final void d2() {
        startActivity(new Intent(this, VisaImportantActivity.class));
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    /* access modifiers changed from: private */
    public static final void f2(o oVar, View view) {
        ((AlertDialog) oVar.c()).dismiss();
    }

    public final ArrayList M1() {
        return this.f9484j0;
    }

    public final ArrayList N1() {
        return this.f9483i0;
    }

    public final ArrayList O1() {
        return this.f9482h0;
    }

    public final void Q1() {
        P1().f20607k.setVisibility(8);
        P1().f20609m.setVisibility(8);
        if (n.a(Utility.f9497a.k(c1()), "CHL")) {
            P1().f20604h.setText(getString(R.string.txt_know_citizenship_details));
            P1().f20602f.setText(getString(R.string.txt_citizenship_information));
            P1().f20606j.setVisibility(8);
        }
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.key_embassy_location);
        n.d(string, "getString(...)");
        String b10 = aVar.b(c12, string, "");
        AppCompatTextView appCompatTextView = P1().f20601e;
        if (kc.n.e0(b10)) {
            b10 = getString(R.string.txt_select_city);
            n.d(b10, "getString(...)");
        }
        appCompatTextView.setText(b10);
        Activity c13 = c1();
        String string2 = getString(R.string.selectedCountryId);
        n.d(string2, "getString(...)");
        this.f9481g0 = aVar.a(c13, string2, 0);
        P1().f20610n.setOnClickListener(new x5(this));
        P1().f20607k.setOnClickListener(new y5(this));
        P1().f20609m.setOnClickListener(new z5(this));
        P1().f20608l.setOnClickListener(new a6(this));
        P1().f20606j.setOnClickListener(new b6(this));
        P1().f20598b.setOnClickListener(new c6(this));
        P1().f20599c.setOnClickListener(new d6(this));
    }

    public final void e2() {
        o d10 = c.d(this, R.layout.city_selection_dialog);
        z zVar = new z(this.f9481g0, this.f9482h0, new b(this, d10));
        RecyclerView recyclerView = (RecyclerView) ((View) d10.d()).findViewById(R.id.rcyl_citySelection);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.t0();
        recyclerView.setAdapter(zVar);
        ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btn_cancel_Citydialog)).setOnClickListener(new f6(d10));
        ((AlertDialog) d10.c()).show();
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) P1().b());
        Q1();
    }
}
