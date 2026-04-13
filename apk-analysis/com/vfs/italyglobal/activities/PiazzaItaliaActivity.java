package com.vfs.italyglobal.activities;

import android.os.Bundle;
import android.view.View;
import bc.n;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.pojo.PiazzaItaliaConfigModel;
import java.util.List;
import nb.h;
import nb.i;
import ua.c4;
import ua.d4;
import ua.e4;
import va.x;
import xa.o;
import ya.h1;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class PiazzaItaliaActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9438f0 = i.a(new d4(this));

    /* renamed from: g0  reason: collision with root package name */
    private final h f9439g0 = i.a(new e4(this));

    public PiazzaItaliaActivity() {
        super(false, false, 3, (bc.h) null);
    }

    /* access modifiers changed from: private */
    public static final void G1(PiazzaItaliaActivity piazzaItaliaActivity, View view) {
        List r02 = piazzaItaliaActivity.Y().r0();
        n.d(r02, "getFragments(...)");
        if (!r02.isEmpty()) {
            piazzaItaliaActivity.Y().S0();
        }
        if (n.a(piazzaItaliaActivity.D1().f20577d.getText(), piazzaItaliaActivity.getString(R.string.piazza_italia))) {
            piazzaItaliaActivity.onBackPressed();
        }
    }

    /* access modifiers changed from: private */
    public static final o H1(PiazzaItaliaActivity piazzaItaliaActivity) {
        return o.c(piazzaItaliaActivity.getLayoutInflater());
    }

    /* access modifiers changed from: private */
    public static final PiazzaItaliaConfigModel I1(PiazzaItaliaActivity piazzaItaliaActivity) {
        return piazzaItaliaActivity.d1().f();
    }

    private final void J1() {
        D1().f20577d.setText(getString(R.string.piazza_italia));
        Y().m().q(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_right).n(R.id.fragment_piazza_italia, h1.f20984r0.a()).g();
    }

    public final o D1() {
        return (o) this.f9438f0.getValue();
    }

    public final PiazzaItaliaConfigModel E1() {
        return (PiazzaItaliaConfigModel) this.f9439g0.getValue();
    }

    public final void F1() {
        D1().f20576c.setOnClickListener(new c4(this));
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) D1().b());
        J1();
        F1();
    }
}
