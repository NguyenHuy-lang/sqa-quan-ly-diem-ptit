package sqa.example.model;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_nien_khoa_nganh")
public class NienKhoaNganh implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nganh_id")
    private Nganh nganh;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nien_khoa_id")
    private NienKhoa nienKhoa;
}
