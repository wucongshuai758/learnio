package com.wcs.learn.netty.serial;

import java.io.Serializable;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午11:01 2019/3/7 Modifyby:
 **/
public class SubscribeResp  implements Serializable {
    private static final long serialVersionUID = -4261173283103510587L;

    private int subReqId;

    private int respCode;

    private String desc;
    @Override
    public String toString() {
        return "subReqId="+subReqId+" respCode="+respCode+" desc="+desc;
    }

    public int getSubReqId() {
        return subReqId;
    }

    public void setSubReqId(int subReqId) {
        this.subReqId = subReqId;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
