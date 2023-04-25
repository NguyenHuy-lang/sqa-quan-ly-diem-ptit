CREATE TABLE tbl_nien_khoa_nganh(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    nien_khoa_id INT NOT NULL,
    nganh_id INT NOT NULL,
    CONSTRAINT fk_nien_khoa_nganh_nien_khoa FOREIGN KEY(nien_khoa_id)
    REFERENCES tbl_nien_khoa(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_nien_khoa_nganh_nganh FOREIGN KEY(nganh_id)
    REFERENCES tbl_nganh(id) ON DELETE CASCADE ON UPDATE CASCADE
);