package com.wcs.learn.netty.httpXml;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午11:42 2019/3/10 Modifyby:
 **/

public class HttpXmlRequest {

    private FullHttpRequest request;
    private Object body;

    public HttpXmlRequest(FullHttpRequest request, Object body) {
        this.request = request;
        this.body = body;
    }

    public final FullHttpRequest getRequest() {
        return request;
    }

    public final void setRequest(FullHttpRequest request) {
        this.request = request;
    }

    public final Object getBody() {
        return body;
    }

    public final void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "HttpXmlRequest [request=" + request + ", body =" + body + "]";
    }
}
