package com.zyq.shopserver.system.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    @JSONField(name = "order_id", ordinal = 1)
    private Integer orderId;
    @JSONField(name = "user_id", ordinal = 2)
    private Integer userId;
    @JsonProperty("order_number")
    @JSONField(name = "order_number", ordinal = 3)
    private String orderNumber;
    @JsonProperty("order_price")
    @JSONField(name = "order_price", ordinal = 4)
    private Double orderPrice;
    @JsonProperty("order_pay")
    @JSONField(name = "order_pay", ordinal = 5)
    private String orderPay;
    @JsonProperty("is_send")
    @JSONField(name = "is_send", ordinal = 6)
    private String isSend;
    @JSONField(name = "trade_no", ordinal = 7)
    private String tradeNo;
    @JSONField(name = "order_invoice_title", ordinal = 8)
    private String orderInvoiceTitle;
    @JSONField(name = "order_order_invoice_title_company", ordinal = 9)
    private String orderInvoiceCompany;
    @JSONField(name = "order_order_invoice_title_content", ordinal = 10)
    private String orderInvoiceContent;
    @JSONField(name = "consignee_addr", ordinal = 11)
    private String consigneeAddr;
    @JsonProperty("pay_status")
    @JSONField(name = "pay_status", ordinal = 12)
    private String payStatus;
    @JSONField(name = "create_time", ordinal = 13, format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(name = "update_time", ordinal = 14, format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public List<OrderGoods> getGoods() {
        return goods;
    }

    public void setGoods(List<OrderGoods> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderPrice=" + orderPrice +
                ", orderPay='" + orderPay + '\'' +
                ", isSend='" + isSend + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", orderInvoiceTitle='" + orderInvoiceTitle + '\'' +
                ", orderInvoiceCompany='" + orderInvoiceCompany + '\'' +
                ", orderInvoiceContent='" + orderInvoiceContent + '\'' +
                ", consigneeAddr='" + consigneeAddr + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", goods=" + goods +
                '}';
    }

    @JSONField(name = "goods", ordinal = 15)
    private List<OrderGoods> goods;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderPay() {
        return orderPay;
    }

    public void setOrderPay(String orderPay) {
        this.orderPay = orderPay;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOrderInvoiceTitle() {
        return orderInvoiceTitle;
    }

    public void setOrderInvoiceTitle(String orderInvoiceTitle) {
        this.orderInvoiceTitle = orderInvoiceTitle;
    }

    public String getOrderInvoiceCompany() {
        return orderInvoiceCompany;
    }

    public void setOrderInvoiceCompany(String orderInvoiceCompany) {
        this.orderInvoiceCompany = orderInvoiceCompany;
    }

    public String getOrderInvoiceContent() {
        return orderInvoiceContent;
    }

    public void setOrderInvoiceContent(String orderInvoiceContent) {
        this.orderInvoiceContent = orderInvoiceContent;
    }

    public String getConsigneeAddr() {
        return consigneeAddr;
    }

    public void setConsigneeAddr(String consigneeAddr) {
        this.consigneeAddr = consigneeAddr;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = new Date(createTime);
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = new Date(updateTime);
    }

}
