package com.vfs.italyglobal.activities;

import ac.p;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import bc.n;
import be.d;
import be.f;
import com.google.android.material.navigation.NavigationView;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.pojo.ConfigurationData;
import com.vfs.italyglobal.pojo.MasterGetCategory;
import com.vfs.italyglobal.pojo.NavItem;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.c;
import com.vfs.italyglobal.utilities.g;
import java.io.IOException;
import java.util.ArrayList;
import nb.h;
import nb.i;
import ua.g2;
import ua.h2;
import ua.i2;
import ua.j2;
import ua.k2;
import ua.l2;
import ua.m2;
import ua.n2;
import ua.p2;
import ua.q2;
import ua.r2;
import ua.s2;
import va.x;
import wa.r;
import xa.j;
import ya.a1;
import ya.d2;
import ya.k;
import ya.o2;
import ya.y1;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class Dashboard extends x implements NavigationView.d {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9391f0 = i.a(new g2(this));

    /* renamed from: g0  reason: collision with root package name */
    private final h f9392g0 = i.a(new j2(this));

    /* renamed from: h0  reason: collision with root package name */
    private String f9393h0 = "";

    /* renamed from: i0  reason: collision with root package name */
    private androidx.appcompat.app.b f9394i0;

    /* renamed from: j0  reason: collision with root package name */
    private int f9395j0;

    /* renamed from: k0  reason: collision with root package name */
    private boolean f9396k0;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Dashboard f9397a;

        a(Dashboard dashboard) {
            this.f9397a = dashboard;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(Dashboard dashboard, int i10, String str) {
            n.e(str, "<unused var>");
            if (!(i10 == 403 || i10 == 429)) {
                Utility.Companion companion = Utility.f9497a;
                Context Q1 = dashboard.Q1();
                String string = dashboard.getString(R.string.generic_error);
                n.d(string, "getString(...)");
                companion.w(Q1, string);
            }
            return nb.x.f15721a;
        }

        public void a(d dVar, be.x xVar) {
            String str;
            n.e(dVar, "call");
            n.e(xVar, "response");
            Utility.f9497a.c();
            try {
                ConfigurationData configurationData = (ConfigurationData) xVar.a();
                if (configurationData == null || configurationData.getPayAtVACEnabled() == null || configurationData.getPayOnlineEnabled() == null) {
                    c.h(xVar, this.f9397a.Q1(), new s2(this.f9397a));
                    return;
                }
                configurationData.getPayAtVACEnabled();
                configurationData.getPayOnlineEnabled();
                Boolean payOnlineEnabled = configurationData.getPayOnlineEnabled();
                n.b(payOnlineEnabled);
                if (payOnlineEnabled.booleanValue()) {
                    str = "PAYOnline";
                } else {
                    str = "PAYAtVAC";
                }
                g.a aVar = g.f9512a;
                Context Q1 = this.f9397a.Q1();
                String string = this.f9397a.getString(R.string.appmnt_payment_mode);
                n.d(string, "getString(...)");
                aVar.g(Q1, string, str);
                Context Q12 = this.f9397a.Q1();
                String string2 = this.f9397a.getString(R.string.no_of_applicants);
                n.d(string2, "getString(...)");
                Integer noOfMaxAllowedApplicant = configurationData.getNoOfMaxAllowedApplicant();
                n.b(noOfMaxAllowedApplicant);
                aVar.f(Q12, string2, noOfMaxAllowedApplicant.intValue());
                Context Q13 = this.f9397a.Q1();
                String string3 = this.f9397a.getString(R.string.enableOtpLoginRegistration);
                n.d(string3, "getString(...)");
                Boolean enableOTPLoginRegistration = configurationData.getEnableOTPLoginRegistration();
                n.b(enableOTPLoginRegistration);
                aVar.h(Q13, string3, enableOTPLoginRegistration.booleanValue());
                configurationData.getNoOfMaxAllowedApplicant();
            } catch (Exception e10) {
                Utility.Companion companion = Utility.f9497a;
                Context Q14 = this.f9397a.Q1();
                String string4 = this.f9397a.getString(R.string.generic_error);
                n.d(string4, "getString(...)");
                companion.w(Q14, string4);
                e10.toString();
            }
        }

        public void b(d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            if (!(th instanceof IOException) || !n.a(th.getMessage(), this.f9397a.getString(R.string.ssl_pinning_error))) {
                Dashboard dashboard = this.f9397a;
                String string = dashboard.getString(R.string.generic_error);
                n.d(string, "getString(...)");
                companion.w(dashboard, string);
                return;
            }
            companion.w(this.f9397a.c1(), ((IOException) th).getLocalizedMessage().toString());
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class b implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Dashboard f9398a;

        b(Dashboard dashboard) {
            this.f9398a = dashboard;
        }

        public void a(d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            try {
                MasterGetCategory masterGetCategory = (MasterGetCategory) xVar.a();
                if (masterGetCategory != null) {
                    Integer status = masterGetCategory.getStatus();
                    if (status != null) {
                        if (status.intValue() == 200 && !masterGetCategory.getPageCategoryData().isEmpty()) {
                            Utility.f9497a.v(this.f9398a.c1(), masterGetCategory.getPageCategoryData());
                            return;
                        }
                    }
                }
                c.i(xVar, this.f9398a.c1(), (p) null, 2, (Object) null);
            } catch (Exception e10) {
                e10.toString();
            }
        }

        public void b(d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
        }
    }

    public Dashboard() {
        super(false, false, 3, (bc.h) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0051 A[Catch:{ Exception -> 0x0029 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void M1(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            boolean r0 = com.vfs.italyglobal.utilities.c.j(r4)     // Catch:{ Exception -> 0x0029 }
            if (r0 == 0) goto L_0x006d
            com.vfs.italyglobal.utilities.Utility$Companion r0 = com.vfs.italyglobal.utilities.Utility.f9497a     // Catch:{ Exception -> 0x0029 }
            android.content.Context r1 = r4.Q1()     // Catch:{ Exception -> 0x0029 }
            r0.y(r1)     // Catch:{ Exception -> 0x0029 }
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Exception -> 0x0029 }
            r0.<init>()     // Catch:{ Exception -> 0x0029 }
            java.lang.String r1 = "referer"
            com.vfs.italyglobal.AppDelegate r2 = r4.d1()     // Catch:{ Exception -> 0x0029 }
            com.vfs.italyglobal.pojo.AppConfigModel r2 = r2.e()     // Catch:{ Exception -> 0x0029 }
            java.lang.String r3 = ""
            if (r2 == 0) goto L_0x002b
            java.lang.String r2 = r2.getAppointmentUrlOrigin()     // Catch:{ Exception -> 0x0029 }
            if (r2 != 0) goto L_0x002c
            goto L_0x002b
        L_0x0029:
            r5 = move-exception
            goto L_0x007f
        L_0x002b:
            r2 = r3
        L_0x002c:
            r0.put(r1, r2)     // Catch:{ Exception -> 0x0029 }
            java.lang.String r1 = "origin"
            com.vfs.italyglobal.AppDelegate r2 = r4.d1()     // Catch:{ Exception -> 0x0029 }
            com.vfs.italyglobal.pojo.AppConfigModel r2 = r2.e()     // Catch:{ Exception -> 0x0029 }
            if (r2 == 0) goto L_0x0041
            java.lang.String r2 = r2.getAppointmentUrlOrigin()     // Catch:{ Exception -> 0x0029 }
            if (r2 != 0) goto L_0x0042
        L_0x0041:
            r2 = r3
        L_0x0042:
            r0.put(r1, r2)     // Catch:{ Exception -> 0x0029 }
            java.lang.String r1 = "cfmlift"
            com.vfs.italyglobal.AppDelegate r2 = r4.d1()     // Catch:{ Exception -> 0x0029 }
            com.vfs.italyglobal.pojo.AppConfigModel r2 = r2.e()     // Catch:{ Exception -> 0x0029 }
            if (r2 == 0) goto L_0x0059
            java.lang.String r2 = r2.getAppointmentCfmLift()     // Catch:{ Exception -> 0x0029 }
            if (r2 != 0) goto L_0x0058
            goto L_0x0059
        L_0x0058:
            r3 = r2
        L_0x0059:
            r0.put(r1, r3)     // Catch:{ Exception -> 0x0029 }
            ab.b r1 = r4.f1()     // Catch:{ Exception -> 0x0029 }
            be.d r5 = r1.A(r0, r5, r6)     // Catch:{ Exception -> 0x0029 }
            com.vfs.italyglobal.activities.Dashboard$a r6 = new com.vfs.italyglobal.activities.Dashboard$a     // Catch:{ Exception -> 0x0029 }
            r6.<init>(r4)     // Catch:{ Exception -> 0x0029 }
            r5.A(r6)     // Catch:{ Exception -> 0x0029 }
            return
        L_0x006d:
            com.vfs.italyglobal.utilities.Utility$Companion r5 = com.vfs.italyglobal.utilities.Utility.f9497a     // Catch:{ Exception -> 0x0029 }
            r6 = 2131886591(0x7f1201ff, float:1.9407765E38)
            java.lang.String r6 = r4.getString(r6)     // Catch:{ Exception -> 0x0029 }
            java.lang.String r0 = "getString(...)"
            bc.n.d(r6, r0)     // Catch:{ Exception -> 0x0029 }
            r5.w(r4, r6)     // Catch:{ Exception -> 0x0029 }
            return
        L_0x007f:
            r5.toString()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vfs.italyglobal.activities.Dashboard.M1(java.lang.String, java.lang.String):void");
    }

    private final void N1() {
        g1().j().A(new b(this));
    }

    private final void O1() {
        AppConfigModel.CountryConfig j10 = d1().j();
        boolean z10 = true;
        if (j10 == null || !j10.getChatbotAccess()) {
            R1();
        } else {
            p2();
            z10 = false;
        }
        this.f9396k0 = z10;
    }

    private final void R1() {
        P1().f20439d.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public static final void T1(Dashboard dashboard, View view) {
        dashboard.onBackPressed();
        if (n.a(dashboard.P1().f20446k.getText(), dashboard.getString(R.string.dasbhoard_title))) {
            dashboard.P1().f20441f.setVisibility(0);
        } else {
            dashboard.P1().f20441f.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public static final void U1(Dashboard dashboard, View view) {
        dashboard.b2();
    }

    /* access modifiers changed from: private */
    public static final void V1(Dashboard dashboard, AdapterView adapterView, View view, int i10, long j10) {
        dashboard.d2(i10);
    }

    /* access modifiers changed from: private */
    public static final void W1(Dashboard dashboard, View view) {
        dashboard.Z1();
    }

    /* access modifiers changed from: private */
    public static final j X1(Dashboard dashboard) {
        j c10 = j.c(dashboard.getLayoutInflater());
        n.d(c10, "inflate(...)");
        return c10;
    }

    private final void Z1() {
        Intent intent = new Intent(this, CountrySelection.class);
        intent.putExtra(getString(R.string.isFromSplash), false);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /* access modifiers changed from: private */
    public static final void a2(Dashboard dashboard, View view) {
        dashboard.onBackPressed();
    }

    private final void b2() {
        startActivity(new Intent(this, ChatBotActivity.class));
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    private final void c2(int i10) {
        Bundle bundle = new Bundle();
        switch (i10) {
            case 1:
                String string = getString(R.string.side_scanning_doc);
                n.d(string, "getString(...)");
                this.f9393h0 = string;
                break;
            case 2:
                String string2 = getString(R.string.side_online_payment);
                n.d(string2, "getString(...)");
                this.f9393h0 = string2;
                break;
            case 3:
                String string3 = getString(R.string.side_travel_shop);
                n.d(string3, "getString(...)");
                this.f9393h0 = string3;
                break;
            case 4:
                String string4 = getString(R.string.side_tour_promotion);
                n.d(string4, "getString(...)");
                this.f9393h0 = string4;
                break;
            case 5:
                String string5 = getString(R.string.side_italy_network);
                n.d(string5, "getString(...)");
                this.f9393h0 = string5;
                break;
            case 6:
                String string6 = getString(R.string.side_VAS_option);
                n.d(string6, "getString(...)");
                this.f9393h0 = string6;
                break;
            case 7:
                String string7 = getString(R.string.side_about_us);
                n.d(string7, "getString(...)");
                this.f9393h0 = string7;
                break;
            case 8:
                String string8 = getString(R.string.side_contact_us);
                n.d(string8, "getString(...)");
                this.f9393h0 = string8;
                break;
        }
        bundle.putString(getString(R.string.bundle_value), this.f9393h0);
        k kVar = new k();
        kVar.D1(bundle);
        androidx.fragment.app.x m10 = Y().m();
        m10.q(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right).n(R.id.fragment, kVar);
        m10.g();
        Y().S0();
    }

    private final void d2(int i10) {
        switch (i10) {
            case 0:
                androidx.fragment.app.i e02 = Y().e0(R.id.fragment);
                n.b(e02);
                n.d(e02.toString(), "toString(...)");
                return;
            case 1:
                if (n.a(this.f9393h0, getString(R.string.side_scanning_doc))) {
                    P1().f20437b.h();
                    return;
                }
                c2(1);
                P1().f20437b.h();
                return;
            case 2:
                if (n.a(this.f9393h0, getString(R.string.side_online_payment))) {
                    P1().f20437b.h();
                    return;
                }
                c2(2);
                P1().f20437b.h();
                return;
            case 3:
                if (n.a(this.f9393h0, getString(R.string.side_travel_shop))) {
                    P1().f20437b.h();
                    return;
                }
                c2(3);
                P1().f20437b.h();
                return;
            case 4:
                if (n.a(this.f9393h0, getString(R.string.side_tour_promotion))) {
                    P1().f20437b.h();
                    return;
                }
                c2(4);
                P1().f20437b.h();
                return;
            case 5:
                if (n.a(this.f9393h0, getString(R.string.side_italy_network))) {
                    P1().f20437b.h();
                    return;
                }
                c2(5);
                P1().f20437b.h();
                return;
            case 6:
                if (n.a(this.f9393h0, getString(R.string.side_VAS_option))) {
                    P1().f20437b.h();
                    return;
                }
                c2(6);
                P1().f20437b.h();
                return;
            case 7:
                if (n.a(this.f9393h0, getString(R.string.side_about_us))) {
                    P1().f20437b.h();
                    return;
                }
                c2(7);
                P1().f20437b.h();
                return;
            case 8:
                if (n.a(this.f9393h0, getString(R.string.side_contact_us))) {
                    P1().f20437b.h();
                    return;
                }
                c2(8);
                P1().f20437b.h();
                return;
            default:
                return;
        }
    }

    private final void e2() {
        androidx.fragment.app.x m10 = Y().m();
        m10.q(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_right).o(R.id.fragment, ya.d.f20935y0.a(), "Visa Details Fragment").p(new n2(this));
        m10.g();
        Y().S0();
    }

    /* access modifiers changed from: private */
    public static final void f2(Dashboard dashboard) {
        dashboard.P1().f20446k.setText(dashboard.getString(R.string.casa_Italia));
    }

    private final void g2() {
        androidx.fragment.app.x m10 = Y().m();
        m10.q(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_right).o(R.id.fragment, ya.p.f21051t0.a(), "Dashboard Fragment").p(new m2(this));
        m10.g();
        Y().S0();
    }

    /* access modifiers changed from: private */
    public static final void h2(Dashboard dashboard) {
        dashboard.P1().f20446k.setText(dashboard.Q1().getString(R.string.dasbhoard_title));
    }

    private final void i2() {
        this.f9393h0 = "";
        androidx.fragment.app.x m10 = Y().m();
        m10.q(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right).o(R.id.fragment, ya.p.f21051t0.a(), "Dashboard Fragment").p(new i2(this));
        m10.g();
        Y().S0();
    }

    /* access modifiers changed from: private */
    public static final void j2(Dashboard dashboard) {
        dashboard.P1().f20446k.setText(dashboard.Q1().getString(R.string.dasbhoard_title));
    }

    private final void k2() {
        androidx.fragment.app.x m10 = Y().m();
        m10.q(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_right).o(R.id.fragment, y1.f21119s0.a(), "Visa Details Fragment").p(new k2(this));
        m10.g();
        Y().S0();
    }

    /* access modifiers changed from: private */
    public static final void l2(Dashboard dashboard) {
        dashboard.P1().f20446k.setText(dashboard.getString(R.string.tourism_promotion));
    }

    private final void m2() {
        androidx.fragment.app.x m10 = Y().m();
        m10.q(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_right).o(R.id.fragment, o2.f21044u0.a(), "Visa Details Fragment").p(new l2(this));
        m10.g();
        Y().S0();
    }

    /* access modifiers changed from: private */
    public static final void n2(Dashboard dashboard) {
        dashboard.P1().f20446k.setText(dashboard.Q1().getString(R.string.visa));
    }

    private final void p2() {
        if (!this.f9396k0) {
            P1().f20439d.setVisibility(0);
        }
    }

    public final j P1() {
        return (j) this.f9391f0.getValue();
    }

    public final Context Q1() {
        return (Context) this.f9392g0.getValue();
    }

    public final void S1() {
        g.a aVar = g.f9512a;
        Context Q1 = Q1();
        String string = getString(R.string.selectedCountryId);
        n.d(string, "getString(...)");
        this.f9395j0 = aVar.a(Q1, string, 0);
        ArrayList arrayList = new ArrayList();
        this.f9394i0 = new androidx.appcompat.app.b(this, P1().f20437b, (Toolbar) null, R.string.open_nav_drawer, R.string.close_nav_drawer);
        DrawerLayout drawerLayout = P1().f20437b;
        androidx.appcompat.app.b bVar = this.f9394i0;
        n.b(bVar);
        drawerLayout.a(bVar);
        androidx.appcompat.app.b bVar2 = this.f9394i0;
        n.b(bVar2);
        bVar2.i();
        P1().f20444i.setNavigationItemSelectedListener(this);
        P1().f20440e.setOnClickListener(new p2(this));
        P1().f20439d.setVisibility(8);
        P1().f20441f.setVisibility(0);
        AppCompatTextView appCompatTextView = P1().f20445j;
        Context Q12 = Q1();
        String string2 = getString(R.string.key_countryname);
        n.d(string2, "getString(...)");
        appCompatTextView.setText(aVar.b(Q12, string2, ""));
        P1().f20439d.setOnClickListener(new q2(this));
        String string3 = getString(R.string.side_home);
        n.d(string3, "getString(...)");
        arrayList.add(new NavItem(string3, "Navigate to dashboard", R.drawable.nav_home));
        String string4 = getString(R.string.side_scanning_doc);
        n.d(string4, "getString(...)");
        arrayList.add(new NavItem(string4, "", R.drawable.icon_scanning_doc));
        String string5 = getString(R.string.side_online_payment);
        n.d(string5, "getString(...)");
        arrayList.add(new NavItem(string5, "Must know information for visa applicants", R.drawable.nav_online_payment));
        String string6 = getString(R.string.side_travel_shop);
        n.d(string6, "getString(...)");
        arrayList.add(new NavItem(string6, "Book your appointment", R.drawable.nav_travel_shop));
        String string7 = getString(R.string.side_tour_promotion);
        n.d(string7, "getString(...)");
        arrayList.add(new NavItem(string7, "Track your application", R.drawable.nav_tour_promotion));
        String string8 = getString(R.string.side_italy_network);
        n.d(string8, "getString(...)");
        arrayList.add(new NavItem(string8, "Stay updated with latest news", R.drawable.nav_italy_network));
        String string9 = getString(R.string.side_VAS_option);
        n.d(string9, "getString(...)");
        arrayList.add(new NavItem(string9, "Change city", R.drawable.nav_vas));
        String string10 = getString(R.string.side_about_us);
        n.d(string10, "getString(...)");
        arrayList.add(new NavItem(string10, "Information about VFS", R.drawable.nav_about_us));
        String string11 = getString(R.string.side_contact_us);
        n.d(string11, "getString(...)");
        arrayList.add(new NavItem(string11, "Stay updated", R.drawable.nav_contact_us));
        P1().f20443h.setAdapter(new r(Q1(), arrayList));
        P1().f20443h.setOnItemClickListener(new r2(this));
        Y().e0(R.id.fragment);
        P1().f20442g.setOnClickListener(new h2(this));
        Utility.Companion companion = Utility.f9497a;
        M1(companion.q(), companion.k(Q1()));
        N1();
    }

    public boolean e(MenuItem menuItem) {
        n.e(menuItem, "item");
        return true;
    }

    public final void o2() {
        P1().f20440e.setVisibility(0);
        ViewGroup.LayoutParams layoutParams = P1().f20446k.getLayoutParams();
        n.c(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        ((LinearLayout.LayoutParams) layoutParams).weight = 0.55f;
        androidx.fragment.app.i e02 = Y().e0(R.id.fragment);
        n.b(e02);
        if (e02 instanceof o2) {
            P1().f20442g.setVisibility(8);
        } else {
            P1().f20441f.setVisibility(8);
        }
        R1();
    }

    public void onBackPressed() {
        androidx.fragment.app.i e02 = Y().e0(R.id.fragment);
        n.b(e02);
        R1();
        if (e02 instanceof ya.r2) {
            m2();
        } else if ((e02 instanceof d2) || (e02 instanceof a1)) {
            k2();
        } else if (e02 instanceof ya.f) {
            e2();
        } else if ((e02 instanceof o2) || (e02 instanceof y1) || (e02 instanceof ya.d)) {
            g2();
        } else if (e02 instanceof ya.p) {
            super.onBackPressed();
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        } else {
            g2();
        }
        onResume();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        k1();
        setContentView((View) P1().b());
        try {
            P1().f20440e.setOnClickListener(new ua.o2(this));
            Y().e0(R.id.fragment);
            i2();
            g.a aVar = g.f9512a;
            Context Q1 = Q1();
            String string = getString(R.string.isFromLocaleChangeDashboard);
            n.d(string, "getString(...)");
            if (!aVar.c(Q1, string, false)) {
                S1();
            } else {
                Context Q12 = Q1();
                String string2 = getString(R.string.isFromLocaleChangeDashboard);
                n.d(string2, "getString(...)");
                aVar.h(Q12, string2, false);
            }
        } catch (Exception e10) {
            e10.toString();
        }
        O1();
        U0();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Utility.Companion companion = Utility.f9497a;
        companion.c();
        companion.d();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            androidx.fragment.app.i e02 = Y().e0(R.id.fragment);
            n.b(e02);
            if (e02 instanceof ya.p) {
                p2();
                P1().f20441f.setVisibility(0);
                P1().f20442g.setVisibility(0);
                P1().f20440e.setVisibility(8);
                ViewGroup.LayoutParams layoutParams = P1().f20446k.getLayoutParams();
                n.c(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                ((LinearLayout.LayoutParams) layoutParams).weight = 0.7f;
            }
        } catch (Exception e10) {
            e10.toString();
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: private */
    public static final Dashboard Y1(Dashboard dashboard) {
        return dashboard;
    }
}
