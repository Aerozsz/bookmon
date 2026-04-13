package org.apache.http.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;
import java.security.KeyStore;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.util.Args;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public class SSLContextBuilder {
    static final String TLS = "TLS";
    private String keyManagerFactoryAlgorithm = KeyManagerFactory.getDefaultAlgorithm();
    private final Set<KeyManager> keyManagers = new LinkedHashSet();
    private String keyStoreType = KeyStore.getDefaultType();
    private String protocol;
    private Provider provider;
    private SecureRandom secureRandom;
    private String trustManagerFactoryAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
    private final Set<TrustManager> trustManagers = new LinkedHashSet();

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    static class KeyManagerDelegate extends X509ExtendedKeyManager {
        private final PrivateKeyStrategy aliasStrategy;
        private final X509ExtendedKeyManager keyManager;

        KeyManagerDelegate(X509ExtendedKeyManager x509ExtendedKeyManager, PrivateKeyStrategy privateKeyStrategy) {
            this.keyManager = x509ExtendedKeyManager;
            this.aliasStrategy = privateKeyStrategy;
        }

        public String chooseClientAlias(String[] strArr, Principal[] principalArr, Socket socket) {
            return this.aliasStrategy.chooseAlias(getClientAliasMap(strArr, principalArr), socket);
        }

        public String chooseEngineClientAlias(String[] strArr, Principal[] principalArr, SSLEngine sSLEngine) {
            return this.aliasStrategy.chooseAlias(getClientAliasMap(strArr, principalArr), (Socket) null);
        }

        public String chooseEngineServerAlias(String str, Principal[] principalArr, SSLEngine sSLEngine) {
            return this.aliasStrategy.chooseAlias(getServerAliasMap(str, principalArr), (Socket) null);
        }

        public String chooseServerAlias(String str, Principal[] principalArr, Socket socket) {
            return this.aliasStrategy.chooseAlias(getServerAliasMap(str, principalArr), socket);
        }

        public X509Certificate[] getCertificateChain(String str) {
            return this.keyManager.getCertificateChain(str);
        }

        public Map<String, PrivateKeyDetails> getClientAliasMap(String[] strArr, Principal[] principalArr) {
            HashMap hashMap = new HashMap();
            for (String str : strArr) {
                String[] clientAliases = this.keyManager.getClientAliases(str, principalArr);
                if (clientAliases != null) {
                    for (String str2 : clientAliases) {
                        hashMap.put(str2, new PrivateKeyDetails(str, this.keyManager.getCertificateChain(str2)));
                    }
                }
            }
            return hashMap;
        }

        public String[] getClientAliases(String str, Principal[] principalArr) {
            return this.keyManager.getClientAliases(str, principalArr);
        }

        public PrivateKey getPrivateKey(String str) {
            return this.keyManager.getPrivateKey(str);
        }

        public Map<String, PrivateKeyDetails> getServerAliasMap(String str, Principal[] principalArr) {
            HashMap hashMap = new HashMap();
            String[] serverAliases = this.keyManager.getServerAliases(str, principalArr);
            if (serverAliases != null) {
                for (String str2 : serverAliases) {
                    hashMap.put(str2, new PrivateKeyDetails(str, this.keyManager.getCertificateChain(str2)));
                }
            }
            return hashMap;
        }

        public String[] getServerAliases(String str, Principal[] principalArr) {
            return this.keyManager.getServerAliases(str, principalArr);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    static class TrustManagerDelegate implements X509TrustManager {
        private final X509TrustManager trustManager;
        private final TrustStrategy trustStrategy;

        TrustManagerDelegate(X509TrustManager x509TrustManager, TrustStrategy trustStrategy2) {
            this.trustManager = x509TrustManager;
            this.trustStrategy = trustStrategy2;
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            this.trustManager.checkClientTrusted(x509CertificateArr, str);
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            if (!this.trustStrategy.isTrusted(x509CertificateArr, str)) {
                this.trustManager.checkServerTrusted(x509CertificateArr, str);
            }
        }

        public X509Certificate[] getAcceptedIssuers() {
            return this.trustManager.getAcceptedIssuers();
        }
    }

    public static SSLContextBuilder create() {
        return new SSLContextBuilder();
    }

    public SSLContext build() {
        SSLContext sSLContext;
        String str = this.protocol;
        if (str == null) {
            str = "TLS";
        }
        Provider provider2 = this.provider;
        if (provider2 != null) {
            sSLContext = SSLContext.getInstance(str, provider2);
        } else {
            sSLContext = SSLContext.getInstance(str);
        }
        initSSLContext(sSLContext, this.keyManagers, this.trustManagers, this.secureRandom);
        return sSLContext;
    }

    /* JADX WARNING: type inference failed for: r5v1, types: [java.lang.Object[]] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initSSLContext(javax.net.ssl.SSLContext r3, java.util.Collection<javax.net.ssl.KeyManager> r4, java.util.Collection<javax.net.ssl.TrustManager> r5, java.security.SecureRandom r6) {
        /*
            r2 = this;
            boolean r0 = r4.isEmpty()
            r1 = 0
            if (r0 != 0) goto L_0x0014
            int r0 = r4.size()
            javax.net.ssl.KeyManager[] r0 = new javax.net.ssl.KeyManager[r0]
            java.lang.Object[] r4 = r4.toArray(r0)
            javax.net.ssl.KeyManager[] r4 = (javax.net.ssl.KeyManager[]) r4
            goto L_0x0015
        L_0x0014:
            r4 = r1
        L_0x0015:
            boolean r0 = r5.isEmpty()
            if (r0 != 0) goto L_0x0028
            int r0 = r5.size()
            javax.net.ssl.TrustManager[] r0 = new javax.net.ssl.TrustManager[r0]
            java.lang.Object[] r5 = r5.toArray(r0)
            r1 = r5
            javax.net.ssl.TrustManager[] r1 = (javax.net.ssl.TrustManager[]) r1
        L_0x0028:
            r3.init(r4, r1, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.ssl.SSLContextBuilder.initSSLContext(javax.net.ssl.SSLContext, java.util.Collection, java.util.Collection, java.security.SecureRandom):void");
    }

    public SSLContextBuilder loadKeyMaterial(KeyStore keyStore, char[] cArr, PrivateKeyStrategy privateKeyStrategy) {
        String str = this.keyManagerFactoryAlgorithm;
        if (str == null) {
            str = KeyManagerFactory.getDefaultAlgorithm();
        }
        KeyManagerFactory instance = KeyManagerFactory.getInstance(str);
        instance.init(keyStore, cArr);
        KeyManager[] keyManagers2 = instance.getKeyManagers();
        if (keyManagers2 != null) {
            if (privateKeyStrategy != null) {
                for (int i10 = 0; i10 < keyManagers2.length; i10++) {
                    KeyManager keyManager = keyManagers2[i10];
                    if (keyManager instanceof X509ExtendedKeyManager) {
                        keyManagers2[i10] = new KeyManagerDelegate((X509ExtendedKeyManager) keyManager, privateKeyStrategy);
                    }
                }
            }
            Collections.addAll(this.keyManagers, keyManagers2);
        }
        return this;
    }

    public SSLContextBuilder loadTrustMaterial(KeyStore keyStore, TrustStrategy trustStrategy) {
        String str = this.trustManagerFactoryAlgorithm;
        if (str == null) {
            str = TrustManagerFactory.getDefaultAlgorithm();
        }
        TrustManagerFactory instance = TrustManagerFactory.getInstance(str);
        instance.init(keyStore);
        TrustManager[] trustManagers2 = instance.getTrustManagers();
        if (trustManagers2 != null) {
            if (trustStrategy != null) {
                for (int i10 = 0; i10 < trustManagers2.length; i10++) {
                    TrustManager trustManager = trustManagers2[i10];
                    if (trustManager instanceof X509TrustManager) {
                        trustManagers2[i10] = new TrustManagerDelegate((X509TrustManager) trustManager, trustStrategy);
                    }
                }
            }
            Collections.addAll(this.trustManagers, trustManagers2);
        }
        return this;
    }

    public SSLContextBuilder setKeyManagerFactoryAlgorithm(String str) {
        this.keyManagerFactoryAlgorithm = str;
        return this;
    }

    public SSLContextBuilder setKeyStoreType(String str) {
        this.keyStoreType = str;
        return this;
    }

    public SSLContextBuilder setProtocol(String str) {
        this.protocol = str;
        return this;
    }

    public SSLContextBuilder setProvider(Provider provider2) {
        this.provider = provider2;
        return this;
    }

    public SSLContextBuilder setSecureRandom(SecureRandom secureRandom2) {
        this.secureRandom = secureRandom2;
        return this;
    }

    public SSLContextBuilder setTrustManagerFactoryAlgorithm(String str) {
        this.trustManagerFactoryAlgorithm = str;
        return this;
    }

    public String toString() {
        return "[provider=" + this.provider + ", protocol=" + this.protocol + ", keyStoreType=" + this.keyStoreType + ", keyManagerFactoryAlgorithm=" + this.keyManagerFactoryAlgorithm + ", keyManagers=" + this.keyManagers + ", trustManagerFactoryAlgorithm=" + this.trustManagerFactoryAlgorithm + ", trustManagers=" + this.trustManagers + ", secureRandom=" + this.secureRandom + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }

    @Deprecated
    public SSLContextBuilder useProtocol(String str) {
        this.protocol = str;
        return this;
    }

    public SSLContextBuilder setProvider(String str) {
        this.provider = Security.getProvider(str);
        return this;
    }

    public SSLContextBuilder loadKeyMaterial(KeyStore keyStore, char[] cArr) {
        return loadKeyMaterial(keyStore, cArr, (PrivateKeyStrategy) null);
    }

    public SSLContextBuilder loadTrustMaterial(TrustStrategy trustStrategy) {
        return loadTrustMaterial((KeyStore) null, trustStrategy);
    }

    /* JADX INFO: finally extract failed */
    public SSLContextBuilder loadKeyMaterial(File file, char[] cArr, char[] cArr2, PrivateKeyStrategy privateKeyStrategy) {
        Args.notNull(file, "Keystore file");
        KeyStore instance = KeyStore.getInstance(this.keyStoreType);
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            instance.load(fileInputStream, cArr);
            fileInputStream.close();
            return loadKeyMaterial(instance, cArr2, privateKeyStrategy);
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public SSLContextBuilder loadTrustMaterial(File file, char[] cArr, TrustStrategy trustStrategy) {
        Args.notNull(file, "Truststore file");
        KeyStore instance = KeyStore.getInstance(this.keyStoreType);
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            instance.load(fileInputStream, cArr);
            fileInputStream.close();
            return loadTrustMaterial(instance, trustStrategy);
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    public SSLContextBuilder loadKeyMaterial(File file, char[] cArr, char[] cArr2) {
        return loadKeyMaterial(file, cArr, cArr2, (PrivateKeyStrategy) null);
    }

    public SSLContextBuilder loadTrustMaterial(File file, char[] cArr) {
        return loadTrustMaterial(file, cArr, (TrustStrategy) null);
    }

    /* JADX INFO: finally extract failed */
    public SSLContextBuilder loadKeyMaterial(URL url, char[] cArr, char[] cArr2, PrivateKeyStrategy privateKeyStrategy) {
        Args.notNull(url, "Keystore URL");
        KeyStore instance = KeyStore.getInstance(this.keyStoreType);
        InputStream openStream = url.openStream();
        try {
            instance.load(openStream, cArr);
            openStream.close();
            return loadKeyMaterial(instance, cArr2, privateKeyStrategy);
        } catch (Throwable th) {
            openStream.close();
            throw th;
        }
    }

    public SSLContextBuilder loadTrustMaterial(File file) {
        return loadTrustMaterial(file, (char[]) null);
    }

    /* JADX INFO: finally extract failed */
    public SSLContextBuilder loadTrustMaterial(URL url, char[] cArr, TrustStrategy trustStrategy) {
        Args.notNull(url, "Truststore URL");
        KeyStore instance = KeyStore.getInstance(this.keyStoreType);
        InputStream openStream = url.openStream();
        try {
            instance.load(openStream, cArr);
            openStream.close();
            return loadTrustMaterial(instance, trustStrategy);
        } catch (Throwable th) {
            openStream.close();
            throw th;
        }
    }

    public SSLContextBuilder loadKeyMaterial(URL url, char[] cArr, char[] cArr2) {
        return loadKeyMaterial(url, cArr, cArr2, (PrivateKeyStrategy) null);
    }

    public SSLContextBuilder loadTrustMaterial(URL url, char[] cArr) {
        return loadTrustMaterial(url, cArr, (TrustStrategy) null);
    }
}
