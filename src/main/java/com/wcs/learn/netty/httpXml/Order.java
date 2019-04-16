package com.wcs.learn.netty.httpXml;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午11:35 2019/3/10 Modifyby:
 **/




@XStreamAlias("order")
public class Order {
    @XStreamAsAttribute
    private long orderNumber;

    private Customer customer;

    private Address billTo;

    private Shipping shipping;

    private Address shipTo;
    @XStreamAsAttribute
    private Float total;

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderId) {
        this.orderNumber = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getBillTo() {
        return billTo;
    }

    public void setBillTo(Address billTo) {
        this.billTo = billTo;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Address getShipTo() {
        return shipTo;
    }

    public void setShipTo(Address shipTo) {
        this.shipTo = shipTo;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order [orderNumber=" + orderNumber + ", customer=" + customer + ", billTo=" + billTo + ", shipping="
            + shipping.toString() + ", shipTo=" + shipTo + ", total=" + total + "]";
    }

}

