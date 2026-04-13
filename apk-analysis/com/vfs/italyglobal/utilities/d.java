package com.vfs.italyglobal.utilities;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Base64;
import bc.w;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Map;
import kc.l;
import kc.n;
import nb.u;
import ob.g0;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    public static final d f9508a = new d();

    private d() {
    }

    private static final int c(byte[] bArr, w wVar, w wVar2, int i10) {
        byte b10 = bArr[wVar.f4724b];
        int i11 = wVar2.f4724b;
        int i12 = (b10 >> (7 - i11)) & 1;
        int i13 = i11 + 1;
        wVar2.f4724b = i13;
        if (i13 == 8) {
            wVar2.f4724b = 0;
            wVar.f4724b++;
        }
        return i12 | (i10 & 254);
    }

    private final PrivateKey d(String str) {
        PrivateKey generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(new l("\\s").c(n.G(n.G(str, "-----BEGIN PRIVATE KEY-----", "", false, 4, (Object) null), "-----END PRIVATE KEY-----", "", false, 4, (Object) null), ""), 0)));
        bc.n.d(generatePrivate, "generatePrivate(...)");
        return generatePrivate;
    }

    public final String a(Bitmap bitmap) {
        bc.n.e(bitmap, "bitmap");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        bc.n.d(encodeToString, "encodeToString(...)");
        return encodeToString;
    }

    public final Bitmap b(Bitmap bitmap, String str) {
        bc.n.e(bitmap, "inputBitmap");
        bc.n.e(str, "message");
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        byte[] bytes = str.getBytes(kc.d.f14093b);
        bc.n.d(bytes, "getBytes(...)");
        w wVar = new w();
        w wVar2 = new w();
        int height = copy.getHeight();
        loop0:
        for (int i10 = 0; i10 < height; i10++) {
            int width = copy.getWidth();
            for (int i11 = 0; i11 < width; i11++) {
                if (wVar.f4724b >= bytes.length) {
                    break loop0;
                }
                bc.n.b(copy);
                int pixel = copy.getPixel(i11, i10);
                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);
                int c10 = c(bytes, wVar, wVar2, red);
                if (wVar.f4724b < bytes.length) {
                    green = c(bytes, wVar, wVar2, green);
                }
                if (wVar.f4724b < bytes.length) {
                    blue = c(bytes, wVar, wVar2, blue);
                }
                copy.setPixel(i11, i10, Color.rgb(c10, green, blue));
            }
        }
        bc.n.b(copy);
        return copy;
    }

    public final Map e(Bitmap bitmap, String str, String str2) {
        bc.n.e(bitmap, "bitmap");
        bc.n.e(str, "watermarkText");
        bc.n.e(str2, "keyParameter");
        String a10 = a(b(bitmap, str));
        return g0.i(u.a("imageData", a10), u.a("signatureData", f(a10, d(str2))));
    }

    public final String f(String str, PrivateKey privateKey) {
        bc.n.e(str, "base64String");
        bc.n.e(privateKey, "privateKey");
        Signature instance = Signature.getInstance("SHA256withRSA");
        instance.initSign(privateKey);
        byte[] bytes = str.getBytes(kc.d.f14093b);
        bc.n.d(bytes, "getBytes(...)");
        instance.update(bytes);
        String encodeToString = Base64.encodeToString(instance.sign(), 2);
        bc.n.d(encodeToString, "encodeToString(...)");
        return encodeToString;
    }
}
