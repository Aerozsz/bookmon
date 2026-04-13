package com.vfs.italyglobal.utilities;

import ac.a;
import ac.p;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import bc.n;
import be.x;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.utilities.Utility;
import nb.o;
import xa.f0;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public abstract class c {
    public static final AlertDialog c(Activity activity, String str, String str2, a aVar, boolean z10, String str3, a aVar2, boolean z11) {
        n.e(activity, "<this>");
        n.e(str, "message");
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AlertDialogCustom_SS);
        f0 a10 = f0.a(LayoutInflater.from(activity).inflate(R.layout.custom_alert_dialog, (ViewGroup) null));
        n.d(a10, "bind(...)");
        builder.setView(a10.b());
        builder.setCancelable(false);
        AlertDialog create = builder.create();
        create.requestWindowFeature(1);
        Window window = create.getWindow();
        if (window != null) {
            window.setLayout(-1, -2);
            window.setBackgroundDrawable(new ColorDrawable(activity.getColor(17170445)));
        }
        a10.f20390d.setText(str);
        if (str2 != null) {
            a10.f20389c.setText(str2);
        }
        a10.f20389c.setOnClickListener(new a(z11, create, aVar));
        if (z10) {
            a10.f20388b.setVisibility(0);
            if (str3 != null) {
                a10.f20388b.setText(str3);
            }
            a10.f20388b.setOnClickListener(new b(z11, create, aVar2));
        }
        n.b(create);
        return create;
    }

    public static final o d(Activity activity, int i10) {
        n.e(activity, "<this>");
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AlertDialogCustom_SS);
        View inflate = LayoutInflater.from(activity).inflate(i10, (ViewGroup) null);
        builder.setView(inflate);
        builder.setCancelable(false);
        AlertDialog create = builder.create();
        create.requestWindowFeature(1);
        Window window = create.getWindow();
        if (window != null) {
            window.setLayout(-1, -2);
            window.setBackgroundDrawable(new ColorDrawable(activity.getColor(17170445)));
        }
        return new o(create, inflate);
    }

    public static /* synthetic */ AlertDialog e(Activity activity, String str, String str2, a aVar, boolean z10, String str3, a aVar2, boolean z11, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str2 = null;
        }
        if ((i10 & 4) != 0) {
            aVar = null;
        }
        if ((i10 & 8) != 0) {
            z10 = false;
        }
        if ((i10 & 16) != 0) {
            str3 = null;
        }
        if ((i10 & 32) != 0) {
            aVar2 = null;
        }
        if ((i10 & 64) != 0) {
            z11 = true;
        }
        return c(activity, str, str2, aVar, z10, str3, aVar2, z11);
    }

    /* access modifiers changed from: private */
    public static final void f(boolean z10, AlertDialog alertDialog, a aVar, View view) {
        if (z10 && alertDialog != null) {
            alertDialog.dismiss();
        }
        if (aVar != null) {
            aVar.c();
        }
    }

    /* access modifiers changed from: private */
    public static final void g(boolean z10, AlertDialog alertDialog, a aVar, View view) {
        if (z10 && alertDialog != null) {
            alertDialog.dismiss();
        }
        if (aVar != null) {
            aVar.c();
        }
    }

    public static final void h(x xVar, Context context, p pVar) {
        n.e(xVar, "<this>");
        n.e(context, "context");
        int b10 = xVar.b();
        if (b10 == 403 || b10 == 429) {
            Utility.Companion companion = Utility.f9497a;
            String string = context.getString(R.string.txt_please_try_later);
            n.d(string, "getString(...)");
            companion.w(context, string);
            if (pVar != null) {
                Integer valueOf = Integer.valueOf(xVar.b());
                String string2 = context.getString(R.string.txt_please_try_later);
                n.d(string2, "getString(...)");
                pVar.m(valueOf, string2);
            }
        } else if (pVar != null) {
            Integer valueOf2 = Integer.valueOf(xVar.b());
            String string3 = context.getString(R.string.generic_error);
            n.d(string3, "getString(...)");
            pVar.m(valueOf2, string3);
        }
    }

    public static /* synthetic */ void i(x xVar, Context context, p pVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            pVar = null;
        }
        h(xVar, context, pVar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0060 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean j(android.content.Context r4) {
        /*
            java.lang.String r0 = "<this>"
            bc.n.e(r4, r0)
            java.lang.String r0 = "connectivity"
            java.lang.Object r4 = r4.getSystemService(r0)
            java.lang.String r0 = "null cannot be cast to non-null type android.net.ConnectivityManager"
            bc.n.c(r4, r0)
            android.net.ConnectivityManager r4 = (android.net.ConnectivityManager) r4
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 29
            r2 = 0
            r3 = 1
            if (r0 < r1) goto L_0x0042
            android.net.Network r0 = r4.getActiveNetwork()
            android.net.NetworkCapabilities r4 = r4.getNetworkCapabilities(r0)
            if (r4 == 0) goto L_0x0060
            boolean r0 = r4.hasTransport(r2)
            if (r0 == 0) goto L_0x002b
            return r3
        L_0x002b:
            boolean r0 = r4.hasTransport(r3)
            if (r0 == 0) goto L_0x0032
            return r3
        L_0x0032:
            r0 = 3
            boolean r0 = r4.hasTransport(r0)
            if (r0 == 0) goto L_0x003a
            return r3
        L_0x003a:
            r0 = 2
            boolean r4 = r4.hasTransport(r0)
            if (r4 == 0) goto L_0x0060
            return r3
        L_0x0042:
            android.net.NetworkInfo r0 = r4.getActiveNetworkInfo()     // Catch:{ Exception -> 0x005c }
            if (r0 == 0) goto L_0x0060
            android.net.NetworkInfo r4 = r4.getActiveNetworkInfo()     // Catch:{ Exception -> 0x005c }
            bc.n.b(r4)     // Catch:{ Exception -> 0x005c }
            boolean r4 = r4.isAvailable()     // Catch:{ Exception -> 0x005c }
            if (r4 == 0) goto L_0x0060
            boolean r4 = r0.isConnected()     // Catch:{ Exception -> 0x005c }
            if (r4 == 0) goto L_0x0060
            return r3
        L_0x005c:
            r4 = move-exception
            r4.getMessage()
        L_0x0060:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vfs.italyglobal.utilities.c.j(android.content.Context):boolean");
    }

    public static final void k(String str, Bundle bundle) {
        n.e(str, "eventName");
        o8.a.a(l8.c.f14397a).a(str, bundle);
    }

    public static /* synthetic */ void l(String str, Bundle bundle, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            bundle = null;
        }
        k(str, bundle);
    }
}
