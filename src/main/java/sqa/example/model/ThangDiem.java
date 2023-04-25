package sqa.example.model;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@Builder
@Entity
@Table(name = "tbl_thang_diem")
public class ThangDiem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "_from")
    private double from;
    @Column(name = "_to")
    private double to;
    @Column(name = "diem_chu")
    private String diemChu;
    @Column(name = "diem_he_4")
    private Double diemHe4;
}

