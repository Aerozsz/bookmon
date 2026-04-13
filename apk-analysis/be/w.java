package be;

import be.s;
import de.b;
import de.c;
import de.d;
import de.e;
import de.f;
import de.g;
import de.h;
import de.i;
import de.j;
import de.k;
import de.l;
import de.m;
import de.n;
import de.o;
import de.p;
import de.q;
import de.r;
import de.t;
import de.u;
import de.v;
import de.x;
import fd.b0;
import fd.c0;
import fd.f0;
import fd.y;
import fd.z;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
final class w {

    /* renamed from: a  reason: collision with root package name */
    private final Class f21581a;

    /* renamed from: b  reason: collision with root package name */
    private final Method f21582b;

    /* renamed from: c  reason: collision with root package name */
    private final z f21583c;

    /* renamed from: d  reason: collision with root package name */
    final String f21584d;

    /* renamed from: e  reason: collision with root package name */
    private final String f21585e;

    /* renamed from: f  reason: collision with root package name */
    private final y f21586f;

    /* renamed from: g  reason: collision with root package name */
    private final b0 f21587g;

    /* renamed from: h  reason: collision with root package name */
    private final boolean f21588h;

    /* renamed from: i  reason: collision with root package name */
    private final boolean f21589i;

    /* renamed from: j  reason: collision with root package name */
    private final boolean f21590j;

    /* renamed from: k  reason: collision with root package name */
    private final s[] f21591k;

    /* renamed from: l  reason: collision with root package name */
    final boolean f21592l;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    static final class a {

        /* renamed from: y  reason: collision with root package name */
        private static final Pattern f21593y = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");

        /* renamed from: z  reason: collision with root package name */
        private static final Pattern f21594z = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]*");

        /* renamed from: a  reason: collision with root package name */
        final y f21595a;

        /* renamed from: b  reason: collision with root package name */
        final Class f21596b;

        /* renamed from: c  reason: collision with root package name */
        final Method f21597c;

        /* renamed from: d  reason: collision with root package name */
        final Annotation[] f21598d;

        /* renamed from: e  reason: collision with root package name */
        final Annotation[][] f21599e;

        /* renamed from: f  reason: collision with root package name */
        final Type[] f21600f;

        /* renamed from: g  reason: collision with root package name */
        boolean f21601g;

        /* renamed from: h  reason: collision with root package name */
        boolean f21602h;

        /* renamed from: i  reason: collision with root package name */
        boolean f21603i;

        /* renamed from: j  reason: collision with root package name */
        boolean f21604j;

        /* renamed from: k  reason: collision with root package name */
        boolean f21605k;

        /* renamed from: l  reason: collision with root package name */
        boolean f21606l;

        /* renamed from: m  reason: collision with root package name */
        boolean f21607m;

        /* renamed from: n  reason: collision with root package name */
        boolean f21608n;

        /* renamed from: o  reason: collision with root package name */
        String f21609o;

        /* renamed from: p  reason: collision with root package name */
        boolean f21610p;

        /* renamed from: q  reason: collision with root package name */
        boolean f21611q;

        /* renamed from: r  reason: collision with root package name */
        boolean f21612r;

        /* renamed from: s  reason: collision with root package name */
        String f21613s;

        /* renamed from: t  reason: collision with root package name */
        y f21614t;

        /* renamed from: u  reason: collision with root package name */
        b0 f21615u;

        /* renamed from: v  reason: collision with root package name */
        Set f21616v;

        /* renamed from: w  reason: collision with root package name */
        s[] f21617w;

        /* renamed from: x  reason: collision with root package name */
        boolean f21618x;

        a(y yVar, Class cls, Method method) {
            this.f21595a = yVar;
            this.f21596b = cls;
            this.f21597c = method;
            this.f21598d = method.getAnnotations();
            this.f21600f = method.getGenericParameterTypes();
            this.f21599e = method.getParameterAnnotations();
        }

        private static Class a(Class cls) {
            if (Boolean.TYPE == cls) {
                return Boolean.class;
            }
            if (Byte.TYPE == cls) {
                return Byte.class;
            }
            if (Character.TYPE == cls) {
                return Character.class;
            }
            if (Double.TYPE == cls) {
                return Double.class;
            }
            if (Float.TYPE == cls) {
                return Float.class;
            }
            if (Integer.TYPE == cls) {
                return Integer.class;
            }
            if (Long.TYPE == cls) {
                return Long.class;
            }
            if (Short.TYPE == cls) {
                return Short.class;
            }
            return cls;
        }

        private y c(String[] strArr, boolean z10) {
            y.a aVar = new y.a();
            for (String str : strArr) {
                int indexOf = str.indexOf(58);
                if (indexOf == -1 || indexOf == 0 || indexOf == str.length() - 1) {
                    throw c0.n(this.f21597c, "@Headers value must be in the form \"Name: Value\". Found: \"%s\"", str);
                }
                String substring = str.substring(0, indexOf);
                String trim = str.substring(indexOf + 1).trim();
                if ("Content-Type".equalsIgnoreCase(substring)) {
                    try {
                        this.f21615u = b0.e(trim);
                    } catch (IllegalArgumentException e10) {
                        throw c0.o(this.f21597c, e10, "Malformed content type: %s", trim);
                    }
                } else if (z10) {
                    aVar.e(substring, trim);
                } else {
                    aVar.a(substring, trim);
                }
            }
            return aVar.f();
        }

        private void d(String str, String str2, boolean z10) {
            String str3 = this.f21609o;
            if (str3 == null) {
                this.f21609o = str;
                this.f21610p = z10;
                if (!str2.isEmpty()) {
                    int indexOf = str2.indexOf(63);
                    if (indexOf != -1 && indexOf < str2.length() - 1) {
                        String substring = str2.substring(indexOf + 1);
                        if (f21593y.matcher(substring).find()) {
                            throw c0.n(this.f21597c, "URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", substring);
                        }
                    }
                    this.f21613s = str2;
                    this.f21616v = h(str2);
                    return;
                }
                return;
            }
            throw c0.n(this.f21597c, "Only one HTTP method is allowed. Found: %s and %s.", str3, str);
        }

        private void e(Annotation annotation) {
            if (annotation instanceof b) {
                d(HttpDelete.METHOD_NAME, ((b) annotation).value(), false);
            } else if (annotation instanceof f) {
                d(HttpGet.METHOD_NAME, ((f) annotation).value(), false);
            } else if (annotation instanceof g) {
                d(HttpHead.METHOD_NAME, ((g) annotation).value(), false);
            } else if (annotation instanceof n) {
                d(HttpPatch.METHOD_NAME, ((n) annotation).value(), true);
            } else if (annotation instanceof o) {
                d(HttpPost.METHOD_NAME, ((o) annotation).value(), true);
            } else if (annotation instanceof p) {
                d(HttpPut.METHOD_NAME, ((p) annotation).value(), true);
            } else if (annotation instanceof m) {
                d(HttpOptions.METHOD_NAME, ((m) annotation).value(), false);
            } else if (annotation instanceof h) {
                h hVar = (h) annotation;
                d(hVar.method(), hVar.path(), hVar.hasBody());
            } else if (annotation instanceof k) {
                k kVar = (k) annotation;
                String[] value = kVar.value();
                if (value.length != 0) {
                    this.f21614t = c(value, kVar.allowUnsafeNonAsciiValues());
                    return;
                }
                throw c0.n(this.f21597c, "@Headers annotation is empty.", new Object[0]);
            } else if (annotation instanceof l) {
                if (!this.f21611q) {
                    this.f21612r = true;
                    return;
                }
                throw c0.n(this.f21597c, "Only one encoding annotation is allowed.", new Object[0]);
            } else if (!(annotation instanceof e)) {
            } else {
                if (!this.f21612r) {
                    this.f21611q = true;
                    return;
                }
                throw c0.n(this.f21597c, "Only one encoding annotation is allowed.", new Object[0]);
            }
        }

        private s f(int i10, Type type, Annotation[] annotationArr, boolean z10) {
            s sVar;
            if (annotationArr != null) {
                sVar = null;
                for (Annotation g10 : annotationArr) {
                    s g11 = g(i10, type, annotationArr, g10);
                    if (g11 != null) {
                        if (sVar == null) {
                            sVar = g11;
                        } else {
                            throw c0.p(this.f21597c, i10, "Multiple Retrofit annotations found, only one allowed.", new Object[0]);
                        }
                    }
                }
            } else {
                sVar = null;
            }
            if (sVar != null) {
                return sVar;
            }
            if (z10) {
                try {
                    if (c0.h(type) == rb.e.class) {
                        this.f21618x = true;
                        return null;
                    }
                } catch (NoClassDefFoundError unused) {
                }
            }
            throw c0.p(this.f21597c, i10, "No Retrofit annotation found.", new Object[0]);
        }

        private s g(int i10, Type type, Annotation[] annotationArr, Annotation annotation) {
            Class<String> cls = String.class;
            if (annotation instanceof de.y) {
                j(i10, type);
                if (this.f21608n) {
                    throw c0.p(this.f21597c, i10, "Multiple @Url method annotations found.", new Object[0]);
                } else if (this.f21604j) {
                    throw c0.p(this.f21597c, i10, "@Path parameters may not be used with @Url.", new Object[0]);
                } else if (this.f21605k) {
                    throw c0.p(this.f21597c, i10, "A @Url parameter must not come after a @Query.", new Object[0]);
                } else if (this.f21606l) {
                    throw c0.p(this.f21597c, i10, "A @Url parameter must not come after a @QueryName.", new Object[0]);
                } else if (this.f21607m) {
                    throw c0.p(this.f21597c, i10, "A @Url parameter must not come after a @QueryMap.", new Object[0]);
                } else if (this.f21613s == null) {
                    this.f21608n = true;
                    if (type == z.class || type == cls || type == URI.class || ((type instanceof Class) && "android.net.Uri".equals(((Class) type).getName()))) {
                        return new s.p(this.f21597c, i10);
                    }
                    throw c0.p(this.f21597c, i10, "@Url must be okhttp3.HttpUrl, String, java.net.URI, or android.net.Uri type.", new Object[0]);
                } else {
                    throw c0.p(this.f21597c, i10, "@Url cannot be used with @%s URL", this.f21609o);
                }
            } else if (annotation instanceof de.s) {
                j(i10, type);
                if (this.f21605k) {
                    throw c0.p(this.f21597c, i10, "A @Path parameter must not come after a @Query.", new Object[0]);
                } else if (this.f21606l) {
                    throw c0.p(this.f21597c, i10, "A @Path parameter must not come after a @QueryName.", new Object[0]);
                } else if (this.f21607m) {
                    throw c0.p(this.f21597c, i10, "A @Path parameter must not come after a @QueryMap.", new Object[0]);
                } else if (this.f21608n) {
                    throw c0.p(this.f21597c, i10, "@Path parameters may not be used with @Url.", new Object[0]);
                } else if (this.f21613s != null) {
                    this.f21604j = true;
                    de.s sVar = (de.s) annotation;
                    String value = sVar.value();
                    i(i10, value);
                    return new s.k(this.f21597c, i10, value, this.f21595a.i(type, annotationArr), sVar.encoded());
                } else {
                    throw c0.p(this.f21597c, i10, "@Path can only be used with relative url on @%s", this.f21609o);
                }
            } else {
                Class<Iterable> cls2 = Iterable.class;
                if (annotation instanceof t) {
                    j(i10, type);
                    t tVar = (t) annotation;
                    String value2 = tVar.value();
                    boolean encoded = tVar.encoded();
                    Class h10 = c0.h(type);
                    this.f21605k = true;
                    if (cls2.isAssignableFrom(h10)) {
                        if (type instanceof ParameterizedType) {
                            return new s.l(value2, this.f21595a.i(c0.g(0, (ParameterizedType) type), annotationArr), encoded).c();
                        }
                        throw c0.p(this.f21597c, i10, h10.getSimpleName() + " must include generic type (e.g., " + h10.getSimpleName() + "<String>)", new Object[0]);
                    } else if (!h10.isArray()) {
                        return new s.l(value2, this.f21595a.i(type, annotationArr), encoded);
                    } else {
                        return new s.l(value2, this.f21595a.i(a(h10.getComponentType()), annotationArr), encoded).b();
                    }
                } else if (annotation instanceof v) {
                    j(i10, type);
                    boolean encoded2 = ((v) annotation).encoded();
                    Class h11 = c0.h(type);
                    this.f21606l = true;
                    if (cls2.isAssignableFrom(h11)) {
                        if (type instanceof ParameterizedType) {
                            return new s.n(this.f21595a.i(c0.g(0, (ParameterizedType) type), annotationArr), encoded2).c();
                        }
                        throw c0.p(this.f21597c, i10, h11.getSimpleName() + " must include generic type (e.g., " + h11.getSimpleName() + "<String>)", new Object[0]);
                    } else if (!h11.isArray()) {
                        return new s.n(this.f21595a.i(type, annotationArr), encoded2);
                    } else {
                        return new s.n(this.f21595a.i(a(h11.getComponentType()), annotationArr), encoded2).b();
                    }
                } else {
                    Class<Map> cls3 = Map.class;
                    if (annotation instanceof u) {
                        j(i10, type);
                        Class h12 = c0.h(type);
                        this.f21607m = true;
                        if (cls3.isAssignableFrom(h12)) {
                            Type i11 = c0.i(type, h12, cls3);
                            if (i11 instanceof ParameterizedType) {
                                ParameterizedType parameterizedType = (ParameterizedType) i11;
                                Type g10 = c0.g(0, parameterizedType);
                                if (cls == g10) {
                                    return new s.m(this.f21597c, i10, this.f21595a.i(c0.g(1, parameterizedType), annotationArr), ((u) annotation).encoded());
                                }
                                throw c0.p(this.f21597c, i10, "@QueryMap keys must be of type String: " + g10, new Object[0]);
                            }
                            throw c0.p(this.f21597c, i10, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                        }
                        throw c0.p(this.f21597c, i10, "@QueryMap parameter type must be Map.", new Object[0]);
                    } else if (annotation instanceof i) {
                        j(i10, type);
                        i iVar = (i) annotation;
                        String value3 = iVar.value();
                        Class h13 = c0.h(type);
                        if (cls2.isAssignableFrom(h13)) {
                            if (type instanceof ParameterizedType) {
                                return new s.f(value3, this.f21595a.i(c0.g(0, (ParameterizedType) type), annotationArr), iVar.allowUnsafeNonAsciiValues()).c();
                            }
                            throw c0.p(this.f21597c, i10, h13.getSimpleName() + " must include generic type (e.g., " + h13.getSimpleName() + "<String>)", new Object[0]);
                        } else if (!h13.isArray()) {
                            return new s.f(value3, this.f21595a.i(type, annotationArr), iVar.allowUnsafeNonAsciiValues());
                        } else {
                            return new s.f(value3, this.f21595a.i(a(h13.getComponentType()), annotationArr), iVar.allowUnsafeNonAsciiValues()).b();
                        }
                    } else if (annotation instanceof j) {
                        if (type == y.class) {
                            return new s.h(this.f21597c, i10);
                        }
                        j(i10, type);
                        Class h14 = c0.h(type);
                        if (cls3.isAssignableFrom(h14)) {
                            Type i12 = c0.i(type, h14, cls3);
                            if (i12 instanceof ParameterizedType) {
                                ParameterizedType parameterizedType2 = (ParameterizedType) i12;
                                Type g11 = c0.g(0, parameterizedType2);
                                if (cls == g11) {
                                    return new s.g(this.f21597c, i10, this.f21595a.i(c0.g(1, parameterizedType2), annotationArr), ((j) annotation).allowUnsafeNonAsciiValues());
                                }
                                throw c0.p(this.f21597c, i10, "@HeaderMap keys must be of type String: " + g11, new Object[0]);
                            }
                            throw c0.p(this.f21597c, i10, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                        }
                        throw c0.p(this.f21597c, i10, "@HeaderMap parameter type must be Map or Headers.", new Object[0]);
                    } else if (annotation instanceof c) {
                        j(i10, type);
                        if (this.f21611q) {
                            c cVar = (c) annotation;
                            String value4 = cVar.value();
                            boolean encoded3 = cVar.encoded();
                            this.f21601g = true;
                            Class h15 = c0.h(type);
                            if (cls2.isAssignableFrom(h15)) {
                                if (type instanceof ParameterizedType) {
                                    return new s.d(value4, this.f21595a.i(c0.g(0, (ParameterizedType) type), annotationArr), encoded3).c();
                                }
                                throw c0.p(this.f21597c, i10, h15.getSimpleName() + " must include generic type (e.g., " + h15.getSimpleName() + "<String>)", new Object[0]);
                            } else if (!h15.isArray()) {
                                return new s.d(value4, this.f21595a.i(type, annotationArr), encoded3);
                            } else {
                                return new s.d(value4, this.f21595a.i(a(h15.getComponentType()), annotationArr), encoded3).b();
                            }
                        } else {
                            throw c0.p(this.f21597c, i10, "@Field parameters can only be used with form encoding.", new Object[0]);
                        }
                    } else if (annotation instanceof d) {
                        j(i10, type);
                        if (this.f21611q) {
                            Class h16 = c0.h(type);
                            if (cls3.isAssignableFrom(h16)) {
                                Type i13 = c0.i(type, h16, cls3);
                                if (i13 instanceof ParameterizedType) {
                                    ParameterizedType parameterizedType3 = (ParameterizedType) i13;
                                    Type g12 = c0.g(0, parameterizedType3);
                                    if (cls == g12) {
                                        h i14 = this.f21595a.i(c0.g(1, parameterizedType3), annotationArr);
                                        this.f21601g = true;
                                        return new s.e(this.f21597c, i10, i14, ((d) annotation).encoded());
                                    }
                                    throw c0.p(this.f21597c, i10, "@FieldMap keys must be of type String: " + g12, new Object[0]);
                                }
                                throw c0.p(this.f21597c, i10, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                            }
                            throw c0.p(this.f21597c, i10, "@FieldMap parameter type must be Map.", new Object[0]);
                        }
                        throw c0.p(this.f21597c, i10, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
                    } else {
                        Class<c0.c> cls4 = c0.c.class;
                        if (annotation instanceof q) {
                            j(i10, type);
                            if (this.f21612r) {
                                q qVar = (q) annotation;
                                this.f21602h = true;
                                String value5 = qVar.value();
                                Class h17 = c0.h(type);
                                if (!value5.isEmpty()) {
                                    y i15 = y.i("Content-Disposition", "form-data; name=\"" + value5 + "\"", "Content-Transfer-Encoding", qVar.encoding());
                                    if (cls2.isAssignableFrom(h17)) {
                                        if (type instanceof ParameterizedType) {
                                            Type g13 = c0.g(0, (ParameterizedType) type);
                                            if (!cls4.isAssignableFrom(c0.h(g13))) {
                                                return new s.i(this.f21597c, i10, i15, this.f21595a.g(g13, annotationArr, this.f21598d)).c();
                                            }
                                            throw c0.p(this.f21597c, i10, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                                        }
                                        throw c0.p(this.f21597c, i10, h17.getSimpleName() + " must include generic type (e.g., " + h17.getSimpleName() + "<String>)", new Object[0]);
                                    } else if (h17.isArray()) {
                                        Class a10 = a(h17.getComponentType());
                                        if (!cls4.isAssignableFrom(a10)) {
                                            return new s.i(this.f21597c, i10, i15, this.f21595a.g(a10, annotationArr, this.f21598d)).b();
                                        }
                                        throw c0.p(this.f21597c, i10, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                                    } else if (!cls4.isAssignableFrom(h17)) {
                                        return new s.i(this.f21597c, i10, i15, this.f21595a.g(type, annotationArr, this.f21598d));
                                    } else {
                                        throw c0.p(this.f21597c, i10, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                                    }
                                } else if (cls2.isAssignableFrom(h17)) {
                                    if (!(type instanceof ParameterizedType)) {
                                        throw c0.p(this.f21597c, i10, h17.getSimpleName() + " must include generic type (e.g., " + h17.getSimpleName() + "<String>)", new Object[0]);
                                    } else if (cls4.isAssignableFrom(c0.h(c0.g(0, (ParameterizedType) type)))) {
                                        return s.o.f21559a.c();
                                    } else {
                                        throw c0.p(this.f21597c, i10, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                                    }
                                } else if (h17.isArray()) {
                                    if (cls4.isAssignableFrom(h17.getComponentType())) {
                                        return s.o.f21559a.b();
                                    }
                                    throw c0.p(this.f21597c, i10, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                                } else if (cls4.isAssignableFrom(h17)) {
                                    return s.o.f21559a;
                                } else {
                                    throw c0.p(this.f21597c, i10, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                                }
                            } else {
                                throw c0.p(this.f21597c, i10, "@Part parameters can only be used with multipart encoding.", new Object[0]);
                            }
                        } else if (annotation instanceof r) {
                            j(i10, type);
                            if (this.f21612r) {
                                this.f21602h = true;
                                Class h18 = c0.h(type);
                                if (cls3.isAssignableFrom(h18)) {
                                    Type i16 = c0.i(type, h18, cls3);
                                    if (i16 instanceof ParameterizedType) {
                                        ParameterizedType parameterizedType4 = (ParameterizedType) i16;
                                        Type g14 = c0.g(0, parameterizedType4);
                                        if (cls == g14) {
                                            Type g15 = c0.g(1, parameterizedType4);
                                            if (!cls4.isAssignableFrom(c0.h(g15))) {
                                                return new s.j(this.f21597c, i10, this.f21595a.g(g15, annotationArr, this.f21598d), ((r) annotation).encoding());
                                            }
                                            throw c0.p(this.f21597c, i10, "@PartMap values cannot be MultipartBody.Part. Use @Part List<Part> or a different value type instead.", new Object[0]);
                                        }
                                        throw c0.p(this.f21597c, i10, "@PartMap keys must be of type String: " + g14, new Object[0]);
                                    }
                                    throw c0.p(this.f21597c, i10, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                                }
                                throw c0.p(this.f21597c, i10, "@PartMap parameter type must be Map.", new Object[0]);
                            }
                            throw c0.p(this.f21597c, i10, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
                        } else if (annotation instanceof de.a) {
                            j(i10, type);
                            if (this.f21611q || this.f21612r) {
                                throw c0.p(this.f21597c, i10, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
                            } else if (!this.f21603i) {
                                try {
                                    h g16 = this.f21595a.g(type, annotationArr, this.f21598d);
                                    this.f21603i = true;
                                    return new s.c(this.f21597c, i10, g16);
                                } catch (RuntimeException e10) {
                                    throw c0.q(this.f21597c, e10, i10, "Unable to create @Body converter for %s", type);
                                }
                            } else {
                                throw c0.p(this.f21597c, i10, "Multiple @Body method annotations found.", new Object[0]);
                            }
                        } else if (!(annotation instanceof x)) {
                            return null;
                        } else {
                            j(i10, type);
                            Class a11 = a(c0.h(type));
                            int i17 = i10 - 1;
                            while (i17 >= 0) {
                                s sVar2 = this.f21617w[i17];
                                if (!(sVar2 instanceof s.q) || !((s.q) sVar2).f21562a.equals(a11)) {
                                    i17--;
                                } else {
                                    throw c0.p(this.f21597c, i10, "@Tag type " + a11.getName() + " is duplicate of " + t.f21564b.a(this.f21597c, i17) + " and would always overwrite its value.", new Object[0]);
                                }
                            }
                            return new s.q(a11);
                        }
                    }
                }
            }
        }

        static Set h(String str) {
            Matcher matcher = f21593y.matcher(str);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            while (matcher.find()) {
                linkedHashSet.add(matcher.group(1));
            }
            return linkedHashSet;
        }

        private void i(int i10, String str) {
            if (!f21594z.matcher(str).matches()) {
                throw c0.p(this.f21597c, i10, "@Path parameter name must match %s. Found: %s", f21593y.pattern(), str);
            } else if (!this.f21616v.contains(str)) {
                throw c0.p(this.f21597c, i10, "URL \"%s\" does not contain \"{%s}\".", this.f21613s, str);
            }
        }

        private void j(int i10, Type type) {
            if (c0.j(type)) {
                throw c0.p(this.f21597c, i10, "Parameter type must not include a type variable or wildcard: %s", type);
            }
        }

        /* access modifiers changed from: package-private */
        public w b() {
            boolean z10;
            for (Annotation e10 : this.f21598d) {
                e(e10);
            }
            if (this.f21609o != null) {
                if (!this.f21610p) {
                    if (this.f21612r) {
                        throw c0.n(this.f21597c, "Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                    } else if (this.f21611q) {
                        throw c0.n(this.f21597c, "FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                    }
                }
                int length = this.f21599e.length;
                this.f21617w = new s[length];
                int i10 = length - 1;
                for (int i11 = 0; i11 < length; i11++) {
                    s[] sVarArr = this.f21617w;
                    Type type = this.f21600f[i11];
                    Annotation[] annotationArr = this.f21599e[i11];
                    if (i11 == i10) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    sVarArr[i11] = f(i11, type, annotationArr, z10);
                }
                if (this.f21613s != null || this.f21608n) {
                    boolean z11 = this.f21611q;
                    if (!z11 && !this.f21612r && !this.f21610p && this.f21603i) {
                        throw c0.n(this.f21597c, "Non-body HTTP method cannot contain @Body.", new Object[0]);
                    } else if (z11 && !this.f21601g) {
                        throw c0.n(this.f21597c, "Form-encoded method must contain at least one @Field.", new Object[0]);
                    } else if (!this.f21612r || this.f21602h) {
                        return new w(this);
                    } else {
                        throw c0.n(this.f21597c, "Multipart method must contain at least one @Part.", new Object[0]);
                    }
                } else {
                    throw c0.n(this.f21597c, "Missing either @%s URL or @Url parameter.", this.f21609o);
                }
            } else {
                throw c0.n(this.f21597c, "HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
            }
        }
    }

    w(a aVar) {
        this.f21581a = aVar.f21596b;
        this.f21582b = aVar.f21597c;
        this.f21583c = aVar.f21595a.f21624c;
        this.f21584d = aVar.f21609o;
        this.f21585e = aVar.f21613s;
        this.f21586f = aVar.f21614t;
        this.f21587g = aVar.f21615u;
        this.f21588h = aVar.f21610p;
        this.f21589i = aVar.f21611q;
        this.f21590j = aVar.f21612r;
        this.f21591k = aVar.f21617w;
        this.f21592l = aVar.f21618x;
    }

    static w b(y yVar, Class cls, Method method) {
        return new a(yVar, cls, method).b();
    }

    /* access modifiers changed from: package-private */
    public f0 a(Object obj, Object[] objArr) {
        s[] sVarArr = this.f21591k;
        int length = objArr.length;
        if (length == sVarArr.length) {
            v vVar = new v(this.f21584d, this.f21583c, this.f21585e, this.f21586f, this.f21587g, this.f21588h, this.f21589i, this.f21590j);
            if (this.f21592l) {
                length--;
            }
            ArrayList arrayList = new ArrayList(length);
            for (int i10 = 0; i10 < length; i10++) {
                arrayList.add(objArr[i10]);
                sVarArr[i10].a(vVar, objArr[i10]);
            }
            return vVar.k().m(o.class, new o(this.f21581a, obj, this.f21582b, arrayList)).a();
        }
        throw new IllegalArgumentException("Argument count (" + length + ") doesn't match expected count (" + sVarArr.length + ")");
    }
}
