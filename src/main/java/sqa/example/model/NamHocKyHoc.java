package sqa.example.model;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_nam_hoc_ky_hoc")
public class NamHocKyHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nam_hoc_id")
    private NamHoc namHoc;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ky_hoc_id")
    private KyHoc kyHoc;
}
