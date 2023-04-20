UPDATE tbl_nguoi_dung SET gioi_tinh = LOWER(gioi_tinh), ten = LOWER(ten), dia_chi = LOWER(dia_chi), email = LOWER(email);
UPDATE tbl_ky_hoc SET ten = LOWER(ten);
UPDATE tbl_mon_hoc SET ten = LOWER(ten);
UPDATE tbl_nganh SET ten = LOWER(ten);
UPDATE tbl_nien_khoa SET ten = LOWER(ten);
UPDATE tbl_phong_hoc SET ten = LOWER(ten);
UPDATE tbl_sinh_vien SET ma_sinh_vien = LOWER(ma_sinh_vien)

