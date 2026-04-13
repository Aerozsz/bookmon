package okhttp3.internal.platform;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.security.NetworkSecurityPolicy;
import android.util.CloseGuard;
import bc.h;
import bc.n;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import ob.p;
import qd.c;
import rd.b;
import rd.f;
import rd.g;
import rd.i;
import rd.j;
import ud.e;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class a extends c implements c {

    /* renamed from: f  reason: collision with root package name */
    public static final C0210a f16268f = new C0210a((h) null);
    /* access modifiers changed from: private */

    /* renamed from: g  reason: collision with root package name */
    public static final boolean f16269g;

    /* renamed from: d  reason: collision with root package name */
    private Context f16270d;

    /* renamed from: e  reason: collision with root package name */
    private final List f16271e;

    /* renamed from: okhttp3.internal.platform.a$a  reason: collision with other inner class name */
    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class C0210a {
        public /* synthetic */ C0210a(h hVar) {
            this();
        }

        public final c a() {
            if (b()) {
                return new a();
            }
            return null;
        }

        public final boolean b() {
            return a.f16269g;
        }

        private C0210a() {
        }
    }

    static {
        boolean z10;
        if (!c.f16279a.f() || Build.VERSION.SDK_INT < 29) {
            z10 = false;
        } else {
            z10 = true;
        }
        f16269g = z10;
    }

    public a() {
        List o10 = p.o(rd.a.f17366a.a(), new i(f.f17374f.d()), new i(rd.h.f17385a.b()), new i(g.f17382a.a()));
        ArrayList arrayList = new ArrayList();
        for (Object next : o10) {
            if (((j) next).a()) {
                arrayList.add(next);
            }
        }
        this.f16271e = arrayList;
    }

    public void a(Context context) {
        this.f16270d = context;
    }

    public Context b() {
        return this.f16270d;
    }

    public ud.c e(X509TrustManager x509TrustManager) {
        n.e(x509TrustManager, "trustManager");
        b a10 = b.f17367d.a(x509TrustManager);
        if (a10 != null) {
            return a10;
        }
        return super.e(x509TrustManager);
    }

    public e f(X509TrustManager x509TrustManager) {
        n.e(x509TrustManager, "trustManager");
        StrictMode.noteSlowCall("buildTrustRootIndex");
        return super.f(x509TrustManager);
    }

    public void g(SSLSocket sSLSocket, String str, List list) {
        Object obj;
        n.e(sSLSocket, "sslSocket");
        n.e(list, "protocols");
        Iterator it = this.f16271e.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((j) obj).b(sSLSocket)) {
                break;
            }
        }
        j jVar = (j) obj;
        if (jVar != null) {
            jVar.d(sSLSocket, str, list);
        }
    }

    public String i(SSLSocket sSLSocket) {
        Object obj;
        n.e(sSLSocket, "sslSocket");
        Iterator it = this.f16271e.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((j) obj).b(sSLSocket)) {
                break;
            }
        }
        j jVar = (j) obj;
        if (jVar != null) {
            return jVar.c(sSLSocket);
        }
        return null;
    }

    public Object j(String str) {
        n.e(str, "closer");
        if (Build.VERSION.SDK_INT < 30) {
            return super.j(str);
        }
        CloseGuard a10 = qd.a.a();
        a10.open(str);
        return a10;
    }

    public boolean k(String str) {
        n.e(str, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
    }

    public void n(String str, Object obj) {
        n.e(str, "message");
        if (Build.VERSION.SDK_INT >= 30) {
            n.c(obj, "null cannot be cast to non-null type android.util.CloseGuard");
            qd.b.a(obj).warnIfOpen();
            return;
        }
        super.n(str, obj);
    }

    public SSLContext o() {
        StrictMode.noteSlowCall("newSSLContext");
        return super.o();
    }
}
