package m3;

import android.content.Context;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import m3.q1;
import n3.e;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class d3 implements q0 {

    /* renamed from: c  reason: collision with root package name */
    public static ScheduledFuture f14790c;

    /* renamed from: a  reason: collision with root package name */
    public final h2 f14791a;

    /* renamed from: b  reason: collision with root package name */
    public final r f14792b;

    static {
        byte[] bArr = {124, 81, 51, -85, -75, -97, 123, 50};
        c(bArr, new byte[]{83, 90, -58, 120, 43, -28, -103, -19});
        System.loadLibrary(new String(bArr, StandardCharsets.UTF_8).intern());
    }

    public d3(Context context, i3 i3Var, String str, o3 o3Var, w2 w2Var, e eVar) {
        o3 o3Var2 = o3Var;
        r rVar = new r(o3Var2, new q1(new g2(context), (q1.a) null), str, w2Var, this);
        this.f14792b = rVar;
        i4 i4Var = new i4(context, w2Var.a(), (q1.a) null);
        Context context2 = context;
        this.f14791a = new h2(context2, i3Var, rVar, o3Var, w2Var, i4Var);
        d(context);
        b(context, eVar);
        g(context);
        a(context);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0098, code lost:
        if (r4 != 0) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x009b, code lost:
        r4 = -1058029970;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x01c1, code lost:
        r2 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0084, code lost:
        if (r4 != 0) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0086, code lost:
        r4 = r15;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void c(byte[] r23, byte[] r24) {
        /*
            r0 = r23
            r2 = 0
            r3 = -1003175592(0xffffffffc434c158, float:-723.021)
            r4 = r3
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r2
        L_0x000b:
            int r8 = ~r4
            r9 = 16777216(0x1000000, float:2.3509887E-38)
            r8 = r8 & r9
            r10 = -16777217(0xfffffffffeffffff, float:-1.7014117E38)
            r11 = r4 & r10
            int r11 = r11 * r8
            r8 = r4 | r9
            r12 = r4 & r9
            int r12 = r12 * r8
            int r12 = r12 + r11
            int r4 = r4 >>> 8
            r8 = -1095531540(0xffffffffbeb383ec, float:-0.35061586)
            r11 = r4 & r8
            r11 = r11 | r12
            int r4 = ~r4
            r4 = r4 | r8
            r4 = r4 | r12
            int r4 = r4 - r11
            int r4 = ~r4
            r8 = -130029571(0xfffffffff83fe7fd, float:-1.5569281E34)
            int r8 = r8 - r4
            r11 = 2
            r4 = r4 & r11
            r4 = r4 | r8
            r8 = -1171264002(0xffffffffba2fedfe, float:-6.711184E-4)
            int r8 = r8 - r4
            r4 = r8 | 1
            int r4 = r4 * r11
            int r8 = ~r8
            int r8 = r8 + r4
            r4 = -1109882652(0xffffffffbdd888e4, float:-0.10572985)
            r4 = r4 ^ r8
            r15 = -1216566512(0xffffffffb77cab10, float:-1.5060199E-5)
            r16 = 935800592(0x37c72f10, float:2.3744564E-5)
            r8 = 1
            switch(r4) {
                case -1922532006: goto L_0x01ee;
                case -1486048729: goto L_0x01c5;
                case -497756741: goto L_0x0190;
                case 256719606: goto L_0x00a0;
                case 1429728656: goto L_0x0089;
                case 1870596681: goto L_0x0088;
                case 1879000533: goto L_0x0049;
                default: goto L_0x0046;
            }
        L_0x0046:
            r4 = r16
            goto L_0x000b
        L_0x0049:
            int r4 = r3.length
            int r5 = 0 - r6
            int r9 = 0 - r5
            r10 = r9 | r4
            r4 = r4 ^ r9
            r4 = r4 ^ r10
            int r12 = r9 * 2
            int r13 = r3.length
            int r14 = ~r13
            r14 = r14 & r9
            int r14 = r14 * r11
            r9 = r9 ^ r13
            int r9 = r9 - r14
            byte r9 = r3[r9]
            int r13 = r3.length
            r14 = r13 ^ r5
            r5 = r5 | r13
            int r5 = r5 * r11
            int r5 = r5 - r14
            byte r5 = r2[r5]
            byte r13 = (byte) r11
            r14 = r5 | 1
            byte r14 = (byte) r14
            int r13 = r13 * r14
            int r10 = r10 - r12
            int r10 = r10 + r4
            int r4 = ~r5
            byte r4 = (byte) r4
            byte r5 = (byte) r13
            int r4 = r4 + r5
            r4 = r4 ^ r9
            r4 = r4 ^ r8
            byte r4 = (byte) r4
            r3[r10] = r4
            int r4 = r6 * 2
            int r5 = ~r6
            int r5 = r5 + r4
            long r9 = (long) r6
            long r11 = (long) r11
            int r4 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            int r4 = r4 >>> 31
            r4 = r4 & r8
            if (r4 == 0) goto L_0x0082
            goto L_0x0084
        L_0x0082:
            r15 = r16
        L_0x0084:
            if (r4 == 0) goto L_0x009b
        L_0x0086:
            r4 = r15
            goto L_0x000b
        L_0x0088:
            return
        L_0x0089:
            int r4 = r3.length
            int r5 = r4 % 4
            long r9 = (long) r5
            long r11 = (long) r8
            int r4 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            int r4 = r4 >>> 31
            r4 = r4 & r8
            if (r4 == 0) goto L_0x0096
            goto L_0x0098
        L_0x0096:
            r15 = r16
        L_0x0098:
            if (r4 == 0) goto L_0x009b
            goto L_0x0086
        L_0x009b:
            r4 = -1058029970(0xffffffffc0efbe6e, float:-7.491996)
            goto L_0x000b
        L_0x00a0:
            r4 = r7 | -4
            int r15 = r7 + -1
            int r15 = r15 - r4
            byte r4 = r2[r15]
            byte r4 = (byte) r4
            r17 = r9
            int r9 = ~r4
            r9 = r9 & r17
            r18 = r4 & r10
            int r18 = r18 * r9
            r9 = r4 | r17
            r4 = r4 & r17
            int r4 = r4 * r9
            int r4 = r4 + r18
            r9 = r7 & 2
            int r18 = r7 + 2
            int r9 = r18 - r9
            r19 = r10
            byte r10 = r2[r9]
            r10 = r10 & 255(0xff, float:3.57E-43)
            int r12 = ~r10
            r20 = 65536(0x10000, float:9.18355E-41)
            r12 = r12 & r20
            int r10 = r10 * r12
            int r12 = -1 - r10
            int r21 = -1 - r4
            r12 = r12 | r21
            int r4 = m3.l.a(r10, r4, r8, r12)
            int r10 = -1 - r7
            r10 = r10 | -2
            int r18 = r18 + r10
            byte r10 = r2[r18]
            r10 = r10 & 255(0xff, float:3.57E-43)
            int r12 = ~r10
            r12 = r12 & 256(0x100, float:3.59E-43)
            int r10 = r10 * r12
            int r4 = ~r4
            r4 = r4 | r10
            int r10 = r10 - r8
            int r10 = r10 - r4
            byte r4 = r2[r7]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r12 = -755325340(0xffffffffd2faa664, float:-5.38266698E11)
            r21 = r10 & r12
            r21 = r21 | r4
            int r10 = ~r10
            r10 = r10 | r12
            r4 = r4 | r10
            int r4 = r4 - r21
            int r4 = ~r4
            byte r10 = r3[r15]
            byte r10 = (byte) r10
            int r12 = ~r10
            r12 = r12 & r17
            r19 = r10 & r19
            int r19 = r19 * r12
            r12 = r10 | r17
            r10 = r10 & r17
            int r10 = r10 * r12
            int r10 = r10 + r19
            byte r12 = r3[r9]
            r12 = r12 & 255(0xff, float:3.57E-43)
            r21 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            int r13 = ~r12
            r13 = r13 & r20
            int r12 = r12 * r13
            byte r13 = r3[r18]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r14 = ~r13
            r14 = r14 & 256(0x100, float:3.59E-43)
            int r13 = r13 * r14
            byte r14 = r3[r7]
            r14 = r14 & 255(0xff, float:3.57E-43)
            r19 = r2
            r17 = 0
            double r1 = (double) r4
            int r1 = (r1 > r21 ? 1 : (r1 == r21 ? 0 : -1))
            int r1 = r1 >>> 31
            int r1 = r4 << r1
            r2 = 1983400305(0x76384971, float:9.344455E32)
            int r2 = r2 - r10
            r4 = r10 & 2
            r2 = r2 | r4
            r4 = -659933419(0xffffffffd8aa3715, float:-1.49722842E15)
            int r4 = r4 - r2
            r2 = r4 | r12
            int r2 = r2 * r11
            int r10 = ~r12
            r4 = r4 ^ r10
            int r4 = r4 + r2
            int r4 = r4 + r8
            r2 = r4 & r14
            int r2 = r2 * r11
            r4 = r4 ^ r14
            int r4 = r4 + r2
            r2 = -2109111237(0xffffffff8249843b, float:-1.4805096E-37)
            r10 = r13 | r2
            r10 = r10 & r4
            int r12 = ~r13
            r2 = r2 & r12
            r2 = r2 & r4
            r4 = r4 | r13
            int r4 = r4 - r2
            int r4 = r4 + r10
            r2 = r1 | r4
            int r1 = m3.s.a(r2, r11, r1, r4)
            byte r2 = (byte) r1
            r3[r7] = r2
            int r2 = r1 >>> 8
            byte r2 = (byte) r2
            r3[r18] = r2
            int r2 = r1 >>> 16
            byte r2 = (byte) r2
            r3[r9] = r2
            int r1 = r1 >>> 24
            byte r1 = (byte) r1
            r3[r15] = r1
            r1 = r7 & 4
            int r1 = r1 * r11
            r2 = r7 ^ 4
            int r7 = r2 + r1
            int r1 = r3.length
            int r2 = r3.length
            int r2 = r2 % 4
            int r2 = 0 - r2
            r4 = r1 ^ r2
            long r9 = (long) r7
            r1 = r1 | r2
            int r1 = r1 * r11
            int r1 = r1 - r4
            long r1 = (long) r1
            int r1 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            int r1 = r1 >>> 31
            r1 = r1 & r8
            if (r1 == 0) goto L_0x0183
            r4 = -1515449616(0xffffffffa5ac12f0, float:-2.9850076E-16)
            goto L_0x0185
        L_0x0183:
            r4 = r16
        L_0x0185:
            r2 = r19
            if (r1 == 0) goto L_0x018b
            goto L_0x000b
        L_0x018b:
            r4 = -10521562(0xffffffffff5f7426, float:-2.9702092E38)
            goto L_0x000b
        L_0x0190:
            r19 = r2
            r17 = 0
            int r1 = r3.length
            int r2 = 0 - r6
            r4 = r1 ^ r2
            r1 = r1 | r2
            int r1 = r1 * r11
            int r1 = r1 - r4
            byte r4 = r19[r1]
            int r9 = r3.length
            r10 = 1163302289(0x45569591, float:3433.348)
            r10 = r10 | r2
            r10 = r10 & r9
            int r12 = ~r2
            r13 = 1163302289(0x45569591, float:3433.348)
            r12 = r12 & r13
            r12 = r12 & r9
            r2 = r2 | r9
            int r2 = r2 - r12
            int r2 = r2 + r10
            byte r2 = r19[r2]
            byte r9 = (byte) r11
            r10 = r2 | r4
            byte r10 = (byte) r10
            int r9 = r9 * r10
            int r4 = ~r4
            r2 = r2 ^ r4
            byte r2 = (byte) r2
            byte r4 = (byte) r9
            int r2 = r2 + r4
            byte r2 = (byte) r2
            byte r4 = (byte) r8
            int r2 = r2 + r4
            byte r2 = (byte) r2
            r19[r1] = r2
            r4 = r16
        L_0x01c1:
            r2 = r19
            goto L_0x000b
        L_0x01c5:
            r17 = 0
            int r1 = r0.length
            int r2 = r0.length
            int r2 = r2 % 4
            int r2 = 0 - r2
            int r3 = ~r1
            int r2 = 0 - r2
            r3 = r3 & r2
            int r2 = ~r2
            r1 = r1 & r2
            int r1 = r1 - r3
            if (r1 > 0) goto L_0x01d8
            r8 = r17
        L_0x01d8:
            if (r8 == 0) goto L_0x01de
            r12 = -1515449616(0xffffffffa5ac12f0, float:-2.9850076E-16)
            goto L_0x01e0
        L_0x01de:
            r12 = r16
        L_0x01e0:
            if (r8 == 0) goto L_0x01e4
            r4 = r12
            goto L_0x01e7
        L_0x01e4:
            r4 = -10521562(0xffffffffff5f7426, float:-2.9702092E38)
        L_0x01e7:
            r2 = r24
            r3 = r0
            r7 = r17
            goto L_0x000b
        L_0x01ee:
            r19 = r2
            r17 = 0
            r21 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            int r1 = r3.length
            int r2 = 0 - r5
            int r4 = r2 * 3
            r6 = -4
            int r2 = m3.p.a(r2, r6, r8, r1)
            r1 = r1 & r11
            r1 = r1 | r2
            r2 = r17
            int r1 = m3.m.a(r2, r1, r4, r8)
            byte r1 = r19[r1]
            byte r1 = (byte) r1
            double r9 = (double) r1
            int r1 = (r9 > r21 ? 1 : (r9 == r21 ? 0 : -1))
            r4 = -1
            if (r1 > r4) goto L_0x0210
            r8 = r2
        L_0x0210:
            if (r8 == 0) goto L_0x0215
            r4 = r16
            goto L_0x0219
        L_0x0215:
            r1 = -1671996003(0xffffffff9c575d9d, float:-7.1258525E-22)
            r4 = r1
        L_0x0219:
            r6 = r5
            goto L_0x01c1
        */
        throw new UnsupportedOperationException("Method not decompiled: m3.d3.c(byte[], byte[]):void");
    }

    public final void a(Context context) {
        new Thread(new c3(this, context)).start();
    }

    public final void b(Context context, e eVar) {
        new Thread(new b3(this, context, eVar)).start();
    }

    public final void d(Context context) {
        Iterator it = ((ArrayList) this.f14791a.b()).iterator();
        while (it.hasNext()) {
            v0 v0Var = (v0) it.next();
            if (v0Var != null) {
                v0Var.c();
            }
        }
    }

    public final void e(Context context, e eVar) {
        s0.f15125a.b(context);
        this.f14791a.a(b.f14750b.a(eVar), (g0) null);
        g gVar = this.f14791a.f14897r;
        if (gVar != null) {
            gVar.c(context);
        }
    }

    public final void f(Context context) {
        Iterator it = ((ArrayList) this.f14791a.b()).iterator();
        while (it.hasNext()) {
            v0 v0Var = (v0) it.next();
            if (v0Var != null) {
                v0Var.c();
                v0Var.c(context);
            }
        }
        this.f14791a.f14880a.U(context);
        this.f14792b.b(context);
    }

    public final void g(Context context) {
        f14790c = Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new u2(this.f14791a, context), 180, 180, TimeUnit.SECONDS);
    }
}
