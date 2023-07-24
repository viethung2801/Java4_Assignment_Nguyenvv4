package viethung.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "KhachHang")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private String id;

    @Column(name = "Ma", length = 20, unique = true)
    private String code;

    @Column(name = "Ten")
    private String firstName;

    @Column(name = "TenDem")
    private String middleName;

    @Column(name = "Ho")
    private String lastName;

    @Column(name = "NgaySinh")
    private Date dateBirth;

    @Column(name = "Sdt")
    private String phoneNumber;

    @Column(name = "DiaChi")
    private String address;

    @Column(name = "ThanhPho")
    private String city;

    @Column(name = "QuocGia")
    private String country;

    @Column(name = "MatKhau")
    private String password;

    public Customer() {
    }
    public Customer(String id, String code, String firstName, String middleName, String lastName, Date dateBirth, String phoneNumber, String address, String city, String country, String password) {
        this.id = id;
        this.code = code;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.password = password;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
