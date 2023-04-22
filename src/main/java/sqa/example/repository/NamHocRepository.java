package sqa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sqa.example.model.NamHoc;

@Repository
public interface NamHocRepository extends JpaRepository<NamHoc, Integer> {}
