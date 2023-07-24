package viethung.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "CuaHang")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private String id;

    @Column(name = "Ma", length = 20, unique = true)
    private String code;

    @Column(name = "Ten", length = 30)
    private String name;

    @Column(name = "DiaChi")
    private String address;

    @Column(name = "ThanhPho")
    private String city;

    @Column(name = "QuocGia")
    private String country;

    public Store() {
    }
    public Store(String id, String code, String name, String address, String city, String country) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
