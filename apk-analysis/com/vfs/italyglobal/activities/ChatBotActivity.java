package com.vfs.italyglobal.activities;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import bc.n;
import be.d;
import be.f;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.AppDelegate;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.pojo.ChatBotFaq;
import com.vfs.italyglobal.pojo.ChatBotLabel;
import com.vfs.italyglobal.pojo.ChatBotMenu;
import com.vfs.italyglobal.pojo.ChatBotResponse;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.g;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import nb.h;
import nb.i;
import org.apache.http.protocol.HTTP;
import ua.a2;
import ua.b2;
import ua.c2;
import ua.z1;
import va.x;
import wa.k;
import wa.o;
import xa.b0;
import xa.c0;
import xa.m;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class ChatBotActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private int f9362f0 = 1;

    /* renamed from: g0  reason: collision with root package name */
    private String f9363g0;

    /* renamed from: h0  reason: collision with root package name */
    public ab.b f9364h0;

    /* renamed from: i0  reason: collision with root package name */
    public ChatBotResponse f9365i0;

    /* renamed from: j0  reason: collision with root package name */
    private ArrayList f9366j0 = new ArrayList();

    /* renamed from: k0  reason: collision with root package name */
    private ArrayList f9367k0 = new ArrayList();

    /* renamed from: l0  reason: collision with root package name */
    private ArrayList f9368l0 = new ArrayList();

    /* renamed from: m0  reason: collision with root package name */
    private int f9369m0;

    /* renamed from: n0  reason: collision with root package name */
    private final h f9370n0 = i.a(new z1(this));

    /* renamed from: o0  reason: collision with root package name */
    private final int f9371o0 = -997;

    /* renamed from: p0  reason: collision with root package name */
    private final int f9372p0 = -998;

    /* renamed from: q0  reason: collision with root package name */
    private final int f9373q0 = -9979;

    /* renamed from: r0  reason: collision with root package name */
    private final int f9374r0 = -10;

    /* renamed from: s0  reason: collision with root package name */
    private final int f9375s0 = -20;

    /* renamed from: t0  reason: collision with root package name */
    private final int f9376t0 = -30;

    /* renamed from: u0  reason: collision with root package name */
    private boolean f9377u0;

    /* renamed from: v0  reason: collision with root package name */
    private boolean f9378v0;

    /* renamed from: w0  reason: collision with root package name */
    private c0 f9379w0;

    /* renamed from: x0  reason: collision with root package name */
    private b0 f9380x0;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ChatBotActivity f9381a;

        a(ChatBotActivity chatBotActivity) {
            this.f9381a = chatBotActivity;
        }

        public void a(d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            try {
                Utility.Companion companion = Utility.f9497a;
                companion.c();
                if (xVar.a() != null) {
                    ChatBotActivity chatBotActivity = this.f9381a;
                    Object a10 = xVar.a();
                    n.b(a10);
                    chatBotActivity.j2((ChatBotResponse) a10);
                    this.f9381a.c2();
                    ChatBotActivity chatBotActivity2 = this.f9381a;
                    chatBotActivity2.d2("welcome_message", 0, chatBotActivity2.U1());
                    return;
                }
                companion.w(this.f9381a.c1(), xVar.f().toString());
            } catch (Exception e10) {
                Utility.f9497a.c();
                e10.toString();
            }
        }

        public void b(d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            Activity c12 = this.f9381a.c1();
            String localizedMessage = th.getLocalizedMessage();
            n.b(localizedMessage);
            companion.w(c12, localizedMessage);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class b implements za.a {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ChatBotActivity f9382a;

        b(ChatBotActivity chatBotActivity) {
            this.f9382a = chatBotActivity;
        }

        public void a(String str, int i10, String str2, int i11) {
            Integer num;
            n.e(str, "value");
            n.e(str2, "type");
            try {
                ChatBotMenu E1 = this.f9382a.K1(str, i11);
                ((ChatBotMenu) this.f9382a.N1().get(i10)).getMenuId();
                if (!this.f9382a.N1().isEmpty()) {
                    if (E1 != null) {
                        num = E1.getMenuId();
                    } else {
                        num = null;
                    }
                    int T1 = this.f9382a.T1();
                    if (num != null) {
                        if (num.intValue() == T1) {
                            this.f9382a.k2(false);
                            ChatBotActivity chatBotActivity = this.f9382a;
                            chatBotActivity.d2("welcome_message", 0, chatBotActivity.U1());
                            return;
                        }
                    }
                    int S1 = this.f9382a.S1();
                    if (num != null) {
                        if (num.intValue() == S1) {
                            this.f9382a.k2(false);
                            List<ChatBotMenu> menus = this.f9382a.M1().getMenus();
                            n.d(menus, "getMenus(...)");
                            ChatBotActivity chatBotActivity2 = this.f9382a;
                            for (T next : menus) {
                                Integer menuId = ((ChatBotMenu) next).getMenuId();
                                int P1 = chatBotActivity2.P1();
                                if (menuId != null) {
                                    if (menuId.intValue() == P1) {
                                        ChatBotMenu chatBotMenu = (ChatBotMenu) next;
                                        this.f9382a.P1();
                                        E1.getMenuId();
                                        E1.getParentMenu();
                                        chatBotMenu.getMenuType();
                                        if (!this.f9382a.a2()) {
                                            ChatBotActivity chatBotActivity3 = this.f9382a;
                                            String menuName = chatBotMenu.getMenuName();
                                            n.d(menuName, "getMenuName(...)");
                                            chatBotActivity3.n2(menuName, this.f9382a.P1(), E1);
                                            return;
                                        }
                                        return;
                                    }
                                }
                            }
                            throw new NoSuchElementException("Collection contains no element matching the predicate.");
                        }
                    }
                    int R1 = this.f9382a.R1();
                    if (num != null) {
                        if (num.intValue() == R1) {
                            this.f9382a.k2(false);
                            ChatBotActivity chatBotActivity4 = this.f9382a;
                            chatBotActivity4.d2("close_message", -1, chatBotActivity4.U1());
                            return;
                        }
                    }
                    if (E1 != null) {
                        this.f9382a.n2(str, i10, E1);
                    }
                }
            } catch (Exception e10) {
                e10.printStackTrace();
            }
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class c implements za.a {
        c() {
        }

        public void a(String str, int i10, String str2, int i11) {
            n.e(str, "value");
            n.e(str2, "type");
        }
    }

    public ChatBotActivity() {
        super(false, false, 3, (bc.h) null);
    }

    private final void H1() {
        if (com.vfs.italyglobal.utilities.c.j(this)) {
            AppConfigModel.CountryConfig.ChatbotRequestBody Z1 = Z1();
            if (Z1 != null) {
                Utility.f9497a.y(c1());
                L1().n("N3zMd08jXFGooumC", Z1).A(new a(this));
                return;
            }
            return;
        }
        Utility.Companion companion = Utility.f9497a;
        Activity c12 = c1();
        String string = getString(R.string.network_error);
        n.d(string, "getString(...)");
        companion.w(c12, string);
    }

    private final void I1() {
        new Handler(Looper.getMainLooper()).postDelayed(new c2(this), 500);
    }

    /* access modifiers changed from: private */
    public static final void J1(ChatBotActivity chatBotActivity) {
        chatBotActivity.Q1().f20525g.w(130);
    }

    /* access modifiers changed from: private */
    public final ChatBotMenu K1(String str, int i10) {
        int hashCode = str.hashCode();
        ChatBotMenu chatBotMenu = null;
        if (hashCode == -1544869189 ? str.equals("Refresh") : hashCode == 65203672 ? str.equals(HTTP.CONN_CLOSE) : !(hashCode != 1797702943 || !str.equals("Go Back"))) {
            int hashCode2 = str.hashCode();
            if (hashCode2 != -1544869189) {
                if (hashCode2 != 65203672) {
                    if (hashCode2 == 1797702943 && str.equals("Go Back")) {
                        ChatBotMenu chatBotMenu2 = new ChatBotMenu();
                        chatBotMenu2.setMenuId(Integer.valueOf(this.f9372p0));
                        chatBotMenu2.setMenuName("Go Back");
                        return chatBotMenu2;
                    }
                } else if (str.equals(HTTP.CONN_CLOSE)) {
                    ChatBotMenu chatBotMenu3 = new ChatBotMenu();
                    chatBotMenu3.setMenuId(Integer.valueOf(this.f9373q0));
                    chatBotMenu3.setMenuName(HTTP.CONN_CLOSE);
                    return chatBotMenu3;
                }
            } else if (str.equals("Refresh")) {
                ChatBotMenu chatBotMenu4 = new ChatBotMenu();
                chatBotMenu4.setMenuId(Integer.valueOf(this.f9371o0));
                chatBotMenu4.setMenuName("Refresh");
                return chatBotMenu4;
            }
        } else if (!this.f9368l0.isEmpty()) {
            Iterator it = this.f9368l0.iterator();
            n.d(it, "iterator(...)");
            while (it.hasNext()) {
                Object next = it.next();
                n.d(next, "next(...)");
                ChatBotMenu chatBotMenu5 = (ChatBotMenu) next;
                if (chatBotMenu5.getMenuName().equals(str) && chatBotMenu5.getMenuId().equals(Integer.valueOf(i10))) {
                    chatBotMenu5.getMenuName();
                    chatBotMenu5.getParentMenu();
                    chatBotMenu5.toString();
                    chatBotMenu = chatBotMenu5;
                }
            }
            return chatBotMenu;
        }
        return null;
    }

    private final String O1() {
        List<ChatBotLabel> labels = M1().getLabels();
        n.d(labels, "getLabels(...)");
        if (labels.isEmpty()) {
            return "";
        }
        for (ChatBotLabel next : M1().getLabels()) {
            if (next.getLabelName().equals("close_confirmation")) {
                String content = next.getContent();
                n.d(content, "getContent(...)");
                return content;
            }
        }
        return "";
    }

    private final m Q1() {
        return (m) this.f9370n0.getValue();
    }

    private final boolean V1(int i10) {
        this.f9367k0.clear();
        if (this.f9368l0.isEmpty()) {
            return false;
        }
        Iterator it = this.f9368l0.iterator();
        n.d(it, "iterator(...)");
        if (!it.hasNext()) {
            return false;
        }
        Object next = it.next();
        n.d(next, "next(...)");
        ChatBotMenu chatBotMenu = (ChatBotMenu) next;
        if (!chatBotMenu.getParentMenu().equals(Integer.valueOf(i10))) {
            return true;
        }
        chatBotMenu.getParentMenu();
        return true;
    }

    /* access modifiers changed from: private */
    public static final void X1(ChatBotActivity chatBotActivity, View view) {
        Editable text = chatBotActivity.Q1().f20521c.getText();
        n.d(text, "getText(...)");
        if (text.length() > 0) {
            chatBotActivity.f9363g0 = chatBotActivity.Q1().f20521c.getText().toString();
            chatBotActivity.Q1().f20521c.getText().clear();
            String str = chatBotActivity.f9363g0;
            n.b(str);
            chatBotActivity.n2(str, -1, new ChatBotMenu());
        }
    }

    /* access modifiers changed from: private */
    public static final void Y1(ChatBotActivity chatBotActivity, View view) {
        chatBotActivity.onBackPressed();
    }

    private final AppConfigModel.CountryConfig.ChatbotRequestBody Z1() {
        T t10;
        g.a aVar = g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.selectedLangCode);
        n.d(string, "getString(...)");
        String b10 = aVar.b(c12, string, "en");
        Application application = getApplication();
        n.c(application, "null cannot be cast to non-null type com.vfs.italyglobal.AppDelegate");
        AppConfigModel.CountryConfig j10 = ((AppDelegate) application).j();
        T t11 = null;
        if (j10 == null) {
            return null;
        }
        Object b11 = ab.a.f517a.d(c1(), j10.getChatbotBaseUrl()).b(ab.b.class);
        n.d(b11, "create(...)");
        i2((ab.b) b11);
        List<AppConfigModel.CountryConfig.ChatbotRequestBody> chatbotRequestBody = j10.getChatbotRequestBody();
        Iterator<T> it = chatbotRequestBody.iterator();
        while (true) {
            if (!it.hasNext()) {
                t10 = null;
                break;
            }
            t10 = it.next();
            String languageCode = ((AppConfigModel.CountryConfig.ChatbotRequestBody) t10).getLanguageCode();
            String lowerCase = b10.toLowerCase(Locale.ROOT);
            n.d(lowerCase, "toLowerCase(...)");
            if (n.a(languageCode, lowerCase)) {
                break;
            }
        }
        AppConfigModel.CountryConfig.ChatbotRequestBody chatbotRequestBody2 = (AppConfigModel.CountryConfig.ChatbotRequestBody) t10;
        if (chatbotRequestBody2 != null) {
            return chatbotRequestBody2;
        }
        Iterator<T> it2 = chatbotRequestBody.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            T next = it2.next();
            String languageCode2 = ((AppConfigModel.CountryConfig.ChatbotRequestBody) next).getLanguageCode();
            String lowerCase2 = "en".toLowerCase(Locale.ROOT);
            n.d(lowerCase2, "toLowerCase(...)");
            if (n.a(languageCode2, lowerCase2)) {
                t11 = next;
                break;
            }
        }
        return (AppConfigModel.CountryConfig.ChatbotRequestBody) t11;
    }

    /* access modifiers changed from: private */
    public static final m b2(ChatBotActivity chatBotActivity) {
        return m.c(chatBotActivity.getLayoutInflater());
    }

    /* access modifiers changed from: private */
    public final void c2() {
        int i10;
        Iterator<ChatBotMenu> it = M1().getMenus().iterator();
        while (true) {
            if (!it.hasNext()) {
                i10 = 0;
                break;
            }
            ChatBotMenu next = it.next();
            if (next.getMenuName().equals("Frequently Asked Questions")) {
                Integer menuId = next.getMenuId();
                n.d(menuId, "getMenuId(...)");
                i10 = menuId.intValue();
                break;
            }
        }
        if (i10 == 0) {
            i10 = -2;
        }
        List<ChatBotFaq> faqs = M1().getFaqs();
        n.d(faqs, "getFaqs(...)");
        if (!faqs.isEmpty()) {
            for (ChatBotFaq next2 : M1().getFaqs()) {
                ChatBotMenu chatBotMenu = new ChatBotMenu();
                chatBotMenu.setParentMenu(Integer.valueOf(i10));
                chatBotMenu.setMenuId(next2.getFaqId());
                chatBotMenu.setMenuName(next2.getFaqName());
                chatBotMenu.setMenuDescription(next2.getFaqAnwser());
                this.f9368l0.add(chatBotMenu);
            }
        }
        this.f9368l0.addAll(M1().getMenus());
    }

    /* access modifiers changed from: private */
    public final void d2(String str, int i10, int i11) {
        try {
            f2(str, i10, i11);
            e2(i10, i11);
            b0 c10 = b0.c(getLayoutInflater());
            n.d(c10, "inflate(...)");
            this.f9380x0 = c10;
            if (getTitle() != null) {
                b0 b0Var = null;
                if (!this.f9368l0.isEmpty()) {
                    b0 b0Var2 = this.f9380x0;
                    if (b0Var2 == null) {
                        n.o("rvChatBotBinding");
                    } else {
                        b0Var = b0Var2;
                    }
                    l2(b0Var);
                    return;
                }
                b0 b0Var3 = this.f9380x0;
                if (b0Var3 == null) {
                    n.o("rvChatBotBinding");
                } else {
                    b0Var = b0Var3;
                }
                m2(b0Var);
            }
        } catch (Exception e10) {
            M1().toString();
            e10.printStackTrace();
        }
    }

    private final void e2(int i10, int i11) {
        this.f9367k0.clear();
        boolean z10 = false;
        if (!this.f9368l0.isEmpty()) {
            Iterator it = this.f9368l0.iterator();
            n.d(it, "iterator(...)");
            while (it.hasNext()) {
                Object next = it.next();
                n.d(next, "next(...)");
                ChatBotMenu chatBotMenu = (ChatBotMenu) next;
                if (chatBotMenu.getParentMenu().equals(Integer.valueOf(i10))) {
                    chatBotMenu.getMenuName();
                    chatBotMenu.getParentMenu();
                    this.f9367k0.add(chatBotMenu);
                    z10 = true;
                }
            }
        }
        if (!z10 && i10 != -1) {
            ChatBotMenu chatBotMenu2 = new ChatBotMenu();
            chatBotMenu2.setMenuId(Integer.valueOf(this.f9371o0));
            chatBotMenu2.setMenuName("Refresh");
            this.f9367k0.add(chatBotMenu2);
            ChatBotMenu chatBotMenu3 = new ChatBotMenu();
            chatBotMenu3.setMenuId(Integer.valueOf(this.f9372p0));
            chatBotMenu3.setMenuName("Go Back");
            this.f9367k0.add(chatBotMenu3);
            ChatBotMenu chatBotMenu4 = new ChatBotMenu();
            chatBotMenu4.setMenuId(Integer.valueOf(this.f9373q0));
            chatBotMenu4.setMenuName(HTTP.CONN_CLOSE);
            this.f9367k0.add(chatBotMenu4);
        }
        this.f9367k0.size();
        new ArrayList(new HashSet(this.f9367k0));
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00bf A[Catch:{ Exception -> 0x004e }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00db A[Catch:{ Exception -> 0x004e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void f2(java.lang.String r4, int r5, int r6) {
        /*
            r3 = this;
            java.util.ArrayList r0 = r3.f9366j0     // Catch:{ Exception -> 0x004e }
            r0.clear()     // Catch:{ Exception -> 0x004e }
            int r0 = r3.f9374r0     // Catch:{ Exception -> 0x004e }
            if (r6 != r0) goto L_0x0051
            com.vfs.italyglobal.pojo.ChatBotResponse r0 = r3.M1()     // Catch:{ Exception -> 0x004e }
            java.util.List r0 = r0.getLabels()     // Catch:{ Exception -> 0x004e }
            java.lang.String r1 = "getLabels(...)"
            bc.n.d(r0, r1)     // Catch:{ Exception -> 0x004e }
            boolean r0 = r0.isEmpty()     // Catch:{ Exception -> 0x004e }
            if (r0 != 0) goto L_0x0051
            int r0 = r4.length()     // Catch:{ Exception -> 0x004e }
            if (r0 <= 0) goto L_0x0051
            com.vfs.italyglobal.pojo.ChatBotResponse r0 = r3.M1()     // Catch:{ Exception -> 0x004e }
            java.util.List r0 = r0.getLabels()     // Catch:{ Exception -> 0x004e }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x004e }
        L_0x002e:
            boolean r1 = r0.hasNext()     // Catch:{ Exception -> 0x004e }
            if (r1 == 0) goto L_0x0051
            java.lang.Object r1 = r0.next()     // Catch:{ Exception -> 0x004e }
            com.vfs.italyglobal.pojo.ChatBotLabel r1 = (com.vfs.italyglobal.pojo.ChatBotLabel) r1     // Catch:{ Exception -> 0x004e }
            java.lang.String r2 = r1.getLabelName()     // Catch:{ Exception -> 0x004e }
            boolean r2 = r2.equals(r4)     // Catch:{ Exception -> 0x004e }
            if (r2 == 0) goto L_0x002e
            java.util.ArrayList r4 = r3.f9366j0     // Catch:{ Exception -> 0x004e }
            java.lang.String r0 = r1.getContent()     // Catch:{ Exception -> 0x004e }
            r4.add(r0)     // Catch:{ Exception -> 0x004e }
            goto L_0x0051
        L_0x004e:
            r4 = move-exception
            goto L_0x00e8
        L_0x0051:
            int r4 = r3.f9375s0     // Catch:{ Exception -> 0x004e }
            if (r6 == r4) goto L_0x0059
            int r4 = r3.f9376t0     // Catch:{ Exception -> 0x004e }
            if (r6 != r4) goto L_0x00e7
        L_0x0059:
            java.util.ArrayList r4 = r3.f9368l0     // Catch:{ Exception -> 0x004e }
            boolean r4 = r4.isEmpty()     // Catch:{ Exception -> 0x004e }
            if (r4 != 0) goto L_0x00e7
            java.util.ArrayList r4 = r3.f9368l0     // Catch:{ Exception -> 0x004e }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ Exception -> 0x004e }
            java.lang.String r6 = "iterator(...)"
            bc.n.d(r4, r6)     // Catch:{ Exception -> 0x004e }
        L_0x006c:
            boolean r6 = r4.hasNext()     // Catch:{ Exception -> 0x004e }
            if (r6 == 0) goto L_0x00e7
            java.lang.Object r6 = r4.next()     // Catch:{ Exception -> 0x004e }
            java.lang.String r0 = "next(...)"
            bc.n.d(r6, r0)     // Catch:{ Exception -> 0x004e }
            com.vfs.italyglobal.pojo.ChatBotMenu r6 = (com.vfs.italyglobal.pojo.ChatBotMenu) r6     // Catch:{ Exception -> 0x004e }
            java.lang.Integer r0 = r6.getMenuId()     // Catch:{ Exception -> 0x004e }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r5)     // Catch:{ Exception -> 0x004e }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x004e }
            if (r0 == 0) goto L_0x006c
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x004e }
            r4.<init>()     // Catch:{ Exception -> 0x004e }
            java.lang.String r0 = r6.getContentDetails()     // Catch:{ Exception -> 0x004e }
            if (r0 == 0) goto L_0x00a5
            int r0 = r0.length()     // Catch:{ Exception -> 0x004e }
            if (r0 != 0) goto L_0x009d
            goto L_0x00a5
        L_0x009d:
            java.lang.String r6 = r6.getContentDetails()     // Catch:{ Exception -> 0x004e }
            r4.append(r6)     // Catch:{ Exception -> 0x004e }
            goto L_0x00b9
        L_0x00a5:
            java.lang.String r0 = r6.getMenuDescription()     // Catch:{ Exception -> 0x004e }
            if (r0 == 0) goto L_0x00b9
            int r0 = r0.length()     // Catch:{ Exception -> 0x004e }
            if (r0 != 0) goto L_0x00b2
            goto L_0x00b9
        L_0x00b2:
            java.lang.String r6 = r6.getMenuDescription()     // Catch:{ Exception -> 0x004e }
            r4.append(r6)     // Catch:{ Exception -> 0x004e }
        L_0x00b9:
            boolean r5 = r3.V1(r5)     // Catch:{ Exception -> 0x004e }
            if (r5 != 0) goto L_0x00db
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004e }
            r5.<init>()     // Catch:{ Exception -> 0x004e }
            java.lang.String r6 = "[BR]"
            r5.append(r6)     // Catch:{ Exception -> 0x004e }
            java.lang.String r6 = r3.O1()     // Catch:{ Exception -> 0x004e }
            r5.append(r6)     // Catch:{ Exception -> 0x004e }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x004e }
            r4.append(r5)     // Catch:{ Exception -> 0x004e }
            r5 = 1
            r3.f9378v0 = r5     // Catch:{ Exception -> 0x004e }
            goto L_0x00de
        L_0x00db:
            r5 = 0
            r3.f9378v0 = r5     // Catch:{ Exception -> 0x004e }
        L_0x00de:
            java.util.ArrayList r5 = r3.f9366j0     // Catch:{ Exception -> 0x004e }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x004e }
            r5.add(r4)     // Catch:{ Exception -> 0x004e }
        L_0x00e7:
            return
        L_0x00e8:
            r4.toString()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vfs.italyglobal.activities.ChatBotActivity.f2(java.lang.String, int, int):void");
    }

    private final void g2(b0 b0Var) {
        if (!this.f9367k0.isEmpty()) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(c1(), 1, false);
            k kVar = new k(c1(), this.f9367k0, new b(this));
            b0Var.f20302e.setLayoutManager(linearLayoutManager);
            b0Var.f20302e.setHasFixedSize(false);
            b0Var.f20302e.setAdapter(kVar);
        }
    }

    private final void h2(b0 b0Var) {
        if (!this.f9366j0.isEmpty()) {
            boolean z10 = false;
            for (ChatBotMenu chatBotMenu : this.f9367k0) {
                if (!z10 && (chatBotMenu.getMenuName().equals("Refresh") || chatBotMenu.getMenuName().equals("Go Back") || chatBotMenu.getMenuName().equals(HTTP.CONN_CLOSE))) {
                    this.f9366j0.set(0, ((String) this.f9366j0.get(0)) + "[BR] Is there any thing else  i can help you with?");
                    z10 = true;
                }
            }
            if (!this.f9366j0.isEmpty()) {
                Object obj = this.f9366j0.get(0);
                n.d(obj, "get(...)");
                List C0 = kc.n.C0(kc.n.N0(kc.n.G((String) obj, ";", "\n", false, 4, (Object) null)).toString(), new String[]{"[BR]"}, false, 0, 6, (Object) null);
                C0.size();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(c1(), 1, false);
                o oVar = new o(C0, this.f9378v0, new c());
                b0Var.f20303f.setLayoutManager(linearLayoutManager);
                b0Var.f20303f.setHasFixedSize(false);
                b0Var.f20303f.setAdapter(oVar);
            }
        }
    }

    private final void l2(b0 b0Var) {
        h2(b0Var);
        g2(b0Var);
        Q1().f20523e.addView(b0Var.b());
        I1();
    }

    private final void m2(b0 b0Var) {
        h2(b0Var);
        Q1().f20523e.addView(b0Var.b());
        I1();
    }

    public final ab.b L1() {
        ab.b bVar = this.f9364h0;
        if (bVar != null) {
            return bVar;
        }
        n.o("apiInterface");
        return null;
    }

    public final ChatBotResponse M1() {
        ChatBotResponse chatBotResponse = this.f9365i0;
        if (chatBotResponse != null) {
            return chatBotResponse;
        }
        n.o("chatBotResponse");
        return null;
    }

    public final ArrayList N1() {
        return this.f9368l0;
    }

    public final int P1() {
        return this.f9369m0;
    }

    public final int R1() {
        return this.f9373q0;
    }

    public final int S1() {
        return this.f9372p0;
    }

    public final int T1() {
        return this.f9371o0;
    }

    public final int U1() {
        return this.f9374r0;
    }

    public final void W1() {
        Q1().f20520b.setOnClickListener(new a2(this));
        Q1().f20522d.setOnClickListener(new b2(this));
        H1();
    }

    public final boolean a2() {
        return this.f9377u0;
    }

    public final void i2(ab.b bVar) {
        n.e(bVar, "<set-?>");
        this.f9364h0 = bVar;
    }

    public final void j2(ChatBotResponse chatBotResponse) {
        n.e(chatBotResponse, "<set-?>");
        this.f9365i0 = chatBotResponse;
    }

    public final void k2(boolean z10) {
        this.f9378v0 = z10;
    }

    public final void n2(String str, int i10, ChatBotMenu chatBotMenu) {
        n.e(str, "userInput");
        n.e(chatBotMenu, "chatBotMenu");
        c0 c10 = c0.c(getLayoutInflater());
        n.d(c10, "inflate(...)");
        this.f9379w0 = c10;
        LinearLayout linearLayout = Q1().f20523e;
        c0 c0Var = this.f9379w0;
        c0 c0Var2 = null;
        if (c0Var == null) {
            n.o("chatBotInputBinding");
            c0Var = null;
        }
        linearLayout.addView(c0Var.b());
        c0 c0Var3 = this.f9379w0;
        if (c0Var3 == null) {
            n.o("chatBotInputBinding");
        } else {
            c0Var2 = c0Var3;
        }
        c0Var2.f20323c.setText(str);
        if (i10 != -1) {
            Integer menuId = chatBotMenu.getMenuId();
            int i11 = this.f9372p0;
            if (menuId != null && menuId.intValue() == i11) {
                chatBotMenu.getMenuId();
                chatBotMenu.getParentMenu();
                chatBotMenu.setMenuId(Integer.valueOf(this.f9369m0));
                this.f9377u0 = true;
            } else {
                Integer parentMenu = chatBotMenu.getParentMenu();
                n.d(parentMenu, "getParentMenu(...)");
                this.f9369m0 = parentMenu.intValue();
                this.f9377u0 = false;
            }
            Integer menuId2 = chatBotMenu.getMenuId();
            n.d(menuId2, "getMenuId(...)");
            d2("", menuId2.intValue(), this.f9375s0);
        }
        I1();
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) Q1().b());
        W1();
    }
}
