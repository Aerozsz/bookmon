package com.vfs.italyglobal.utilities;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import bc.h;
import bc.n;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.utilities.g;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import kc.d;
import kc.l;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class k {

    /* renamed from: c  reason: collision with root package name */
    public static final a f9521c = new a((h) null);

    /* renamed from: a  reason: collision with root package name */
    private PublicKey f9522a;

    /* renamed from: b  reason: collision with root package name */
    private PrivateKey f9523b;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a {
        public /* synthetic */ a(h hVar) {
            this();
        }

        private a() {
        }
    }

    private final KeyStore a() {
        KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
        instance.load((KeyStore.LoadStoreParameter) null);
        n.b(instance);
        return instance;
    }

    private final void e() {
        if (!a().containsAlias("Keyalaisras")) {
            KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
            KeyGenParameterSpec build = new KeyGenParameterSpec.Builder("Keyalaisras", 6).setKeySize(2048).setDigests(new String[]{MessageDigestAlgorithms.SHA_256, MessageDigestAlgorithms.SHA_512}).setSignaturePaddings(new String[]{"PSS"}).setEncryptionPaddings(new String[]{"OAEPPadding"}).build();
            n.d(build, "build(...)");
            instance.initialize(build);
            instance.generateKeyPair();
        }
    }

    private final Cipher f(int i10, Key key) {
        Cipher instance = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        instance.init(i10, key, new OAEPParameterSpec(MessageDigestAlgorithms.SHA_256, "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
        n.b(instance);
        return instance;
    }

    private final PublicKey g(String str) {
        PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(new l("\\s").c(kc.n.G(kc.n.G(str, "-----BEGIN PUBLIC KEY-----", "", false, 4, (Object) null), "-----END PUBLIC KEY-----", "", false, 4, (Object) null), ""), 2)));
        n.d(generatePublic, "generatePublic(...)");
        return generatePublic;
    }

    public final String b(Context context, String str) {
        n.e(context, "context");
        n.e(str, "data");
        c(context);
        PublicKey publicKey = this.f9522a;
        if (publicKey != null) {
            Cipher f10 = f(1, publicKey);
            byte[] bytes = str.getBytes(d.f14093b);
            n.d(bytes, "getBytes(...)");
            String encodeToString = Base64.encodeToString(f10.doFinal(bytes), 2);
            n.d(encodeToString, "encodeToString(...)");
            return encodeToString;
        }
        throw new IllegalStateException("API public key not initialized");
    }

    public final void c(Context context) {
        n.e(context, "context");
        if (this.f9522a == null) {
            g.a aVar = g.f9512a;
            String string = context.getString(R.string.api_public_key);
            n.d(string, "getString(...)");
            String b10 = aVar.b(context, string, "");
            if (b10.length() > 0) {
                d(context, b10);
            }
        }
    }

    public final void d(Context context, String str) {
        n.e(context, "context");
        e();
        this.f9523b = (PrivateKey) a().getKey("Keyalaisras", (char[]) null);
        if (str == null) {
            str = context.getString(R.string.public_key);
            n.d(str, "getString(...)");
        }
        this.f9522a = g(str);
    }
}
