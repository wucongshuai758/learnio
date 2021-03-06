package com.wcs.learn.netty.httpXml;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import java.util.List;
/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午11:39 2019/3/10 Modifyby:
 **/



public class Customer {
    @XStreamAsAttribute
    private long customerNumber;

    private String firstName;

    private String lastName;

    private List<String> middleNames;

    public long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(long customerId) {
        this.customerNumber = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getMiddleNames() {
        return middleNames;
    }

    public void setMiddleNames(List<String> middleNames) {
        this.middleNames = middleNames;
    }

    @Override
    public String toString() {
        return "Customer [customerNumber=" + customerNumber + ", firstName=" + firstName + ", lastName=" + lastName
            + ", middleNames=" + middleNames + "]";
    }

}

