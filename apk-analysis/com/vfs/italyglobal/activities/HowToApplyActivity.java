package com.vfs.italyglobal.activities;

import android.os.Bundle;
import android.view.View;
import com.phoenixcapture.camerakit.R;
import nb.h;
import nb.i;
import ua.a3;
import ua.b3;
import va.x;
import xa.t0;
import ya.o0;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class HowToApplyActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9402f0 = i.a(new b3(this));

    public HowToApplyActivity() {
        super(false, false, 3, (bc.h) null);
    }

    /* access modifiers changed from: private */
    public static final void E1(HowToApplyActivity howToApplyActivity, View view) {
        howToApplyActivity.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final t0 F1(HowToApplyActivity howToApplyActivity) {
        return t0.c(howToApplyActivity.getLayoutInflater());
    }

    private final void G1() {
        if (!getIntent().hasExtra(getString(R.string.isForAfterSubmission))) {
            C1().f20672d.setText(getString(R.string.how_to_apply));
        } else if (getIntent().getBooleanExtra(getString(R.string.isForAfterSubmission), false)) {
            C1().f20672d.setText(getString(R.string.after_submission));
        } else {
            C1().f20672d.setText(getString(R.string.how_to_apply));
        }
        Y().m().q(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_right).n(R.id.fragment_how_to_apply, o0.f21038s0.a(getIntent().getBooleanExtra(getString(R.string.isForAfterSubmission), false))).g();
    }

    public final t0 C1() {
        return (t0) this.f9402f0.getValue();
    }

    public final void D1() {
        C1().f20671c.setOnClickListener(new a3(this));
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) C1().b());
        G1();
        D1();
    }
}
