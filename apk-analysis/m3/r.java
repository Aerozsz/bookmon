package m3;

import android.content.Context;
import com.aheaditec.talsec_security.security.Natives;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import m3.w2;
import org.json.JSONException;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public class r {

    /* renamed from: a  reason: collision with root package name */
    public final o3 f15072a;

    /* renamed from: b  reason: collision with root package name */
    public final boolean f15073b;

    /* renamed from: c  reason: collision with root package name */
    public final q1 f15074c;

    /* renamed from: d  reason: collision with root package name */
    public final w2 f15075d;

    /* renamed from: e  reason: collision with root package name */
    public final a f15076e;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final String f15077a;

        /* renamed from: b  reason: collision with root package name */
        public final w2 f15078b;

        /* renamed from: c  reason: collision with root package name */
        public final LinkedList f15079c = new LinkedList();

        /* renamed from: d  reason: collision with root package name */
        public final q0 f15080d;

        static {
            byte[] bArr = {75, 49, 87, 91};
            d(bArr, new byte[]{63, 45, 84, 32, -35, 106, -90, -36});
            System.loadLibrary(new String(bArr, StandardCharsets.UTF_8).intern());
        }

        public a(w2 w2Var, String str, q0 q0Var) {
            this.f15077a = str;
            this.f15080d = q0Var;
            this.f15078b = w2Var;
            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new q(this), 1, 5, TimeUnit.SECONDS);
        }

        public static void b(String str, String str2, String str3, String str4, String str5, String str6) {
            byte nextInt = (byte) new Random().nextInt(256);
            byte nextInt2 = (byte) new Random().nextInt(256);
            Natives.f5324a.d(e(str2, nextInt, nextInt2), e(str3, nextInt, nextInt2), e(str4, nextInt, nextInt2), e(str, nextInt, nextInt2), e(str5, nextInt, nextInt2), e(str6, nextInt, nextInt2), nextInt, nextInt2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x00a9, code lost:
            if ((((((long) r5) > ((long) 2) ? 1 : (((long) r5) == ((long) 2) ? 0 : -1)) >>> 31) & 1) != 0) goto L_0x01d7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x00ad, code lost:
            r4 = -365117735;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x01d5, code lost:
            if ((((((long) r7) > ((long) 1) ? 1 : (((long) r7) == ((long) 1) ? 0 : -1)) >>> 31) & 1) != 0) goto L_0x01d7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x01d7, code lost:
            r4 = -458924450;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static void d(byte[] r23, byte[] r24) {
            /*
                r0 = r23
                r1 = 0
                r2 = 0
                r3 = 1516727821(0x5a676e0d, float:1.62854304E16)
                r5 = r1
                r6 = r5
                r7 = r6
                r4 = r3
                r3 = r2
            L_0x000c:
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
                r8 = 650911840(0x26cc2060, float:1.4164119E-15)
                r11 = r12 | r8
                r11 = r11 & r4
                int r13 = ~r12
                r8 = r8 & r13
                r8 = r8 & r4
                int r4 = m3.y.a(r8, r4, r12, r11)
                r8 = 642535957(0x264c5215, float:7.088796E-16)
                r11 = r4 & r8
                r12 = 2
                int r11 = r11 * r12
                r4 = r4 ^ r8
                int r4 = r4 + r11
                r8 = r4 | 1
                int r8 = r8 * r12
                int r4 = ~r4
                int r4 = r4 + r8
                r8 = 962785775(0x3962f1ef, float:2.1643167E-4)
                r4 = r4 ^ r8
                r13 = -746753280(0xffffffffd37d7300, float:-1.08855611E12)
                r14 = 9221120237041090560(0x7ff8000000000000, double:NaN)
                r8 = 3
                r16 = -365117735(0xffffffffea3cbed9, float:-5.7044827E25)
                r17 = r9
                r9 = 1
                switch(r4) {
                    case -1896910703: goto L_0x01dc;
                    case -1725904394: goto L_0x01cb;
                    case -1399959314: goto L_0x00d7;
                    case -1135475043: goto L_0x00d6;
                    case 180635757: goto L_0x00b1;
                    case 511524454: goto L_0x0074;
                    case 961838909: goto L_0x004d;
                    default: goto L_0x004c;
                }
            L_0x004c:
                goto L_0x00ad
            L_0x004d:
                int r4 = r3.length
                int r5 = 0 - r7
                r8 = 165327505(0x9dab291, float:5.2649514E-33)
                r10 = r5 | r8
                r10 = r10 & r4
                int r11 = ~r5
                r8 = r8 & r11
                r8 = r8 & r4
                r4 = r4 | r5
                int r4 = r4 - r8
                int r4 = r4 + r10
                byte r4 = r2[r4]
                byte r4 = (byte) r4
                double r4 = (double) r4
                int r4 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
                r5 = -1
                if (r4 > r5) goto L_0x0066
                r9 = r1
            L_0x0066:
                if (r9 == 0) goto L_0x0069
                goto L_0x006c
            L_0x0069:
                r16 = 1093626513(0x412f6a91, float:10.963517)
            L_0x006c:
                if (r9 == 0) goto L_0x0070
                r4 = r13
                goto L_0x0072
            L_0x0070:
                r4 = r16
            L_0x0072:
                r5 = r7
                goto L_0x000c
            L_0x0074:
                int r4 = r3.length
                int r7 = 0 - r5
                int r10 = ~r4
                int r11 = 0 - r7
                r10 = r10 & r11
                int r10 = r10 * r12
                int r13 = r3.length
                r14 = r13 ^ r7
                r13 = r13 | r7
                int r13 = r13 * r12
                int r13 = r13 - r14
                byte r13 = r3[r13]
                int r14 = r3.length
                r15 = r14 & r7
                int r15 = r15 * r12
                r7 = r7 ^ r14
                int r7 = r7 + r15
                byte r7 = r2[r7]
                byte r14 = (byte) r12
                int r15 = ~r7
                r15 = r15 & r13
                byte r15 = (byte) r15
                int r14 = r14 * r15
                r4 = r4 ^ r11
                int r4 = r4 - r10
                byte r7 = (byte) r7
                byte r10 = (byte) r13
                int r7 = r7 - r10
                byte r7 = (byte) r7
                byte r10 = (byte) r14
                int r7 = r7 + r10
                byte r7 = (byte) r7
                r3[r4] = r7
                int r4 = ~r5
                int r4 = r4 * r12
                int r7 = m3.p3.a(r5, r8, r4, r9)
                long r10 = (long) r5
                long r12 = (long) r12
                int r4 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
                int r4 = r4 >>> 31
                r4 = r4 & r9
                if (r4 == 0) goto L_0x00ad
                goto L_0x01d7
            L_0x00ad:
                r4 = r16
                goto L_0x000c
            L_0x00b1:
                int r2 = r0.length
                int r3 = r0.length
                int r3 = r3 % 4
                int r3 = 0 - r3
                r4 = r2 | r3
                int r4 = r4 * r12
                int r3 = ~r3
                r2 = r2 ^ r3
                int r2 = r2 + r4
                int r2 = r2 + r9
                if (r2 > 0) goto L_0x00c1
                r9 = r1
            L_0x00c1:
                if (r9 == 0) goto L_0x00c7
                r11 = -1605440657(0xffffffffa04eeb6f, float:-1.7526777E-19)
                goto L_0x00c9
            L_0x00c7:
                r11 = r16
            L_0x00c9:
                if (r9 == 0) goto L_0x00cd
                r4 = r11
                goto L_0x00d0
            L_0x00cd:
                r4 = -169475207(0xfffffffff5e60379, float:-5.8315367E32)
            L_0x00d0:
                r2 = r24
                r3 = r0
                r6 = r1
                goto L_0x000c
            L_0x00d6:
                return
            L_0x00d7:
                r4 = -1205100633(0xffffffffb82b9fa7, float:-4.0918265E-5)
                r4 = r4 & r6
                r13 = -1205100636(0xffffffffb82b9fa4, float:-4.0918254E-5)
                r13 = r13 & r6
                int r4 = m3.y.a(r13, r6, r8, r4)
                byte r8 = r2[r4]
                byte r8 = (byte) r8
                int r13 = ~r8
                r13 = r13 & r17
                r18 = r8 & r10
                int r18 = r18 * r13
                r13 = r8 | r17
                r8 = r8 & r17
                int r8 = r8 * r13
                int r8 = r8 + r18
                r13 = r6 | -3
                int r18 = r6 + -1
                int r13 = r18 - r13
                r19 = r10
                byte r10 = r2[r13]
                r10 = r10 & 255(0xff, float:3.57E-43)
                int r11 = ~r10
                r20 = 65536(0x10000, float:9.18355E-41)
                r11 = r11 & r20
                int r10 = r10 * r11
                int r11 = -1 - r10
                int r21 = -1 - r8
                r11 = r11 | r21
                int r8 = m3.l.a(r10, r8, r9, r11)
                r10 = r6 | -2
                int r18 = r18 - r10
                byte r10 = r2[r18]
                r10 = r10 & 255(0xff, float:3.57E-43)
                int r11 = ~r10
                r11 = r11 & 256(0x100, float:3.59E-43)
                int r10 = r10 * r11
                int r8 = ~r8
                r8 = r8 | r10
                int r10 = r10 - r9
                int r10 = r10 - r8
                byte r8 = r2[r6]
                r8 = r8 & 255(0xff, float:3.57E-43)
                int r11 = -1 - r10
                int r21 = -1 - r8
                r11 = r11 | r21
                int r8 = m3.l.a(r10, r8, r9, r11)
                byte r10 = r3[r4]
                byte r10 = (byte) r10
                int r11 = ~r10
                r11 = r11 & r17
                r19 = r10 & r19
                int r19 = r19 * r11
                r11 = r10 | r17
                r10 = r10 & r17
                int r10 = r10 * r11
                int r10 = r10 + r19
                byte r11 = r3[r13]
                r11 = r11 & 255(0xff, float:3.57E-43)
                r21 = r14
                int r14 = ~r11
                r14 = r14 & r20
                int r11 = r11 * r14
                int r14 = ~r10
                r11 = r11 & r14
                int r11 = r11 + r10
                byte r10 = r3[r18]
                r10 = r10 & 255(0xff, float:3.57E-43)
                int r14 = ~r10
                r14 = r14 & 256(0x100, float:3.59E-43)
                int r10 = r10 * r14
                r14 = 911399251(0x3652d953, float:3.1418938E-6)
                r14 = r14 & r10
                r14 = r14 | r11
                int r10 = ~r10
                r15 = 911399251(0x3652d953, float:3.1418938E-6)
                r10 = r10 | r15
                r10 = r10 | r11
                int r10 = r10 - r14
                int r10 = ~r10
                byte r11 = r3[r6]
                r11 = r11 & 255(0xff, float:3.57E-43)
                r14 = 1433568692(0x557285b4, float:1.6666004E13)
                r14 = r14 & r10
                r14 = r14 | r11
                int r10 = ~r10
                r15 = 1433568692(0x557285b4, float:1.6666004E13)
                r10 = r10 | r15
                r10 = r10 | r11
                int r10 = r10 - r14
                int r10 = ~r10
                double r14 = (double) r8
                int r11 = (r14 > r21 ? 1 : (r14 == r21 ? 0 : -1))
                int r11 = r11 >>> 31
                int r8 = r8 << r11
                r11 = -1672003491(0xffffffff9c57405d, float:-7.122072E-22)
                int r11 = r11 - r8
                r8 = r8 & r12
                r8 = r8 | r11
                r11 = -1254002618(0xffffffffb5417046, float:-7.206148E-7)
                int r11 = r11 - r8
                r8 = r11 & r10
                int r8 = r8 * r12
                int r11 = r11 + r10
                int r11 = r11 - r8
                byte r8 = (byte) r11
                r3[r6] = r8
                int r8 = r11 >>> 8
                byte r8 = (byte) r8
                r3[r18] = r8
                int r8 = r11 >>> 16
                byte r8 = (byte) r8
                r3[r13] = r8
                int r8 = r11 >>> 24
                byte r8 = (byte) r8
                r3[r4] = r8
                r4 = r6 & 4
                int r4 = r4 * r12
                r6 = r6 ^ 4
                int r6 = r6 + r4
                int r4 = r3.length
                int r8 = r3.length
                int r8 = r8 % 4
                int r8 = 0 - r8
                int r10 = r8 * 3
                r11 = -4
                int r8 = m3.p.a(r8, r11, r9, r4)
                long r13 = (long) r6
                r4 = r4 & r12
                r4 = r4 | r8
                int r4 = m3.m.a(r1, r4, r10, r9)
                long r10 = (long) r4
                int r4 = (r13 > r10 ? 1 : (r13 == r10 ? 0 : -1))
                int r4 = r4 >>> 31
                r4 = r4 & r9
                if (r4 == 0) goto L_0x01bf
                r11 = -1605440657(0xffffffffa04eeb6f, float:-1.7526777E-19)
                goto L_0x01c1
            L_0x01bf:
                r11 = r16
            L_0x01c1:
                if (r4 == 0) goto L_0x01c6
                r4 = r11
                goto L_0x000c
            L_0x01c6:
                r4 = -169475207(0xfffffffff5e60379, float:-5.8315367E32)
                goto L_0x000c
            L_0x01cb:
                int r4 = r3.length
                int r7 = r4 % 4
                long r10 = (long) r7
                long r12 = (long) r9
                int r4 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
                int r4 = r4 >>> 31
                r4 = r4 & r9
                if (r4 == 0) goto L_0x00ad
            L_0x01d7:
                r4 = -458924450(0xffffffffe4a55e5e, float:-2.4404101E22)
                goto L_0x000c
            L_0x01dc:
                int r4 = r3.length
                int r8 = 0 - r5
                r9 = r4 & r8
                int r9 = r9 * r12
                r4 = r4 ^ r8
                int r4 = r4 + r9
                byte r9 = r2[r4]
                int r10 = r3.length
                int r8 = 0 - r8
                r11 = r8 | r10
                r10 = r10 ^ r8
                r10 = r10 ^ r11
                int r8 = m3.b0.a(r8, r12, r11, r10)
                byte r8 = r2[r8]
                r10 = r8 ^ r9
                byte r11 = (byte) r12
                r8 = r8 | r9
                byte r8 = (byte) r8
                int r11 = r11 * r8
                byte r8 = (byte) r11
                byte r9 = (byte) r10
                int r8 = r8 - r9
                byte r8 = (byte) r8
                r2[r4] = r8
                r4 = r13
                goto L_0x000c
            */
            throw new UnsupportedOperationException("Method not decompiled: m3.r.a.d(byte[], byte[]):void");
        }

        public static byte[] e(String str, byte b10, byte b11) {
            byte[] bytes = str == null ? new byte[]{0, 0, 0, 0, 0} : str.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            byte[] bArr = new byte[(length + 1)];
            for (int i10 = 0; i10 < length; i10++) {
                byte b12 = bytes[i10];
                b10 = (byte) (b10 ^ b12);
                bArr[i10] = (byte) (b12 ^ b11);
            }
            bArr[bytes.length] = (byte) (b10 ^ b11);
            return bArr;
        }

        public final f2 a() {
            if (this.f15079c.isEmpty()) {
                return null;
            }
            return (f2) this.f15079c.remove(0);
        }

        public void c(f2 f2Var) {
            this.f15079c.add(f2Var);
        }

        public final void f() {
            while (!this.f15079c.isEmpty()) {
                f2 a10 = a();
                if (a10 != null) {
                    g(a10);
                }
            }
        }

        public void g(f2 f2Var) {
            try {
                w2.b e10 = this.f15078b.e();
                String jSONObject = f2Var.c().toString();
                byte[] bArr = {-69, 72, -49, -19, -56, -29, 30};
                d(bArr, new byte[]{-32, -3, -72, 111, -87, -112, 123, -28});
                Charset charset = StandardCharsets.UTF_8;
                String intern = new String(bArr, charset).intern();
                byte[] bArr2 = {57, -107, 111, -109, 15};
                d(bArr2, new byte[]{116, -64, 35, -51, 104, 84, 0, 6});
                if (!intern.equals(new String(bArr2, charset).intern())) {
                    e10.getClass();
                    b(jSONObject, e10.c(), (String) null, (String) null, this.f15077a, f2Var.e());
                    return;
                }
                byte[] bArr3 = {4, 38, -18};
                d(bArr3, new byte[]{72, 105, -87, -91, 81, -27, 10, -4});
                new String(bArr3, charset).intern();
            } catch (JSONException unused) {
            }
        }
    }

    public r(o3 o3Var, q1 q1Var, String str, w2 w2Var, q0 q0Var) {
        boolean z10;
        this.f15072a = o3Var;
        if (str != null) {
            z10 = true;
        } else {
            z10 = false;
        }
        this.f15073b = z10;
        this.f15074c = q1Var;
        this.f15075d = w2Var;
        this.f15076e = new a(w2Var, str, q0Var);
    }

    public d0 a() {
        return this.f15072a.c();
    }

    public void b(Context context) {
        Set set;
        if (d()) {
            set = x3.f(context);
        } else {
            set = null;
        }
        Set set2 = set;
        o3 o3Var = this.f15072a;
        q1 q1Var = this.f15074c;
        d0 c10 = o3Var.c();
        this.f15075d.getClass();
        this.f15076e.c(new x3(o3Var, q1Var, c10, set2, (String) null, this.f15073b));
    }

    public void c(String str, List list, List list2) {
        o3 o3Var = this.f15072a;
        q1 q1Var = this.f15074c;
        d0 c10 = o3Var.c();
        this.f15075d.getClass();
        this.f15076e.g(new i0(o3Var, q1Var, str, list, list2, c10, (String) null, this.f15073b));
    }

    public boolean d() {
        this.f15075d.e().getClass();
        return false;
    }
}
