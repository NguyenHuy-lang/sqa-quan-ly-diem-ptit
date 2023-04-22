package sqa.example.service;

import sqa.example.model.MonHoc;

public class MonHocServiceUtils {
	public static boolean isInvalid(MonHoc entity) {
		boolean isInvalid = false;
		isInvalid |= entity.getTyLeDiemCC() < 0 || entity.getTyLeDiemTH() < 0 
				  || entity.getTyLeDiemKT() < 0 || entity.getTyLeDiemBT() < 0 || entity.getTyLeDiemCuoiKy() < 0;
		isInvalid |= entity.getTyLeDiemCC() > 1 || entity.getTyLeDiemTH() > 1 
				  || entity.getTyLeDiemKT() > 1 || entity.getTyLeDiemBT() > 1 || entity.getTyLeDiemCuoiKy() > 1;
		isInvalid |= entity.getTyLeDiemCC() + entity.getTyLeDiemTH() + entity.getTyLeDiemKT() + entity.getTyLeDiemBT() + entity.getTyLeDiemCuoiKy() != 1;
		return isInvalid;
	}
}
