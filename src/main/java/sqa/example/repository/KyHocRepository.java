package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sqa.example.model.KyHoc;
@Repository
public interface KyHocRepository extends JpaRepository<KyHoc, Integer> {
    @Query(value = "SELECT * FROM tbl_ky_hoc kh WHERE kh.ten = :ten LIMIT 1", nativeQuery = true)
    KyHoc getKyHocByName(@Param("ten") String ten);
}
