package m3;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PublicKey;
import java.util.UUID;
import javax.crypto.Cipher;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public class a1 extends l2 {

    /* renamed from: p  reason: collision with root package name */
    public static final String f14730p;

    /* renamed from: q  reason: collision with root package name */
    public static final String f14731q;

    /* renamed from: r  reason: collision with root package name */
    public static final String f14732r;

    /* renamed from: n  reason: collision with root package name */
    public final d1 f14733n;

    /* renamed from: o  reason: collision with root package name */
    public final h1 f14734o;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final boolean f14735a;

        /* renamed from: b  reason: collision with root package name */
        public final boolean f14736b;

        public a(boolean z10, boolean z11) {
            this.f14735a = z10;
            this.f14736b = z11;
        }
    }

    static {
        byte[] bArr = {125, 14, 80, -66, 123, -124, 45, 77, -78, 105, -127, 111, -96, -78, 100, 90, 52, 21, -124, 69};
        k(bArr, new byte[]{47, 93, 17, -111, 62, -57, 111, 98, -30, 34, -62, 60, -111, -30, 5, 62, 80, 124, -22, 34});
        Charset charset = StandardCharsets.UTF_8;
        f14732r = new String(bArr, charset).intern();
        byte[] bArr2 = {40, 10, 26, -27, 24, 12, -19, 113, -75, -29, -1, -122, -117, -112, -70};
        k(bArr2, new byte[]{105, 100, 126, -105, 119, 101, -119, 58, -48, -102, -84, -14, -28, -30, -33});
        f14731q = new String(bArr2, charset).intern();
        byte[] bArr3 = {-92, 90, -100, 112, 92, 72, -43, -69, 10, 122, 126, -92, -114};
        k(bArr3, new byte[]{-16, 59, -16, 3, 57, 43, -105, -46, 100, 30, 23, -54, -23});
        f14730p = new String(bArr3, charset).intern();
    }

    public a1(r rVar, e4 e4Var, Context context, h1 h1Var) {
        super(rVar, e4Var);
        this.f14733n = new d1(context);
        this.f14734o = h1Var;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void k(byte[] r15, byte[] r16) {
        /*
            r0 = 0
            r1 = 0
            r2 = -1850458006(0xffffffff91b4406a, float:-2.843869E-28)
            r4 = r0
            r5 = r4
            r3 = r2
            r2 = r1
        L_0x0009:
            int r6 = ~r3
            r7 = 16777216(0x1000000, float:2.3509887E-38)
            r6 = r6 & r7
            r8 = -16777217(0xfffffffffeffffff, float:-1.7014117E38)
            r8 = r8 & r3
            int r8 = r8 * r6
            r6 = r3 | r7
            r7 = r7 & r3
            int r7 = r7 * r6
            int r7 = r7 + r8
            int r3 = r3 >>> 8
            int r6 = ~r7
            r6 = r6 | r3
            r7 = 1
            int r3 = r3 - r7
            int r3 = r3 - r6
            r6 = 2028104049(0x78e26971, float:3.6737428E34)
            int r6 = r6 - r3
            r8 = 2
            r3 = r3 & r8
            r3 = r3 | r6
            r6 = -1700147435(0xffffffff9aa9cf15, float:-7.023132E-23)
            int r6 = r6 - r3
            r3 = r6 | 1
            int r3 = r3 * r8
            int r6 = ~r6
            int r6 = r6 + r3
            r3 = -1363443157(0xffffffffaebb822b, float:-8.526898E-11)
            r3 = r3 ^ r6
            r6 = 614229416(0x249c65a8, float:6.782643E-17)
            r9 = 1985663266(0x765ad122, float:1.1095336E33)
            r10 = -1396193641(0xffffffffacc7c697, float:-5.677968E-12)
            switch(r3) {
                case -1940167324: goto L_0x008d;
                case -360299937: goto L_0x0073;
                case 399486784: goto L_0x0072;
                case 585276366: goto L_0x0064;
                case 1733787683: goto L_0x0040;
                default: goto L_0x003e;
            }
        L_0x003e:
            r3 = r10
            goto L_0x0009
        L_0x0040:
            byte r3 = r2[r4]
            byte r5 = r1[r4]
            byte r6 = (byte) r8
            r11 = r5 & r3
            byte r11 = (byte) r11
            int r6 = r6 * r11
            byte r5 = (byte) r5
            byte r3 = (byte) r3
            int r5 = r5 + r3
            byte r3 = (byte) r5
            byte r5 = (byte) r6
            int r3 = r3 - r5
            byte r3 = (byte) r3
            r2[r4] = r3
            r3 = r4 & 1
            int r3 = r3 * r8
            r5 = r4 ^ 1
            int r5 = r5 + r3
            long r11 = (long) r5
            int r3 = r2.length
            long r13 = (long) r3
            int r3 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            int r3 = r3 >>> 31
            r3 = r3 & r7
            if (r3 == 0) goto L_0x003e
            r3 = r9
            goto L_0x0009
        L_0x0064:
            int r1 = r15.length
            if (r1 > 0) goto L_0x0068
            r7 = r0
        L_0x0068:
            if (r7 == 0) goto L_0x006c
            r3 = r9
            goto L_0x006d
        L_0x006c:
            r3 = r10
        L_0x006d:
            r2 = r15
            r1 = r16
            r5 = r0
            goto L_0x0009
        L_0x0072:
            return
        L_0x0073:
            byte r3 = r1[r5]
            byte r3 = (byte) r3
            double r3 = (double) r3
            r8 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            int r3 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            r4 = -1
            if (r3 > r4) goto L_0x007f
            r7 = r0
        L_0x007f:
            if (r7 == 0) goto L_0x0082
            goto L_0x0085
        L_0x0082:
            r10 = 427928065(0x1981aa01, float:1.3406951E-23)
        L_0x0085:
            if (r7 == 0) goto L_0x0089
            r3 = r6
            goto L_0x008a
        L_0x0089:
            r3 = r10
        L_0x008a:
            r4 = r5
            goto L_0x0009
        L_0x008d:
            byte r3 = r1[r4]
            int r7 = ~r3
            byte r8 = (byte) r0
            byte r9 = (byte) r3
            int r8 = r8 - r9
            r7 = r7 & r8
            int r8 = ~r8
            r3 = r3 & r8
            byte r3 = (byte) r3
            byte r7 = (byte) r7
            int r3 = r3 - r7
            byte r3 = (byte) r3
            r1[r4] = r3
            r3 = r6
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: m3.a1.k(byte[], byte[]):void");
    }

    public final KeyStore.PrivateKeyEntry J(KeyStore keyStore) {
        try {
            byte[] bArr = {52, 118, 28, 108, -56, -8, -64, -39, 71, 20, 29, 15, -10};
            k(bArr, new byte[]{96, 23, 112, 31, -83, -101, -126, -80, 41, 112, 116, 97, -111});
            KeyStore.Entry entry = keyStore.getEntry(new String(bArr, StandardCharsets.UTF_8).intern(), (KeyStore.ProtectionParameter) null);
            if (entry instanceof KeyStore.PrivateKeyEntry) {
                return (KeyStore.PrivateKeyEntry) entry;
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public final void K(KeyStore keyStore, Context context) {
        try {
            byte[] bArr = {39, -65, 37, -60, -114, -15, -115, 110, 97, 111, 22, 61, -32};
            k(bArr, new byte[]{115, -34, 73, -73, -21, -110, -49, 7, 15, 11, Byte.MAX_VALUE, 83, -121});
            Charset charset = StandardCharsets.UTF_8;
            if (keyStore.containsAlias(new String(bArr, charset).intern())) {
                byte[] bArr2 = {-94, -127, 86, -55, -7, 24, -74, -15, 101, 80, -124, -8, -41};
                k(bArr2, new byte[]{-10, -32, 58, -70, -100, 123, -12, -104, 11, 52, -19, -106, -80});
                keyStore.deleteEntry(new String(bArr2, charset).intern());
            }
            byte[] bArr3 = {62, 68, -38};
            k(bArr3, new byte[]{108, 23, -101, 40, 89, -51, -16, 103});
            String intern = new String(bArr3, charset).intern();
            byte[] bArr4 = {84, 20, -20, 39, -118, 126, -65, -13, 12, -108, 89, -77, 82, 52, -39};
            k(bArr4, new byte[]{21, 122, -120, 85, -27, 23, -37, -72, 105, -19, 10, -57, 61, 70, -68});
            KeyPairGenerator instance = KeyPairGenerator.getInstance(intern, new String(bArr4, charset).intern());
            byte[] bArr5 = {-55, -117, 14, 44, -105, 36, -107, 116, -91, 101, -56, -3, 109};
            k(bArr5, new byte[]{-99, -22, 98, 95, -14, 71, -41, 29, -53, 1, -95, -109, 10});
            KeyGenParameterSpec.Builder keySize = new KeyGenParameterSpec.Builder(new String(bArr5, charset).intern(), 3).setKeySize(2048);
            byte[] bArr6 = {75, 58, -26, 53, -107, -36, -64, 3, 14, 34, -82, -41};
            k(bArr6, new byte[]{27, 113, -91, 102, -92, -116, -95, 103, 106, 75, -64, -80});
            instance.initialize(keySize.setEncryptionPaddings(new String[]{new String(bArr6, charset).intern()}).setUserAuthenticationRequired(false).build());
            PublicKey publicKey = instance.generateKeyPair().getPublic();
            byte[] bArr7 = {44, -56, -4, -83, -33, -20, -47, -111, -47, -74, 124, 41, 48};
            k(bArr7, new byte[]{120, -87, -112, -34, -70, -113, -109, -8, -65, -46, 21, 71, 87});
            if (keyStore.containsAlias(new String(bArr7, charset).intern())) {
                this.f14733n.b(publicKey);
            }
        } catch (Exception | NoSuchMethodError unused) {
        }
    }

    public final boolean L(PublicKey publicKey, KeyStore.PrivateKeyEntry privateKeyEntry, String str) {
        try {
            byte[] bArr = {30, 115, -76, 58, 0, 13, -103, -65, 84, -127, 21, 28, -22, 72, -18, -108, 14, 3, 34, -18};
            k(bArr, new byte[]{76, 32, -11, 21, 69, 78, -37, -112, 4, -54, 86, 79, -37, 24, -113, -16, 106, 106, 76, -119});
            Charset charset = StandardCharsets.UTF_8;
            Cipher instance = Cipher.getInstance(new String(bArr, charset).intern());
            instance.init(1, publicKey);
            byte[] doFinal = instance.doFinal(str.getBytes(charset));
            byte[] bArr2 = {88, -121, -101, -118, -115, -5, -25, 99, -117, 86, 7, -125, 92, 53, 67, 71, -13, -106, 84, 105};
            k(bArr2, new byte[]{10, -44, -38, -91, -56, -72, -91, 76, -37, 29, 68, -48, 109, 101, 34, 35, -105, -1, 58, 14});
            Cipher instance2 = Cipher.getInstance(new String(bArr2, charset).intern());
            instance2.init(2, privateKeyEntry.getPrivateKey());
            return str.equals(new String(instance2.doFinal(doFinal), charset));
        } catch (Exception unused) {
            return true;
        }
    }

    public final a M(Context context) {
        try {
            byte[] bArr = {63, 39, 88, -63, 120, -85, 12, 47, -101, 62, 74, -19, -3, 80, 90};
            k(bArr, new byte[]{126, 73, 60, -77, 23, -62, 104, 100, -2, 71, 25, -103, -110, 34, 63});
            Charset charset = StandardCharsets.UTF_8;
            KeyStore instance = KeyStore.getInstance(new String(bArr, charset).intern());
            instance.load((KeyStore.LoadStoreParameter) null);
            PublicKey f10 = this.f14733n.f();
            if (f10 != null) {
                byte[] bArr2 = {8, -15, 94, -39, -62, -59, 25, -31, -87, 1, 4, -107, -117};
                k(bArr2, new byte[]{92, -112, 50, -86, -89, -90, 91, -120, -57, 101, 109, -5, -20});
                if (!instance.containsAlias(new String(bArr2, charset).intern())) {
                    byte[] bArr3 = {99, 15, -99, -16, 65, -78, 97, 125, 86, -15, 120, -28, -113, -9, -104, -72, 60};
                    k(bArr3, new byte[]{7, 102, -7, -69, 36, -53, 50, 9, 57, -125, 29, -89, -25, -106, -10, -33, 89});
                    String intern = new String(bArr3, charset).intern();
                    byte[] bArr4 = {58, 88, 93, -20, -4, 53, 55, 125, -100, 57, 121, -116, 116, 70, 76};
                    k(bArr4, new byte[]{91, 52, 52, -115, -113, 21, 89, 18, -24, 25, 31, -29, 1, 40, 40});
                    A(intern, new String(bArr4, charset).intern());
                    K(instance, context);
                    return new a(false, true);
                }
                KeyStore.PrivateKeyEntry J = J(instance);
                if (J != null) {
                    if (L(f10, J, UUID.randomUUID().toString())) {
                        return new a(false, false);
                    }
                    byte[] bArr5 = {4, 4, 12, -84, -54, 0, -10, 0, 121, 67, -6, 102, -85, 34, 92, 78, -78};
                    k(bArr5, new byte[]{96, 109, 104, -25, -81, 121, -91, 116, 22, 49, -97, 37, -61, 67, 50, 41, -41});
                    String intern2 = new String(bArr5, charset).intern();
                    byte[] bArr6 = {14, 77, 112, -35, 5, 33, -125, -122, -57, 27, -100, -126, -124, -79, 64, -85};
                    k(bArr6, new byte[]{101, 40, 9, -83, 100, 72, -15, -90, -86, 114, -17, -17, -27, -59, 35, -61});
                    A(intern2, new String(bArr6, charset).intern());
                    return new a(true, false);
                }
            }
            K(instance, context);
            return new a(false, false);
        } catch (Exception unused) {
            return new a(false, false);
        }
    }

    public void N(Context context) {
        super.H(b(new z0(this, context)));
    }

    public final j0 O(Context context) {
        a M = M(context);
        return new j0(!P(), !M.f14735a, !M.f14736b);
    }

    public final boolean P() {
        String a10 = this.f14734o.a();
        if (a10 == null) {
            return false;
        }
        if (this.f14733n.d()) {
            String e10 = this.f14733n.e();
            if (!a10.equals(e10)) {
                byte[] bArr = {-53, 30, 58, 22, 88, -105, -8, -100, -55, -56, -6, -88, 98, 26, -12, -97, -124, -23};
                k(bArr, new byte[]{-81, 119, 94, 87, 54, -13, -118, -13, -96, -84, -77, -52, 33, 114, -107, -15, -29, -116});
                Charset charset = StandardCharsets.UTF_8;
                String intern = new String(bArr, charset).intern();
                StringBuilder sb2 = new StringBuilder();
                byte[] bArr2 = {68, 79, 34, -80, -84};
                k(bArr2, new byte[]{11, 35, 70, -118, -116, -94, -122, -11});
                sb2.append(new String(bArr2, charset).intern());
                sb2.append(e10);
                byte[] bArr3 = {117, -1, -48, -92, 45, 25, -115};
                k(bArr3, new byte[]{89, -33, -66, -63, 90, 35, -83, -37});
                sb2.append(new String(bArr3, charset).intern());
                sb2.append(a10);
                A(intern, sb2.toString());
                return true;
            }
        } else {
            this.f14733n.a(a10);
        }
        return false;
    }

    public /* bridge */ /* synthetic */ boolean c() {
        return false;
    }

    public void c(Context context) {
        N(context);
    }
}
