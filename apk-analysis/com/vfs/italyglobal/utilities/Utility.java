package com.vfs.italyglobal.utilities;

import ac.a;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Window;
import bc.h;
import bc.n;
import com.google.gson.e;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.AppDelegate;
import com.vfs.italyglobal.pojo.PageCategoryData;
import com.vfs.italyglobal.utilities.g;
import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.TimeZone;
import k.j;
import kc.l;
import nb.x;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public abstract class Utility {

    /* renamed from: a  reason: collision with root package name */
    public static final Companion f9497a = new Companion((h) null);
    /* access modifiers changed from: private */

    /* renamed from: b  reason: collision with root package name */
    public static Dialog f9498b;
    /* access modifiers changed from: private */

    /* renamed from: c  reason: collision with root package name */
    public static Activity f9499c;
    /* access modifiers changed from: private */

    /* renamed from: d  reason: collision with root package name */
    public static Dialog f9500d;
    /* access modifiers changed from: private */

    /* renamed from: e  reason: collision with root package name */
    public static final l f9501e = new l("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,15}$");

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public static /* synthetic */ PageCategoryData i(Companion companion, Context context, String str, boolean z10, int i10, Object obj) {
            if ((i10 & 4) != 0) {
                z10 = false;
            }
            return companion.h(context, str, z10);
        }

        /* access modifiers changed from: private */
        public static final x x() {
            Dialog a10 = Utility.f9500d;
            Dialog dialog = null;
            if (a10 == null) {
                n.o("dialog");
                a10 = null;
            }
            if (a10.isShowing()) {
                Dialog a11 = Utility.f9500d;
                if (a11 == null) {
                    n.o("dialog");
                } else {
                    dialog = a11;
                }
                dialog.dismiss();
            }
            return x.f15721a;
        }

        public final void b(Context context) {
            n.e(context, "context");
            g.a aVar = g.f9512a;
            String string = context.getString(R.string.appointment_URN);
            n.d(string, "getString(...)");
            aVar.g(context, string, "");
        }

        public final void c() {
            try {
                if (Utility.f9499c != null) {
                    Activity b10 = Utility.f9499c;
                    Dialog dialog = null;
                    if (b10 == null) {
                        n.o("loaderHostActivity");
                        b10 = null;
                    }
                    if (b10.isFinishing()) {
                        Activity b11 = Utility.f9499c;
                        if (b11 == null) {
                            n.o("loaderHostActivity");
                            b11 = null;
                        }
                        if (b11.isDestroyed()) {
                            return;
                        }
                    }
                    if (Utility.f9498b != null) {
                        Dialog c10 = Utility.f9498b;
                        if (c10 == null) {
                            n.o("loadingDialog");
                            c10 = null;
                        }
                        if (c10.isShowing()) {
                            Dialog c11 = Utility.f9498b;
                            if (c11 == null) {
                                n.o("loadingDialog");
                            } else {
                                dialog = c11;
                            }
                            dialog.dismiss();
                        }
                    }
                }
            } catch (Exception e10) {
                e10.toString();
            }
        }

        public final void d() {
            try {
                if (Utility.f9500d != null) {
                    Dialog a10 = Utility.f9500d;
                    Dialog dialog = null;
                    if (a10 == null) {
                        n.o("dialog");
                        a10 = null;
                    }
                    if (a10.isShowing()) {
                        Dialog a11 = Utility.f9500d;
                        if (a11 == null) {
                            n.o("dialog");
                        } else {
                            dialog = a11;
                        }
                        dialog.dismiss();
                    }
                }
            } catch (Exception e10) {
                e10.toString();
            }
        }

        public final String e(Context context, String str) {
            n.e(context, "context");
            n.e(str, "data");
            try {
                Context applicationContext = context.getApplicationContext();
                n.c(applicationContext, "null cannot be cast to non-null type com.vfs.italyglobal.AppDelegate");
                return kc.n.G(((AppDelegate) applicationContext).i().b(context, str), " ", "", false, 4, (Object) null);
            } catch (Exception unused) {
                return "";
            }
        }

        public final String f(String str) {
            String str2;
            n.e(str, "date");
            try {
                if (kc.n.Q(str, " ", false, 2, (Object) null)) {
                    str2 = str;
                    try {
                        str = (String) kc.n.C0(str2, new String[]{" "}, false, 0, 6, (Object) null).get(0);
                    } catch (Exception e10) {
                        e = e10;
                        e.toString();
                        return str2;
                    }
                } else {
                    String str3 = str;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date parse = simpleDateFormat.parse(str);
                n.b(parse);
                String format = simpleDateFormat2.format(parse);
                n.d(format, "format(...)");
                return format;
            } catch (Exception e11) {
                e = e11;
                str2 = str;
                e.toString();
                return str2;
            }
        }

        public final int g(int i10, int i11, int i12) {
            return Period.between(LocalDate.of(i10, i11 + 1, i12), LocalDate.now()).getYears();
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.vfs.italyglobal.pojo.PageCategoryData} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: com.vfs.italyglobal.pojo.PageCategoryData} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: com.vfs.italyglobal.pojo.PageCategoryData} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: com.vfs.italyglobal.pojo.PageCategoryData} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: com.vfs.italyglobal.pojo.PageCategoryData} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: com.vfs.italyglobal.pojo.PageCategoryData} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: com.vfs.italyglobal.pojo.PageCategoryData} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.vfs.italyglobal.pojo.PageCategoryData h(android.content.Context r4, java.lang.String r5, boolean r6) {
            /*
                r3 = this;
                java.lang.String r0 = "context"
                bc.n.e(r4, r0)
                java.lang.String r0 = "pageSubCategoryName"
                bc.n.e(r5, r0)
                com.vfs.italyglobal.utilities.g$a r0 = com.vfs.italyglobal.utilities.g.f9512a
                r1 = 2131886250(0x7f1200aa, float:1.9407074E38)
                java.lang.String r1 = r4.getString(r1)
                java.lang.String r2 = "getString(...)"
                bc.n.d(r1, r2)
                java.lang.String r2 = ""
                java.lang.String r4 = r0.b(r4, r1, r2)
                boolean r0 = bc.n.a(r4, r2)
                r1 = 0
                if (r0 != 0) goto L_0x007d
                com.google.gson.e r0 = new com.google.gson.e
                r0.<init>()
                com.vfs.italyglobal.utilities.Utility$Companion$getCategoryData$categoryJson$1 r2 = new com.vfs.italyglobal.utilities.Utility$Companion$getCategoryData$categoryJson$1
                r2.<init>()
                java.lang.reflect.Type r2 = r2.getType()
                java.lang.Object r4 = r0.j(r4, r2)
                java.lang.String r0 = "fromJson(...)"
                bc.n.d(r4, r0)
                java.util.ArrayList r4 = (java.util.ArrayList) r4
                if (r6 == 0) goto L_0x005f
                java.util.Iterator r4 = r4.iterator()
            L_0x0044:
                boolean r6 = r4.hasNext()
                if (r6 == 0) goto L_0x005c
                java.lang.Object r6 = r4.next()
                r0 = r6
                com.vfs.italyglobal.pojo.PageCategoryData r0 = (com.vfs.italyglobal.pojo.PageCategoryData) r0
                java.lang.String r0 = r0.getPageCategoryName()
                boolean r0 = bc.n.a(r0, r5)
                if (r0 == 0) goto L_0x0044
                r1 = r6
            L_0x005c:
                com.vfs.italyglobal.pojo.PageCategoryData r1 = (com.vfs.italyglobal.pojo.PageCategoryData) r1
                return r1
            L_0x005f:
                java.util.Iterator r4 = r4.iterator()
            L_0x0063:
                boolean r6 = r4.hasNext()
                if (r6 == 0) goto L_0x007b
                java.lang.Object r6 = r4.next()
                r0 = r6
                com.vfs.italyglobal.pojo.PageCategoryData r0 = (com.vfs.italyglobal.pojo.PageCategoryData) r0
                java.lang.String r0 = r0.getPageSubCategoryName()
                boolean r0 = bc.n.a(r0, r5)
                if (r0 == 0) goto L_0x0063
                r1 = r6
            L_0x007b:
                com.vfs.italyglobal.pojo.PageCategoryData r1 = (com.vfs.italyglobal.pojo.PageCategoryData) r1
            L_0x007d:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vfs.italyglobal.utilities.Utility.Companion.h(android.content.Context, java.lang.String, boolean):com.vfs.italyglobal.pojo.PageCategoryData");
        }

        public final ArrayList j(Context context) {
            n.e(context, "context");
            g.a aVar = g.f9512a;
            String string = context.getString(R.string.category_data);
            n.d(string, "getString(...)");
            return (ArrayList) new e().j(aVar.b(context, string, ""), new Utility$Companion$getCategoryDataList$1().getType());
        }

        public final String k(Context context) {
            n.e(context, "mContext");
            g.a aVar = g.f9512a;
            String string = context.getString(R.string.selectedCountryCode);
            n.d(string, "getString(...)");
            return aVar.b(context, string, "");
        }

        public final String l() {
            return "mobile;" + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(new Date(System.currentTimeMillis()));
        }

        public final String m() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String format = simpleDateFormat.format(new Date());
            n.d(format, "format(...)");
            return format;
        }

        public final String n(String str) {
            n.e(str, "date");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
            try {
                String format = simpleDateFormat.format(simpleDateFormat.parse(str));
                n.d(format, "format(...)");
                return format;
            } catch (ParseException e10) {
                e10.toString();
                return "";
            }
        }

        public final String o() {
            String languageTag = Locale.getDefault().toLanguageTag();
            n.b(languageTag);
            return languageTag;
        }

        public final String p() {
            try {
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                n.d(networkInterfaces, "getNetworkInterfaces(...)");
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface nextElement = networkInterfaces.nextElement();
                    n.d(nextElement, "nextElement(...)");
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    n.d(inetAddresses, "getInetAddresses(...)");
                    while (true) {
                        if (inetAddresses.hasMoreElements()) {
                            InetAddress nextElement2 = inetAddresses.nextElement();
                            n.d(nextElement2, "nextElement(...)");
                            InetAddress inetAddress = nextElement2;
                            if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                                String hostAddress = ((Inet4Address) inetAddress).getHostAddress();
                                if (hostAddress == null) {
                                    return "0.0.0.0";
                                }
                                return hostAddress;
                            }
                        }
                    }
                }
                return "";
            } catch (SocketException e10) {
                e10.getMessage();
                return "";
            }
        }

        public final String q() {
            return "ITA";
        }

        public final File r(Context context, String str) {
            n.e(context, "context");
            n.e(str, "fileName");
            if (Build.VERSION.SDK_INT >= 29) {
                StringBuilder sb2 = new StringBuilder();
                File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
                n.b(externalFilesDir);
                sb2.append(externalFilesDir.getPath());
                sb2.append(File.separator);
                sb2.append(str);
                return new File(sb2.toString());
            }
            return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator + str);
        }

        public final boolean s(CharSequence charSequence) {
            n.e(charSequence, "target");
            if (TextUtils.isEmpty(charSequence) || !Patterns.EMAIL_ADDRESS.matcher(charSequence).matches()) {
                return false;
            }
            return true;
        }

        public final boolean t(String str) {
            n.e(str, "password");
            return Utility.f9501e.b(str);
        }

        public final String u(String str) {
            n.e(str, "date");
            try {
                DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm:ss a");
                String format = LocalDate.parse(str, ofPattern).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                n.b(format);
                return format;
            } catch (Exception unused) {
                return f(str);
            }
        }

        public final void v(Context context, ArrayList arrayList) {
            n.e(context, "context");
            n.e(arrayList, "data");
            String s10 = new e().s(arrayList);
            g.a aVar = g.f9512a;
            String string = context.getString(R.string.category_data);
            n.d(string, "getString(...)");
            n.b(s10);
            aVar.g(context, string, s10);
        }

        public final void w(Context context, String str) {
            n.e(context, "mContext");
            n.e(str, "message");
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (!activity.isFinishing() || !activity.isDestroyed()) {
                    Utility.f9500d = c.e(activity, str, (String) null, new o(), false, (String) null, (a) null, false, 122, (Object) null);
                    Dialog a10 = Utility.f9500d;
                    if (a10 == null) {
                        n.o("dialog");
                        a10 = null;
                    }
                    a10.show();
                }
            }
        }

        public final void y(Context context) {
            n.e(context, "mContext");
            try {
                if (context instanceof Activity) {
                    if (((Activity) context).isFinishing()) {
                        if (((Activity) context).isDestroyed()) {
                            return;
                        }
                    }
                    Utility.f9499c = (Activity) context;
                    Utility.f9498b = new Dialog(context);
                    Dialog c10 = Utility.f9498b;
                    Dialog dialog = null;
                    if (c10 == null) {
                        n.o("loadingDialog");
                        c10 = null;
                    }
                    c10.requestWindowFeature(1);
                    Dialog c11 = Utility.f9498b;
                    if (c11 == null) {
                        n.o("loadingDialog");
                        c11 = null;
                    }
                    c11.setContentView(R.layout.loading_dialog);
                    Dialog c12 = Utility.f9498b;
                    if (c12 == null) {
                        n.o("loadingDialog");
                        c12 = null;
                    }
                    c12.setCancelable(false);
                    Dialog c13 = Utility.f9498b;
                    if (c13 == null) {
                        n.o("loadingDialog");
                        c13 = null;
                    }
                    Window window = c13.getWindow();
                    if (window != null) {
                        window.setLayout(-2, -2);
                        window.setBackgroundDrawable(new ColorDrawable(context.getColor(17170445)));
                    }
                    Dialog c14 = Utility.f9498b;
                    if (c14 == null) {
                        n.o("loadingDialog");
                        c14 = null;
                    }
                    if (!c14.isShowing()) {
                        Dialog c15 = Utility.f9498b;
                        if (c15 == null) {
                            n.o("loadingDialog");
                        } else {
                            dialog = c15;
                        }
                        dialog.show();
                    }
                }
            } catch (Exception e10) {
                e10.toString();
            }
        }

        public final void z(Context context) {
            n.e(context, "mContext");
            String string = context.getString(R.string.socketTimeoutError);
            n.d(string, "getString(...)");
            Utility.f9500d = c.e((Activity) context, string, (String) null, (a) null, false, (String) null, (a) null, false, j.M0, (Object) null);
            Dialog a10 = Utility.f9500d;
            if (a10 == null) {
                n.o("dialog");
                a10 = null;
            }
            a10.show();
        }

        private Companion() {
        }
    }
}
