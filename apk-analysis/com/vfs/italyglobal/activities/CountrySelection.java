package com.vfs.italyglobal.activities;

import ac.p;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import bc.n;
import be.f;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.pojo.CountryData;
import com.vfs.italyglobal.pojo.CountryList;
import com.vfs.italyglobal.pojo.MasterGetCategory;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.g;
import java.util.ArrayList;
import java.util.List;
import nb.h;
import nb.i;
import ua.d2;
import ua.e2;
import ua.f2;
import va.x;
import wa.b0;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class CountrySelection extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9383f0 = i.a(new d2(this));

    /* renamed from: g0  reason: collision with root package name */
    private ArrayList f9384g0;

    /* renamed from: h0  reason: collision with root package name */
    public ArrayList f9385h0;
    /* access modifiers changed from: private */

    /* renamed from: i0  reason: collision with root package name */
    public boolean f9386i0;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ CountrySelection f9387a;

        a(CountrySelection countrySelection) {
            this.f9387a = countrySelection;
        }

        public void a(be.d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            this.f9387a.J1().clear();
            Utility.f9497a.c();
            this.f9387a.H1();
            CountryData countryData = (CountryData) xVar.a();
            if (countryData != null) {
                ArrayList J1 = this.f9387a.J1();
                List<CountryList> extraData = countryData.getExtraData();
                n.b(extraData);
                J1.addAll(extraData);
            } else {
                com.vfs.italyglobal.utilities.c.i(xVar, this.f9387a.c1(), (p) null, 2, (Object) null);
            }
            if (!this.f9387a.J1().isEmpty()) {
                CountrySelection countrySelection = this.f9387a;
                countrySelection.R1(countrySelection.J1());
                return;
            }
            this.f9387a.K1().f20428i.setVisibility(0);
            this.f9387a.K1().f20421b.setVisibility(8);
            this.f9387a.K1().f20428i.setText(this.f9387a.getString(R.string.no_records));
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.f9497a.c();
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class b implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ CountrySelection f9388a;

        b(CountrySelection countrySelection) {
            this.f9388a = countrySelection;
        }

        public void a(be.d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            if (!this.f9388a.isFinishing()) {
                Utility.f9497a.c();
            }
            try {
                MasterGetCategory masterGetCategory = (MasterGetCategory) xVar.a();
                if (masterGetCategory != null) {
                    Integer status = masterGetCategory.getStatus();
                    if (status != null) {
                        if (status.intValue() == 200 && !masterGetCategory.getPageCategoryData().isEmpty()) {
                            Utility.f9497a.v(this.f9388a.c1(), masterGetCategory.getPageCategoryData());
                            return;
                        }
                    }
                }
                com.vfs.italyglobal.utilities.c.i(xVar, this.f9388a.c1(), (p) null, 2, (Object) null);
            } catch (Exception e10) {
                e10.toString();
            }
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            if (!this.f9388a.isFinishing()) {
                Utility.f9497a.c();
            }
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class d implements za.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ CountrySelection f9390a;

        d(CountrySelection countrySelection) {
            this.f9390a = countrySelection;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x00da, code lost:
            if (r7.equals("AL_IT") == false) goto L_0x00f5;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(com.vfs.italyglobal.pojo.CountryList r7) {
            /*
                r6 = this;
                java.lang.String r0 = "countryList"
                bc.n.e(r7, r0)
                com.vfs.italyglobal.utilities.g$a r0 = com.vfs.italyglobal.utilities.g.f9512a
                com.vfs.italyglobal.activities.CountrySelection r1 = r6.f9390a
                android.app.Activity r1 = r1.c1()
                com.vfs.italyglobal.activities.CountrySelection r2 = r6.f9390a
                r3 = 2131886689(0x7f120261, float:1.9407964E38)
                java.lang.String r2 = r2.getString(r3)
                java.lang.String r3 = "getString(...)"
                bc.n.d(r2, r3)
                java.lang.String r4 = r7.getFlag()
                java.lang.String r4 = java.lang.String.valueOf(r4)
                r0.g(r1, r2, r4)
                com.vfs.italyglobal.activities.CountrySelection r1 = r6.f9390a
                android.app.Activity r1 = r1.c1()
                com.vfs.italyglobal.activities.CountrySelection r2 = r6.f9390a
                r4 = 2131886382(0x7f12012e, float:1.9407341E38)
                java.lang.String r2 = r2.getString(r4)
                bc.n.d(r2, r3)
                java.lang.String r4 = r7.getCountryName()
                bc.n.b(r4)
                r0.g(r1, r2, r4)
                com.vfs.italyglobal.activities.CountrySelection r1 = r6.f9390a
                android.app.Activity r1 = r1.c1()
                com.vfs.italyglobal.activities.CountrySelection r2 = r6.f9390a
                r4 = 2131886690(0x7f120262, float:1.9407966E38)
                java.lang.String r2 = r2.getString(r4)
                bc.n.d(r2, r3)
                java.lang.Integer r4 = r7.getCountryId()
                bc.n.b(r4)
                int r4 = r4.intValue()
                r0.f(r1, r2, r4)
                com.vfs.italyglobal.activities.CountrySelection r1 = r6.f9390a
                android.app.Activity r1 = r1.c1()
                com.vfs.italyglobal.activities.CountrySelection r2 = r6.f9390a
                r4 = 2131886688(0x7f120260, float:1.9407962E38)
                java.lang.String r2 = r2.getString(r4)
                bc.n.d(r2, r3)
                java.lang.String r4 = r7.getVfsCountryId()
                bc.n.b(r4)
                r0.g(r1, r2, r4)
                com.vfs.italyglobal.activities.CountrySelection r1 = r6.f9390a
                android.app.Activity r1 = r1.c1()
                com.vfs.italyglobal.activities.CountrySelection r2 = r6.f9390a
                r4 = 2131886383(0x7f12012f, float:1.9407343E38)
                java.lang.String r2 = r2.getString(r4)
                bc.n.d(r2, r3)
                java.lang.String r4 = ""
                r0.g(r1, r2, r4)
                com.vfs.italyglobal.activities.CountrySelection r1 = r6.f9390a
                android.app.Activity r1 = r1.c1()
                com.vfs.italyglobal.activities.CountrySelection r2 = r6.f9390a
                r5 = 2131886314(0x7f1200ea, float:1.9407203E38)
                java.lang.String r2 = r2.getString(r5)
                bc.n.d(r2, r3)
                r0.g(r1, r2, r4)
                java.lang.String r1 = r7.getVfsCountryId()
                java.lang.String r2 = "DZA"
                boolean r1 = bc.n.a(r1, r2)
                java.lang.String r2 = "it"
                java.lang.String r4 = "en"
                if (r1 == 0) goto L_0x00f8
                java.lang.String r7 = r7.getCountryCode()
                if (r7 == 0) goto L_0x00f5
                int r1 = r7.hashCode()
                r5 = 62386373(0x3b7f0c5, float:1.0811051E-36)
                if (r1 == r5) goto L_0x00e9
                r5 = 62386528(0x3b7f160, float:1.081119E-36)
                if (r1 == r5) goto L_0x00dd
                r5 = 62386623(0x3b7f1bf, float:1.08112755E-36)
                if (r1 == r5) goto L_0x00d4
                goto L_0x00f5
            L_0x00d4:
                java.lang.String r1 = "AL_IT"
                boolean r7 = r7.equals(r1)
                if (r7 != 0) goto L_0x00f6
                goto L_0x00f5
            L_0x00dd:
                java.lang.String r1 = "AL_FR"
                boolean r7 = r7.equals(r1)
                if (r7 != 0) goto L_0x00e6
                goto L_0x00f5
            L_0x00e6:
                java.lang.String r2 = "fr"
                goto L_0x00f6
            L_0x00e9:
                java.lang.String r1 = "AL_AR"
                boolean r7 = r7.equals(r1)
                if (r7 != 0) goto L_0x00f2
                goto L_0x00f5
            L_0x00f2:
                java.lang.String r2 = "ar"
                goto L_0x00f6
            L_0x00f5:
                r2 = r4
            L_0x00f6:
                r4 = r2
                goto L_0x011c
            L_0x00f8:
                java.lang.String r1 = r7.getVfsCountryId()
                java.lang.String r5 = "CHL"
                boolean r1 = bc.n.a(r1, r5)
                if (r1 == 0) goto L_0x011c
                java.lang.String r7 = r7.getCountryCode()
                java.lang.String r1 = "CL_IT"
                boolean r1 = bc.n.a(r7, r1)
                if (r1 == 0) goto L_0x0111
                goto L_0x00f6
            L_0x0111:
                java.lang.String r1 = "CL_SP"
                boolean r7 = bc.n.a(r7, r1)
                if (r7 == 0) goto L_0x00f5
                java.lang.String r2 = "es"
                goto L_0x00f6
            L_0x011c:
                com.vfs.italyglobal.activities.CountrySelection r7 = r6.f9390a
                android.app.Activity r7 = r7.c1()
                com.vfs.italyglobal.activities.CountrySelection r1 = r6.f9390a
                r2 = 2131886692(0x7f120264, float:1.940797E38)
                java.lang.String r1 = r1.getString(r2)
                bc.n.d(r1, r3)
                r0.g(r7, r1, r4)
                s1.h r7 = s1.h.b(r4)
                java.lang.String r1 = "forLanguageTags(...)"
                bc.n.d(r7, r1)
                androidx.appcompat.app.h.O(r7)
                com.vfs.italyglobal.activities.CountrySelection r7 = r6.f9390a
                android.app.Activity r7 = r7.c1()
                com.vfs.italyglobal.activities.CountrySelection r1 = r6.f9390a
                r2 = 2131886373(0x7f120125, float:1.9407323E38)
                java.lang.String r1 = r1.getString(r2)
                bc.n.d(r1, r3)
                r2 = 1
                r0.h(r7, r1, r2)
                int r7 = android.os.Build.VERSION.SDK_INT
                r1 = 33
                if (r7 >= r1) goto L_0x0176
                com.vfs.italyglobal.activities.CountrySelection r7 = r6.f9390a
                boolean r7 = r7.f9386i0
                if (r7 != 0) goto L_0x0176
                com.vfs.italyglobal.activities.CountrySelection r7 = r6.f9390a
                android.app.Activity r7 = r7.c1()
                com.vfs.italyglobal.activities.CountrySelection r1 = r6.f9390a
                r4 = 2131886374(0x7f120126, float:1.9407325E38)
                java.lang.String r1 = r1.getString(r4)
                bc.n.d(r1, r3)
                r0.h(r7, r1, r2)
            L_0x0176:
                com.vfs.italyglobal.activities.CountrySelection r7 = r6.f9390a
                r7.k1()
                android.content.Intent r7 = new android.content.Intent
                com.vfs.italyglobal.activities.CountrySelection r0 = r6.f9390a
                android.content.Context r0 = r0.getApplicationContext()
                java.lang.Class<com.vfs.italyglobal.activities.TermsAndConditionActivity> r1 = com.vfs.italyglobal.activities.TermsAndConditionActivity.class
                r7.<init>(r0, r1)
                com.vfs.italyglobal.activities.CountrySelection r0 = r6.f9390a
                r0.startActivity(r7)
                com.vfs.italyglobal.activities.CountrySelection r7 = r6.f9390a
                r0 = 2130771997(0x7f01001d, float:1.71471E38)
                r1 = 2130771998(0x7f01001e, float:1.7147102E38)
                r7.overridePendingTransition(r0, r1)
                com.vfs.italyglobal.activities.CountrySelection r7 = r6.f9390a
                r7.finish()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vfs.italyglobal.activities.CountrySelection.d.a(com.vfs.italyglobal.pojo.CountryList):void");
        }
    }

    public CountrySelection() {
        super(true, false);
    }

    private final void G1() {
        Utility.f9497a.y(c1());
        be.d<CountryData> p10 = g1().p();
        if (K1().f20427h.j()) {
            K1().f20427h.setRefreshing(false);
        }
        try {
            p10.A(new a(this));
        } catch (Exception e10) {
            Utility.f9497a.c();
            e10.toString();
        }
    }

    /* access modifiers changed from: private */
    public final void H1() {
        Utility.f9497a.y(c1());
        g1().j().A(new b(this));
    }

    private final void I1() {
        finish();
    }

    /* access modifiers changed from: private */
    public final xa.i K1() {
        return (xa.i) this.f9383f0.getValue();
    }

    private final void L1() {
        K1().f20421b.setVisibility(0);
        K1().f20424e.setVisibility(8);
        K1().f20425f.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public static final void N1(CountrySelection countrySelection) {
        if (countrySelection.K1().f20427h.j()) {
            countrySelection.K1().f20427h.setRefreshing(false);
        }
        if (com.vfs.italyglobal.utilities.c.j(countrySelection)) {
            countrySelection.L1();
            countrySelection.G1();
            countrySelection.J1().clear();
            ArrayList arrayList = countrySelection.f9384g0;
            if (arrayList == null) {
                n.o("countryList");
                arrayList = null;
            }
            arrayList.clear();
            return;
        }
        countrySelection.S1();
    }

    /* access modifiers changed from: private */
    public static final void O1(CountrySelection countrySelection, View view) {
        countrySelection.I1();
    }

    /* access modifiers changed from: private */
    public static final xa.i P1(CountrySelection countrySelection) {
        return xa.i.c(countrySelection.getLayoutInflater());
    }

    private final void S1() {
        K1().f20421b.setVisibility(8);
        K1().f20424e.setVisibility(0);
        Utility.Companion companion = Utility.f9497a;
        String string = getString(R.string.network_error);
        n.d(string, "getString(...)");
        companion.w(this, string);
    }

    public final ArrayList J1() {
        ArrayList arrayList = this.f9385h0;
        if (arrayList != null) {
            return arrayList;
        }
        n.o("arrCountryList");
        return null;
    }

    public final void M1() {
        Q1(new ArrayList());
        this.f9384g0 = new ArrayList();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Object obj = extras.get(getString(R.string.isFromSplash));
            n.c(obj, "null cannot be cast to non-null type kotlin.Boolean");
            this.f9386i0 = ((Boolean) obj).booleanValue();
        }
        if (!this.f9386i0) {
            K1().f20423d.setVisibility(0);
        } else {
            K1().f20423d.setVisibility(8);
        }
        if (com.vfs.italyglobal.utilities.c.j(this)) {
            G1();
        } else {
            S1();
        }
        K1().f20427h.setOnRefreshListener(new e2(this));
        K1().f20423d.setOnClickListener(new f2(this));
    }

    public final void Q1(ArrayList arrayList) {
        n.e(arrayList, "<set-?>");
        this.f9385h0 = arrayList;
    }

    public final void R1(ArrayList arrayList) {
        n.e(arrayList, "arrCountryList");
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(arrayList);
        ArrayList arrayList3 = this.f9384g0;
        ArrayList arrayList4 = null;
        if (arrayList3 == null) {
            n.o("countryList");
            arrayList3 = null;
        }
        arrayList3.addAll(arrayList);
        Activity c12 = c1();
        ArrayList arrayList5 = this.f9384g0;
        if (arrayList5 == null) {
            n.o("countryList");
        } else {
            arrayList4 = arrayList5;
        }
        b0 b0Var = new b0(c12, arrayList4, arrayList2, new d(this));
        K1().f20426g.setLayoutManager(new LinearLayoutManager(this));
        K1().f20426g.t0();
        K1().f20426g.setAdapter(b0Var);
        K1().f20422c.addTextChangedListener(new c(b0Var));
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) K1().b());
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.isFromLocaleChange);
        n.d(string, "getString(...)");
        if (!aVar.c(c12, string, false)) {
            M1();
            return;
        }
        Activity c13 = c1();
        String string2 = getString(R.string.isFromLocaleChange);
        n.d(string2, "getString(...)");
        aVar.h(c13, string2, false);
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class c implements TextWatcher {

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ b0 f9389b;

        c(b0 b0Var) {
            this.f9389b = b0Var;
        }

        public void onTextChanged(CharSequence charSequence, int i10, int i11, int i12) {
            this.f9389b.getFilter().filter(String.valueOf(charSequence));
        }

        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i10, int i11, int i12) {
        }
    }
}
