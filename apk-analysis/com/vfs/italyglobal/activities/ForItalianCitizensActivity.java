package com.vfs.italyglobal.activities;

import android.os.Bundle;
import android.view.View;
import bc.n;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.pojo.PageCategoryData;
import com.vfs.italyglobal.utilities.Utility;
import nb.h;
import nb.i;
import ua.t2;
import ua.u2;
import va.x;
import xa.k;
import ya.s;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class ForItalianCitizensActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9399f0 = i.a(new t2(this));

    public ForItalianCitizensActivity() {
        super(false, false, 3, (bc.h) null);
    }

    /* access modifiers changed from: private */
    public static final void E1(ForItalianCitizensActivity forItalianCitizensActivity, View view) {
        forItalianCitizensActivity.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final k F1(ForItalianCitizensActivity forItalianCitizensActivity) {
        return k.c(forItalianCitizensActivity.getLayoutInflater());
    }

    private final void G1(String str, int i10, int i11) {
        Bundle bundle = new Bundle();
        bundle.putInt(getString(R.string.pageCatId), i10);
        bundle.putInt(getString(R.string.pageSubCatId), i11);
        bundle.putString(getString(R.string.bundle_name), str);
        s sVar = new s();
        sVar.D1(bundle);
        Y().m().q(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right).n(R.id.fragment_for_italian_citizens, sVar).g();
    }

    public final k C1() {
        return (k) this.f9399f0.getValue();
    }

    public final void D1() {
        C1().f20470c.setOnClickListener(new u2(this));
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) C1().b());
        PageCategoryData i10 = Utility.Companion.i(Utility.f9497a, c1(), "Consular services for Italian abroad", false, 4, (Object) null);
        if (i10 != null) {
            String string = getString(R.string.consular_services_for_italians_abroad);
            n.d(string, "getString(...)");
            Integer pageCategoryId = i10.getPageCategoryId();
            n.b(pageCategoryId);
            int intValue = pageCategoryId.intValue();
            Integer pageSubCategoryId = i10.getPageSubCategoryId();
            n.b(pageSubCategoryId);
            G1(string, intValue, pageSubCategoryId.intValue());
        }
        D1();
    }
}
