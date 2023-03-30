package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sqa.example.model.GiaoVien;

@Repository
public interface GiaoVienRepository extends JpaRepository<GiaoVien, Integer> {
	@Query(value = "SELECT * FROM tbl_giao_vien gv WHERE gv.ma_giao_vien = :ma_giao_vien", nativeQuery = true)
    GiaoVien getGiaoVienByMaGiaoVien(@Param("ma_giao_vien") String ma_giao_vien);
}
