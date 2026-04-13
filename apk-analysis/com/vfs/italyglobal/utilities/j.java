package com.vfs.italyglobal.utilities;

import android.app.Activity;
import androidx.appcompat.app.d;
import bc.h;
import bc.n;
import com.phoenixcapture.camerakit.R;
import java.util.List;
import java.util.Objects;
import n3.c;
import n3.e;
import n3.f;
import nb.x;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class j implements f.b {

    /* renamed from: d  reason: collision with root package name */
    public static final a f9516d = new a((h) null);

    /* renamed from: e  reason: collision with root package name */
    private static volatile boolean f9517e;

    /* renamed from: a  reason: collision with root package name */
    private final Activity f9518a;

    /* renamed from: b  reason: collision with root package name */
    private f f9519b;

    /* renamed from: c  reason: collision with root package name */
    private final f.a f9520c = new b();

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a {
        public /* synthetic */ a(h hVar) {
            this();
        }

        private a() {
        }
    }

    public j(Activity activity) {
        n.e(activity, "activity");
        this.f9518a = activity;
    }

    private final void o(String str) {
        q();
    }

    private final void q() {
        if (!f9517e) {
            f9517e = true;
            this.f9518a.runOnUiThread(new h(this));
        }
    }

    /* access modifiers changed from: private */
    public static final void r(j jVar) {
        Activity activity = jVar.f9518a;
        String string = activity.getString(R.string.root_device_msg);
        n.d(string, "getString(...)");
        c.e(activity, string, (String) null, new i(jVar), false, (String) null, (ac.a) null, false, 122, (Object) null).show();
    }

    /* access modifiers changed from: private */
    public static final x s(j jVar) {
        d dVar;
        Activity activity = jVar.f9518a;
        if (activity instanceof d) {
            dVar = (d) activity;
        } else {
            dVar = null;
        }
        if (dVar != null) {
            dVar.finishAffinity();
        }
        System.exit(0);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }

    public void c() {
        o("HOOK");
    }

    public void f() {
        o("TAMPER");
    }

    public void i(List list) {
        Objects.toString(list);
    }

    public void j() {
        o("ROOT");
    }

    public void k() {
        o("EMULATOR");
    }

    public final void p() {
        try {
            e c10 = new e.a("com.vfs.italyglobal", new String[]{m.a(this.f9518a)}).l(l.f9524a).k(true).c();
            f fVar = new f(this, this.f9520c);
            this.f9519b = fVar;
            fVar.b(this.f9518a);
            c.b(this.f9518a, c10);
        } catch (Exception e10) {
            e10.getMessage();
            e10.printStackTrace();
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class b implements f.a {
        b() {
        }

        public void a() {
        }

        public void b() {
        }

        public void c() {
        }

        public void d() {
        }

        public void e() {
        }
    }

    public void a() {
    }

    public void b() {
    }

    public void d() {
    }

    public void e() {
    }

    public void g() {
    }

    public void h() {
    }

    public void l() {
    }
}
