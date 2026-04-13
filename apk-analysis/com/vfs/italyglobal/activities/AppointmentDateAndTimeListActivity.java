package com.vfs.italyglobal.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bc.n;
import com.applandeo.materialcalendarview.CalendarView;
import com.google.gson.j;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.activities.AppointmentLogin;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.pojo.AppointmentRescheduleDataResponse;
import com.vfs.italyglobal.pojo.ErrorMessage;
import com.vfs.italyglobal.pojo.SearchApplicantDetails;
import com.vfs.italyglobal.pojo.SearchAppointmentDetails;
import com.vfs.italyglobal.pojo.Slots;
import com.vfs.italyglobal.pojo.TimeSlotResponse;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.g;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import nb.h;
import nb.i;
import nb.o;
import ob.p;
import ua.c0;
import ua.e0;
import ua.f0;
import ua.g0;
import ua.h0;
import ua.i0;
import ua.j0;
import va.x;
import wa.d0;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class AppointmentDateAndTimeListActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9281f0 = i.a(new c0(this));

    /* renamed from: g0  reason: collision with root package name */
    private final ArrayList f9282g0 = new ArrayList();

    /* renamed from: h0  reason: collision with root package name */
    private final ArrayList f9283h0 = new ArrayList();

    /* renamed from: i0  reason: collision with root package name */
    private boolean f9284i0;

    /* renamed from: j0  reason: collision with root package name */
    private SearchAppointmentDetails f9285j0 = new SearchAppointmentDetails();

    /* renamed from: k0  reason: collision with root package name */
    private int f9286k0;

    /* renamed from: l0  reason: collision with root package name */
    private Bundle f9287l0;

    /* renamed from: m0  reason: collision with root package name */
    private DatePickerDialog f9288m0;

    /* renamed from: n0  reason: collision with root package name */
    private boolean f9289n0 = true;
    /* access modifiers changed from: private */

    /* renamed from: o0  reason: collision with root package name */
    public List f9290o0;

    /* renamed from: p0  reason: collision with root package name */
    private SimpleDateFormat f9291p0;

    /* renamed from: q0  reason: collision with root package name */
    private String f9292q0;
    /* access modifiers changed from: private */

    /* renamed from: r0  reason: collision with root package name */
    public List f9293r0 = new ArrayList();
    /* access modifiers changed from: private */

    /* renamed from: s0  reason: collision with root package name */
    public int f9294s0 = -1;
    /* access modifiers changed from: private */

    /* renamed from: t0  reason: collision with root package name */
    public int f9295t0 = -1;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a implements be.f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentDateAndTimeListActivity f9296a;

        a(AppointmentDateAndTimeListActivity appointmentDateAndTimeListActivity) {
            this.f9296a = appointmentDateAndTimeListActivity;
        }

        /* JADX WARNING: type inference failed for: r1v2 */
        /* JADX WARNING: type inference failed for: r1v3, types: [java.lang.CharSequence] */
        /* JADX WARNING: type inference failed for: r1v5, types: [java.util.List] */
        /* JADX WARNING: type inference failed for: r1v6 */
        /* JADX WARNING: type inference failed for: r1v7 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(be.d r4, be.x r5) {
            /*
                r3 = this;
                java.lang.String r0 = "call"
                bc.n.e(r4, r0)
                java.lang.String r4 = "response"
                bc.n.e(r5, r4)
                com.vfs.italyglobal.utilities.Utility$Companion r4 = com.vfs.italyglobal.utilities.Utility.f9497a
                r4.c()
                java.lang.Object r5 = r5.a()
                com.vfs.italyglobal.pojo.CalenderResponse r5 = (com.vfs.italyglobal.pojo.CalenderResponse) r5
                if (r5 == 0) goto L_0x00b6
                java.util.ArrayList r0 = r5.getCalendars()
                com.vfs.italyglobal.pojo.ErrorMessage r5 = r5.getError()
                r1 = 0
                if (r0 == 0) goto L_0x007c
                boolean r2 = r0.isEmpty()
                if (r2 == 0) goto L_0x0029
                goto L_0x007c
            L_0x0029:
                com.vfs.italyglobal.activities.AppointmentDateAndTimeListActivity r4 = r3.f9296a
                java.util.ArrayList r5 = new java.util.ArrayList
                r2 = 10
                int r2 = ob.p.t(r0, r2)
                r5.<init>(r2)
                java.util.Iterator r0 = r0.iterator()
            L_0x003a:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L_0x004e
                java.lang.Object r2 = r0.next()
                com.vfs.italyglobal.pojo.Calendars r2 = (com.vfs.italyglobal.pojo.Calendars) r2
                java.lang.String r2 = r2.getDate()
                r5.add(r2)
                goto L_0x003a
            L_0x004e:
                java.util.List r5 = ob.p.F(r5)
                java.lang.String r0 = "null cannot be cast to non-null type kotlin.collections.List<kotlin.String>"
                bc.n.c(r5, r0)
                r4.f9290o0 = r5
                com.vfs.italyglobal.activities.AppointmentDateAndTimeListActivity r4 = r3.f9296a
                java.util.List r4 = r4.f9290o0
                java.lang.String r5 = "enabledDates"
                if (r4 != 0) goto L_0x0068
                bc.n.o(r5)
                r4 = r1
            L_0x0068:
                java.util.Objects.toString(r4)
                com.vfs.italyglobal.activities.AppointmentDateAndTimeListActivity r4 = r3.f9296a
                java.util.List r0 = r4.f9290o0
                if (r0 != 0) goto L_0x0077
                bc.n.o(r5)
                goto L_0x0078
            L_0x0077:
                r1 = r0
            L_0x0078:
                r4.h2(r1)
                return
            L_0x007c:
                if (r5 == 0) goto L_0x0082
                java.lang.String r1 = r5.getDescription()
            L_0x0082:
                if (r1 == 0) goto L_0x009c
                int r0 = r1.length()
                if (r0 != 0) goto L_0x008b
                goto L_0x009c
            L_0x008b:
                com.vfs.italyglobal.activities.AppointmentDateAndTimeListActivity r0 = r3.f9296a
                android.app.Activity r0 = r0.c1()
                java.lang.String r5 = r5.getDescription()
                bc.n.b(r5)
                r4.w(r0, r5)
                return
            L_0x009c:
                com.vfs.italyglobal.activities.AppointmentDateAndTimeListActivity r4 = r3.f9296a
                xa.b r4 = r4.a2()
                androidx.recyclerview.widget.RecyclerView r4 = r4.f20294d
                r5 = 8
                r4.setVisibility(r5)
                com.vfs.italyglobal.activities.AppointmentDateAndTimeListActivity r4 = r3.f9296a
                xa.b r4 = r4.a2()
                androidx.appcompat.widget.AppCompatTextView r4 = r4.f20296f
                r5 = 0
                r4.setVisibility(r5)
                return
            L_0x00b6:
                com.vfs.italyglobal.activities.AppointmentDateAndTimeListActivity r5 = r3.f9296a
                android.app.Activity r5 = r5.c1()
                com.vfs.italyglobal.activities.AppointmentDateAndTimeListActivity r0 = r3.f9296a
                r1 = 2131886350(0x7f12010e, float:1.9407276E38)
                java.lang.String r0 = r0.getString(r1)
                java.lang.String r1 = "getString(...)"
                bc.n.d(r0, r1)
                r4.w(r5, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vfs.italyglobal.activities.AppointmentDateAndTimeListActivity.a.a(be.d, be.x):void");
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            Activity c12 = this.f9296a.c1();
            String localizedMessage = th.getLocalizedMessage();
            n.b(localizedMessage);
            companion.w(c12, localizedMessage);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class b implements be.f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentDateAndTimeListActivity f9297a;

        b(AppointmentDateAndTimeListActivity appointmentDateAndTimeListActivity) {
            this.f9297a = appointmentDateAndTimeListActivity;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(AppointmentDateAndTimeListActivity appointmentDateAndTimeListActivity, int i10, String str) {
            n.e(str, "<unused var>");
            AppointmentLogin.a.c(AppointmentLogin.f9338h0, appointmentDateAndTimeListActivity.c1(), i10, false, 4, (Object) null);
            return nb.x.f15721a;
        }

        public void a(be.d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            AppointmentRescheduleDataResponse appointmentRescheduleDataResponse = (AppointmentRescheduleDataResponse) xVar.a();
            if (appointmentRescheduleDataResponse != null) {
                Boolean isAppointmentRescheduled = appointmentRescheduleDataResponse.getIsAppointmentRescheduled();
                n.b(isAppointmentRescheduled);
                if (isAppointmentRescheduled.booleanValue()) {
                    AppointmentDateAndTimeListActivity appointmentDateAndTimeListActivity = this.f9297a;
                    String string = appointmentDateAndTimeListActivity.getString(R.string.reschedule_success_message);
                    n.d(string, "getString(...)");
                    appointmentDateAndTimeListActivity.l2(string, String.valueOf(appointmentRescheduleDataResponse.getUrn()), String.valueOf(appointmentRescheduleDataResponse.getAppointmentDate()), String.valueOf(appointmentRescheduleDataResponse.getAppointmentTime()));
                } else if (appointmentRescheduleDataResponse.getError() != null) {
                    Activity c12 = this.f9297a.c1();
                    ErrorMessage error = appointmentRescheduleDataResponse.getError();
                    n.b(error);
                    companion.w(c12, String.valueOf(error.getDescription()));
                } else {
                    Activity c13 = this.f9297a.c1();
                    String string2 = this.f9297a.getString(R.string.generic_error);
                    n.d(string2, "getString(...)");
                    companion.w(c13, string2);
                }
            } else {
                com.vfs.italyglobal.utilities.c.h(xVar, this.f9297a.c1(), new j0(this.f9297a));
            }
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            if (th instanceof SocketTimeoutException) {
                companion.z(this.f9297a.c1());
                this.f9297a.onBackPressed();
                return;
            }
            String localizedMessage = th.getLocalizedMessage();
            if (localizedMessage != null) {
                companion.w(this.f9297a.c1(), localizedMessage);
            }
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class c implements be.f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentDateAndTimeListActivity f9298a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ String f9299b;

        c(AppointmentDateAndTimeListActivity appointmentDateAndTimeListActivity, String str) {
            this.f9298a = appointmentDateAndTimeListActivity;
            this.f9299b = str;
        }

        public void a(be.d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            TimeSlotResponse timeSlotResponse = (TimeSlotResponse) xVar.a();
            if (timeSlotResponse != null) {
                String error = timeSlotResponse.getError();
                if (error == null || error.length() == 0) {
                    List slots = timeSlotResponse.getSlots();
                    if (slots == null) {
                        slots = p.k();
                    }
                    if (!slots.isEmpty()) {
                        this.f9298a.a2().f20294d.setVisibility(0);
                        this.f9298a.a2().f20296f.setVisibility(8);
                        this.f9298a.g2(slots, this.f9299b);
                        return;
                    }
                    this.f9298a.a2().f20294d.setVisibility(8);
                    this.f9298a.a2().f20296f.setVisibility(0);
                    return;
                }
                Activity c12 = this.f9298a.c1();
                String error2 = timeSlotResponse.getError();
                n.b(error2);
                companion.w(c12, error2);
                return;
            }
            Activity c13 = this.f9298a.c1();
            String string = this.f9298a.getString(R.string.generic_error);
            n.d(string, "getString(...)");
            companion.w(c13, string);
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            Activity c12 = this.f9298a.c1();
            String localizedMessage = th.getLocalizedMessage();
            n.b(localizedMessage);
            companion.w(c12, localizedMessage);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class d implements Comparator {
        public final int compare(Object obj, Object obj2) {
            Date date;
            Date date2;
            try {
                String slot = ((Slots) obj).getSlot();
                n.b(slot);
                date = new SimpleDateFormat("HH:mm", Locale.getDefault()).parse((String) kc.n.C0(slot, new String[]{"-"}, false, 0, 6, (Object) null).get(0));
            } catch (Exception unused) {
                date = new Date(0);
            }
            try {
                String slot2 = ((Slots) obj2).getSlot();
                n.b(slot2);
                date2 = new SimpleDateFormat("HH:mm", Locale.getDefault()).parse((String) kc.n.C0(slot2, new String[]{"-"}, false, 0, 6, (Object) null).get(0));
            } catch (Exception unused2) {
                date2 = new Date(0);
            }
            return qb.a.a(date, date2);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class e implements a4.f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentDateAndTimeListActivity f9300a;

        e(AppointmentDateAndTimeListActivity appointmentDateAndTimeListActivity) {
            this.f9300a = appointmentDateAndTimeListActivity;
        }

        public void a(x3.f fVar) {
            n.e(fVar, "eventDay");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Calendar a10 = fVar.a();
            String format = simpleDateFormat.format(a10.getTime());
            List K1 = this.f9300a.f9293r0;
            boolean z10 = false;
            if (K1 == null || !K1.isEmpty()) {
                Iterator it = K1.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Calendar calendar = (Calendar) it.next();
                    if (calendar.get(1) == a10.get(1) && calendar.get(2) == a10.get(2) && calendar.get(5) == a10.get(5)) {
                        z10 = true;
                        break;
                    }
                }
            }
            if (z10) {
                this.f9300a.a2().f20292b.setDate(a10);
                AppointmentDateAndTimeListActivity appointmentDateAndTimeListActivity = this.f9300a;
                n.b(format);
                appointmentDateAndTimeListActivity.W1(format);
            }
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class f implements a4.e {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentDateAndTimeListActivity f9301a;

        f(AppointmentDateAndTimeListActivity appointmentDateAndTimeListActivity) {
            this.f9301a = appointmentDateAndTimeListActivity;
        }

        public void a() {
            Calendar currentPageDate = this.f9301a.a2().f20292b.getCurrentPageDate();
            int i10 = currentPageDate.get(2);
            int i11 = currentPageDate.get(1);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            if (!(this.f9301a.f9294s0 == i10 && this.f9301a.f9295t0 == i11)) {
                this.f9301a.f9294s0 = i10;
                this.f9301a.f9295t0 = i11;
                Calendar instance = Calendar.getInstance();
                instance.set(i11, i10, 1);
                String format = simpleDateFormat.format(instance.getTime());
                AppointmentDateAndTimeListActivity appointmentDateAndTimeListActivity = this.f9301a;
                n.b(format);
                appointmentDateAndTimeListActivity.U1(format);
            }
            this.f9301a.q2(i11, i10);
        }
    }

    public AppointmentDateAndTimeListActivity() {
        super(false, false, 3, (bc.h) null);
    }

    /* access modifiers changed from: private */
    public final void U1(String str) {
        String str2;
        String str3;
        String appointmentCfmLift;
        Utility.Companion companion = Utility.f9497a;
        companion.y(c1());
        j jVar = new j();
        String q10 = companion.q();
        String k10 = companion.k(c1());
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.appmnt_center_code);
        n.d(string, "getString(...)");
        String str4 = "";
        String b10 = aVar.b(c12, string, str4);
        jVar.r("missionCode", q10);
        jVar.r("countryCode", k10);
        jVar.r("centerCode", b10);
        Activity c13 = c1();
        String string2 = getString(R.string.appmnt_visa_sub_category_code);
        n.d(string2, "getString(...)");
        jVar.r("visaCategoryCode", aVar.b(c13, string2, str4));
        Activity c14 = c1();
        String string3 = getString(R.string.appmnt_login_loggedInUser);
        n.d(string3, "getString(...)");
        jVar.r("loginUser", aVar.b(c14, string3, str4));
        jVar.r("fromDate", str);
        Activity c15 = c1();
        String string4 = getString(R.string.appointment_URN);
        n.d(string4, "getString(...)");
        jVar.r("urn", aVar.b(c15, string4, str4));
        Activity c16 = c1();
        String string5 = getString(R.string.appmnt_login_authToken);
        n.d(string5, "getString(...)");
        String b11 = aVar.b(c16, string5, str4);
        String e10 = companion.e(this, companion.l());
        HashMap hashMap = new HashMap();
        hashMap.put("Authorize", b11);
        AppConfigModel e11 = d1().e();
        if (e11 == null || (str2 = e11.getAppointmentUrlOrigin()) == null) {
            str2 = str4;
        }
        hashMap.put("referer", str2);
        AppConfigModel e12 = d1().e();
        if (e12 == null || (str3 = e12.getAppointmentUrlOrigin()) == null) {
            str3 = str4;
        }
        hashMap.put("origin", str3);
        AppConfigModel e13 = d1().e();
        if (!(e13 == null || (appointmentCfmLift = e13.getAppointmentCfmLift()) == null)) {
            str4 = appointmentCfmLift;
        }
        hashMap.put("cfmlift", str4);
        hashMap.put("ClientSource", e10);
        f1().i(hashMap, jVar).A(new a(this));
    }

    private final void V1(String str) {
        String str2;
        String str3;
        String appointmentCfmLift;
        j jVar = new j();
        Utility.Companion companion = Utility.f9497a;
        String q10 = companion.q();
        String k10 = companion.k(c1());
        jVar.r("missionCode", q10);
        jVar.r("countryCode", k10);
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.appmnt_center_code);
        n.d(string, "getString(...)");
        String str4 = "";
        jVar.r("centerCode", aVar.b(c12, string, str4));
        Activity c13 = c1();
        String string2 = getString(R.string.appmnt_login_loggedInUser);
        n.d(string2, "getString(...)");
        jVar.r("loginUser", aVar.b(c13, string2, str4));
        Activity c14 = c1();
        String string3 = getString(R.string.appointment_URN);
        n.d(string3, "getString(...)");
        jVar.r("urn", aVar.b(c14, string3, str4));
        jVar.q("allocationId", Integer.valueOf(Integer.parseInt(str)));
        jVar.r("paymentMode", "VAC");
        jVar.q("totalAmount", 150);
        Activity c15 = c1();
        String string4 = getString(R.string.appmnt_login_authToken);
        n.d(string4, "getString(...)");
        String b10 = aVar.b(c15, string4, str4);
        String e10 = companion.e(this, companion.l());
        HashMap hashMap = new HashMap();
        hashMap.put("Authorize", b10);
        AppConfigModel e11 = d1().e();
        if (e11 == null || (str2 = e11.getAppointmentUrlOrigin()) == null) {
            str2 = str4;
        }
        hashMap.put("referer", str2);
        AppConfigModel e12 = d1().e();
        if (e12 == null || (str3 = e12.getAppointmentUrlOrigin()) == null) {
            str3 = str4;
        }
        hashMap.put("origin", str3);
        AppConfigModel e13 = d1().e();
        if (!(e13 == null || (appointmentCfmLift = e13.getAppointmentCfmLift()) == null)) {
            str4 = appointmentCfmLift;
        }
        hashMap.put("cfmlift", str4);
        hashMap.put("ClientSource", e10);
        f1().g(hashMap, jVar).A(new b(this));
    }

    /* access modifiers changed from: private */
    public final void W1(String str) {
        String str2;
        String str3;
        String appointmentCfmLift;
        Utility.Companion companion = Utility.f9497a;
        companion.y(c1());
        j jVar = new j();
        String q10 = companion.q();
        String k10 = companion.k(c1());
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.appmnt_center_code);
        n.d(string, "getString(...)");
        String str4 = "";
        String b10 = aVar.b(c12, string, str4);
        jVar.r("missionCode", q10);
        jVar.r("countryCode", k10);
        jVar.r("centerCode", b10);
        Activity c13 = c1();
        String string2 = getString(R.string.appmnt_login_loggedInUser);
        n.d(string2, "getString(...)");
        jVar.r("loginUser", aVar.b(c13, string2, str4));
        Activity c14 = c1();
        String string3 = getString(R.string.appmnt_visa_sub_category_code);
        n.d(string3, "getString(...)");
        jVar.r("visaCategoryCode", aVar.b(c14, string3, str4));
        jVar.r("slotDate", str);
        Activity c15 = c1();
        String string4 = getString(R.string.appointment_URN);
        n.d(string4, "getString(...)");
        jVar.r("urn", aVar.b(c15, string4, str4));
        Activity c16 = c1();
        String string5 = getString(R.string.appmnt_login_authToken);
        n.d(string5, "getString(...)");
        String b11 = aVar.b(c16, string5, str4);
        String e10 = companion.e(this, companion.l());
        HashMap hashMap = new HashMap();
        hashMap.put("Authorize", b11);
        AppConfigModel e11 = d1().e();
        if (e11 == null || (str2 = e11.getAppointmentUrlOrigin()) == null) {
            str2 = str4;
        }
        hashMap.put("referer", str2);
        AppConfigModel e12 = d1().e();
        if (e12 == null || (str3 = e12.getAppointmentUrlOrigin()) == null) {
            str3 = str4;
        }
        hashMap.put("origin", str3);
        AppConfigModel e13 = d1().e();
        if (!(e13 == null || (appointmentCfmLift = e13.getAppointmentCfmLift()) == null)) {
            str4 = appointmentCfmLift;
        }
        hashMap.put("cfmlift", str4);
        hashMap.put("ClientSource", e10);
        f1().d(hashMap, jVar).A(new c(this, str));
    }

    private final void X1() {
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.appointment_URN);
        n.d(string, "getString(...)");
        aVar.g(c12, string, "");
    }

    private final void Y1() {
        if (!this.f9289n0) {
            this.f9283h0.clear();
            this.f9282g0.clear();
            a2().f20294d.setVisibility(8);
            a2().f20296f.setVisibility(0);
        }
    }

    private final void Z1() {
        this.f9292q0 = null;
        a2().f20294d.setAdapter((RecyclerView.h) null);
        a2().f20294d.setVisibility(8);
        a2().f20296f.setVisibility(0);
        a2().f20292b.setSelectedDates(p.k());
    }

    /* access modifiers changed from: private */
    public final xa.b a2() {
        return (xa.b) this.f9281f0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void c2(AppointmentDateAndTimeListActivity appointmentDateAndTimeListActivity, View view) {
        appointmentDateAndTimeListActivity.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static final xa.b d2(AppointmentDateAndTimeListActivity appointmentDateAndTimeListActivity) {
        return xa.b.c(appointmentDateAndTimeListActivity.getLayoutInflater());
    }

    private final void e2() {
        startActivity(new Intent(this, AppointmentListActivity.class));
        finish();
    }

    private final void f2(Slots slots, String str) {
        Intent intent = new Intent(this, VASListActivity.class);
        intent.putExtra(getString(R.string.bundle_appmntTimeSlots), slots);
        intent.putExtra(getString(R.string.bundle_appmnt_selected_slot_date), str);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    /* access modifiers changed from: private */
    public final void g2(List list, String str) {
        String slot;
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Slots slots = (Slots) it.next();
            String categoryCode = slots.getCategoryCode();
            if (categoryCode == null) {
                categoryCode = "";
            }
            if (!(!kc.n.Q(categoryCode, "GU", false, 2, (Object) null) || (slot = slots.getSlot()) == null || slot.length() == 0)) {
                arrayList.add(slots);
            }
        }
        if (arrayList.size() > 1) {
            p.w(arrayList, new d());
        }
        if (!arrayList.isEmpty()) {
            a2().f20296f.setVisibility(8);
            a2().f20294d.setVisibility(0);
            j2(arrayList, str);
        } else {
            a2().f20296f.setVisibility(0);
            a2().f20294d.setVisibility(8);
        }
        arrayList.size();
    }

    /* access modifiers changed from: private */
    public final void h2(List list) {
        Object obj;
        this.f9293r0.clear();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            try {
                Date parse = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).parse((String) it.next());
                if (parse != null) {
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(parse);
                    List list2 = this.f9293r0;
                    n.b(instance);
                    list2.add(instance);
                }
            } catch (Exception e10) {
                e10.printStackTrace();
            }
        }
        Objects.toString(this.f9293r0);
        if (!this.f9293r0.isEmpty()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date parse2 = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            Calendar instance2 = Calendar.getInstance();
            n.b(parse2);
            instance2.setTime(parse2);
            CalendarView calendarView = a2().f20292b;
            n.b(instance2);
            calendarView.setMinimumDate(instance2);
        }
        if (!this.f9293r0.isEmpty()) {
            Iterator it2 = this.f9293r0.iterator();
            if (!it2.hasNext()) {
                obj = null;
            } else {
                Object next = it2.next();
                if (!it2.hasNext()) {
                    obj = next;
                } else {
                    long timeInMillis = ((Calendar) next).getTimeInMillis();
                    do {
                        Object next2 = it2.next();
                        long timeInMillis2 = ((Calendar) next2).getTimeInMillis();
                        if (timeInMillis > timeInMillis2) {
                            next = next2;
                            timeInMillis = timeInMillis2;
                        }
                    } while (it2.hasNext());
                }
                obj = next;
            }
            n.b(obj);
            Calendar calendar = (Calendar) obj;
            a2().f20292b.setDate(calendar);
            String format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.getTime());
            n.d(format, "format(...)");
            W1(format);
        }
        a2().f20292b.post(new e0(this));
        a2().f20292b.setOnDayClickListener(new e(this));
        f fVar = new f(this);
        a2().f20292b.setOnForwardPageChangeListener(fVar);
        a2().f20292b.setOnPreviousPageChangeListener(fVar);
    }

    /* access modifiers changed from: private */
    public static final void i2(AppointmentDateAndTimeListActivity appointmentDateAndTimeListActivity) {
        Calendar currentPageDate = appointmentDateAndTimeListActivity.a2().f20292b.getCurrentPageDate();
        appointmentDateAndTimeListActivity.q2(currentPageDate.get(1), currentPageDate.get(2));
        appointmentDateAndTimeListActivity.a2().f20292b.setSelectionBackground(R.drawable.custom_selected_date);
    }

    private final void j2(List list, String str) {
        RecyclerView recyclerView = a2().f20294d;
        n.d(recyclerView, "rvTimeSlots");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new d0(list, new f0(this, str)));
    }

    /* access modifiers changed from: private */
    public static final nb.x k2(AppointmentDateAndTimeListActivity appointmentDateAndTimeListActivity, String str, Slots slots) {
        n.e(slots, "selectedSlot");
        g.a aVar = g.f9512a;
        String string = appointmentDateAndTimeListActivity.getString(R.string.appmnt_allocation_Id);
        n.d(string, "getString(...)");
        aVar.g(appointmentDateAndTimeListActivity, string, String.valueOf(slots.getAllocationId()));
        if (appointmentDateAndTimeListActivity.f9284i0) {
            String string2 = appointmentDateAndTimeListActivity.getString(R.string.sure_reschedule);
            n.d(string2, "getString(...)");
            appointmentDateAndTimeListActivity.n2(string2, String.valueOf(slots.getAllocationId()), str.toString(), String.valueOf(slots.getSlot()));
        } else {
            appointmentDateAndTimeListActivity.f2(slots, str);
            slots.getAllocationId();
        }
        return nb.x.f15721a;
    }

    /* access modifiers changed from: private */
    public static final void m2(AppointmentDateAndTimeListActivity appointmentDateAndTimeListActivity, View view) {
        appointmentDateAndTimeListActivity.X1();
        appointmentDateAndTimeListActivity.e2();
    }

    /* access modifiers changed from: private */
    public static final void o2(AlertDialog alertDialog, View view) {
        alertDialog.dismiss();
    }

    /* access modifiers changed from: private */
    public static final void p2(AppointmentDateAndTimeListActivity appointmentDateAndTimeListActivity, String str, AlertDialog alertDialog, View view) {
        appointmentDateAndTimeListActivity.V1(str);
        appointmentDateAndTimeListActivity.Z1();
        Utility.f9497a.y(appointmentDateAndTimeListActivity.c1());
        alertDialog.dismiss();
    }

    /* access modifiers changed from: private */
    public final void q2(int i10, int i11) {
        ArrayList arrayList = new ArrayList();
        Calendar instance = Calendar.getInstance();
        instance.set(i10, i11, 1);
        int actualMaximum = instance.getActualMaximum(5);
        if (1 <= actualMaximum) {
            int i12 = 1;
            while (true) {
                Calendar instance2 = Calendar.getInstance();
                instance2.set(i10, i11, i12);
                List list = this.f9293r0;
                if (list == null || !list.isEmpty()) {
                    Iterator it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Calendar calendar = (Calendar) it.next();
                        if (calendar.get(1) == instance2.get(1) && calendar.get(2) == instance2.get(2) && calendar.get(5) == instance2.get(5)) {
                            break;
                        }
                    }
                }
                n.b(instance2);
                arrayList.add(instance2);
                if (i12 == actualMaximum) {
                    break;
                }
                i12++;
            }
        }
        a2().f20292b.setDisabledDays(arrayList);
    }

    public final void b2() {
        Bundle extras = getIntent().getExtras();
        this.f9287l0 = extras;
        n.b(extras);
        this.f9284i0 = extras.getBoolean(getString(R.string.isToReschedule));
        this.f9291p0 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        if (this.f9284i0) {
            Bundle bundle = this.f9287l0;
            n.b(bundle);
            Object obj = bundle.get(getString(R.string.appmnt_booked_data));
            n.c(obj, "null cannot be cast to non-null type com.vfs.italyglobal.pojo.SearchAppointmentDetails");
            SearchAppointmentDetails searchAppointmentDetails = (SearchAppointmentDetails) obj;
            this.f9285j0 = searchAppointmentDetails;
            List<SearchApplicantDetails> applicants = searchAppointmentDetails.getApplicants();
            n.b(applicants);
            this.f9286k0 = applicants.size();
        } else {
            Bundle bundle2 = this.f9287l0;
            n.b(bundle2);
            this.f9286k0 = bundle2.getInt(getString(R.string.no_of_applicants));
        }
        SimpleDateFormat simpleDateFormat = this.f9291p0;
        if (simpleDateFormat == null) {
            n.o("sdf");
            simpleDateFormat = null;
        }
        String format = simpleDateFormat.format(new Date());
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.appmnt_earliest_date);
        n.d(string, "getString(...)");
        n.b(format);
        Date parse = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).parse(aVar.b(c12, string, format));
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        n.b(parse);
        String format2 = simpleDateFormat2.format(parse);
        n.b(format2);
        U1(format2);
        a2().f20293c.setOnClickListener(new ua.d0(this));
    }

    public final void l2(String str, String str2, String str3, String str4) {
        n.e(str, "message");
        n.e(str2, "refNo");
        n.e(str3, "date");
        n.e(str4, "time");
        o d10 = com.vfs.italyglobal.utilities.c.d(this, R.layout.custom_alert_dialog_appointment_successfull);
        ((TextView) ((View) d10.d()).findViewById(R.id.txt_centerText)).setText(str);
        ((TextView) ((View) d10.d()).findViewById(R.id.txt_dialog_ref_no)).setText(str2);
        ((TextView) ((View) d10.d()).findViewById(R.id.txt_dialog_dateTime)).setText(str3 + getString(R.string.at) + str4);
        ((TextView) ((View) d10.d()).findViewById(R.id.txt_no_of_applicnats)).setVisibility(8);
        ((TextView) ((View) d10.d()).findViewById(R.id.txt_dialog_no_of_applicant)).setVisibility(8);
        ((TextView) ((View) d10.d()).findViewById(R.id.txt_colon)).setVisibility(8);
        ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btn_dialog)).setOnClickListener(new i0(this));
        ((AlertDialog) d10.c()).show();
    }

    public final void n2(String str, String str2, String str3, String str4) {
        n.e(str, "message");
        n.e(str2, "allocationId");
        n.e(str3, "date");
        n.e(str4, "time");
        o d10 = com.vfs.italyglobal.utilities.c.d(this, R.layout.custom_alert_re_schedule_appmnt_dialog);
        AlertDialog alertDialog = (AlertDialog) d10.c();
        ((TextView) ((View) d10.d()).findViewById(R.id.txt_centerText)).setText(str);
        ((TextView) ((View) d10.d()).findViewById(R.id.txt_dialog_ref_no)).setText(str2);
        ((TextView) ((View) d10.d()).findViewById(R.id.txt_dialog_dateTime)).setText(str3 + getString(R.string.at) + str4);
        ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btn_dialog_cancle)).setOnClickListener(new g0(alertDialog));
        ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btn_dialog_confirm_reschedule)).setOnClickListener(new h0(this, str2, alertDialog));
        alertDialog.show();
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) a2().b());
        b2();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Utility.Companion companion = Utility.f9497a;
        companion.c();
        companion.d();
        DatePickerDialog datePickerDialog = this.f9288m0;
        if (datePickerDialog != null) {
            n.b(datePickerDialog);
            if (datePickerDialog.isShowing()) {
                DatePickerDialog datePickerDialog2 = this.f9288m0;
                n.b(datePickerDialog2);
                datePickerDialog2.dismiss();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Y1();
    }
}
