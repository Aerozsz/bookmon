package org.apache.http.protocol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.util.Args;

@Deprecated
/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class BasicHttpProcessor implements HttpProcessor, HttpRequestInterceptorList, HttpResponseInterceptorList, Cloneable {
    protected final List<HttpRequestInterceptor> requestInterceptors = new ArrayList();
    protected final List<HttpResponseInterceptor> responseInterceptors = new ArrayList();

    public void addInterceptor(HttpRequestInterceptor httpRequestInterceptor) {
        addRequestInterceptor(httpRequestInterceptor);
    }

    public void addRequestInterceptor(HttpRequestInterceptor httpRequestInterceptor) {
        if (httpRequestInterceptor != null) {
            this.requestInterceptors.add(httpRequestInterceptor);
        }
    }

    public void addResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor, int i10) {
        if (httpResponseInterceptor != null) {
            this.responseInterceptors.add(i10, httpResponseInterceptor);
        }
    }

    public void clearInterceptors() {
        clearRequestInterceptors();
        clearResponseInterceptors();
    }

    public void clearRequestInterceptors() {
        this.requestInterceptors.clear();
    }

    public void clearResponseInterceptors() {
        this.responseInterceptors.clear();
    }

    public Object clone() {
        BasicHttpProcessor basicHttpProcessor = (BasicHttpProcessor) super.clone();
        copyInterceptors(basicHttpProcessor);
        return basicHttpProcessor;
    }

    public BasicHttpProcessor copy() {
        BasicHttpProcessor basicHttpProcessor = new BasicHttpProcessor();
        copyInterceptors(basicHttpProcessor);
        return basicHttpProcessor;
    }

    /* access modifiers changed from: protected */
    public void copyInterceptors(BasicHttpProcessor basicHttpProcessor) {
        basicHttpProcessor.requestInterceptors.clear();
        basicHttpProcessor.requestInterceptors.addAll(this.requestInterceptors);
        basicHttpProcessor.responseInterceptors.clear();
        basicHttpProcessor.responseInterceptors.addAll(this.responseInterceptors);
    }

    public HttpRequestInterceptor getRequestInterceptor(int i10) {
        if (i10 < 0 || i10 >= this.requestInterceptors.size()) {
            return null;
        }
        return this.requestInterceptors.get(i10);
    }

    public int getRequestInterceptorCount() {
        return this.requestInterceptors.size();
    }

    public HttpResponseInterceptor getResponseInterceptor(int i10) {
        if (i10 < 0 || i10 >= this.responseInterceptors.size()) {
            return null;
        }
        return this.responseInterceptors.get(i10);
    }

    public int getResponseInterceptorCount() {
        return this.responseInterceptors.size();
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        for (HttpRequestInterceptor process : this.requestInterceptors) {
            process.process(httpRequest, httpContext);
        }
    }

    public void removeRequestInterceptorByClass(Class<? extends HttpRequestInterceptor> cls) {
        Iterator<HttpRequestInterceptor> it = this.requestInterceptors.iterator();
        while (it.hasNext()) {
            if (it.next().getClass().equals(cls)) {
                it.remove();
            }
        }
    }

    public void removeResponseInterceptorByClass(Class<? extends HttpResponseInterceptor> cls) {
        Iterator<HttpResponseInterceptor> it = this.responseInterceptors.iterator();
        while (it.hasNext()) {
            if (it.next().getClass().equals(cls)) {
                it.remove();
            }
        }
    }

    public void setInterceptors(List<?> list) {
        Args.notNull(list, "Inteceptor list");
        this.requestInterceptors.clear();
        this.responseInterceptors.clear();
        for (Object next : list) {
            if (next instanceof HttpRequestInterceptor) {
                addInterceptor((HttpRequestInterceptor) next);
            }
            if (next instanceof HttpResponseInterceptor) {
                addInterceptor((HttpResponseInterceptor) next);
            }
        }
    }

    public void addInterceptor(HttpRequestInterceptor httpRequestInterceptor, int i10) {
        addRequestInterceptor(httpRequestInterceptor, i10);
    }

    public void addRequestInterceptor(HttpRequestInterceptor httpRequestInterceptor, int i10) {
        if (httpRequestInterceptor != null) {
            this.requestInterceptors.add(i10, httpRequestInterceptor);
        }
    }

    public void addResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor) {
        if (httpResponseInterceptor != null) {
            this.responseInterceptors.add(httpResponseInterceptor);
        }
    }

    public void addInterceptor(HttpResponseInterceptor httpResponseInterceptor) {
        addResponseInterceptor(httpResponseInterceptor);
    }

    public void process(HttpResponse httpResponse, HttpContext httpContext) {
        for (HttpResponseInterceptor process : this.responseInterceptors) {
            process.process(httpResponse, httpContext);
        }
    }

    public void addInterceptor(HttpResponseInterceptor httpResponseInterceptor, int i10) {
        addResponseInterceptor(httpResponseInterceptor, i10);
    }
}
