package com.google.firebase.sessions;

import android.content.Context;
import android.util.Log;
import ba.f0;
import ba.j0;
import ba.l;
import ba.m;
import ba.o;
import ba.s0;
import ba.w0;
import ba.x0;
import ba.y0;
import ba.z0;
import bc.n;
import e2.d;
import e2.e0;
import e2.j;
import e2.y;
import ea.h;
import java.io.File;
import java.util.List;
import l8.f;
import mc.l0;
import mc.m0;
import ob.p;
import r9.e;
import rb.i;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public interface b {

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public interface a {
        a a(f fVar);

        a b(i iVar);

        b build();

        a c(i iVar);

        a d(q9.b bVar);

        a e(Context context);

        a f(e eVar);
    }

    /* renamed from: com.google.firebase.sessions.b$b  reason: collision with other inner class name */
    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public interface C0104b {

        /* renamed from: a  reason: collision with root package name */
        public static final a f8884a = a.f8885a;

        /* renamed from: com.google.firebase.sessions.b$b$a */
        /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
        public static final class a {

            /* renamed from: a  reason: collision with root package name */
            static final /* synthetic */ a f8885a = new a();

            private a() {
            }

            private final e2.i f(e0 e0Var, f2.b bVar, List list, l0 l0Var, ac.a aVar) {
                if (h()) {
                    return y.f10537a.a(e0Var, bVar, list, l0Var, aVar);
                }
                return j.f10293a.a(e0Var, bVar, list, l0Var, aVar);
            }

            static /* synthetic */ e2.i g(a aVar, e0 e0Var, f2.b bVar, List list, l0 l0Var, ac.a aVar2, int i10, Object obj) {
                if ((i10 & 4) != 0) {
                    list = p.k();
                }
                return aVar.f(e0Var, bVar, list, l0Var, aVar2);
            }

            private final boolean h() {
                try {
                    System.loadLibrary("datastore_shared_counter");
                    return true;
                } catch (SecurityException | UnsatisfiedLinkError unused) {
                    return false;
                }
            }

            /* access modifiers changed from: private */
            public static final h j(d dVar) {
                n.e(dVar, "ex");
                Log.w("FirebaseSessions", "CorruptionException in session configs DataStore", dVar);
                return ea.i.f10680a.d();
            }

            /* access modifiers changed from: private */
            public static final File k(Context context) {
                return d2.a.a(context, "aqs/sessionConfigsDataStore.data");
            }

            /* access modifiers changed from: private */
            public static final ba.e0 m(f0 f0Var, d dVar) {
                n.e(dVar, "ex");
                Log.w("FirebaseSessions", "CorruptionException in session data DataStore", dVar);
                return f0Var.a();
            }

            /* access modifiers changed from: private */
            public static final File n(Context context) {
                return d2.a.a(context, "aqs/sessionDataStore.data");
            }

            public final ba.b e(f fVar) {
                n.e(fVar, "firebaseApp");
                return j0.f4566a.b(fVar);
            }

            public final e2.i i(Context context, i iVar) {
                n.e(context, "appContext");
                n.e(iVar, "blockingDispatcher");
                return g(this, ea.i.f10680a, new f2.b(new m()), (List) null, m0.a(iVar), new ba.n(context), 4, (Object) null);
            }

            public final e2.i l(Context context, i iVar, f0 f0Var) {
                n.e(context, "appContext");
                n.e(iVar, "blockingDispatcher");
                n.e(f0Var, "sessionDataSerializer");
                return g(this, f0Var, new f2.b(new o(f0Var)), (List) null, m0.a(iVar), new ba.p(context), 4, (Object) null);
            }

            public final w0 o() {
                return x0.f4686a;
            }

            public final y0 p() {
                return z0.f4689a;
            }
        }
    }

    l a();

    s0 b();
}
