package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sqa.example.model.MonHoc;

public interface MonHocRepository extends JpaRepository<MonHoc, Integer> {
    @Query(value = "SELECT * FROM tbl_mon_hoc mh WHERE mh.ten = :ten LIMIT 1", nativeQuery = true)
    MonHoc getMonHocByName(@Param("ten") String ten);
}
