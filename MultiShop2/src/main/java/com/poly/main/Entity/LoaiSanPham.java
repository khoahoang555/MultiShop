package com.poly.main.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="LOAISANPHAM")
public class LoaiSanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maloai;
	
	private String tenloai;
	
	private String mota;

	public LoaiSanPham(String tenloai, String mota) {
		super();
		this.tenloai = tenloai;
		this.mota = mota;
	}
	
}
