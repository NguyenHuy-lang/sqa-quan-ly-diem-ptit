package sqa.example.model;
import javax.persistence.*;
import lombok.*;
import sqa.example.model.*;
import java.util.List;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_lop_hoc_phan")
public class LopHocPhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "bat_dau")
    private Date batDau;
    @Column(name = "ket_thuc")
    private Date ketThuc;
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc_id")
    private NienKhoaNganhNamHocKyHocMonHoc nienKhoaNganhNamHocKyHocMonHoc;
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "phong_hoc_id")
    private PhongHoc phongHoc;
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "lop_hoc_phan_id")
    private List<KetQua> listKetQua;
    @ManyToOne
    @JoinColumn(name = "giao_vien_id")
    private GiaoVien giaoVien;

}
