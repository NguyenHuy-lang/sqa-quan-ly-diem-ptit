package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sqa.example.model.NguoiDung;
import sqa.example.model.SinhVien;
@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, Integer> {
    @Override
    SinhVien getById(Integer integer);
    @Query(value = "SELECT * from tbl_sinh_vien sv where sv.nguoi_dung_id = :nguoiDungId", nativeQuery = true)
    SinhVien getSinhVienByNguoiDungId(@Param("nguoiDungId") Integer nguoiDungId);
}
