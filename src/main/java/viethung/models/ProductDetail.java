package viethung.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ChiTietSp")
public class ProductDetail {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdSP")
    private Product product;

    @OneToOne
    @JoinColumn(name = "IdNsx")
    private Producer producer;

    @OneToOne
    @JoinColumn(name = "IdMauSac")
    private Color color;

    @OneToOne
    @JoinColumn(name = "IdDongSP")
    private Category category;

    @Column(name = "NamBH")
    private int warrantyYear;

    @Column(name = "MoTa")
    private String description;

    @Column(name = "SoLuongTon")
    private int quantity;

    @Column(name = "GiaNhap")
    private long cost;

    @Column(name = "GiaBan")
    private long price;

    @OneToMany(mappedBy = "productDetail")
    private List<OrderDetail> orderDetailList ;
    public ProductDetail() {
    }

    public ProductDetail(String id, Product product, Producer producer, Color color, Category category, int warrantyYear, String description, int quantity, long cost, long price, List<OrderDetail> orderDetailList) {
        this.id = id;
        this.product = product;
        this.producer = producer;
        this.color = color;
        this.category = category;
        this.warrantyYear = warrantyYear;
        this.description = description;
        this.quantity = quantity;
        this.cost = cost;
        this.price = price;
        this.orderDetailList = orderDetailList;
    }

    public ProductDetail(String id, int warrantyYear, String description, int quantity, long cost, long price) {
        this.id = id;
        this.warrantyYear = warrantyYear;
        this.description = description;
        this.quantity = quantity;
        this.cost = cost;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getWarrantyYear() {
        return warrantyYear;
    }

    public void setWarrantyYear(int warrantyYear) {
        this.warrantyYear = warrantyYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
