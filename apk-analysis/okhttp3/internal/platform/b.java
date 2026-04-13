package okhttp3.internal.platform;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.security.NetworkSecurityPolicy;
import android.util.Log;
import bc.h;
import bc.n;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import ob.p;
import qd.c;
import rd.f;
import rd.g;
import rd.i;
import rd.j;
import rd.k;
import ud.e;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class b extends c implements c {

    /* renamed from: f  reason: collision with root package name */
    public static final a f16272f = new a((h) null);

    /* renamed from: g  reason: collision with root package name */
    private static final String f16273g = "OkHttp";
    /* access modifiers changed from: private */

    /* renamed from: h  reason: collision with root package name */
    public static final boolean f16274h;

    /* renamed from: d  reason: collision with root package name */
    private Context f16275d;

    /* renamed from: e  reason: collision with root package name */
    private final List f16276e;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a {
        public /* synthetic */ a(h hVar) {
            this();
        }

        public final c a() {
            if (b()) {
                return new b();
            }
            return null;
        }

        public final boolean b() {
            return b.f16274h;
        }

        private a() {
        }
    }

    /* renamed from: okhttp3.internal.platform.b$b  reason: collision with other inner class name */
    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class C0211b implements e {

        /* renamed from: a  reason: collision with root package name */
        private final X509TrustManager f16277a;

        /* renamed from: b  reason: collision with root package name */
        private final Method f16278b;

        public C0211b(X509TrustManager x509TrustManager, Method method) {
            n.e(x509TrustManager, "trustManager");
            n.e(method, "findByIssuerAndSignatureMethod");
            this.f16277a = x509TrustManager;
            this.f16278b = method;
        }

        public X509Certificate a(X509Certificate x509Certificate) {
            n.e(x509Certificate, "cert");
            try {
                Object invoke = this.f16278b.invoke(this.f16277a, new Object[]{x509Certificate});
                n.c(invoke, "null cannot be cast to non-null type java.security.cert.TrustAnchor");
                return ((TrustAnchor) invoke).getTrustedCert();
            } catch (IllegalAccessException e10) {
                throw new AssertionError("unable to get issues and signature", e10);
            } catch (InvocationTargetException unused) {
                return null;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof C0211b)) {
                return false;
            }
            C0211b bVar = (C0211b) obj;
            if (n.a(this.f16277a, bVar.f16277a) && n.a(this.f16278b, bVar.f16278b)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.f16277a.hashCode() * 31) + this.f16278b.hashCode();
        }

        public String toString() {
            return "CustomTrustRootIndex(trustManager=" + this.f16277a + ", findByIssuerAndSignatureMethod=" + this.f16278b + ')';
        }
    }

    static {
        boolean z10;
        if (!c.f16279a.f() || Build.VERSION.SDK_INT >= 29) {
            z10 = false;
        } else {
            z10 = true;
        }
        f16274h = z10;
    }

    public b() {
        List o10 = p.o(k.a.b(k.f17390j, (String) null, 1, (Object) null), new i(f.f17374f.d()), new i(rd.h.f17385a.b()), new i(g.f17382a.a()));
        ArrayList arrayList = new ArrayList();
        for (Object next : o10) {
            if (((j) next).a()) {
                arrayList.add(next);
            }
        }
        this.f16276e = arrayList;
    }

    public void a(Context context) {
        this.f16275d = context;
    }

    public Context b() {
        return this.f16275d;
    }

    public ud.c e(X509TrustManager x509TrustManager) {
        n.e(x509TrustManager, "trustManager");
        rd.b a10 = rd.b.f17367d.a(x509TrustManager);
        if (a10 != null) {
            return a10;
        }
        return super.e(x509TrustManager);
    }

    public e f(X509TrustManager x509TrustManager) {
        n.e(x509TrustManager, "trustManager");
        try {
            StrictMode.noteSlowCall("buildTrustRootIndex");
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", new Class[]{X509Certificate.class});
            declaredMethod.setAccessible(true);
            n.b(declaredMethod);
            return new C0211b(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return super.f(x509TrustManager);
        }
    }

    public void g(SSLSocket sSLSocket, String str, List list) {
        Object obj;
        n.e(sSLSocket, "sslSocket");
        n.e(list, "protocols");
        Iterator it = this.f16276e.iterator();
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

    public void h(Socket socket, InetSocketAddress inetSocketAddress, int i10) {
        n.e(socket, "socket");
        n.e(inetSocketAddress, "address");
        try {
            socket.connect(inetSocketAddress, i10);
        } catch (ClassCastException e10) {
            if (Build.VERSION.SDK_INT == 26) {
                throw new IOException("Exception in connect", e10);
            }
            throw e10;
        }
    }

    public String i(SSLSocket sSLSocket) {
        Object obj;
        n.e(sSLSocket, "sslSocket");
        Iterator it = this.f16276e.iterator();
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

    public boolean k(String str) {
        n.e(str, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
    }

    public void l(String str, int i10, Throwable th) {
        n.e(str, "message");
        if (i10 == 5) {
            Log.w(f16273g, str, th);
        } else {
            Log.i(f16273g, str, th);
        }
    }

    public SSLContext o() {
        StrictMode.noteSlowCall("newSSLContext");
        return super.o();
    }
}
