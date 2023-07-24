package viethung.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "NhanVien")
public class Staff {
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

    @Column(name = "GioiTinh")
    private String gender;

    @Column(name = "NgaySinh")
    private Date dateBirth;

    @Column(name = "Sdt")
    private String phoneNumber;

    @Column(name = "DiaChi")
    private String address;

    @Column(name = "MatKhau")
    private String password;

    @Column(name = "TrangThai")
    private int status;

    @OneToOne
    @JoinColumn(name = "IdCV")
    private Position position;

    @OneToOne
    @JoinColumn(name = "IdCH")
    private Store store;

    public Staff(String id, String code, String firstName, String middleName, String lastName, String gender, Date dateBirth, String phoneNumber, String address, String password, int status) {
        this.id = id;
        this.code = code;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateBirth = dateBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.status = status;
    }

    public Staff() {
    }
    public Staff(String id, String code, String firstName, String middleName, String lastName, Date dateBirth, String phoneNumber, String address, String password, int status, Position position, Store store) {
        this.id = id;
        this.code = code;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.status = status;
        this.position = position;
        this.store = store;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
