package com.vfs.italyglobal.utilities;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import androidx.webkit.WebViewClientCompat;
import bc.n;
import k3.c;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class f extends WebViewClientCompat {

    /* renamed from: b  reason: collision with root package name */
    private final c f9511b;

    public f(c cVar) {
        n.e(cVar, "assetLoader");
        this.f9511b = cVar;
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        n.e(webView, "view");
        n.e(webResourceRequest, "request");
        return this.f9511b.a(webResourceRequest.getUrl());
    }
}
