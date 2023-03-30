package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sqa.example.model.Nganh;

@Repository
public interface NganhRepository extends JpaRepository<Nganh, Integer> {
//    @Query(value = "SELECT * FROM tbl_nganh n WHERE n.ten = :ten LIMIT 1", nativeQuery = true)
    Nganh getNganhByTen(String ten);
}
