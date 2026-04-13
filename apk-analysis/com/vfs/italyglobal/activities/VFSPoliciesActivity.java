package com.vfs.italyglobal.activities;

import android.os.Bundle;
import android.view.View;
import com.phoenixcapture.camerakit.R;
import nb.h;
import nb.i;
import ua.q5;
import ua.r5;
import va.x;
import ya.i2;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class VFSPoliciesActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9475f0 = i.a(new r5(this));

    public VFSPoliciesActivity() {
        super(false, false, 3, (bc.h) null);
    }

    private final void D1() {
        C1().f20715c.setOnClickListener(new q5(this));
    }

    /* access modifiers changed from: private */
    public static final void E1(VFSPoliciesActivity vFSPoliciesActivity, View view) {
        vFSPoliciesActivity.C1().f20716d.setText(vFSPoliciesActivity.getString(R.string.vfs_policies));
        vFSPoliciesActivity.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final xa.x F1(VFSPoliciesActivity vFSPoliciesActivity) {
        return xa.x.c(vFSPoliciesActivity.getLayoutInflater());
    }

    private final void G1() {
        Y().m().q(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right).n(R.id.fragment_vfs_policies, i2.f20993q0.a()).g();
    }

    public final xa.x C1() {
        return (xa.x) this.f9475f0.getValue();
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
