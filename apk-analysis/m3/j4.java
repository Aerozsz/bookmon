package m3;

import android.security.keystore.KeyInfo;
import android.security.keystore.KeyProtection;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.ProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import m3.g4;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public class j4 extends g4 {

    /* renamed from: d  reason: collision with root package name */
    public final a f14964d;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static class a extends g4.a {

        /* renamed from: c  reason: collision with root package name */
        public final int f14965c;

        /* renamed from: d  reason: collision with root package name */
        public final int f14966d;

        /* renamed from: e  reason: collision with root package name */
        public final String f14967e;

        /* renamed from: f  reason: collision with root package name */
        public final String f14968f;

        public a(String str, int i10, String str2, int i11, String str3, String str4) {
            super(str, str2);
            if (i10 > 0) {
                this.f14965c = i10;
                this.f14966d = i11;
                this.f14967e = str3;
                this.f14968f = str4;
                return;
            }
            byte[] bArr = {-27, -109, -87, 53, 34, -112, -72, 4, 21, -9, -30, 97, -113, 108, 125, 63, 79, -49, 46, 101, -79, 8, 98, 93, 35, 38, 96, 25, 61, -19, 91, 2, -119, -68, 10};
            c(bArr, new byte[]{-27, -28, 117, -1, -78, 21, 10, 23, -40, -106, 53, -87, 19, 55, -102, -46, -121, -100, -62, -94, 117, -111, -95, -38, -90, 103, -73, 9, -63, -82, -91, 5, -25, -100, 58});
            throw new IllegalArgumentException(new String(bArr, StandardCharsets.UTF_8).intern());
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
            throw new UnsupportedOperationException("Method not decompiled: m3.j4.a.c(byte[], byte[]):void");
        }

        public String d() {
            return this.f14967e;
        }

        public int e() {
            return this.f14965c;
        }

        public String f() {
            return this.f14968f;
        }

        public int g() {
            return this.f14966d;
        }
    }

    public j4(a aVar, KeyStore keyStore) {
        super(aVar, keyStore);
        this.f14964d = aVar;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00a2, code lost:
        if (r4 != 0) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00e5, code lost:
        if (r4 != 0) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00e9, code lost:
        r4 = -1138188205;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0040, code lost:
        r4 = r15;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void u(byte[] r21, byte[] r22) {
        /*
            r0 = r21
            r1 = 0
            r2 = 0
            r3 = 1180709023(0x4660309f, float:14348.155)
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
            int r8 = -1 - r4
            int r11 = -1 - r12
            r8 = r8 | r11
            r11 = 1
            int r4 = m3.l.a(r4, r12, r11, r8)
            r8 = -201803027(0xfffffffff3f8baed, float:-3.941287E31)
            r12 = r4 & r8
            r13 = 2
            int r12 = r12 * r13
            r4 = r4 ^ r8
            int r4 = r4 + r12
            r8 = -814310662(0xffffffffcf769afa, float:-4.13735168E9)
            r12 = r4 & r8
            int r12 = r12 * r13
            int r4 = r4 + r8
            int r4 = r4 - r12
            r15 = 1621215041(0x60a1c741, float:9.325886E19)
            r8 = 4
            switch(r4) {
                case -2000520841: goto L_0x01d6;
                case -870579640: goto L_0x00ee;
                case -97532338: goto L_0x00d6;
                case 298177592: goto L_0x00a6;
                case 373627814: goto L_0x00a5;
                case 975213712: goto L_0x005d;
                case 1548321255: goto L_0x0042;
                default: goto L_0x0040;
            }
        L_0x0040:
            r4 = r15
            goto L_0x000c
        L_0x0042:
            int r2 = r0.length
            int r3 = r0.length
            int r3 = r3 % r8
            int r3 = 0 - r3
            int r4 = ~r2
            int r3 = 0 - r3
            r4 = r4 & r3
            int r3 = ~r3
            r2 = r2 & r3
            int r2 = r2 - r4
            if (r2 > 0) goto L_0x0051
            r11 = r1
        L_0x0051:
            if (r11 == 0) goto L_0x0057
            r4 = 1910359311(0x71ddc50f, float:2.1963013E30)
            goto L_0x0058
        L_0x0057:
            r4 = r15
        L_0x0058:
            r2 = r22
            r3 = r0
            r6 = r1
            goto L_0x000c
        L_0x005d:
            int r4 = r3.length
            int r5 = 0 - r7
            r8 = r4 ^ r5
            int r9 = r3.length
            r10 = -656070458(0xffffffffd8e528c6, float:-2.01570627E15)
            r14 = r5 | r10
            r14 = r14 & r9
            r16 = r10
            int r10 = ~r5
            r16 = r10 & r16
            r16 = r16 & r9
            r9 = r9 | r5
            int r9 = r9 - r16
            int r9 = r9 + r14
            byte r9 = r3[r9]
            int r14 = r3.length
            r16 = r14 | r5
            int r16 = r16 * 2
            r10 = r10 ^ r14
            int r10 = r10 + r16
            int r10 = r10 + r11
            byte r10 = r2[r10]
            byte r14 = (byte) r13
            int r12 = ~r10
            r12 = r12 & r9
            byte r12 = (byte) r12
            int r14 = r14 * r12
            r4 = r4 | r5
            int r4 = r4 * r13
            int r4 = r4 - r8
            byte r5 = (byte) r10
            byte r8 = (byte) r9
            int r5 = r5 - r8
            byte r5 = (byte) r5
            byte r8 = (byte) r14
            int r5 = r5 + r8
            byte r5 = (byte) r5
            r3[r4] = r5
            int r4 = r7 * 2
            int r5 = ~r7
            int r5 = r5 + r4
            long r8 = (long) r7
            long r12 = (long) r13
            int r4 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            int r4 = r4 >>> 31
            r4 = r4 & r11
            if (r4 == 0) goto L_0x00a2
            r15 = 986083301(0x3ac66fe5, float:0.0015139548)
        L_0x00a2:
            if (r4 == 0) goto L_0x00e9
            goto L_0x0040
        L_0x00a5:
            return
        L_0x00a6:
            int r4 = r3.length
            int r8 = 0 - r7
            int r9 = r8 * 3
            r10 = -4
            int r10 = m3.p.a(r8, r10, r11, r4)
            r4 = r4 & r13
            r4 = r4 | r10
            int r4 = m3.m.a(r1, r4, r9, r11)
            byte r9 = r2[r4]
            int r10 = r3.length
            int r8 = 0 - r8
            r11 = r8 | r10
            r10 = r10 ^ r8
            r10 = r10 ^ r11
            int r8 = m3.b0.a(r8, r13, r11, r10)
            byte r8 = r2[r8]
            byte r10 = (byte) r13
            r11 = r8 & r9
            byte r11 = (byte) r11
            int r10 = r10 * r11
            r8 = r8 ^ r9
            byte r8 = (byte) r8
            byte r9 = (byte) r10
            int r8 = r8 + r9
            byte r8 = (byte) r8
            r2[r4] = r8
            r4 = 1565752577(0x5d537d01, float:9.5245861E17)
            goto L_0x000c
        L_0x00d6:
            int r4 = r3.length
            int r5 = r4 % 4
            long r8 = (long) r5
            long r12 = (long) r11
            int r4 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            int r4 = r4 >>> 31
            r4 = r4 & r11
            if (r4 == 0) goto L_0x00e5
            r15 = 986083301(0x3ac66fe5, float:0.0015139548)
        L_0x00e5:
            if (r4 == 0) goto L_0x00e9
            goto L_0x0040
        L_0x00e9:
            r4 = -1138188205(0xffffffffbc28a053, float:-0.0102921305)
            goto L_0x000c
        L_0x00ee:
            r4 = r6 | -4
            int r12 = r6 + -1
            int r12 = r12 - r4
            byte r4 = r2[r12]
            byte r4 = (byte) r4
            r17 = r9
            int r9 = ~r4
            r9 = r9 & r17
            r16 = r4 & r10
            int r16 = r16 * r9
            r9 = r4 | r17
            r4 = r4 & r17
            int r4 = r4 * r9
            int r4 = r4 + r16
            int r9 = -1 - r6
            r9 = r9 | -3
            int r16 = r6 + 3
            int r16 = r16 + r9
            byte r9 = r2[r16]
            r9 = r9 & 255(0xff, float:3.57E-43)
            r18 = r10
            int r10 = ~r9
            r19 = 65536(0x10000, float:9.18355E-41)
            r10 = r10 & r19
            int r9 = r9 * r10
            r10 = -1268032266(0xffffffffb46b5cf6, float:-2.19199E-7)
            r20 = r9 & r10
            r20 = r20 | r4
            int r9 = ~r9
            r9 = r9 | r10
            r4 = r4 | r9
            int r4 = r4 - r20
            int r4 = ~r4
            r9 = -132004403(0xfffffffff821c5cd, float:-1.3124557E34)
            r9 = r9 & r6
            r10 = -132004404(0xfffffffff821c5cc, float:-1.3124556E34)
            r10 = r10 & r6
            int r9 = m3.y.a(r10, r6, r11, r9)
            byte r10 = r2[r9]
            r10 = r10 & 255(0xff, float:3.57E-43)
            r20 = r13
            int r13 = ~r10
            r13 = r13 & 256(0x100, float:3.59E-43)
            int r10 = r10 * r13
            r13 = r10 & r4
            int r10 = r10 + r4
            int r10 = r10 - r13
            byte r4 = r2[r6]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r13 = ~r4
            r10 = r10 & r13
            int r10 = r10 + r4
            byte r4 = r3[r12]
            byte r4 = (byte) r4
            int r13 = ~r4
            r13 = r13 & r17
            r18 = r4 & r18
            int r18 = r18 * r13
            r13 = r4 | r17
            r4 = r4 & r17
            int r4 = r4 * r13
            int r4 = r4 + r18
            byte r13 = r3[r16]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r14 = ~r13
            r14 = r14 & r19
            int r13 = r13 * r14
            r14 = -1355861741(0xffffffffaf2f3113, float:-1.5933592E-10)
            r14 = r14 & r13
            r14 = r14 | r4
            int r13 = ~r13
            r18 = -1355861741(0xffffffffaf2f3113, float:-1.5933592E-10)
            r13 = r13 | r18
            r4 = r4 | r13
            int r4 = r4 - r14
            int r4 = ~r4
            byte r13 = r3[r9]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r14 = ~r13
            r14 = r14 & 256(0x100, float:3.59E-43)
            int r13 = r13 * r14
            int r14 = -1 - r13
            int r18 = -1 - r4
            r14 = r14 | r18
            int r4 = m3.l.a(r13, r4, r11, r14)
            byte r13 = r3[r6]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = ~r13
            r13 = r13 | r4
            int r4 = r4 - r11
            int r4 = r4 - r13
            double r13 = (double) r10
            r18 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            int r13 = (r13 > r18 ? 1 : (r13 == r18 ? 0 : -1))
            int r13 = r13 >>> 31
            int r10 = r10 << r13
            r13 = -418000873(0xffffffffe715d017, float:-7.074712E23)
            r13 = r13 & r10
            int r13 = r13 * 2
            r14 = -418000873(0xffffffffe715d017, float:-7.074712E23)
            r10 = r10 ^ r14
            int r10 = r10 + r13
            r13 = r10 & r4
            int r13 = r13 * 2
            int r10 = r10 + r4
            int r10 = r10 - r13
            byte r4 = (byte) r10
            r3[r6] = r4
            int r4 = r10 >>> 8
            byte r4 = (byte) r4
            r3[r9] = r4
            int r4 = r10 >>> 16
            byte r4 = (byte) r4
            r3[r16] = r4
            int r4 = r10 >>> 24
            byte r4 = (byte) r4
            r3[r12] = r4
            r4 = r6 & 4
            int r4 = r4 * 2
            r6 = r6 ^ 4
            int r6 = r6 + r4
            int r4 = r3.length
            int r9 = r3.length
            int r8 = m3.c0.a(r9, r8, r1, r1)
            r9 = r4 ^ r8
            long r12 = (long) r6
            int r8 = ~r8
            r4 = r4 & r8
            int r4 = r4 * 2
            int r4 = r4 - r9
            long r8 = (long) r4
            int r4 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
            int r4 = r4 >>> 31
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0040
            r4 = 1910359311(0x71ddc50f, float:2.1963013E30)
            goto L_0x000c
        L_0x01d6:
            r20 = r13
            int r4 = r3.length
            int r7 = 0 - r5
            int r7 = 0 - r7
            r8 = r4 ^ r7
            int r7 = ~r7
            r4 = r4 & r7
            int r4 = r4 * 2
            int r4 = r4 - r8
            byte r4 = r2[r4]
            byte r4 = (byte) r4
            double r7 = (double) r4
            r9 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            r7 = -1
            if (r4 > r7) goto L_0x01f0
            r11 = r1
        L_0x01f0:
            if (r11 == 0) goto L_0x01f6
            r8 = 1565752577(0x5d537d01, float:9.5245861E17)
            goto L_0x01f7
        L_0x01f6:
            r8 = r15
        L_0x01f7:
            if (r11 == 0) goto L_0x01fb
            r4 = r8
            goto L_0x01fe
        L_0x01fb:
            r4 = -1164716566(0xffffffffba93d5ea, float:-0.0011278961)
        L_0x01fe:
            r7 = r5
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: m3.j4.u(byte[], byte[]):void");
    }

    public boolean b() {
        return v().isInsideSecureHardware();
    }

    public Key c(KeyStore.Entry entry) {
        return ((KeyStore.SecretKeyEntry) entry).getSecretKey();
    }

    public KeyStore.Entry e(Date date) {
        return new KeyStore.SecretKeyEntry(new SecretKeySpec(new byte[this.f14964d.e()], this.f14964d.a()));
    }

    public KeyStore.ProtectionParameter k(Date date) {
        try {
            return new KeyProtection.Builder(this.f14964d.g()).setBlockModes(new String[]{this.f14964d.d()}).setEncryptionPaddings(new String[]{this.f14964d.f()}).setUserAuthenticationRequired(false).setUserAuthenticationValidityDurationSeconds(Integer.MAX_VALUE).build();
        } catch (NoSuchMethodError unused) {
            byte[] bArr = {-88, -67, 37, -31, -98, -91, 32, -90, 3, 66, -90, -120, 81, 119, 43, 88, 70, -4, 120, 5, -21, -95, 47, 53, -20, -40, 72, 40, -60, 74, -8, 41, -68, 125, 57, -8, -69, -68, 99, -96, -110, -25, 24, 97};
            u(bArr, new byte[]{-44, 12, 54, -88, -38, 1, -21, -35, 90, 87, -79, 21, 29, -121, 74, 86, 40, -33, -10, -126, -126, -12, -7, 93, -121, -25, 38, 102, -112, 110, 123, 95, -69, -115, 51, -78, -78, 13, -7, -35, -49, -78, 84, 104});
            throw new t1(-7780, new String(bArr, StandardCharsets.UTF_8).intern());
        }
    }

    public void m(Key key) {
        byte[] bArr = {-43, 40, -90, -105, -118, -40, 102, 85, 29, 60, -69, 82, 78, 15, -2, -99, -96};
        u(bArr, new byte[]{125, -99, -33, -47, -78, -54, 15, -109, 60, -125, -43, 76, 19, -101, -127, 12, -57});
        Cipher.getInstance(new String(bArr, StandardCharsets.UTF_8).intern()).init(1, key);
    }

    public final KeyInfo s(SecretKey secretKey) {
        try {
            String algorithm = secretKey.getAlgorithm();
            byte[] bArr = {25, -70, -120, -90, 63, 77, -62, -92, 87, -91, 36, 37, -105, -16, 111};
            u(bArr, new byte[]{65, 4, -41, -19, 57, 84, -112, 8, 27, 12, 98, 106, -8, -126, 10});
            return (KeyInfo) SecretKeyFactory.getInstance(algorithm, new String(bArr, StandardCharsets.UTF_8).intern()).getKeySpec(secretKey, KeyInfo.class);
        } catch (InvalidKeySpecException e10) {
            throw super.f(e10);
        } catch (NoSuchAlgorithmException | NoSuchProviderException | ProviderException e11) {
            throw super.l(e11);
        }
    }

    public final void t(Key key, KeyInfo keyInfo) {
        int purposes = keyInfo.getPurposes();
        int keySize = keyInfo.getKeySize() / 8;
        String algorithm = key.getAlgorithm();
        if (purposes != this.f14964d.g() || keySize != this.f14964d.e() || !this.f14964d.a().equals(algorithm)) {
            throw new t1(-7779, (String) null);
        }
    }

    public KeyInfo v() {
        Key d10 = d();
        KeyInfo s10 = s((SecretKey) d10);
        t(d10, s10);
        return s10;
    }
}
