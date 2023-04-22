package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sqa.example.model.GiaoVien;

@Repository
public interface GiaoVienRepository extends JpaRepository<GiaoVien, Integer> {
    @Query(value = "SELECT * from tbl_giao_vien sv where sv.nguoi_dung_id = :nguoiDungId", nativeQuery = true)
    GiaoVien getGiaoVienByNguoiDungId(Integer nguoiDungId);
}
