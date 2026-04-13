package com.vfs.italyglobal.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import bc.a0;
import bc.n;
import be.d;
import be.f;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.pojo.TrackingData;
import com.vfs.italyglobal.pojo.TrackingError;
import com.vfs.italyglobal.pojo.TrackingResponse;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.g;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import nb.h;
import nb.i;
import ob.p;
import org.apache.http.client.utils.URLEncodedUtils;
import ua.a5;
import ua.v4;
import ua.w4;
import ua.x4;
import ua.y4;
import ua.z4;
import va.x;
import xa.t;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class TrackYourApplication extends x {

    /* renamed from: f0  reason: collision with root package name */
    private List f9455f0 = p.n("India Mission", "Italy Mission", "China Mission", "Japan Mission", "Russia Mission");

    /* renamed from: g0  reason: collision with root package name */
    private List f9456g0 = p.n("in-IN", "it-IT", "it-CH", "it-JA", "it-RSS");

    /* renamed from: h0  reason: collision with root package name */
    private final h f9457h0 = i.a(new v4(this));

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static class a extends ArrayAdapter {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context, int i10, List list) {
            super(context, i10, list);
            n.e(context, "context");
            n.b(list);
        }

        public View getView(int i10, View view, ViewGroup viewGroup) {
            n.e(viewGroup, "parent");
            View view2 = super.getView(i10, view, viewGroup);
            n.d(view2, "getView(...)");
            view2.setPadding(0, view2.getPaddingTop(), view2.getPaddingRight(), view2.getPaddingBottom());
            return view2;
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class b implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ TrackYourApplication f9458a;

        b(TrackYourApplication trackYourApplication) {
            this.f9458a = trackYourApplication;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(TrackYourApplication trackYourApplication, int i10, String str) {
            n.e(str, "errorMsg");
            if (!(i10 == 403 || i10 == 429)) {
                Utility.Companion companion = Utility.f9497a;
                Activity c12 = trackYourApplication.c1();
                String string = trackYourApplication.getString(R.string.generic_error);
                n.d(string, "getString(...)");
                companion.w(c12, string);
            }
            return nb.x.f15721a;
        }

        public void a(d dVar, be.x xVar) {
            n.e(dVar, "p0");
            n.e(xVar, "response");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            TrackingResponse trackingResponse = (TrackingResponse) xVar.a();
            if (trackingResponse != null) {
                TrackingData data = trackingResponse.getData();
                n.b(data);
                if (data.getErrors() != null) {
                    Activity c12 = this.f9458a.c1();
                    TrackingData data2 = trackingResponse.getData();
                    n.b(data2);
                    TrackingError errors = data2.getErrors();
                    n.b(errors);
                    companion.w(c12, String.valueOf(errors.getErrorDescription()));
                    return;
                }
                Activity c13 = this.f9458a.c1();
                String string = this.f9458a.getString(R.string.generic_error);
                n.d(string, "getString(...)");
                companion.w(c13, string);
                return;
            }
            com.vfs.italyglobal.utilities.c.h(xVar, this.f9458a.c1(), new a5(this.f9458a));
        }

        public void b(d dVar, Throwable th) {
            n.e(dVar, "p0");
            n.e(th, "p1");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            Activity c12 = this.f9458a.c1();
            String localizedMessage = th.getLocalizedMessage();
            n.d(localizedMessage, "getLocalizedMessage(...)");
            companion.w(c12, localizedMessage);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class c extends WebViewClient {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ TrackYourApplication f9459a;

        c(TrackYourApplication trackYourApplication) {
            this.f9459a = trackYourApplication;
        }

        public void onPageFinished(WebView webView, String str) {
            this.f9459a.I1().f20668i.f20417d.setVisibility(0);
            this.f9459a.I1().f20668i.f20415b.setVisibility(8);
            this.f9459a.I1().f20668i.f20416c.setRefreshing(false);
            Utility.f9497a.c();
            super.onPageFinished(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            this.f9459a.I1().f20668i.f20417d.setVisibility(8);
            super.onPageStarted(webView, str, bitmap);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            WebView webView2 = this.f9459a.I1().f20668i.f20417d;
            n.b(str);
            webView2.loadUrl(str);
            return true;
        }
    }

    public TrackYourApplication() {
        super(false, false, 3, (bc.h) null);
    }

    private final void G1() {
        String str;
        String valueOf = String.valueOf(I1().f20662c.getText());
        String valueOf2 = String.valueOf(I1().f20663d.getText());
        Utility.Companion companion = Utility.f9497a;
        String e10 = companion.e(this, companion.l());
        HashMap hashMap = new HashMap();
        AppConfigModel e11 = d1().e();
        if (e11 == null || (str = e11.getAppointmentUrlOrigin()) == null) {
            str = "";
        }
        hashMap.put("referer", str);
        hashMap.put("Content-Type", URLEncodedUtils.CONTENT_TYPE);
        hashMap.put("ClientSource", e10);
        j1().I(hashMap, "%7B%22Param1%22:%22" + valueOf + "%22,%22Param2%22:%22" + valueOf2 + "%22,%22CulturalCode%22:%22en-US%22,%22Mission%22:%22" + companion.q() + "%22,%22Country%22:%22" + companion.k(c1()) + "%22,%22Token%22:%22asdfh43ercdf93434asdfa345adfadfjpoiansdf%22%7D").A(new b(this));
    }

    private final void H1() {
        I1().f20668i.f20416c.setRefreshing(false);
        if (com.vfs.italyglobal.utilities.c.j(this)) {
            N1();
            return;
        }
        I1().f20668i.f20417d.setVisibility(8);
        Utility.f9497a.c();
        I1().f20668i.f20415b.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public final t I1() {
        return (t) this.f9457h0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void K1(TrackYourApplication trackYourApplication) {
        trackYourApplication.H1();
    }

    /* access modifiers changed from: private */
    public static final void L1(TrackYourApplication trackYourApplication, View view) {
        trackYourApplication.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final void M1(TrackYourApplication trackYourApplication, View view) {
        if (com.vfs.italyglobal.utilities.c.j(trackYourApplication)) {
            Editable text = trackYourApplication.I1().f20662c.getText();
            n.b(text);
            if (text.length() != 0) {
                Editable text2 = trackYourApplication.I1().f20663d.getText();
                n.b(text2);
                if (text2.length() != 0) {
                    trackYourApplication.G1();
                    Utility.f9497a.y(trackYourApplication.c1());
                    return;
                }
            }
            Utility.Companion companion = Utility.f9497a;
            Activity c12 = trackYourApplication.c1();
            String string = trackYourApplication.getString(R.string.ref_surnam_mandatory);
            n.d(string, "getString(...)");
            companion.w(c12, string);
            return;
        }
        Utility.Companion companion2 = Utility.f9497a;
        String string2 = trackYourApplication.getString(R.string.network_error);
        n.d(string2, "getString(...)");
        companion2.w(trackYourApplication, string2);
        companion2.c();
    }

    private final void N1() {
        Utility.f9497a.y(c1());
        WebSettings settings = I1().f20668i.f20417d.getSettings();
        n.d(settings, "getSettings(...)");
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(-1);
        I1().f20668i.f20417d.setDownloadListener(new z4(this));
        I1().f20668i.f20417d.setWebViewClient(new c(this));
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = c1().getString(R.string.selectedCountryCode);
        n.d(string, "getString(...)");
        String b10 = aVar.b(c12, string, "");
        a0 a0Var = a0.f4702a;
        String string2 = c1().getString(R.string.tracking_url);
        n.d(string2, "getString(...)");
        String lowerCase = b10.toLowerCase(Locale.ROOT);
        n.d(lowerCase, "toLowerCase(...)");
        String format = String.format(string2, Arrays.copyOf(new Object[]{lowerCase}, 1));
        n.d(format, "format(...)");
        I1().f20668i.f20417d.loadUrl(format);
    }

    /* access modifiers changed from: private */
    public static final void O1(TrackYourApplication trackYourApplication, String str, String str2, String str3, String str4, long j10) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        trackYourApplication.startActivity(intent);
    }

    /* access modifiers changed from: private */
    public static final t P1(TrackYourApplication trackYourApplication) {
        return t.c(trackYourApplication.getLayoutInflater());
    }

    public final void J1() {
        H1();
        I1().f20668i.f20416c.setOnRefreshListener(new w4(this));
        a aVar = new a(this, 17367049, this.f9455f0);
        aVar.setDropDownViewResource(17367049);
        I1().f20666g.setAdapter((SpinnerAdapter) aVar);
        a aVar2 = new a(this, 17367049, this.f9456g0);
        aVar.setDropDownViewResource(17367049);
        I1().f20665f.setAdapter((SpinnerAdapter) aVar2);
        I1().f20664e.setOnClickListener(new x4(this));
        I1().f20661b.setOnClickListener(new y4(this));
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) I1().b());
        J1();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Utility.Companion companion = Utility.f9497a;
        companion.c();
        companion.d();
    }
}
