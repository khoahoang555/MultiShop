package com.poly.main.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.poly.main.Entity.LoaiSanPham;

@Component
public class DemoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == LoaiSanPham.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		LoaiSanPham entity = (LoaiSanPham) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tenloai", "NotBlank.loaisp.tenloai");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mota", "NotBlank.loaisp.mota");
	}

}
