CREATE TABLE tbl_nien_khoa_nganh_nam_hoc_ky_hoc (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    nam_hoc_ky_hoc_id INT,
    nien_khoa_nganh_id INT,
    CONSTRAINT fk_tbl_tbl_nien_khoa_nganh_nam_hoc_ky_hoc_nam_hoc_ky_hoc FOREIGN KEY(nam_hoc_ky_hoc_id)
    REFERENCES tbl_nam_hoc_ky_hoc(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_tbl_tbl_nien_khoa_nganh_nam_hoc_ky_hoc_nien_khoa_nganh FOREIGN KEY(nien_khoa_nganh_id)
    REFERENCES tbl_nien_khoa_nganh(id) ON DELETE CASCADE ON UPDATE CASCADE
);
