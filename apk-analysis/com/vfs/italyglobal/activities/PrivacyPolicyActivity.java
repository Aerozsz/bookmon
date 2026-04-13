package com.vfs.italyglobal.activities;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import bc.n;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.pojo.PageCategoryData;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.c;
import com.vfs.italyglobal.utilities.g;
import nb.h;
import nb.i;
import ua.f4;
import ua.g4;
import va.x;
import xa.p;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class PrivacyPolicyActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9440f0 = i.a(new g4(this));

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a extends WebViewClient {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ PrivacyPolicyActivity f9441a;

        a(PrivacyPolicyActivity privacyPolicyActivity) {
            this.f9441a = privacyPolicyActivity;
        }

        public void onPageFinished(WebView webView, String str) {
            this.f9441a.E1().f20589f.setVisibility(0);
            this.f9441a.E1().f20586c.setVisibility(8);
            Utility.f9497a.c();
            super.onPageFinished(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            this.f9441a.E1().f20589f.setVisibility(8);
            super.onPageStarted(webView, str, bitmap);
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            WebView webView2 = this.f9441a.E1().f20589f;
            n.b(str);
            webView2.loadUrl(str);
            Utility.f9497a.y(this.f9441a.c1());
            return true;
        }
    }

    public PrivacyPolicyActivity() {
        super(false, false, 3, (bc.h) null);
    }

    /* access modifiers changed from: private */
    public final p E1() {
        return (p) this.f9440f0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void G1(PrivacyPolicyActivity privacyPolicyActivity, View view) {
        privacyPolicyActivity.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final p I1(PrivacyPolicyActivity privacyPolicyActivity) {
        return p.c(privacyPolicyActivity.getLayoutInflater());
    }

    public final void D1() {
        if (c.j(this)) {
            H1();
            return;
        }
        E1().f20589f.setVisibility(8);
        Utility.f9497a.c();
        E1().f20586c.setVisibility(0);
    }

    public final void F1() {
        E1().f20585b.setOnClickListener(new f4(this));
        D1();
    }

    public final void H1() {
        String str;
        try {
            Utility.Companion companion = Utility.f9497a;
            companion.y(c1());
            WebSettings settings = E1().f20589f.getSettings();
            n.d(settings, "getSettings(...)");
            settings.setJavaScriptEnabled(true);
            settings.setUseWideViewPort(true);
            settings.setLoadWithOverviewMode(true);
            settings.setDomStorageEnabled(true);
            settings.setBuiltInZoomControls(true);
            E1().f20589f.setWebViewClient(new a(this));
            g.a aVar = g.f9512a;
            String string = getString(R.string.selectedCountryId);
            n.d(string, "getString(...)");
            int a10 = aVar.a(this, string, 0);
            PageCategoryData h10 = companion.h(c1(), "Register Privacy & Policy", true);
            StringBuilder sb2 = new StringBuilder();
            AppConfigModel e10 = d1().e();
            if (e10 != null) {
                str = e10.getDevPublicURL();
            } else {
                str = null;
            }
            sb2.append(str);
            n.b(h10);
            sb2.append(h10.getPageCategoryId());
            sb2.append(c1().getString(R.string.txtURL_country_id));
            sb2.append(a10);
            E1().f20589f.loadUrl(sb2.toString());
        } catch (Exception e11) {
            e11.toString();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) E1().b());
        F1();
    }
}
