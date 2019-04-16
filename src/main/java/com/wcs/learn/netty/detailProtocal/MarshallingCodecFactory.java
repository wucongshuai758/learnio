package com.wcs.learn.netty.detailProtocal;

import org.jboss.marshalling.*;

import java.io.IOException;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午2:42 2019/4/10 Modifyby:
 **/
public final class MarshallingCodecFactory {

    /**
     * 创建Jboss Marshaller
     *
     * @return
     * @throws IOException
     */
    protected static Marshaller buildMarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling.getMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        Marshaller marshaller = marshallerFactory
            .createMarshaller(configuration);
        return marshaller;
    }

    /**
     * 创建Jboss Unmarshaller
     *
     * @return
     * @throws IOException
     */
    protected static Unmarshaller buildUnMarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling
            .getMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        final Unmarshaller unmarshaller = marshallerFactory
            .createUnmarshaller(configuration);
        return unmarshaller;
    }
}
