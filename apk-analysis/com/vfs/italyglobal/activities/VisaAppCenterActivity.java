package com.vfs.italyglobal.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
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
import ua.s5;
import ua.t5;
import ua.u5;
import va.x;
import xa.y;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class VisaAppCenterActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9476f0 = i.a(new s5(this));

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a extends WebViewClient {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ VisaAppCenterActivity f9477a;

        a(VisaAppCenterActivity visaAppCenterActivity) {
            this.f9477a = visaAppCenterActivity;
        }

        public void onPageFinished(WebView webView, String str) {
            this.f9477a.F1().f20726f.setVisibility(0);
            this.f9477a.F1().f20723c.setVisibility(8);
            this.f9477a.F1().f20724d.setRefreshing(false);
            Utility.f9497a.c();
            super.onPageFinished(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            this.f9477a.F1().f20726f.setVisibility(8);
            super.onPageStarted(webView, str, bitmap);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            WebView webView2 = this.f9477a.F1().f20726f;
            n.b(str);
            webView2.loadUrl(str);
            Utility.f9497a.y(this.f9477a.c1());
            return true;
        }
    }

    public VisaAppCenterActivity() {
        super(false, false, 3, (bc.h) null);
    }

    /* access modifiers changed from: private */
    public final y F1() {
        return (y) this.f9476f0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void H1(VisaAppCenterActivity visaAppCenterActivity, View view) {
        visaAppCenterActivity.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final void I1(VisaAppCenterActivity visaAppCenterActivity) {
        visaAppCenterActivity.E1();
    }

    /* access modifiers changed from: private */
    public static final y K1(VisaAppCenterActivity visaAppCenterActivity) {
        return y.c(visaAppCenterActivity.getLayoutInflater());
    }

    public final void E1() {
        F1().f20724d.setRefreshing(false);
        if (c.j(this)) {
            J1();
            return;
        }
        F1().f20726f.setVisibility(8);
        Utility.f9497a.c();
        F1().f20723c.setVisibility(0);
    }

    public final void G1() {
        F1().f20722b.setOnClickListener(new t5(this));
        E1();
        F1().f20724d.setOnRefreshListener(new u5(this));
    }

    public final void J1() {
        String str;
        try {
            Utility.Companion companion = Utility.f9497a;
            companion.y(c1());
            WebSettings settings = F1().f20726f.getSettings();
            n.d(settings, "getSettings(...)");
            settings.setJavaScriptEnabled(true);
            settings.setBuiltInZoomControls(true);
            F1().f20726f.setWebViewClient(new a(this));
            PageCategoryData i10 = Utility.Companion.i(companion, c1(), "Visa Application Center", false, 4, (Object) null);
            g.a aVar = g.f9512a;
            Activity c12 = c1();
            String string = getString(R.string.selectedCountryId);
            n.d(string, "getString(...)");
            int a10 = aVar.a(c12, string, 0);
            Activity c13 = c1();
            String string2 = getString(R.string.embassy_location_Id);
            n.d(string2, "getString(...)");
            String b10 = aVar.b(c13, string2, "");
            StringBuilder sb2 = new StringBuilder();
            AppConfigModel e10 = d1().e();
            if (e10 != null) {
                str = e10.getDevPublicURL();
            } else {
                str = null;
            }
            sb2.append(str);
            n.b(i10);
            sb2.append(i10.getPageCategoryId());
            sb2.append(getString(R.string.txtURL_page_sub_category_id));
            sb2.append(i10.getPageSubCategoryId());
            sb2.append(getString(R.string.txtURL_embassy_location_id));
            sb2.append(b10);
            sb2.append(getString(R.string.txtURL_country_id));
            sb2.append(a10);
            F1().f20726f.loadUrl(sb2.toString());
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
        setContentView((View) F1().b());
        G1();
    }
}
