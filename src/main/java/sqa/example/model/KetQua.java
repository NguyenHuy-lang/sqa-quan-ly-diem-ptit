package sqa.example.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_ket_qua")
public class KetQua implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "diem_CC")
    private Double diemCC;
    @Column(name = "diem_TH")
    private Double diemTH;
    @Column(name = "diem_KT")
    private Double diemKT;
    @Column(name = "diem_BT")
    private Double diemBT;
    @Column(name = "diem_cuoi_ky")
    private Double diemCuoiKy;
    @Transient
    private Double diemHe4;
    @Transient
    private Double diemHe10;
    @Transient
    private String diemChu;
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "sinh_vien_id")
    @JsonIgnore
    private SinhVien sinhVien;
    @Transient
    private String nameSinhVien;
    @Transient
    private String maSinhVien;

}
