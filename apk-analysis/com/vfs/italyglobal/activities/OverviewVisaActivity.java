package com.vfs.italyglobal.activities;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import bc.n;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.utilities.Utility;
import nb.h;
import nb.i;
import ua.a4;
import ua.b4;
import va.x;
import xa.e1;
import ya.d1;
import ya.u2;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class OverviewVisaActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    public String f9436f0;

    /* renamed from: g0  reason: collision with root package name */
    private final h f9437g0 = i.a(new a4(this));

    public OverviewVisaActivity() {
        super(false, false, 3, (bc.h) null);
    }

    /* access modifiers changed from: private */
    public static final void E1(OverviewVisaActivity overviewVisaActivity, View view) {
        overviewVisaActivity.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final e1 F1(OverviewVisaActivity overviewVisaActivity) {
        return e1.c(overviewVisaActivity.getLayoutInflater());
    }

    public final e1 C1() {
        return (e1) this.f9437g0.getValue();
    }

    public final void D1() {
        G1(1);
        C1().f20372c.setOnClickListener(new b4(this));
    }

    public final void G1(int i10) {
        int i11;
        H1("");
        if (i10 == 1) {
            Y().m().q(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right).o(R.id.fragment_visa, d1.f20951s0.a(), "OverView Main Fragment").g();
        } else {
            Y().m().q(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left).o(R.id.fragment_visa, d1.f20951s0.a(), "OverView Main Fragment").g();
        }
        Y().S0();
        AppCompatTextView appCompatTextView = C1().f20373d;
        if (n.a(Utility.f9497a.k(c1()), "CHL")) {
            i11 = R.string.citizenship_overview;
        } else {
            i11 = R.string.visa_overview;
        }
        appCompatTextView.setText(getString(i11));
    }

    public final void H1(String str) {
        n.e(str, "<set-?>");
        this.f9436f0 = str;
    }

    public void onBackPressed() {
        androidx.fragment.app.i e02 = Y().e0(R.id.fragment_visa);
        n.b(e02);
        if (e02 instanceof u2) {
            G1(2);
        } else if (e02 instanceof d1) {
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
