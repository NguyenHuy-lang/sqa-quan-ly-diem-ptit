package sqa.example.model;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_mon_hoc")
public class MonHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ten")
    private String ten;
    @Column(name = "ty_le_diem_cc")
    private Double tyLeDiemCC;
    @Column(name = "ty_le_diem_th")
    private Double tyLeDiemTH;
    @Column(name = "ty_le_diem_kt")
    private Double tyLeDiemKT;
    @Column(name = "ty_le_diem_bt")
    private Double tyLeDiemBT;
    @Column(name = "ty_le_diem_cuoi_ky")
    private Double tyLeDiemCuoiKy;
}
