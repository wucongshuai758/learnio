package com.wcs.learn.netty.httpXml;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午11:50 2019/3/10 Modifyby:
 **/

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import java.net.InetAddress;
import java.util.List;

public class HttpXmlRequestEncoder extends AbstractHttpXmlEncoder<HttpXmlRequest> {

    @Override
    protected void encode(ChannelHandlerContext ctx, HttpXmlRequest msg, List<Object> out) throws Exception {
        // 调用父类的encode0方法将Order对象转换为xml字符串，并将其封装为ByteBuf
        ByteBuf body = encode0(ctx, msg.getBody());
        FullHttpRequest request = msg.getRequest();
        // 如request为空，则新建一个FullHttpRequest对象，并将设置消息头
        if (request == null) {
            // 在构造方法中，将body设置为请求消息体
            request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "/do", body);
            HttpHeaders headers = request.headers();
            // 表示请求的服务器网址
            headers.set(DefaultHttpHeaders.Names.HOST, InetAddress.getLocalHost().getHostAddress());
            // Connection表示客户端与服务连接类型；Keep-Alive表示长连接；CLOSE表示短连接
            // header中包含了值为close的connection，都表明当前正在使用的tcp链接在请求处理完毕后会被断掉。
            // 以后client再进行新的请求时就必须创建新的tcp链接了。
            headers.set(DefaultHttpHeaders.Names.CONNECTION, HttpHeaders.Values.CLOSE);
            // 浏览器支持的压缩编码是 gzip 和 deflate
            headers.set(DefaultHttpHeaders.Names.ACCEPT_ENCODING,
                HttpHeaders.Values.GZIP.toString() + ',' + HttpHeaders.Values.DEFLATE.toString());
            // 浏览器支持的解码集
            headers.set(DefaultHttpHeaders.Names.ACCEPT_CHARSET, "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
            // 浏览器支持的语言
            headers.set(DefaultHttpHeaders.Names.ACCEPT_LANGUAGE, "zh");
            // 使用的用户代理是 Netty xml Http Client side
            headers.set(DefaultHttpHeaders.Names.USER_AGENT, "Netty xml Http Client side");
            // 浏览器支持的 MIME类型,优先顺序为从左到右
            headers.set(DefaultHttpHeaders.Names.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        }
        // 由于此处没有使用chunk方式，所以要设置消息头中设置消息体的CONTENT_LENGTH
        request.headers().set(DefaultHttpHeaders.Names.CONTENT_LENGTH, body.readableBytes());
        // 将请求消息添加进out中，待后面的编码器对消息进行编码
        out.add(request);
    }

}

