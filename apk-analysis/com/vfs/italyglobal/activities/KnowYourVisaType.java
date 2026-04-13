package com.vfs.italyglobal.activities;

import ac.p;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import bc.n;
import be.f;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.pojo.MasterIdValue;
import com.vfs.italyglobal.pojo.MasterResponseData;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.g;
import java.util.ArrayList;
import java.util.List;
import nb.h;
import nb.i;
import ua.c3;
import ua.d3;
import ua.e3;
import va.x;
import xa.c1;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class KnowYourVisaType extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9403f0 = i.a(new c3(this));

    /* renamed from: g0  reason: collision with root package name */
    private ArrayList f9404g0 = new ArrayList();

    /* renamed from: h0  reason: collision with root package name */
    private ArrayList f9405h0 = new ArrayList();

    /* renamed from: i0  reason: collision with root package name */
    private ArrayList f9406i0 = new ArrayList();

    /* renamed from: j0  reason: collision with root package name */
    private ArrayList f9407j0 = new ArrayList();

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
            view2.setPadding(0, 0, 0, 0);
            return view2;
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class b implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ KnowYourVisaType f9408a;

        b(KnowYourVisaType knowYourVisaType) {
            this.f9408a = knowYourVisaType;
        }

        public void a(be.d dVar, be.x xVar) {
            List<MasterIdValue> extraData;
            n.e(dVar, "call");
            n.e(xVar, "response");
            MasterResponseData masterResponseData = (MasterResponseData) xVar.a();
            Utility.f9497a.c();
            if (masterResponseData != null) {
                KnowYourVisaType knowYourVisaType = this.f9408a;
                if (masterResponseData.getExtraData() != null && (extraData = masterResponseData.getExtraData()) != null && !extraData.isEmpty()) {
                    knowYourVisaType.G1().clear();
                    knowYourVisaType.H1().clear();
                    List<MasterIdValue> extraData2 = masterResponseData.getExtraData();
                    n.b(extraData2);
                    int size = extraData2.size();
                    for (int i10 = 0; i10 < size; i10++) {
                        ArrayList H1 = knowYourVisaType.H1();
                        List<MasterIdValue> extraData3 = masterResponseData.getExtraData();
                        n.b(extraData3);
                        String value = extraData3.get(i10).getValue();
                        n.b(value);
                        H1.add(value);
                        ArrayList G1 = knowYourVisaType.G1();
                        List<MasterIdValue> extraData4 = masterResponseData.getExtraData();
                        n.b(extraData4);
                        G1.add(String.valueOf(extraData4.get(i10).getId()));
                    }
                    knowYourVisaType.R1();
                    return;
                }
                return;
            }
            com.vfs.italyglobal.utilities.c.i(xVar, this.f9408a.c1(), (p) null, 2, (Object) null);
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            Activity c12 = this.f9408a.c1();
            String localizedMessage = th.getLocalizedMessage();
            n.d(localizedMessage, "getLocalizedMessage(...)");
            companion.w(c12, localizedMessage);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class c implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ KnowYourVisaType f9409a;

        c(KnowYourVisaType knowYourVisaType) {
            this.f9409a = knowYourVisaType;
        }

        public void a(be.d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            MasterResponseData masterResponseData = (MasterResponseData) xVar.a();
            Utility.f9497a.c();
            if (masterResponseData != null) {
                KnowYourVisaType knowYourVisaType = this.f9409a;
                if (masterResponseData.getExtraData() != null) {
                    List<MasterIdValue> extraData = masterResponseData.getExtraData();
                    n.b(extraData);
                    if (extraData != null && !extraData.isEmpty()) {
                        knowYourVisaType.I1().clear();
                        knowYourVisaType.J1().clear();
                        List<MasterIdValue> extraData2 = masterResponseData.getExtraData();
                        n.b(extraData2);
                        int size = extraData2.size();
                        for (int i10 = 0; i10 < size; i10++) {
                            ArrayList J1 = knowYourVisaType.J1();
                            List<MasterIdValue> extraData3 = masterResponseData.getExtraData();
                            n.b(extraData3);
                            String value = extraData3.get(i10).getValue();
                            n.b(value);
                            J1.add(value);
                            ArrayList I1 = knowYourVisaType.I1();
                            List<MasterIdValue> extraData4 = masterResponseData.getExtraData();
                            n.b(extraData4);
                            I1.add(String.valueOf(extraData4.get(i10).getId()));
                        }
                        knowYourVisaType.Q1();
                        return;
                    }
                    return;
                }
                return;
            }
            com.vfs.italyglobal.utilities.c.i(xVar, this.f9409a.c1(), (p) null, 2, (Object) null);
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            throw new nb.n("An operation is not implemented: " + "not implemented");
        }
    }

    public KnowYourVisaType() {
        super(false, false, 3, (bc.h) null);
    }

    private final void E1() {
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.embassy_location_Id);
        n.d(string, "getString(...)");
        g1().s(aVar.b(c12, string, "")).A(new b(this));
    }

    /* access modifiers changed from: private */
    public final c1 K1() {
        return (c1) this.f9403f0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void M1(KnowYourVisaType knowYourVisaType, View view) {
        knowYourVisaType.P1();
    }

    /* access modifiers changed from: private */
    public static final void N1(KnowYourVisaType knowYourVisaType, View view) {
        knowYourVisaType.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final c1 O1(KnowYourVisaType knowYourVisaType) {
        return c1.c(knowYourVisaType.getLayoutInflater());
    }

    private final void P1() {
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.visa_cat_id);
        n.d(string, "getString(...)");
        if (aVar.b(c12, string, "").length() == 0) {
            Utility.Companion companion = Utility.f9497a;
            String string2 = getString(R.string.visa_cat_validation);
            n.d(string2, "getString(...)");
            companion.w(this, string2);
            return;
        }
        Activity c13 = c1();
        String string3 = getString(R.string.visa_sub_cat_id);
        n.d(string3, "getString(...)");
        if (aVar.b(c13, string3, "").length() == 0) {
            Utility.Companion companion2 = Utility.f9497a;
            String string4 = getString(R.string.visa_sub_cat_validation);
            n.d(string4, "getString(...)");
            companion2.w(this, string4);
            return;
        }
        startActivity(new Intent(this, OverviewVisaActivity.class));
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    public final void F1(String str) {
        n.e(str, "visaCatId");
        g1().v(str).A(new c(this));
    }

    public final ArrayList G1() {
        return this.f9405h0;
    }

    public final ArrayList H1() {
        return this.f9404g0;
    }

    public final ArrayList I1() {
        return this.f9407j0;
    }

    public final ArrayList J1() {
        return this.f9406i0;
    }

    public final void L1() {
        if (com.vfs.italyglobal.utilities.c.j(this)) {
            Utility.f9497a.y(c1());
            E1();
        }
        if (n.a(Utility.f9497a.k(c1()), "CHL")) {
            K1().f20333i.setText(getString(R.string.know_your_citizenship_type));
            K1().f20332h.setText(getString(R.string.txt_citizenship_information));
            K1().f20331g.setText(getString(R.string.select_citizenship_category));
            K1().f20330f.setText(getString(R.string.select_sub_citizenship_category));
        }
        K1().f20328d.setOnItemSelectedListener(new d(this));
        K1().f20329e.setOnItemSelectedListener(new e(this));
        K1().f20326b.setOnClickListener(new d3(this));
        K1().f20327c.setOnClickListener(new e3(this));
    }

    public final void Q1() {
        a aVar = new a(this, R.layout.simple_spinner_main_item, this.f9406i0);
        aVar.setDropDownViewResource(R.layout.simple_spinner_item);
        K1().f20329e.setAdapter((SpinnerAdapter) aVar);
    }

    public final void R1() {
        a aVar = new a(this, R.layout.simple_spinner_main_item, this.f9404g0);
        aVar.setDropDownViewResource(R.layout.simple_spinner_item);
        K1().f20328d.setAdapter((SpinnerAdapter) aVar);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) K1().b());
        L1();
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class d implements AdapterView.OnItemSelectedListener {

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ KnowYourVisaType f9410b;

        d(KnowYourVisaType knowYourVisaType) {
            this.f9410b = knowYourVisaType;
        }

        public void onItemSelected(AdapterView adapterView, View view, int i10, long j10) {
            Object obj = this.f9410b.G1().get(this.f9410b.K1().f20328d.getSelectedItemPosition());
            n.d(obj, "get(...)");
            String str = (String) obj;
            String str2 = ((String) this.f9410b.H1().get(i10)).toString();
            g.a aVar = g.f9512a;
            Activity c12 = this.f9410b.c1();
            String string = this.f9410b.getString(R.string.visa_cat_id);
            n.d(string, "getString(...)");
            aVar.g(c12, string, str);
            Activity c13 = this.f9410b.c1();
            String string2 = this.f9410b.getString(R.string.visa_cat_name);
            n.d(string2, "getString(...)");
            aVar.g(c13, string2, str2);
            this.f9410b.F1(str);
            Utility.f9497a.y(this.f9410b.c1());
        }

        public void onNothingSelected(AdapterView adapterView) {
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class e implements AdapterView.OnItemSelectedListener {

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ KnowYourVisaType f9411b;

        e(KnowYourVisaType knowYourVisaType) {
            this.f9411b = knowYourVisaType;
        }

        public void onItemSelected(AdapterView adapterView, View view, int i10, long j10) {
            Object obj = this.f9411b.I1().get(this.f9411b.K1().f20329e.getSelectedItemPosition());
            n.d(obj, "get(...)");
            String str = ((String) this.f9411b.J1().get(i10)).toString();
            g.a aVar = g.f9512a;
            Activity c12 = this.f9411b.c1();
            String string = this.f9411b.getString(R.string.visa_sub_cat_id);
            n.d(string, "getString(...)");
            aVar.g(c12, string, (String) obj);
            Activity c13 = this.f9411b.c1();
            String string2 = this.f9411b.getString(R.string.visa_sub_cat_name);
            n.d(string2, "getString(...)");
            aVar.g(c13, string2, str);
        }

        public void onNothingSelected(AdapterView adapterView) {
        }
    }
}
