CREATE TABLE tbl_nam_hoc_ky_hoc(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    nam_hoc_id INT NOT NULL,
    ky_hoc_id INT NOT NULL,
    CONSTRAINT fk_nam_hoc_ky_hoc_nam_hoc FOREIGN KEY(nam_hoc_id)
    REFERENCES tbl_nam_hoc(id) ON DELETE CASCADE ON UPDATE CASCADE
);