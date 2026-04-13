package com.vfs.italyglobal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import bc.n;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.pojo.SearchAppointmentDataResponse;
import com.vfs.italyglobal.pojo.SearchAppointmentDetails;
import java.util.ArrayList;
import java.util.List;
import nb.h;
import nb.i;
import ua.v1;
import ua.w1;
import va.x;
import xa.g;
import za.b;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class BookedAppointmentListActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9358f0 = i.a(new w1(this));

    /* renamed from: g0  reason: collision with root package name */
    private boolean f9359g0;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a implements b {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ BookedAppointmentListActivity f9360a;

        a(BookedAppointmentListActivity bookedAppointmentListActivity) {
            this.f9360a = bookedAppointmentListActivity;
        }

        public void a(SearchAppointmentDetails searchAppointmentDetails) {
            n.e(searchAppointmentDetails, "searchAppointmentDetails");
            this.f9360a.G1(searchAppointmentDetails);
        }
    }

    public BookedAppointmentListActivity() {
        super(false, false, 3, (bc.h) null);
    }

    private final g C1() {
        return (g) this.f9358f0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void E1(BookedAppointmentListActivity bookedAppointmentListActivity, View view) {
        bookedAppointmentListActivity.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final g F1(BookedAppointmentListActivity bookedAppointmentListActivity) {
        return g.c(bookedAppointmentListActivity.getLayoutInflater());
    }

    public final void D1() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Object obj = extras.get(getString(R.string.isToReschedule));
            n.c(obj, "null cannot be cast to non-null type kotlin.Boolean");
            this.f9359g0 = ((Boolean) obj).booleanValue();
            H1((SearchAppointmentDataResponse) extras.get(getString(R.string.search_appmnt_list)));
        }
        C1().f20398b.setOnClickListener(new v1(this));
    }

    public final void G1(SearchAppointmentDetails searchAppointmentDetails) {
        n.e(searchAppointmentDetails, "searchAppointmentDetails");
        Intent intent = new Intent(this, BookedAppointmentDetails.class);
        intent.putExtra(getString(R.string.isToReschedule), this.f9359g0);
        intent.putExtra(getString(R.string.booked_appmnt_applcnt_details), searchAppointmentDetails);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    public final void H1(SearchAppointmentDataResponse searchAppointmentDataResponse) {
        n.b(searchAppointmentDataResponse);
        List<SearchAppointmentDetails> data = searchAppointmentDataResponse.getData();
        n.c(data, "null cannot be cast to non-null type java.util.ArrayList<com.vfs.italyglobal.pojo.SearchAppointmentDetails>");
        wa.i iVar = new wa.i((ArrayList) data, new a(this));
        C1().f20399c.setLayoutManager(new LinearLayoutManager(this));
        C1().f20399c.t0();
        C1().f20399c.setAdapter(iVar);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) C1().b());
        D1();
    }
}
