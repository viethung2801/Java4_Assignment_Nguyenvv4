package viethung.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "SanPham")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private String id;
    @Column(name = "Ma",length = 20,unique = true)
    private String code;
    @Column(name = "Ten",length = 30)
    private String name;

    public Product(String id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
