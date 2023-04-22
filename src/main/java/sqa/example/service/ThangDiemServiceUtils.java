package sqa.example.service;

import sqa.example.model.ThangDiem;

public class ThangDiemServiceUtils {
	public static boolean isInvalid(ThangDiem entity) {
		boolean invalid = entity.getFrom() < 0.0 || entity.getTo() < 0.0;
		invalid |= entity.getFrom() > entity.getTo();
		invalid |= entity.getDiemHe4() < 0.0;
		return invalid;
	}
}
