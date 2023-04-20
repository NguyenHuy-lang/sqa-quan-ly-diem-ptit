package sqa.example.model;

import javax.persistence.*;
import lombok.*;
import sqa.example.model.NguoiDung;

import javax.persistence.DiscriminatorValue;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_giao_vien")

public class GiaoVien implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "nguoi_dung_id")
    private NguoiDung nguoiDung;
	@Column(name = "ma_giao_vien")
    private String maGiaoVien;
}
