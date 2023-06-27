package com.mkyong;

import javax.persistence.*;

@Entity
@Table(name = "mvd", schema = "thlogistics", catalog = "")

public class Mvd {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    public Mvd() {

    }
    @Override
    public String toString() {
        return "KienHang{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", MVD='" + mvd + '\'' +
                ", Cân Nặng='" + cannang + '\'' +
                ", Giávc='" + giavc + '\'' +
                ", Thanh Tiền='" + thanhtien + '\'' +
                ", status='" + status + '\'' +
                ", Time='" + times + '\'' +
                '}';
    }

    @Basic
    @Column(name = "mvd",columnDefinition = "varchar(100) DEFAULT Null")
    private String mvd;
    @Basic
    @Column(name = "name",columnDefinition = "varchar(100) DEFAULT Null")
    private String name;
    @Basic
    @Column(name = "line",columnDefinition = "varchar(20) DEFAULT Null")
    private String line;
    @Basic
    @Column(name = "status",columnDefinition = "int DEFAULT 1")
    private int status;
    @Basic
    @Column(name = "order_id",columnDefinition = "int DEFAULT Null")
    private Integer orderId;
    @Basic
    @Column(name = "user_id",columnDefinition = "int DEFAULT Null")
    private Integer userId;
    @Basic
    @Column(name = "cannang",columnDefinition = "double DEFAULT 0")
    private double cannang;
    @Basic
    @Column(name = "times",columnDefinition = "Longtext DEFAULT Null")
    private String times;
    @Basic
    @Column(name = "giavc",columnDefinition = "double DEFAULT 0")
    private Double giavc;
    @Basic
    @Column(name = "thanhtien",columnDefinition = "double DEFAULT 0")
    private Double thanhtien;
    @Basic
    @Column(name = "ghichu",columnDefinition = "varchar(100) DEFAULT Null")
    private String ghichu;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMvd() {
        return mvd;
    }

    public void setMvd(String mvd) {
        this.mvd = mvd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public double getCannang() {
        return cannang;
    }

    public void setCannang(double cannang) {
        this.cannang = cannang;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Double getGiavc() {
        return giavc;
    }

    public void setGiavc(Double giavc) {
        this.giavc = giavc;
    }

    public Double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(Double thanhtien) {
        this.thanhtien = thanhtien;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mvd mvd1 = (Mvd) o;

        if (id != mvd1.id) return false;
        if (status != mvd1.status) return false;
        if (Double.compare(mvd1.cannang, cannang) != 0) return false;
        if (mvd != null ? !mvd.equals(mvd1.mvd) : mvd1.mvd != null) return false;
        if (name != null ? !name.equals(mvd1.name) : mvd1.name != null) return false;
        if (line != null ? !line.equals(mvd1.line) : mvd1.line != null) return false;
        if (orderId != null ? !orderId.equals(mvd1.orderId) : mvd1.orderId != null) return false;
        if (times != null ? !times.equals(mvd1.times) : mvd1.times != null) return false;
        if (giavc != null ? !giavc.equals(mvd1.giavc) : mvd1.giavc != null) return false;
        if (thanhtien != null ? !thanhtien.equals(mvd1.thanhtien) : mvd1.thanhtien != null) return false;
        if (ghichu != null ? !ghichu.equals(mvd1.ghichu) : mvd1.ghichu != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (mvd != null ? mvd.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (line != null ? line.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        temp = Double.doubleToLongBits(cannang);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (times != null ? times.hashCode() : 0);
        result = 31 * result + (giavc != null ? giavc.hashCode() : 0);
        result = 31 * result + (thanhtien != null ? thanhtien.hashCode() : 0);
        result = 31 * result + (ghichu != null ? ghichu.hashCode() : 0);
        return result;
    }
}
