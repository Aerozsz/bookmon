package com.vfs.italyglobal.utilities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import bc.h;
import bc.n;
import com.vfs.italyglobal.activities.AppointmentLogin;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class q extends Thread {

    /* renamed from: r  reason: collision with root package name */
    public static final a f9531r = new a((h) null);

    /* renamed from: s  reason: collision with root package name */
    private static final String f9532s = q.class.getName();

    /* renamed from: b  reason: collision with root package name */
    private Context f9533b;

    /* renamed from: n  reason: collision with root package name */
    private final long f9534n = 1200000;

    /* renamed from: o  reason: collision with root package name */
    private final long f9535o = 10000;

    /* renamed from: p  reason: collision with root package name */
    private long f9536p;

    /* renamed from: q  reason: collision with root package name */
    private boolean f9537q;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a {
        public /* synthetic */ a(h hVar) {
            this();
        }

        private a() {
        }
    }

    public q(Context context) {
        n.e(context, "context");
        this.f9533b = context;
    }

    private final void b() {
        Intent intent = new Intent(this.f9533b, AppointmentLogin.class);
        intent.addFlags(335577088);
        intent.putExtra("isFromSessionTimeout", true);
        this.f9533b.startActivity(intent);
    }

    /* access modifiers changed from: private */
    public static final void c(q qVar) {
        qVar.b();
    }

    public final synchronized void d() {
        this.f9537q = true;
    }

    public final synchronized void e() {
        this.f9536p = System.currentTimeMillis();
    }

    public void run() {
        e();
        do {
            long currentTimeMillis = System.currentTimeMillis() - this.f9536p;
            try {
                Thread.sleep(this.f9535o);
            } catch (InterruptedException unused) {
            }
            if (currentTimeMillis > this.f9534n) {
                new Handler(Looper.getMainLooper()).post(new p(this));
                d();
            }
        } while (!this.f9537q);
    }
}
