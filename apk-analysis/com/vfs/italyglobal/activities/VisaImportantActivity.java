package com.vfs.italyglobal.activities;

import android.os.Bundle;
import android.view.View;
import bc.n;
import com.phoenixcapture.camerakit.R;
import nb.h;
import nb.i;
import ua.v5;
import ua.w5;
import va.x;
import xa.o1;
import ya.u0;
import ya.x0;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class VisaImportantActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9478f0 = i.a(new v5(this));

    /* renamed from: g0  reason: collision with root package name */
    private String f9479g0 = "";

    public VisaImportantActivity() {
        super(false, false, 3, (bc.h) null);
    }

    /* access modifiers changed from: private */
    public static final void E1(VisaImportantActivity visaImportantActivity, View view) {
        visaImportantActivity.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final o1 F1(VisaImportantActivity visaImportantActivity) {
        return o1.c(visaImportantActivity.getLayoutInflater());
    }

    public final o1 C1() {
        return (o1) this.f9478f0.getValue();
    }

    public final void D1() {
        G1(1);
        C1().f20582c.setOnClickListener(new w5(this));
    }

    public final void G1(int i10) {
        this.f9479g0 = "";
        if (i10 == 1) {
            Y().m().q(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right).o(R.id.fragment_visa_imp_info, x0.f21111r0.a(), "Imp Info Main Fragment").g();
        } else {
            Y().m().q(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left).o(R.id.fragment_visa_imp_info, x0.f21111r0.a(), "Imp Info Main Fragment").g();
        }
        Y().S0();
        C1().f20583d.setText(getString(R.string.important_information));
    }

    public void onBackPressed() {
        androidx.fragment.app.i e02 = Y().e0(R.id.fragment_visa_imp_info);
        n.b(e02);
        if (e02 instanceof u0) {
            G1(2);
        } else if (e02 instanceof x0) {
            super.onBackPressed();
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) C1().b());
        D1();
    }
}
