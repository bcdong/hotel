package hotel.entity;

import hotel.type.ManagerType;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
@Entity
@Table(name = "manager_tbl", schema = "hotel", catalog = "")
public class ManagerTblEntity {
    private int id;
    private String username;
    private String password;
    private String name;
    private ManagerType type;
    private Collection<HotelTblEntity> hotelTblsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 32)
    public ManagerType getType() {
        return type;
    }

    public void setType(ManagerType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManagerTblEntity that = (ManagerTblEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "managerTblByManagerId")
    public Collection<HotelTblEntity> getHotelTblsById() {
        return hotelTblsById;
    }

    public void setHotelTblsById(Collection<HotelTblEntity> hotelTblsById) {
        this.hotelTblsById = hotelTblsById;
    }
}
