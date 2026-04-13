package com.vfs.italyglobal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.phoenixcapture.camerakit.R;
import nb.h;
import nb.i;
import ua.f3;
import ua.g3;
import ua.h3;
import ua.i3;
import ua.j3;
import ua.k3;
import va.x;
import xa.d1;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class ManageAppointmentActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9412f0 = i.a(new k3(this));

    public ManageAppointmentActivity() {
        super(false, false, 3, (bc.h) null);
    }

    private final d1 G1() {
        return (d1) this.f9412f0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void I1(ManageAppointmentActivity manageAppointmentActivity, View view) {
        manageAppointmentActivity.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final void J1(ManageAppointmentActivity manageAppointmentActivity, View view) {
        manageAppointmentActivity.P1();
    }

    /* access modifiers changed from: private */
    public static final void K1(ManageAppointmentActivity manageAppointmentActivity, View view) {
        manageAppointmentActivity.R1();
    }

    /* access modifiers changed from: private */
    public static final void L1(ManageAppointmentActivity manageAppointmentActivity, View view) {
        manageAppointmentActivity.Q1();
    }

    /* access modifiers changed from: private */
    public static final void M1(ManageAppointmentActivity manageAppointmentActivity, View view) {
        manageAppointmentActivity.O1();
    }

    /* access modifiers changed from: private */
    public static final d1 N1(ManageAppointmentActivity manageAppointmentActivity) {
        return d1.c(manageAppointmentActivity.getLayoutInflater());
    }

    private final void O1() {
        Intent intent = new Intent(this, UpdateAppointment.class);
        intent.putExtra(getString(R.string.isToReschedule), false);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    private final void P1() {
        startActivity(new Intent(this, AppointmentGetEarliestDates.class));
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    private final void Q1() {
        Intent intent = new Intent(this, UpdateAppointment.class);
        intent.putExtra(getString(R.string.isToReschedule), true);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    private final void R1() {
        startActivity(new Intent(this, TrackYourApplication.class));
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    public final void H1() {
        G1().f20351c.setOnClickListener(new f3(this));
        G1().f20352d.setOnClickListener(new g3(this));
        G1().f20354f.setOnClickListener(new h3(this));
        G1().f20353e.setOnClickListener(new i3(this));
        G1().f20350b.setOnClickListener(new j3(this));
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) G1().b());
        H1();
    }
}
