package s;

import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.security.identity.IdentityCredential;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
abstract class d {

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    private static class a {
        static KeyGenParameterSpec a(KeyGenParameterSpec.Builder builder) {
            return builder.build();
        }

        static KeyGenParameterSpec.Builder b(String str, int i10) {
            return new KeyGenParameterSpec.Builder(str, i10);
        }

        static void c(KeyGenerator keyGenerator, KeyGenParameterSpec keyGenParameterSpec) {
            keyGenerator.init(keyGenParameterSpec);
        }

        static void d(KeyGenParameterSpec.Builder builder) {
            builder.setBlockModes(new String[]{"CBC"});
        }

        static void e(KeyGenParameterSpec.Builder builder) {
            builder.setEncryptionPaddings(new String[]{"PKCS7Padding"});
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    private static class b {
        static BiometricPrompt.CryptoObject a(Signature signature) {
            return new BiometricPrompt.CryptoObject(signature);
        }

        static BiometricPrompt.CryptoObject b(Cipher cipher) {
            return new BiometricPrompt.CryptoObject(cipher);
        }

        static BiometricPrompt.CryptoObject c(Mac mac) {
            return new BiometricPrompt.CryptoObject(mac);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    private static class c {
        static BiometricPrompt.CryptoObject a(IdentityCredential identityCredential) {
            return new BiometricPrompt.CryptoObject(identityCredential);
        }
    }

    static c a() {
        try {
            KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
            instance.load((KeyStore.LoadStoreParameter) null);
            KeyGenParameterSpec.Builder b10 = a.b("androidxBiometric", 3);
            a.d(b10);
            a.e(b10);
            KeyGenerator instance2 = KeyGenerator.getInstance("AES", "AndroidKeyStore");
            a.c(instance2, a.a(b10));
            instance2.generateKey();
            Cipher instance3 = Cipher.getInstance("AES/CBC/PKCS7Padding");
            instance3.init(1, (SecretKey) instance.getKey("androidxBiometric", (char[]) null));
            return new c(instance3);
        } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | UnrecoverableKeyException | CertificateException | NoSuchPaddingException e10) {
            Log.w("CryptoObjectUtils", "Failed to create fake crypto object.", e10);
            return null;
        }
    }

    static BiometricPrompt.CryptoObject b(c cVar) {
        IdentityCredential b10;
        if (cVar == null) {
            return null;
        }
        Cipher a10 = cVar.a();
        if (a10 != null) {
            return b.b(a10);
        }
        Signature d10 = cVar.d();
        if (d10 != null) {
            return b.a(d10);
        }
        Mac c10 = cVar.c();
        if (c10 != null) {
            return b.c(c10);
        }
        if (Build.VERSION.SDK_INT < 30 || (b10 = cVar.b()) == null) {
            return null;
        }
        return c.a(b10);
    }
}
