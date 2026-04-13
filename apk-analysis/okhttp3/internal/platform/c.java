package okhttp3.internal.platform;

import bc.h;
import bc.n;
import fd.d0;
import fd.e0;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import ob.p;
import ud.b;
import ud.e;
import yd.d;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public abstract class c {

    /* renamed from: a  reason: collision with root package name */
    public static final a f16279a;
    /* access modifiers changed from: private */

    /* renamed from: b  reason: collision with root package name */
    public static volatile c f16280b;

    /* renamed from: c  reason: collision with root package name */
    private static final Logger f16281c = Logger.getLogger(d0.class.getName());

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a {
        public /* synthetic */ a(h hVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final c d() {
            return d.f16282a.a();
        }

        public final List b(List list) {
            n.e(list, "protocols");
            ArrayList<e0> arrayList = new ArrayList<>();
            for (Object next : list) {
                if (((e0) next) != e0.HTTP_1_0) {
                    arrayList.add(next);
                }
            }
            ArrayList arrayList2 = new ArrayList(p.t(arrayList, 10));
            for (e0 e0Var : arrayList) {
                arrayList2.add(e0Var.toString());
            }
            return arrayList2;
        }

        public final byte[] c(List list) {
            n.e(list, "protocols");
            d dVar = new d();
            for (String str : b(list)) {
                dVar.writeByte(str.length());
                dVar.H(str);
            }
            return dVar.W();
        }

        public final c e() {
            return c.f16280b;
        }

        public final boolean f() {
            return d.f16282a.c();
        }

        private a() {
        }
    }

    static {
        a aVar = new a((h) null);
        f16279a = aVar;
        f16280b = aVar.d();
    }

    public static /* synthetic */ void m(c cVar, String str, int i10, Throwable th, int i11, Object obj) {
        if (obj == null) {
            if ((i11 & 2) != 0) {
                i10 = 4;
            }
            if ((i11 & 4) != 0) {
                th = null;
            }
            cVar.l(str, i10, th);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: log");
    }

    public void d(SSLSocket sSLSocket) {
        n.e(sSLSocket, "sslSocket");
    }

    public ud.c e(X509TrustManager x509TrustManager) {
        n.e(x509TrustManager, "trustManager");
        return new ud.a(f(x509TrustManager));
    }

    public e f(X509TrustManager x509TrustManager) {
        n.e(x509TrustManager, "trustManager");
        X509Certificate[] acceptedIssuers = x509TrustManager.getAcceptedIssuers();
        return new b((X509Certificate[]) Arrays.copyOf(acceptedIssuers, acceptedIssuers.length));
    }

    public abstract void g(SSLSocket sSLSocket, String str, List list);

    public void h(Socket socket, InetSocketAddress inetSocketAddress, int i10) {
        n.e(socket, "socket");
        n.e(inetSocketAddress, "address");
        socket.connect(inetSocketAddress, i10);
    }

    public abstract String i(SSLSocket sSLSocket);

    public Object j(String str) {
        n.e(str, "closer");
        if (f16281c.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    public abstract boolean k(String str);

    public void l(String str, int i10, Throwable th) {
        Level level;
        n.e(str, "message");
        if (i10 == 5) {
            level = Level.WARNING;
        } else {
            level = Level.INFO;
        }
        f16281c.log(level, str, th);
    }

    public void n(String str, Object obj) {
        n.e(str, "message");
        if (obj == null) {
            str = str + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        l(str, 5, (Throwable) obj);
    }

    public SSLContext o() {
        SSLContext instance = SSLContext.getInstance("TLS");
        n.d(instance, "getInstance(...)");
        return instance;
    }

    public SSLSocketFactory p(X509TrustManager x509TrustManager) {
        n.e(x509TrustManager, "trustManager");
        try {
            SSLContext o10 = o();
            o10.init((KeyManager[]) null, new TrustManager[]{x509TrustManager}, (SecureRandom) null);
            SSLSocketFactory socketFactory = o10.getSocketFactory();
            n.d(socketFactory, "getSocketFactory(...)");
            return socketFactory;
        } catch (GeneralSecurityException e10) {
            throw new AssertionError("No System TLS: " + e10, e10);
        }
    }

    public X509TrustManager q() {
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init((KeyStore) null);
        TrustManager[] trustManagers = instance.getTrustManagers();
        n.b(trustManagers);
        if (trustManagers.length == 1) {
            TrustManager trustManager = trustManagers[0];
            if (trustManager instanceof X509TrustManager) {
                n.c(trustManager, "null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
                return (X509TrustManager) trustManager;
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Unexpected default trust managers: ");
        String arrays = Arrays.toString(trustManagers);
        n.d(arrays, "toString(...)");
        sb2.append(arrays);
        throw new IllegalStateException(sb2.toString().toString());
    }

    public String toString() {
        String simpleName = getClass().getSimpleName();
        n.d(simpleName, "getSimpleName(...)");
        return simpleName;
    }
}
