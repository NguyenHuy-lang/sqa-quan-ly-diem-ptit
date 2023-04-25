package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sqa.example.model.NamHoc;
@Repository
public interface NamHocRepository extends JpaRepository<NamHoc, Integer> {
    @Query(value = "SELECT * FROM tbl_nam_hoc nh where nh.ten = :ten LIMIT 1", nativeQuery = true)
    NamHoc getIdNamHocByName(@Param("ten") String name);
}
