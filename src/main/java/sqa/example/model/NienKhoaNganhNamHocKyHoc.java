package sqa.example.model;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_nien_khoa_nganh_nam_hoc_ky_hoc")
public class NienKhoaNganhNamHocKyHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nam_hoc_ky_hoc_id")
    private NamHocKyHoc namHocKyHoc;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nien_khoa_nganh_id")
    private NienKhoaNganh nienKhoaNganh;
}
