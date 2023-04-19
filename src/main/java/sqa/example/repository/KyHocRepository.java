package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sqa.example.model.KyHoc;

@Repository
public interface KyHocRepository extends JpaRepository<KyHoc, Integer> {}
