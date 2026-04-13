package ab;

import android.content.Context;
import bc.h;
import bc.n;
import be.y;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.AppDelegate;
import com.vfs.italyglobal.pojo.AppConfigModel;
import fd.a0;
import fd.d0;
import fd.h;
import fd.h0;
import fd.z;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import wd.a;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final a f517a = new a();

    /* renamed from: b  reason: collision with root package name */
    private static final wd.a f518b;

    /* renamed from: ab.a$a  reason: collision with other inner class name */
    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class C0026a implements a0 {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Context f519a;

        public C0026a(Context context) {
            this.f519a = context;
        }

        public final h0 a(a0.a aVar) {
            n.e(aVar, "chain");
            throw new IOException(this.f519a.getString(R.string.ssl_pinning_error));
        }
    }

    static {
        wd.a aVar = new wd.a((a.c) null, 1, (h) null);
        aVar.d(a.b.NONE);
        f518b = aVar;
    }

    private a() {
    }

    private final String a(Context context) {
        String str;
        Context applicationContext = context.getApplicationContext();
        n.c(applicationContext, "null cannot be cast to non-null type com.vfs.italyglobal.AppDelegate");
        AppConfigModel e10 = ((AppDelegate) applicationContext).e();
        if (e10 != null) {
            str = e10.getAppointmentSslPin();
        } else {
            str = null;
        }
        if (str == null || kc.n.e0(str)) {
            return null;
        }
        return str;
    }

    private final d0.a h(Context context, boolean z10) {
        d0.a aVar = new d0.a();
        TimeUnit timeUnit = TimeUnit.MINUTES;
        return aVar.d(10, timeUnit).L(10, timeUnit).N(10, timeUnit).a(f518b);
    }

    static /* synthetic */ d0.a i(a aVar, Context context, boolean z10, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z10 = false;
        }
        return aVar.h(context, z10);
    }

    private final d0.a j(Context context) {
        Context applicationContext = context.getApplicationContext();
        n.c(applicationContext, "null cannot be cast to non-null type com.vfs.italyglobal.AppDelegate");
        z.b bVar = z.f11848j;
        AppConfigModel e10 = ((AppDelegate) applicationContext).e();
        n.b(e10);
        String h10 = bVar.c(e10.getAppointmentUrl()).h();
        String a10 = a(context);
        d0.a i10 = i(this, context, false, 2, (Object) null);
        if (h10.length() == 0 || a10 == null) {
            return i10.a(new C0026a(context));
        }
        return i10.c(new h.a().a(h10, a10).b());
    }

    public final y b(Context context) {
        String str;
        n.e(context, "context");
        y.b bVar = new y.b();
        Context applicationContext = context.getApplicationContext();
        n.c(applicationContext, "null cannot be cast to non-null type com.vfs.italyglobal.AppDelegate");
        AppConfigModel e10 = ((AppDelegate) applicationContext).e();
        if (e10 == null || (str = e10.getApplicationBaseUrl()) == null) {
            str = "https://vfsglobal.com/";
        }
        y d10 = bVar.c(str).a(ce.a.f()).f(i(this, context, false, 2, (Object) null).b()).d();
        n.d(d10, "build(...)");
        return d10;
    }

    public final y c(Context context) {
        String str;
        n.e(context, "context");
        y.b bVar = new y.b();
        Context applicationContext = context.getApplicationContext();
        n.c(applicationContext, "null cannot be cast to non-null type com.vfs.italyglobal.AppDelegate");
        AppConfigModel e10 = ((AppDelegate) applicationContext).e();
        if (e10 == null || (str = e10.getAppointmentUrl()) == null) {
            str = "https://vfsglobal.com/";
        }
        y d10 = bVar.c(str).a(ce.a.f()).f(j(context).b()).d();
        n.d(d10, "build(...)");
        return d10;
    }

    public final y d(Context context, String str) {
        n.e(context, "context");
        n.e(str, "baseurl");
        y d10 = new y.b().c(str).a(ce.a.f()).f(i(this, context, false, 2, (Object) null).b()).d();
        n.d(d10, "build(...)");
        return d10;
    }

    public final y e(Context context) {
        String str;
        n.e(context, "context");
        y.b bVar = new y.b();
        Context applicationContext = context.getApplicationContext();
        n.c(applicationContext, "null cannot be cast to non-null type com.vfs.italyglobal.AppDelegate");
        AppConfigModel e10 = ((AppDelegate) applicationContext).e();
        if (e10 == null || (str = e10.getETokenBaseUrl()) == null) {
            str = "https://vfsglobal.com/";
        }
        y d10 = bVar.c(str).a(ce.a.f()).f(i(this, context, false, 2, (Object) null).b()).d();
        n.d(d10, "build(...)");
        return d10;
    }

    public final y f(Context context) {
        String str;
        n.e(context, "context");
        y.b bVar = new y.b();
        Context applicationContext = context.getApplicationContext();
        n.c(applicationContext, "null cannot be cast to non-null type com.vfs.italyglobal.AppDelegate");
        AppConfigModel e10 = ((AppDelegate) applicationContext).e();
        if (e10 == null || (str = e10.getTrackUrl()) == null) {
            str = "https://vfsglobal.com/";
        }
        y d10 = bVar.c(str).a(ce.a.f()).f(i(this, context, false, 2, (Object) null).b()).d();
        n.d(d10, "build(...)");
        return d10;
    }

    public final y g(Context context) {
        String str;
        n.e(context, "context");
        y.b bVar = new y.b();
        Context applicationContext = context.getApplicationContext();
        n.c(applicationContext, "null cannot be cast to non-null type com.vfs.italyglobal.AppDelegate");
        AppConfigModel e10 = ((AppDelegate) applicationContext).e();
        if (e10 == null || (str = e10.getRsaPublicKeyBaseUrl()) == null) {
            str = "https://vfsglobal.com/";
        }
        y d10 = bVar.c(str).a(ce.a.f()).f(i(this, context, false, 2, (Object) null).b()).d();
        n.d(d10, "build(...)");
        return d10;
    }
}
