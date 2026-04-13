package fd;

import bc.h;
import bc.n;
import fd.e;
import fd.t;
import gd.k;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import jd.d;
import kd.f;
import kd.t;
import nb.x;
import okhttp3.internal.platform.c;
import ud.c;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public class d0 implements e.a {
    public static final b G = new b((h) null);
    /* access modifiers changed from: private */
    public static final List H = k.l(e0.HTTP_2, e0.HTTP_1_1);
    /* access modifiers changed from: private */
    public static final List I = k.l(n.f11788i, n.f11790k);
    private final int A;
    private final int B;
    private final long C;
    private final t D;
    private final d E;
    private final m F;

    /* renamed from: a  reason: collision with root package name */
    private final r f11553a;

    /* renamed from: b  reason: collision with root package name */
    private final List f11554b;

    /* renamed from: c  reason: collision with root package name */
    private final List f11555c;

    /* renamed from: d  reason: collision with root package name */
    private final t.c f11556d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f11557e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f11558f;

    /* renamed from: g  reason: collision with root package name */
    private final b f11559g;

    /* renamed from: h  reason: collision with root package name */
    private final boolean f11560h;

    /* renamed from: i  reason: collision with root package name */
    private final boolean f11561i;

    /* renamed from: j  reason: collision with root package name */
    private final p f11562j;

    /* renamed from: k  reason: collision with root package name */
    private final s f11563k;

    /* renamed from: l  reason: collision with root package name */
    private final Proxy f11564l;

    /* renamed from: m  reason: collision with root package name */
    private final ProxySelector f11565m;

    /* renamed from: n  reason: collision with root package name */
    private final b f11566n;

    /* renamed from: o  reason: collision with root package name */
    private final SocketFactory f11567o;

    /* renamed from: p  reason: collision with root package name */
    private final SSLSocketFactory f11568p;

    /* renamed from: q  reason: collision with root package name */
    private final X509TrustManager f11569q;

    /* renamed from: r  reason: collision with root package name */
    private final List f11570r;

    /* renamed from: s  reason: collision with root package name */
    private final List f11571s;

    /* renamed from: t  reason: collision with root package name */
    private final HostnameVerifier f11572t;

    /* renamed from: u  reason: collision with root package name */
    private final h f11573u;

    /* renamed from: v  reason: collision with root package name */
    private final c f11574v;

    /* renamed from: w  reason: collision with root package name */
    private final int f11575w;

    /* renamed from: x  reason: collision with root package name */
    private final int f11576x;

    /* renamed from: y  reason: collision with root package name */
    private final int f11577y;

    /* renamed from: z  reason: collision with root package name */
    private final int f11578z;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a {
        private int A;
        private int B;
        private int C;
        private long D;
        private kd.t E;
        private d F;

        /* renamed from: a  reason: collision with root package name */
        private r f11579a = new r();

        /* renamed from: b  reason: collision with root package name */
        private m f11580b;

        /* renamed from: c  reason: collision with root package name */
        private final List f11581c = new ArrayList();

        /* renamed from: d  reason: collision with root package name */
        private final List f11582d = new ArrayList();

        /* renamed from: e  reason: collision with root package name */
        private t.c f11583e = k.c(t.f11829b);

        /* renamed from: f  reason: collision with root package name */
        private boolean f11584f = true;

        /* renamed from: g  reason: collision with root package name */
        private boolean f11585g = true;

        /* renamed from: h  reason: collision with root package name */
        private b f11586h;

        /* renamed from: i  reason: collision with root package name */
        private boolean f11587i;

        /* renamed from: j  reason: collision with root package name */
        private boolean f11588j;

        /* renamed from: k  reason: collision with root package name */
        private p f11589k;

        /* renamed from: l  reason: collision with root package name */
        private s f11590l;

        /* renamed from: m  reason: collision with root package name */
        private Proxy f11591m;

        /* renamed from: n  reason: collision with root package name */
        private ProxySelector f11592n;

        /* renamed from: o  reason: collision with root package name */
        private b f11593o;

        /* renamed from: p  reason: collision with root package name */
        private SocketFactory f11594p;

        /* renamed from: q  reason: collision with root package name */
        private SSLSocketFactory f11595q;

        /* renamed from: r  reason: collision with root package name */
        private X509TrustManager f11596r;

        /* renamed from: s  reason: collision with root package name */
        private List f11597s;

        /* renamed from: t  reason: collision with root package name */
        private List f11598t;

        /* renamed from: u  reason: collision with root package name */
        private HostnameVerifier f11599u;

        /* renamed from: v  reason: collision with root package name */
        private h f11600v;

        /* renamed from: w  reason: collision with root package name */
        private c f11601w;

        /* renamed from: x  reason: collision with root package name */
        private int f11602x;

        /* renamed from: y  reason: collision with root package name */
        private int f11603y;

        /* renamed from: z  reason: collision with root package name */
        private int f11604z;

        public a() {
            b bVar = b.f11499b;
            this.f11586h = bVar;
            this.f11587i = true;
            this.f11588j = true;
            this.f11589k = p.f11815b;
            this.f11590l = s.f11826b;
            this.f11593o = bVar;
            SocketFactory socketFactory = SocketFactory.getDefault();
            n.d(socketFactory, "getDefault(...)");
            this.f11594p = socketFactory;
            b bVar2 = d0.G;
            this.f11597s = bVar2.a();
            this.f11598t = bVar2.b();
            this.f11599u = ud.d.f18330a;
            this.f11600v = h.f11641d;
            this.f11603y = 10000;
            this.f11604z = 10000;
            this.A = 10000;
            this.C = 60000;
            this.D = 1024;
        }

        public final b A() {
            return this.f11593o;
        }

        public final ProxySelector B() {
            return this.f11592n;
        }

        public final int C() {
            return this.f11604z;
        }

        public final boolean D() {
            return this.f11584f;
        }

        public final kd.t E() {
            return this.E;
        }

        public final SocketFactory F() {
            return this.f11594p;
        }

        public final SSLSocketFactory G() {
            return this.f11595q;
        }

        public final d H() {
            return this.F;
        }

        public final int I() {
            return this.C;
        }

        public final int J() {
            return this.A;
        }

        public final X509TrustManager K() {
            return this.f11596r;
        }

        public final a L(long j10, TimeUnit timeUnit) {
            n.e(timeUnit, "unit");
            this.f11604z = k.g("timeout", j10, timeUnit);
            return this;
        }

        public final void M(m mVar) {
            this.f11580b = mVar;
        }

        public final a N(long j10, TimeUnit timeUnit) {
            n.e(timeUnit, "unit");
            this.A = k.g("timeout", j10, timeUnit);
            return this;
        }

        public final a a(a0 a0Var) {
            n.e(a0Var, "interceptor");
            this.f11581c.add(a0Var);
            return this;
        }

        public final d0 b() {
            return new d0(this);
        }

        public final a c(h hVar) {
            n.e(hVar, "certificatePinner");
            if (!n.a(hVar, this.f11600v)) {
                this.E = null;
            }
            this.f11600v = hVar;
            return this;
        }

        public final a d(long j10, TimeUnit timeUnit) {
            n.e(timeUnit, "unit");
            this.f11603y = k.g("timeout", j10, timeUnit);
            return this;
        }

        public final b e() {
            return this.f11586h;
        }

        public final c f() {
            return null;
        }

        public final int g() {
            return this.f11602x;
        }

        public final c h() {
            return this.f11601w;
        }

        public final h i() {
            return this.f11600v;
        }

        public final int j() {
            return this.f11603y;
        }

        public final m k() {
            return this.f11580b;
        }

        public final List l() {
            return this.f11597s;
        }

        public final p m() {
            return this.f11589k;
        }

        public final r n() {
            return this.f11579a;
        }

        public final s o() {
            return this.f11590l;
        }

        public final t.c p() {
            return this.f11583e;
        }

        public final boolean q() {
            return this.f11585g;
        }

        public final boolean r() {
            return this.f11587i;
        }

        public final boolean s() {
            return this.f11588j;
        }

        public final HostnameVerifier t() {
            return this.f11599u;
        }

        public final List u() {
            return this.f11581c;
        }

        public final long v() {
            return this.D;
        }

        public final List w() {
            return this.f11582d;
        }

        public final int x() {
            return this.B;
        }

        public final List y() {
            return this.f11598t;
        }

        public final Proxy z() {
            return this.f11591m;
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class b {
        public /* synthetic */ b(h hVar) {
            this();
        }

        public final List a() {
            return d0.I;
        }

        public final List b() {
            return d0.H;
        }

        private b() {
        }
    }

    public d0(a aVar) {
        ProxySelector proxySelector;
        List list;
        a aVar2 = aVar;
        n.e(aVar2, "builder");
        this.f11553a = aVar2.n();
        this.f11554b = k.v(aVar2.u());
        this.f11555c = k.v(aVar2.w());
        this.f11556d = aVar2.p();
        boolean D2 = aVar2.D();
        this.f11557e = D2;
        boolean q10 = aVar2.q();
        this.f11558f = q10;
        this.f11559g = aVar2.e();
        this.f11560h = aVar2.r();
        this.f11561i = aVar2.s();
        this.f11562j = aVar2.m();
        aVar2.f();
        this.f11563k = aVar2.o();
        this.f11564l = aVar2.z();
        if (aVar2.z() != null) {
            proxySelector = sd.a.f17671a;
        } else {
            proxySelector = aVar2.B();
            if (proxySelector == null && (proxySelector = ProxySelector.getDefault()) == null) {
                proxySelector = sd.a.f17671a;
            }
        }
        this.f11565m = proxySelector;
        this.f11566n = aVar2.A();
        this.f11567o = aVar2.F();
        List l10 = aVar2.l();
        this.f11570r = l10;
        this.f11571s = aVar2.y();
        this.f11572t = aVar2.t();
        this.f11575w = aVar2.g();
        int j10 = aVar2.j();
        this.f11576x = j10;
        int C2 = aVar2.C();
        this.f11577y = C2;
        int J = aVar2.J();
        this.f11578z = J;
        int x10 = aVar2.x();
        this.A = x10;
        this.B = aVar2.I();
        this.C = aVar2.v();
        kd.t E2 = aVar2.E();
        E2 = E2 == null ? new kd.t() : E2;
        this.D = E2;
        d H2 = aVar2.H();
        this.E = H2 == null ? d.f13694m : H2;
        m k10 = aVar2.k();
        if (k10 == null) {
            boolean z10 = q10;
            list = l10;
            m mVar = new m(0, 0, (TimeUnit) null, (d) null, (f) null, C2, J, j10, C2, x10, D2, z10, E2, 31, (h) null);
            aVar2.M(mVar);
            k10 = mVar;
        } else {
            list = l10;
        }
        this.F = k10;
        if (list == null || !list.isEmpty()) {
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((n) it.next()).f()) {
                    if (aVar2.G() != null) {
                        this.f11568p = aVar2.G();
                        c h10 = aVar2.h();
                        n.b(h10);
                        this.f11574v = h10;
                        X509TrustManager K = aVar2.K();
                        n.b(K);
                        this.f11569q = K;
                        h i10 = aVar2.i();
                        n.b(h10);
                        this.f11573u = i10.g(h10);
                    } else {
                        c.a aVar3 = okhttp3.internal.platform.c.f16279a;
                        X509TrustManager q11 = aVar3.e().q();
                        this.f11569q = q11;
                        okhttp3.internal.platform.c e10 = aVar3.e();
                        n.b(q11);
                        this.f11568p = e10.p(q11);
                        c.a aVar4 = ud.c.f18329a;
                        n.b(q11);
                        ud.c a10 = aVar4.a(q11);
                        this.f11574v = a10;
                        h i11 = aVar2.i();
                        n.b(a10);
                        this.f11573u = i11.g(a10);
                    }
                }
            }
        }
        this.f11568p = null;
        this.f11574v = null;
        this.f11569q = null;
        this.f11573u = h.f11641d;
        z();
    }

    private final void z() {
        List list = this.f11554b;
        n.c(list, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
        if (!list.contains((Object) null)) {
            List list2 = this.f11555c;
            n.c(list2, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
            if (!list2.contains((Object) null)) {
                List<n> list3 = this.f11570r;
                if (list3 == null || !list3.isEmpty()) {
                    for (n f10 : list3) {
                        if (f10.f()) {
                            if (this.f11568p == null) {
                                throw new IllegalStateException("sslSocketFactory == null");
                            } else if (this.f11574v == null) {
                                throw new IllegalStateException("certificateChainCleaner == null");
                            } else if (this.f11569q == null) {
                                throw new IllegalStateException("x509TrustManager == null");
                            } else {
                                return;
                            }
                        }
                    }
                }
                if (this.f11568p != null) {
                    throw new IllegalStateException("Check failed.");
                } else if (this.f11574v != null) {
                    throw new IllegalStateException("Check failed.");
                } else if (this.f11569q != null) {
                    throw new IllegalStateException("Check failed.");
                } else if (n.a(this.f11573u, h.f11641d)) {
                    x xVar = x.f15721a;
                } else {
                    throw new IllegalStateException("Check failed.");
                }
            } else {
                throw new IllegalStateException(("Null network interceptor: " + this.f11555c).toString());
            }
        } else {
            throw new IllegalStateException(("Null interceptor: " + this.f11554b).toString());
        }
    }

    public final int A() {
        return this.f11578z;
    }

    public e a(f0 f0Var) {
        n.e(f0Var, "request");
        return new kd.n(this, f0Var, false);
    }

    public final a d(z zVar) {
        h hVar;
        HostnameVerifier hostnameVerifier;
        SSLSocketFactory sSLSocketFactory;
        n.e(zVar, "url");
        if (zVar.i()) {
            sSLSocketFactory = y();
            hostnameVerifier = this.f11572t;
            hVar = this.f11573u;
        } else {
            sSLSocketFactory = null;
            hostnameVerifier = null;
            hVar = null;
        }
        return new a(zVar.h(), zVar.m(), this.f11563k, this.f11567o, sSLSocketFactory, hostnameVerifier, hVar, this.f11566n, this.f11564l, this.f11571s, this.f11570r, this.f11565m);
    }

    public final b e() {
        return this.f11559g;
    }

    public final c f() {
        return null;
    }

    public final int g() {
        return this.f11575w;
    }

    public final int h() {
        return this.f11576x;
    }

    public final m i() {
        return this.F;
    }

    public final p j() {
        return this.f11562j;
    }

    public final r k() {
        return this.f11553a;
    }

    public final t.c l() {
        return this.f11556d;
    }

    public final boolean m() {
        return this.f11558f;
    }

    public final boolean n() {
        return this.f11560h;
    }

    public final boolean o() {
        return this.f11561i;
    }

    public final kd.t p() {
        return this.D;
    }

    public final d q() {
        return this.E;
    }

    public final List r() {
        return this.f11554b;
    }

    public final List s() {
        return this.f11555c;
    }

    public final int t() {
        return this.A;
    }

    public final List u() {
        return this.f11571s;
    }

    public final b v() {
        return this.f11566n;
    }

    public final int w() {
        return this.f11577y;
    }

    public final boolean x() {
        return this.f11557e;
    }

    public final SSLSocketFactory y() {
        SSLSocketFactory sSLSocketFactory = this.f11568p;
        if (sSLSocketFactory != null) {
            return sSLSocketFactory;
        }
        throw new IllegalStateException("CLEARTEXT-only client");
    }

    public d0() {
        this(new a());
    }
}
