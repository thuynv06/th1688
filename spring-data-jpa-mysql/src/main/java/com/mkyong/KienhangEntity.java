package com.mkyong;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "kienhang", schema = "thlogistics", catalog = "")
public class KienhangEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    public KienhangEntity() {

    }
    @Override
    public String toString() {
        return "KienHang{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", MVD='" + ladingcode + '\'' +
                ", Cân Nặng='" + size + '\'' +
                ", status='" + status + '\'' +
                ", Time='" + listtimestatus + '\'' +
                '}';
    }

    public KienhangEntity(String ladingCode,Double amount, Double size, int status, String listTimeStatus, String name) {
        this.ladingcode = ladingCode;
//        this.shippingWay = shippingWay;
        this.amount=amount;
        this.size = size;
        this.status = status;
        this.listtimestatus = listTimeStatus;
        this.name = name;
    }

    @Basic
    @Column(name = "order_id", columnDefinition = "int DEFAULT null")
    private Integer orderId;
    @Basic
//    @Column(name = "orderCode", columnDefinition = "varchar(50) DEFAULT null")
    private String ordercode;
    @Basic
    @Column(name = "user_id", columnDefinition = "INT DEFAULT 0")
    private int userId;
//    @Basic
//    @Column(name = "ladingCode", columnDefinition = "varchar(50) DEFAULT null")
    private String ladingcode;
    @Basic
    @Column(name = "type", columnDefinition = "INT DEFAULT 0")
    private Integer type;
    @Basic
    @Column(name = "amount", columnDefinition = "INT DEFAULT 1")
    private Double amount;
//    @Basic
//    @Column(name = "shippingWay", columnDefinition = "varchar(15) DEFAULT 0")
    private String shippingway;
    @Basic
    @Column(name = "size", columnDefinition = "float DEFAULT 0")
    private Double size;
    @Basic
    @Column(name = "feetransport", columnDefinition = "float DEFAULT 0")
    private Double feetransport;
    @Basic
    @Column(name = "totalfeetransport", columnDefinition = "float DEFAULT 0")
    private Double totalfeetransport;
    @Basic
    @Column(name = "status", columnDefinition = "int DEFAULT 0")
    private int status;
    @Basic
    @Column(name = "price", columnDefinition = "float DEFAULT 0")
    private Double price;
    @Basic
    @Column(name = "currency", columnDefinition = "float DEFAULT 0")
    private Double currency;
    @Basic
    @Column(name = "totalmoney", columnDefinition = "float DEFAULT 0")
    private Double totalmoney;
    @Basic
    @Column(name = "totalyen", columnDefinition = "float DEFAULT 0")
    private Double totalyen;
    @Basic
    @Column(name = "servicefee", columnDefinition = "float DEFAULT 0")
    private Double servicefee;
    @Basic
    @Column(name = "totalservicefee", columnDefinition = "float DEFAULT 0")
    private Double totalservicefee;
//    @Basic
//    @Column(name = "listTimeStatus", columnDefinition = "longtext DEFAULT Null")
    private String listtimestatus;
    @Basic
    @Column(name = "name", columnDefinition = "varchar(500) DEFAULT Null")
    private String name;
    @Basic
    @Column(name = "nametq", columnDefinition = "varchar(100) DEFAULT Null")
    private String nametq;
    @Basic
    @Column(name = "bh", columnDefinition = "int DEFAULT 0")
    private int bh;
    @Basic
    @Column(name = "tienbh", columnDefinition = "float DEFAULT 0")
    private double tienbh;
//    @Basic
//    @Column(name = "dateCreated", columnDefinition = "DATETIME NULL DEFAULT CURRENT_TIMESTAMP;")
    private Timestamp datecreated;
    @Basic
    @Column(name = "note", columnDefinition = "text DEFAULT Null")
    private String note;
    @Basic
    @Column(name = "total", columnDefinition = "float DEFAULT 0")
    private Double total;
    @Basic
    @Column(name = "linksp", columnDefinition = "text DEFAULT Null")
    private String linksp;
    @Basic
    @Column(name = "shiptq", columnDefinition = "float DEFAULT 0")
    private Double shiptq;
    @Basic
    @Column(name = "magiamgia", columnDefinition = "float DEFAULT 0")
    private Double magiamgia;
    @Basic
    @Column(name = "giamgiacuahang", columnDefinition = "float DEFAULT 0")
    private Double giamgiacuahang;
    @Basic
    @Column(name = "color", columnDefinition = "varchar(15) DEFAULT Null")
    private String color;
    @Basic
    @Column(name = "kichthuoc", columnDefinition = "varchar(10) DEFAULT Null")
    private String kichthuoc;
    @Basic
    @Column(name = "gianhap", columnDefinition = "float DEFAULT 0")
    private Double gianhap;

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getLadingcode() {
        return ladingcode;
    }

    public void setLadingcode(String ladingcode) {
        this.ladingcode = ladingcode;
    }

    public String getShippingway() {
        return shippingway;
    }

    public void setShippingway(String shippingway) {
        this.shippingway = shippingway;
    }

    public String getListtimestatus() {
        return listtimestatus;
    }

    public void setListtimestatus(String listtimestatus) {
        this.listtimestatus = listtimestatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getFeetransport() {
        return feetransport;
    }

    public void setFeetransport(Double feetransport) {
        this.feetransport = feetransport;
    }

    public Double getTotalfeetransport() {
        return totalfeetransport;
    }

    public void setTotalfeetransport(Double totalfeetransport) {
        this.totalfeetransport = totalfeetransport;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCurrency() {
        return currency;
    }

    public void setCurrency(Double currency) {
        this.currency = currency;
    }

    public Double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Double totalmoney) {
        this.totalmoney = totalmoney;
    }

    public Double getTotalyen() {
        return totalyen;
    }

    public void setTotalyen(Double totalyen) {
        this.totalyen = totalyen;
    }

    public Double getServicefee() {
        return servicefee;
    }

    public void setServicefee(Double servicefee) {
        this.servicefee = servicefee;
    }

    public Double getTotalservicefee() {
        return totalservicefee;
    }

    public void setTotalservicefee(Double totalservicefee) {
        this.totalservicefee = totalservicefee;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNametq() {
        return nametq;
    }

    public void setNametq(String nametq) {
        this.nametq = nametq;
    }

    public int getBh() {
        return bh;
    }

    public void setBh(int bh) {
        this.bh = bh;
    }

    public double getTienbh() {
        return tienbh;
    }

    public void setTienbh(double tienbh) {
        this.tienbh = tienbh;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getLinksp() {
        return linksp;
    }

    public void setLinksp(String linksp) {
        this.linksp = linksp;
    }

    public Double getShiptq() {
        return shiptq;
    }

    public void setShiptq(Double shiptq) {
        this.shiptq = shiptq;
    }

    public Double getMagiamgia() {
        return magiamgia;
    }

    public void setMagiamgia(Double magiamgia) {
        this.magiamgia = magiamgia;
    }

    public Double getGiamgiacuahang() {
        return giamgiacuahang;
    }

    public void setGiamgiacuahang(Double giamgiacuahang) {
        this.giamgiacuahang = giamgiacuahang;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getKichthuoc() {
        return kichthuoc;
    }

    public void setKichthuoc(String kichthuoc) {
        this.kichthuoc = kichthuoc;
    }

    public Double getGianhap() {
        return gianhap;
    }

    public void setGianhap(Double gianhap) {
        this.gianhap = gianhap;
    }


}
