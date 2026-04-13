package com.vfs.italyglobal.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
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
import ua.q4;
import ua.r4;
import ua.s4;
import va.x;
import xa.r;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class TermsAndConditionActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9451f0 = i.a(new q4(this));

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a extends WebViewClient {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ TermsAndConditionActivity f9452a;

        a(TermsAndConditionActivity termsAndConditionActivity) {
            this.f9452a = termsAndConditionActivity;
        }

        public void onPageFinished(WebView webView, String str) {
            this.f9452a.F1().f20649h.setVisibility(0);
            this.f9452a.F1().f20645d.setVisibility(8);
            this.f9452a.F1().f20646e.setVisibility(0);
            Utility.f9497a.c();
            super.onPageFinished(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            this.f9452a.F1().f20649h.setVisibility(8);
            super.onPageStarted(webView, str, bitmap);
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            Utility.f9497a.c();
            super.onReceivedError(webView, webResourceRequest, webResourceError);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            WebView webView2 = this.f9452a.F1().f20649h;
            n.b(str);
            webView2.loadUrl(str);
            return true;
        }
    }

    public TermsAndConditionActivity() {
        super(true, false);
    }

    /* access modifiers changed from: private */
    public final r F1() {
        return (r) this.f9451f0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void H1(TermsAndConditionActivity termsAndConditionActivity, View view) {
        termsAndConditionActivity.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final void I1(TermsAndConditionActivity termsAndConditionActivity, View view) {
        Intent intent = new Intent(termsAndConditionActivity, Dashboard.class);
        intent.addFlags(67108864);
        intent.addFlags(32768);
        Utility.Companion companion = Utility.f9497a;
        companion.c();
        companion.d();
        termsAndConditionActivity.startActivity(intent);
        termsAndConditionActivity.finish();
        termsAndConditionActivity.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        g.f9512a.h(termsAndConditionActivity.c1(), "isFirstTime", false);
    }

    /* access modifiers changed from: private */
    public static final r K1(TermsAndConditionActivity termsAndConditionActivity) {
        return r.c(termsAndConditionActivity.getLayoutInflater());
    }

    public final void E1() {
        if (c.j(this)) {
            J1();
            return;
        }
        F1().f20649h.setVisibility(8);
        Utility.f9497a.c();
        F1().f20643b.setVisibility(8);
        F1().f20648g.setVisibility(8);
        F1().f20645d.setVisibility(0);
    }

    public final void G1() {
        F1().f20644c.setOnClickListener(new r4(this));
        E1();
        F1().f20643b.setOnClickListener(new s4(this));
    }

    public final void J1() {
        String str;
        try {
            Utility.Companion companion = Utility.f9497a;
            companion.y(c1());
            WebSettings settings = F1().f20649h.getSettings();
            n.d(settings, "getSettings(...)");
            settings.setJavaScriptEnabled(true);
            settings.setBuiltInZoomControls(false);
            F1().f20649h.setWebViewClient(new a(this));
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
            sb2.append(getString(R.string.txtURL_country_id));
            sb2.append(a10);
            F1().f20649h.loadUrl(sb2.toString());
        } catch (Exception e11) {
            Utility.f9497a.c();
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
        setContentView((View) F1().b());
        G1();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.isFromLocaleChange);
        n.d(string, "getString(...)");
        aVar.h(c12, string, false);
    }
}
