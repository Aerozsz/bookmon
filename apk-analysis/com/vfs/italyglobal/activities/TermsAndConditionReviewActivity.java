package com.vfs.italyglobal.activities;

import android.app.Activity;
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
import ua.t4;
import ua.u4;
import va.x;
import xa.s;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class TermsAndConditionReviewActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9453f0 = i.a(new u4(this));

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a extends WebViewClient {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ TermsAndConditionReviewActivity f9454a;

        a(TermsAndConditionReviewActivity termsAndConditionReviewActivity) {
            this.f9454a = termsAndConditionReviewActivity;
        }

        public void onPageFinished(WebView webView, String str) {
            this.f9454a.E1().f20657e.setVisibility(0);
            this.f9454a.E1().f20655c.setVisibility(8);
            Utility.f9497a.c();
            super.onPageFinished(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            this.f9454a.E1().f20657e.setVisibility(8);
            super.onPageStarted(webView, str, bitmap);
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            WebView webView2 = this.f9454a.E1().f20657e;
            n.b(str);
            webView2.loadUrl(str);
            Utility.f9497a.y(this.f9454a.c1());
            return true;
        }
    }

    public TermsAndConditionReviewActivity() {
        super(false, false, 3, (bc.h) null);
    }

    /* access modifiers changed from: private */
    public final s E1() {
        return (s) this.f9453f0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void G1(TermsAndConditionReviewActivity termsAndConditionReviewActivity, View view) {
        termsAndConditionReviewActivity.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final s I1(TermsAndConditionReviewActivity termsAndConditionReviewActivity) {
        return s.c(termsAndConditionReviewActivity.getLayoutInflater());
    }

    public final void D1() {
        if (c.j(this)) {
            H1();
            return;
        }
        E1().f20657e.setVisibility(8);
        Utility.f9497a.c();
        E1().f20655c.setVisibility(0);
    }

    public final void F1() {
        E1().f20654b.setOnClickListener(new t4(this));
        D1();
    }

    public final void H1() {
        String str;
        try {
            Utility.Companion companion = Utility.f9497a;
            companion.y(c1());
            WebSettings settings = E1().f20657e.getSettings();
            n.d(settings, "getSettings(...)");
            settings.setJavaScriptEnabled(true);
            settings.setUseWideViewPort(true);
            settings.setLoadWithOverviewMode(true);
            settings.setDomStorageEnabled(true);
            settings.setBuiltInZoomControls(false);
            E1().f20657e.setWebViewClient(new a(this));
            PageCategoryData h10 = companion.h(c1(), "Terms and Condition", true);
            g.a aVar = g.f9512a;
            Activity c12 = c1();
            String string = getString(R.string.selectedCountryId);
            n.d(string, "getString(...)");
            int a10 = aVar.a(c12, string, 0);
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
            sb2.append('&');
            sb2.append(getString(R.string.txtURL_country_id));
            sb2.append(a10);
            E1().f20657e.loadUrl(sb2.toString());
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
