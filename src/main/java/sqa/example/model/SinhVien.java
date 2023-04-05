package sqa.example.model;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_sinh_vien")
@Builder
public class SinhVien{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "nguoi_dung_id")
    private NguoiDung nguoiDung;
    @Column(name = "ma_sinh_vien")
    private String maSinhVien;
    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name = "nien_khoa_nganh_id")
    private NienKhoaNganh nienKhoaNganh;


}
