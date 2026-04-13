package com.vfs.italyglobal.activities;

import android.os.Bundle;
import android.view.View;
import com.phoenixcapture.camerakit.R;
import nb.h;
import nb.i;
import ua.x1;
import ua.y1;
import va.x;
import ya.d;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class CasaItaliaActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9361f0 = i.a(new y1(this));

    public CasaItaliaActivity() {
        super(false, false, 3, (bc.h) null);
    }

    /* access modifiers changed from: private */
    public static final void E1(CasaItaliaActivity casaItaliaActivity, View view) {
        casaItaliaActivity.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final xa.h F1(CasaItaliaActivity casaItaliaActivity) {
        return xa.h.c(casaItaliaActivity.getLayoutInflater());
    }

    private final void G1() {
        Y().m().q(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right).n(R.id.fragment_casa_italia, new d()).g();
    }

    public final xa.h C1() {
        return (xa.h) this.f9361f0.getValue();
    }

    public final void D1() {
        C1().f20412c.setOnClickListener(new x1(this));
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) C1().b());
        D1();
        G1();
    }
}
