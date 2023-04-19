package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sqa.example.model.KetQua;
@Repository
public interface KetQuaRepository extends JpaRepository<KetQua, Integer> {
    @Query(value = "SELECT * FROM tbl_ket_qua kq WHERE kq.sinh_vien_id = :sinh_vien_id AND kq.lop_hoc_phan_id = :lop_hoc_phan_id", nativeQuery = true)
    KetQua findBySinhVienAndLopHocPhanId(@Param("sinh_vien_id") Integer sinh_vien_id, @Param("lop_hoc_phan_id") Integer lop_hoc_phan_id);
}
