package com.vfs.italyglobal.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import bc.h;
import bc.n;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public abstract class g {

    /* renamed from: a  reason: collision with root package name */
    public static final a f9512a = new a((h) null);
    /* access modifiers changed from: private */

    /* renamed from: b  reason: collision with root package name */
    public static SharedPreferences f9513b;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a {
        public /* synthetic */ a(h hVar) {
            this();
        }

        public static /* synthetic */ String d(a aVar, Context context, String str, String str2, int i10, Object obj) {
            if ((i10 & 4) != 0) {
                str2 = "";
            }
            return aVar.b(context, str, str2);
        }

        private final void e(Context context) {
            g.f9513b = context.getSharedPreferences("ItalyAppPref", 0);
        }

        public final int a(Context context, String str, int i10) {
            n.e(context, "context");
            n.e(str, "key");
            e(context);
            SharedPreferences a10 = g.f9513b;
            n.b(a10);
            int i11 = a10.getInt(str, i10);
            g.f9513b = null;
            return i11;
        }

        public final String b(Context context, String str, String str2) {
            n.e(context, "context");
            n.e(str, "key");
            n.e(str2, "defaultValue");
            e(context);
            SharedPreferences a10 = g.f9513b;
            n.b(a10);
            String string = a10.getString(str, str2);
            g.f9513b = null;
            n.b(string);
            return string;
        }

        public final boolean c(Context context, String str, boolean z10) {
            n.e(context, "context");
            n.e(str, "key");
            e(context);
            SharedPreferences a10 = g.f9513b;
            n.b(a10);
            boolean z11 = a10.getBoolean(str, z10);
            g.f9513b = null;
            return z11;
        }

        public final void f(Context context, String str, int i10) {
            n.e(context, "context");
            n.e(str, "key");
            e(context);
            SharedPreferences a10 = g.f9513b;
            n.b(a10);
            SharedPreferences.Editor edit = a10.edit();
            n.b(edit);
            edit.putInt(str, i10);
            edit.commit();
            g.f9513b = null;
        }

        public final void g(Context context, String str, String str2) {
            n.e(context, "context");
            n.e(str, "key");
            n.e(str2, "value");
            e(context);
            SharedPreferences a10 = g.f9513b;
            n.b(a10);
            SharedPreferences.Editor edit = a10.edit();
            n.b(edit);
            edit.putString(str, str2);
            edit.commit();
            g.f9513b = null;
        }

        public final void h(Context context, String str, boolean z10) {
            n.e(context, "context");
            n.e(str, "key");
            e(context);
            SharedPreferences a10 = g.f9513b;
            n.b(a10);
            SharedPreferences.Editor edit = a10.edit();
            n.b(edit);
            edit.putBoolean(str, z10);
            edit.commit();
            g.f9513b = null;
        }

        private a() {
        }
    }
}
