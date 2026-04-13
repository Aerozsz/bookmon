package com.vfs.italyglobal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import bc.n;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.utilities.g;
import ua.p4;
import va.x;
import w1.c1;
import w1.g1;
import w1.w0;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class SplashActivity extends x {
    public SplashActivity() {
        super(false, false);
    }

    /* access modifiers changed from: private */
    public static final void C1(SplashActivity splashActivity) {
        if (g.f9512a.c(splashActivity.c1(), "isFirstTime", false)) {
            splashActivity.E1();
        } else {
            splashActivity.D1();
        }
    }

    private final void D1() {
        startActivity(new Intent(c1(), Dashboard.class));
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        finish();
    }

    private final void E1() {
        Intent intent = new Intent(c1(), CountrySelection.class);
        intent.putExtra(getString(R.string.isFromSplash), true);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        finish();
    }

    public final void B1() {
        g.a aVar = g.f9512a;
        if (aVar.c(c1(), "isFirstTime", true)) {
            aVar.h(c1(), "isFirstTime", true);
        } else {
            aVar.h(c1(), "isFirstTime", false);
            k1();
        }
        new Handler(Looper.getMainLooper()).postDelayed(new p4(this), 3000);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        g1 a10 = w0.a(getWindow(), getWindow().getDecorView());
        n.d(a10, "getInsetsController(...)");
        a10.a(c1.p.e());
        a10.a(c1.p.d());
        setContentView((int) R.layout.activity_splash);
        B1();
    }
}
