package viethung.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "HoaDon")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private String id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "IdKH")
    private Customer customer;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "IdNV")
    private Staff staff;

    @Column(name = "Ma")
    private String code;

    @Column(name = "NgayTao")
    private Date dateCreate;

    @Column(name = "NgayThanhToan")
    private Date datePay;

    @Column(name = "NgayShip")
    private Date dateShip;

    @Column(name = "NgayNhan")
    private Date dateReceive;

    //-1: Hủy ,0:Đang xử lý, 1:đã thanh toán
    @Column(name = "TinhTrang")
    private int status;

    @Column(name = "TenNguoiNhan")
    private String receiver;

    @Column(name = "DiaChi")
    private String address;

    @Column(name = "Sdt")
    private String phoneNumber;


    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<OrderDetail> orderDetailList;

    public Order() {

    }

    public Order(String id, String code, Date dateCreate, Date datePay, Date dateShip, Date dateReceive, int status, String receiver, String address, String phoneNumber) {
        this.id = id;
        this.code = code;
        this.dateCreate = dateCreate;
        this.datePay = datePay;
        this.dateShip = dateShip;
        this.dateReceive = dateReceive;
        this.status = status;
        this.receiver = receiver;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Order(String id, Customer customer, Staff staff, String code, Date dateCreate, Date datePay, Date dateShip, Date dateReceive, int status, String receiver, String address, String phoneNumber, List<OrderDetail> orderDetailList) {
        this.id = id;
        this.customer = customer;
        this.staff = staff;
        this.code = code;
        this.dateCreate = dateCreate;
        this.datePay = datePay;
        this.dateShip = dateShip;
        this.dateReceive = dateReceive;
        this.status = status;
        this.receiver = receiver;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.orderDetailList = orderDetailList;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDatePay() {
        return datePay;
    }

    public void setDatePay(Date datePay) {
        this.datePay = datePay;
    }

    public Date getDateShip() {
        return dateShip;
    }

    public void setDateShip(Date dateShip) {
        this.dateShip = dateShip;
    }

    public Date getDateReceive() {
        return dateReceive;
    }

    public void setDateReceive(Date dateReceive) {
        this.dateReceive = dateReceive;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public long getTotalPrice(){
        long total = 0;
        for (OrderDetail orderDetail:this.orderDetailList) {
            total += orderDetail.getQuanltity() * orderDetail.getPrice();
        }
        return total;
    }
}
