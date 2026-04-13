package com.ahmer.pdfium;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.ParcelFileDescriptor;
import android.util.DisplayMetrics;
import android.util.Log;
import bc.h;
import bc.n;
import com.ahmer.pdfium.PdfDocument;
import com.ahmer.pdfium.util.Size;
import java.io.Closeable;
import nb.x;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class PdfiumCore implements Closeable {

    /* renamed from: p  reason: collision with root package name */
    public static final a f5353p = new a((h) null);
    /* access modifiers changed from: private */

    /* renamed from: q  reason: collision with root package name */
    public static final Object f5354q = new Object();

    /* renamed from: r  reason: collision with root package name */
    private static final String f5355r;

    /* renamed from: b  reason: collision with root package name */
    private final Context f5356b;

    /* renamed from: n  reason: collision with root package name */
    private final PdfDocument f5357n = new PdfDocument();

    /* renamed from: o  reason: collision with root package name */
    private int f5358o;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a {
        public /* synthetic */ a(h hVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final void l(long j10) {
            PdfiumCore.nativeClosePage(j10);
        }

        /* access modifiers changed from: private */
        public final int m(long j10, long j11) {
            return PdfiumCore.nativeGetDestPageIndex(j10, j11);
        }

        /* access modifiers changed from: private */
        public final long n(long j10, int i10, int i11, int i12, int i13) {
            return PdfiumCore.nativeGetLinkAtCoord(j10, i10, i11, i12, i13);
        }

        /* access modifiers changed from: private */
        public final RectF o(long j10) {
            return PdfiumCore.nativeGetLinkRect(j10);
        }

        /* access modifiers changed from: private */
        public final String p(long j10, long j11) {
            return PdfiumCore.nativeGetLinkURI(j10, j11);
        }

        /* access modifiers changed from: private */
        public final long[] q(long j10) {
            return PdfiumCore.nativeGetPageLinks(j10);
        }

        /* access modifiers changed from: private */
        public final Size r(long j10, int i10, int i11) {
            return PdfiumCore.nativeGetPageSizeByIndex(j10, i10, i11);
        }

        /* access modifiers changed from: private */
        public final long s(int i10, String str) {
            return PdfiumCore.nativeOpenDocument(i10, str);
        }

        /* access modifiers changed from: private */
        public final Point t(long j10, int i10, int i11, int i12, int i13, int i14, double d10, double d11) {
            return PdfiumCore.nativePageCoordsToDevice(j10, i10, i11, i12, i13, i14, d10, d11);
        }

        /* access modifiers changed from: private */
        public final void u(long j10, long j11, Bitmap bitmap, int i10, int i11, int i12, int i13, boolean z10) {
            PdfiumCore.nativeRenderPageBitmap(j10, j11, bitmap, i10, i11, i12, i13, z10);
        }

        public final Object k() {
            return PdfiumCore.f5354q;
        }

        private a() {
        }
    }

    static {
        String name = PdfiumCore.class.getName();
        n.d(name, "getName(...)");
        f5355r = name;
        Log.v(name, "Starting AhmerPdfium...");
        try {
            System.loadLibrary("pdfium");
            System.loadLibrary("pdfium_jni");
        } catch (UnsatisfiedLinkError e10) {
            Log.e(f5355r, "UnsatisfiedLinkError: Native libraries failed to load", e10);
        } catch (SecurityException e11) {
            Log.e(f5355r, "SecurityException: Native libraries failed to load", e11);
        } catch (NullPointerException e12) {
            Log.e(f5355r, "NullPointerException: Native libraries failed to load", e12);
        }
    }

    public PdfiumCore(Context context) {
        int i10;
        DisplayMetrics displayMetrics;
        n.e(context, "context");
        this.f5356b = context;
        Resources resources = context.getResources();
        if (resources == null || (displayMetrics = resources.getDisplayMetrics()) == null) {
            i10 = -1;
        } else {
            i10 = displayMetrics.densityDpi;
        }
        this.f5358o = i10;
        Log.d(f5355r, "Starting AhmerPdfium...");
    }

    private final long U(int i10) {
        PdfDocument.e eVar = (PdfDocument.e) this.f5357n.U().get(Integer.valueOf(i10));
        if (eVar != null) {
            return eVar.b();
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public static final native void nativeClosePage(long j10);

    /* access modifiers changed from: private */
    public static final native int nativeGetDestPageIndex(long j10, long j11);

    /* access modifiers changed from: private */
    public static final native long nativeGetLinkAtCoord(long j10, int i10, int i11, int i12, int i13);

    /* access modifiers changed from: private */
    public static final native RectF nativeGetLinkRect(long j10);

    /* access modifiers changed from: private */
    public static final native String nativeGetLinkURI(long j10, long j11);

    /* access modifiers changed from: private */
    public static final native long[] nativeGetPageLinks(long j10);

    /* access modifiers changed from: private */
    public static final native Size nativeGetPageSizeByIndex(long j10, int i10, int i11);

    /* access modifiers changed from: private */
    public static final native long nativeOpenDocument(int i10, String str);

    /* access modifiers changed from: private */
    public static final native Point nativePageCoordsToDevice(long j10, int i10, int i11, int i12, int i13, int i14, double d10, double d11);

    /* access modifiers changed from: private */
    public static final native void nativeRenderPageBitmap(long j10, long j11, Bitmap bitmap, int i10, int i11, int i12, int i13, boolean z10);

    /* JADX WARNING: Can't wrap try/catch for region: R(4:10|11|12|13) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0071, code lost:
        r0 = e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008d A[Catch:{ all -> 0x006f }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0093 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0029 A[Catch:{ all -> 0x006f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List E(int r17, com.ahmer.pdfium.util.SizeF r18, float r19, float r20) {
        /*
            r16 = this;
            r1 = r16
            java.lang.String r0 = "size"
            r2 = r18
            bc.n.e(r2, r0)
            java.lang.Object r3 = f5354q
            monitor-enter(r3)
            long r5 = r16.U(r17)     // Catch:{ all -> 0x006f }
            com.ahmer.pdfium.PdfiumCore$a r0 = f5353p     // Catch:{ all -> 0x006f }
            long[] r0 = r0.q(r5)     // Catch:{ all -> 0x006f }
            java.util.List r0 = ob.j.P(r0)     // Catch:{ all -> 0x006f }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ all -> 0x006f }
            r11.<init>()     // Catch:{ all -> 0x006f }
            java.util.Iterator r12 = r0.iterator()     // Catch:{ all -> 0x006f }
        L_0x0023:
            boolean r0 = r12.hasNext()     // Catch:{ all -> 0x006f }
            if (r0 == 0) goto L_0x0093
            java.lang.Object r0 = r12.next()     // Catch:{ all -> 0x006f }
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ all -> 0x006f }
            long r7 = r0.longValue()     // Catch:{ all -> 0x006f }
            com.ahmer.pdfium.PdfiumCore$a r4 = f5353p     // Catch:{ Exception -> 0x0073 }
            android.graphics.RectF r0 = r4.o(r7)     // Catch:{ Exception -> 0x0073 }
            com.ahmer.pdfium.PdfDocument r9 = r1.f5357n     // Catch:{ Exception -> 0x0073 }
            long r9 = r9.R()     // Catch:{ Exception -> 0x0073 }
            int r13 = r4.m(r9, r7)     // Catch:{ Exception -> 0x0073 }
            com.ahmer.pdfium.PdfDocument r9 = r1.f5357n     // Catch:{ Exception -> 0x0073 }
            long r9 = r9.R()     // Catch:{ Exception -> 0x0073 }
            java.lang.String r7 = r4.p(r9, r7)     // Catch:{ Exception -> 0x0073 }
            if (r7 != 0) goto L_0x0077
            com.ahmer.pdfium.PdfDocument r7 = r1.f5357n     // Catch:{ Exception -> 0x0073 }
            long r14 = r7.R()     // Catch:{ Exception -> 0x0073 }
            float r7 = r2.getWidth()     // Catch:{ Exception -> 0x0073 }
            int r7 = (int) r7     // Catch:{ Exception -> 0x0073 }
            float r8 = r2.getHeight()     // Catch:{ Exception -> 0x0073 }
            int r8 = (int) r8
            r9 = r19
            int r10 = (int) r9
            r1 = r20
            r9 = r10
            int r10 = (int) r1
            long r7 = r4.n(r5, r7, r8, r9, r10)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r7 = r4.p(r14, r7)     // Catch:{ Exception -> 0x0071 }
            goto L_0x0079
        L_0x006f:
            r0 = move-exception
            goto L_0x0095
        L_0x0071:
            r0 = move-exception
            goto L_0x0083
        L_0x0073:
            r0 = move-exception
            r1 = r20
            goto L_0x0083
        L_0x0077:
            r1 = r20
        L_0x0079:
            com.ahmer.pdfium.PdfDocument$c r4 = new com.ahmer.pdfium.PdfDocument$c     // Catch:{ Exception -> 0x0071 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r13)     // Catch:{ Exception -> 0x0071 }
            r4.<init>(r0, r8, r7)     // Catch:{ Exception -> 0x0071 }
            goto L_0x008b
        L_0x0083:
            java.lang.String r4 = f5355r     // Catch:{ all -> 0x006f }
            java.lang.String r7 = "Error processing link"
            android.util.Log.e(r4, r7, r0)     // Catch:{ all -> 0x006f }
            r4 = 0
        L_0x008b:
            if (r4 == 0) goto L_0x0090
            r11.add(r4)     // Catch:{ all -> 0x006f }
        L_0x0090:
            r1 = r16
            goto L_0x0023
        L_0x0093:
            monitor-exit(r3)
            return r11
        L_0x0095:
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ahmer.pdfium.PdfiumCore.E(int, com.ahmer.pdfium.util.SizeF, float, float):java.util.List");
    }

    public final Size G(int i10) {
        Size g10;
        synchronized (f5354q) {
            g10 = f5353p.r(this.f5357n.R(), i10, this.f5358o);
        }
        return g10;
    }

    public final Point I(int i10, int i11, int i12, int i13, int i14, int i15, double d10, double d11) {
        return f5353p.t(U(i10), i11, i12, i13, i14, i15, d10, d11);
    }

    public final RectF L(int i10, int i11, int i12, int i13, int i14, int i15, RectF rectF) {
        RectF rectF2 = rectF;
        n.e(rectF2, "coords");
        int i16 = i10;
        int i17 = i11;
        int i18 = i12;
        int i19 = i13;
        int i20 = i14;
        int i21 = i15;
        Point I = I(i16, i17, i18, i19, i20, i21, (double) rectF2.left, (double) rectF2.top);
        Point I2 = I(i16, i17, i18, i19, i20, i21, (double) rectF2.right, (double) rectF2.bottom);
        return new RectF((float) I.x, (float) I.y, (float) I2.x, (float) I2.y);
    }

    public final PdfDocument R(ParcelFileDescriptor parcelFileDescriptor, String str) {
        n.e(parcelFileDescriptor, "parcelFileDescriptor");
        this.f5357n.e0(parcelFileDescriptor);
        synchronized (f5354q) {
            this.f5357n.f0(f5353p.s(parcelFileDescriptor.getFd(), str));
            x xVar = x.f15721a;
        }
        return this.f5357n;
    }

    /* JADX INFO: finally extract failed */
    public final void W(int i10, Bitmap bitmap, int i11, int i12, int i13, int i14, boolean z10) {
        synchronized (f5354q) {
            try {
                f5353p.u(this.f5357n.R(), U(i10), bitmap, i11, i12, i13, i14, z10);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void close() {
        Log.v(f5355r, "Closing PdfDocument");
        for (Number intValue : this.f5357n.U().keySet()) {
            int intValue2 = intValue.intValue();
            PdfDocument.e eVar = (PdfDocument.e) this.f5357n.U().get(Integer.valueOf(intValue2));
            if (eVar != null) {
                if (eVar.a() > 1) {
                    eVar.c(eVar.a() - 1);
                    return;
                }
                this.f5357n.U().remove(Integer.valueOf(intValue2));
                PdfDocument.e eVar2 = (PdfDocument.e) this.f5357n.U().get(Integer.valueOf(intValue2));
                if (eVar2 != null) {
                    f5353p.l(eVar2.b());
                }
            }
        }
        this.f5357n.close();
    }
}
