package org.apache.http.conn.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.security.KeyStore;
import java.security.SecureRandom;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpInetSocketAddress;
import org.apache.http.conn.scheme.HostNameResolver;
import org.apache.http.conn.scheme.LayeredSchemeSocketFactory;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.SchemeLayeredSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;
import org.apache.http.util.TextUtils;

@Contract(threading = ThreadingBehavior.SAFE_CONDITIONAL)
@Deprecated
/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public class SSLSocketFactory implements LayeredConnectionSocketFactory, SchemeLayeredSocketFactory, LayeredSchemeSocketFactory, LayeredSocketFactory {
    public static final X509HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER = new AllowAllHostnameVerifier();
    public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = new BrowserCompatHostnameVerifier();
    public static final String SSL = "SSL";
    public static final String SSLV2 = "SSLv2";
    public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER = new StrictHostnameVerifier();
    public static final String TLS = "TLS";
    private volatile X509HostnameVerifier hostnameVerifier;
    private final HostNameResolver nameResolver;
    private final javax.net.ssl.SSLSocketFactory socketfactory;
    private final String[] supportedCipherSuites;
    private final String[] supportedProtocols;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SSLSocketFactory(String str, KeyStore keyStore, String str2, KeyStore keyStore2, SecureRandom secureRandom, HostNameResolver hostNameResolver) {
        this(SSLContexts.custom().useProtocol(str).setSecureRandom(secureRandom).loadKeyMaterial(keyStore, str2 != null ? str2.toCharArray() : null).loadTrustMaterial(keyStore2).build(), hostNameResolver);
    }

    public static SSLSocketFactory getSocketFactory() {
        return new SSLSocketFactory(SSLContexts.createDefault(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public static SSLSocketFactory getSystemSocketFactory() {
        return new SSLSocketFactory((javax.net.ssl.SSLSocketFactory) javax.net.ssl.SSLSocketFactory.getDefault(), split(System.getProperty("https.protocols")), split(System.getProperty("https.cipherSuites")), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    private void internalPrepareSocket(SSLSocket sSLSocket) {
        String[] strArr = this.supportedProtocols;
        if (strArr != null) {
            sSLSocket.setEnabledProtocols(strArr);
        }
        String[] strArr2 = this.supportedCipherSuites;
        if (strArr2 != null) {
            sSLSocket.setEnabledCipherSuites(strArr2);
        }
        prepareSocket(sSLSocket);
    }

    private static String[] split(String str) {
        if (TextUtils.isBlank(str)) {
            return null;
        }
        return str.split(" *, *");
    }

    private void verifyHostname(SSLSocket sSLSocket, String str) {
        try {
            this.hostnameVerifier.verify(str, sSLSocket);
        } catch (IOException e10) {
            try {
                sSLSocket.close();
            } catch (Exception unused) {
            }
            throw e10;
        }
    }

    public Socket connectSocket(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpParams httpParams) {
        HttpHost httpHost;
        Args.notNull(inetSocketAddress, "Remote address");
        Args.notNull(httpParams, "HTTP parameters");
        if (inetSocketAddress instanceof HttpInetSocketAddress) {
            httpHost = ((HttpInetSocketAddress) inetSocketAddress).getHttpHost();
        } else {
            httpHost = new HttpHost(inetSocketAddress.getHostName(), inetSocketAddress.getPort(), "https");
        }
        HttpHost httpHost2 = httpHost;
        int soTimeout = HttpConnectionParams.getSoTimeout(httpParams);
        int connectionTimeout = HttpConnectionParams.getConnectionTimeout(httpParams);
        socket.setSoTimeout(soTimeout);
        return connectSocket(connectionTimeout, socket, httpHost2, inetSocketAddress, inetSocketAddress2, (HttpContext) null);
    }

    public Socket createLayeredSocket(Socket socket, String str, int i10, HttpParams httpParams) {
        return createLayeredSocket(socket, str, i10, (HttpContext) null);
    }

    public Socket createSocket(HttpParams httpParams) {
        return createSocket((HttpContext) null);
    }

    public X509HostnameVerifier getHostnameVerifier() {
        return this.hostnameVerifier;
    }

    public boolean isSecure(Socket socket) {
        Args.notNull(socket, "Socket");
        Asserts.check(socket instanceof SSLSocket, "Socket not created by this factory");
        Asserts.check(!socket.isClosed(), "Socket is closed");
        return true;
    }

    public void setHostnameVerifier(X509HostnameVerifier x509HostnameVerifier) {
        Args.notNull(x509HostnameVerifier, "Hostname verifier");
        this.hostnameVerifier = x509HostnameVerifier;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SSLSocketFactory(String str, KeyStore keyStore, String str2, KeyStore keyStore2, SecureRandom secureRandom, TrustStrategy trustStrategy, X509HostnameVerifier x509HostnameVerifier) {
        this(SSLContexts.custom().useProtocol(str).setSecureRandom(secureRandom).loadKeyMaterial(keyStore, str2 != null ? str2.toCharArray() : null).loadTrustMaterial(keyStore2, trustStrategy).build(), x509HostnameVerifier);
    }

    public Socket createLayeredSocket(Socket socket, String str, int i10, boolean z10) {
        return createLayeredSocket(socket, str, i10, (HttpContext) null);
    }

    public Socket createSocket() {
        return createSocket((HttpContext) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SSLSocketFactory(String str, KeyStore keyStore, String str2, KeyStore keyStore2, SecureRandom secureRandom, X509HostnameVerifier x509HostnameVerifier) {
        this(SSLContexts.custom().useProtocol(str).setSecureRandom(secureRandom).loadKeyMaterial(keyStore, str2 != null ? str2.toCharArray() : null).loadTrustMaterial(keyStore2).build(), x509HostnameVerifier);
    }

    public Socket createLayeredSocket(Socket socket, String str, int i10, HttpContext httpContext) {
        SSLSocket sSLSocket = (SSLSocket) this.socketfactory.createSocket(socket, str, i10, true);
        internalPrepareSocket(sSLSocket);
        sSLSocket.startHandshake();
        verifyHostname(sSLSocket, str);
        return sSLSocket;
    }

    public Socket createSocket(Socket socket, String str, int i10, boolean z10) {
        return createLayeredSocket(socket, str, i10, z10);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SSLSocketFactory(KeyStore keyStore, String str, KeyStore keyStore2) {
        this(SSLContexts.custom().loadKeyMaterial(keyStore, str != null ? str.toCharArray() : null).loadTrustMaterial(keyStore2).build(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public Socket createSocket(HttpContext httpContext) {
        return SocketFactory.getDefault().createSocket();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SSLSocketFactory(KeyStore keyStore, String str) {
        this(SSLContexts.custom().loadKeyMaterial(keyStore, str != null ? str.toCharArray() : null).build(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public SSLSocketFactory(KeyStore keyStore) {
        this(SSLContexts.custom().loadTrustMaterial(keyStore).build(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public SSLSocketFactory(TrustStrategy trustStrategy, X509HostnameVerifier x509HostnameVerifier) {
        this(SSLContexts.custom().loadTrustMaterial((KeyStore) null, trustStrategy).build(), x509HostnameVerifier);
    }

    public SSLSocketFactory(TrustStrategy trustStrategy) {
        this(SSLContexts.custom().loadTrustMaterial((KeyStore) null, trustStrategy).build(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public SSLSocketFactory(SSLContext sSLContext) {
        this(sSLContext, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public SSLSocketFactory(SSLContext sSLContext, HostNameResolver hostNameResolver) {
        this.socketfactory = sSLContext.getSocketFactory();
        this.hostnameVerifier = BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
        this.nameResolver = hostNameResolver;
        this.supportedProtocols = null;
        this.supportedCipherSuites = null;
    }

    public Socket connectSocket(Socket socket, String str, int i10, InetAddress inetAddress, int i11, HttpParams httpParams) {
        InetAddress inetAddress2;
        InetSocketAddress inetSocketAddress;
        HostNameResolver hostNameResolver = this.nameResolver;
        if (hostNameResolver != null) {
            inetAddress2 = hostNameResolver.resolve(str);
        } else {
            inetAddress2 = InetAddress.getByName(str);
        }
        if (inetAddress != null || i11 > 0) {
            if (i11 <= 0) {
                i11 = 0;
            }
            inetSocketAddress = new InetSocketAddress(inetAddress, i11);
        } else {
            inetSocketAddress = null;
        }
        return connectSocket(socket, new HttpInetSocketAddress(new HttpHost(str, i10), inetAddress2, i10), inetSocketAddress, httpParams);
    }

    public SSLSocketFactory(SSLContext sSLContext, X509HostnameVerifier x509HostnameVerifier) {
        this(((SSLContext) Args.notNull(sSLContext, "SSL context")).getSocketFactory(), (String[]) null, (String[]) null, x509HostnameVerifier);
    }

    public Socket connectSocket(int i10, Socket socket, HttpHost httpHost, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpContext httpContext) {
        Args.notNull(httpHost, "HTTP host");
        Args.notNull(inetSocketAddress, "Remote address");
        if (socket == null) {
            socket = createSocket(httpContext);
        }
        if (inetSocketAddress2 != null) {
            socket.bind(inetSocketAddress2);
        }
        try {
            socket.connect(inetSocketAddress, i10);
            if (!(socket instanceof SSLSocket)) {
                return createLayeredSocket(socket, httpHost.getHostName(), inetSocketAddress.getPort(), httpContext);
            }
            SSLSocket sSLSocket = (SSLSocket) socket;
            sSLSocket.startHandshake();
            verifyHostname(sSLSocket, httpHost.getHostName());
            return socket;
        } catch (SocketTimeoutException unused) {
            throw new ConnectTimeoutException("Connect to " + inetSocketAddress + " timed out");
        }
    }

    public SSLSocketFactory(SSLContext sSLContext, String[] strArr, String[] strArr2, X509HostnameVerifier x509HostnameVerifier) {
        this(((SSLContext) Args.notNull(sSLContext, "SSL context")).getSocketFactory(), strArr, strArr2, x509HostnameVerifier);
    }

    public SSLSocketFactory(javax.net.ssl.SSLSocketFactory sSLSocketFactory, X509HostnameVerifier x509HostnameVerifier) {
        this(sSLSocketFactory, (String[]) null, (String[]) null, x509HostnameVerifier);
    }

    public SSLSocketFactory(javax.net.ssl.SSLSocketFactory sSLSocketFactory, String[] strArr, String[] strArr2, X509HostnameVerifier x509HostnameVerifier) {
        this.socketfactory = (javax.net.ssl.SSLSocketFactory) Args.notNull(sSLSocketFactory, "SSL socket factory");
        this.supportedProtocols = strArr;
        this.supportedCipherSuites = strArr2;
        this.hostnameVerifier = x509HostnameVerifier == null ? BROWSER_COMPATIBLE_HOSTNAME_VERIFIER : x509HostnameVerifier;
        this.nameResolver = null;
    }

    /* access modifiers changed from: protected */
    public void prepareSocket(SSLSocket sSLSocket) {
    }
}
