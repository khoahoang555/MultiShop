package com.poly.main.Dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.main.Entity.LoaiSanPham;

public interface LoaiSanPhamDAO extends JpaRepository<LoaiSanPham, Integer>{
	@Query("SELECT c FROM LoaiSanPham c WHERE c.tenloai LIKE ?1")
	Page<LoaiSanPham> fillToTable(String name, Pageable pageable);
}
