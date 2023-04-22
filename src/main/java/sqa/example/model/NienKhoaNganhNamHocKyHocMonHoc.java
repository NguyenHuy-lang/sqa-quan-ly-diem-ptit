package sqa.example.model;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc")
public class NienKhoaNganhNamHocKyHocMonHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nien_khoa_nganh_nam_hoc_ky_hoc_id")
    private NienKhoaNganhNamHocKyHoc nienKhoaNganhNamHocKyHoc;
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mon_hoc_id")
    private MonHoc monHoc;
}
