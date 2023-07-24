package viethung.models;

import jakarta.persistence.*;

@Entity
@Table(name = "HoaDonChiTiet")
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "IdHoaDon")
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "IdChiTietSP")
    private ProductDetail productDetail;

    @Column(name = "SoLuong")
    private int quanltity;

    @Column(name = "DonGia")
    private long price;

    public OrderDetail() {
    }
    public OrderDetail(Order order, int quanltity, long price) {
        this.order = order;
        this.quanltity = quanltity;
        this.price = price;
    }
    public OrderDetail(Order order, ProductDetail productDetail, int quanltity, long price) {
        this.order = order;
        this.productDetail = productDetail;
        this.quanltity = quanltity;
        this.price = price;
    }

    public Order getOder() {
        return order;
    }

    public void setOder(Order order) {
        this.order = order;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public int getQuanltity() {
        return quanltity;
    }

    public void setQuanltity(int quanltity) {
        this.quanltity = quanltity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
