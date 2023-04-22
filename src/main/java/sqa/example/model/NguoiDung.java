package sqa.example.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="tbl_nguoi_dung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(name = "ten")
    private String name;
    @Column(name = "gioi_tinh")
    private String gioiTinh;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "email")
    @JsonProperty("email")
    private String email;
    @Column(name = "so_dien_thoai")
    @JsonProperty("so_dien_thoai")
    private String soDienThoai;
    @Column(name = "ngay_sinh")
    private Date ngaySinh;
}
