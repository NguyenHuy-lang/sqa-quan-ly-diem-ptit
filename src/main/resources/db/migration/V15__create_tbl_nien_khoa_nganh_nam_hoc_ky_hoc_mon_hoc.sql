CREATE TABLE tbl_nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    mon_hoc_id INT,
    nien_khoa_nganh_nam_hoc_ky_hoc_id INT,
    CONSTRAINT fk_tbl_nien_khoa_nganh_nam_hoc_ky_hoc_mon_hoc_mon_hoc
    FOREIGN KEY(mon_hoc_id)
    REFERENCES tbl_mon_hoc(id) ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT fk_mon_hoc_ky_hoc
    FOREIGN KEY(nien_khoa_nganh_nam_hoc_ky_hoc_id)
    REFERENCES tbl_nien_khoa_nganh_nam_hoc_ky_hoc(id) ON DELETE CASCADE ON UPDATE CASCADE

);