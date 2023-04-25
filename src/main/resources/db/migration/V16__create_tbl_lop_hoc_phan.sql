CREATE TABLE tbl_lop_hoc_phan(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    bat_dau TIME NOT NULL,
    ket_thuc TIME NOT NULL,
    phong_hoc_id INT,
    giao_vien_id INT,
    nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc_id INT,
    CONSTRAINT fk_lop_hoc_phan_nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc FOREIGN KEY(nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc_id)
    REFERENCES tbl_nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc(id) ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT fk_lop_hoc_phan_phong_hoc FOREIGN KEY(phong_hoc_id)
    REFERENCES tbl_phong_hoc(id) ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT fk_lop_hoc_phan_giao_vien FOREIGN KEY(giao_vien_id)
    REFERENCES tbl_giao_vien(id) ON DELETE CASCADE ON UPDATE CASCADE
);