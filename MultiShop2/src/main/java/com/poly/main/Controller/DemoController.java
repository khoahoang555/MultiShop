package com.poly.main.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.main.Dao.LoaiSanPhamDAO;
import com.poly.main.Dao.RoleDao;
import com.poly.main.Entity.LoaiSanPham;
import com.poly.main.Entity.Size;
import com.poly.main.Model.AddressModel;
import com.poly.main.Service.SessionService;
import com.poly.main.Validator.DemoValidator;

@Controller
public class DemoController {
	@Autowired
	DemoValidator demoValidator;
	
	@Autowired
	LoaiSanPhamDAO dao;
	
	@Autowired
	SessionService session;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		Object target = binder.getTarget();
		if (target == null) {
			return;
		}
		if (target.getClass() == LoaiSanPham.class) {
			binder.setValidator(demoValidator);
		}
	}
	
	@RequestMapping("/admin/demo/form")
	public String index(Model model) {
		LoaiSanPham entity = new LoaiSanPham();
		model.addAttribute("loaisp", entity);
		return "manager/demoForm";
	}
	
	@PostMapping("/admin/demo/form")
	public String save(Model model, @ModelAttribute("loaisp") @Validated LoaiSanPham entity, BindingResult result) {
		if(!result.hasErrors()) {
			dao.save(entity);
			return "redirect:/admin/demo/list";
		}
		return "manager/demoForm";
	}
	
	@RequestMapping("/admin/demo/list")
	public String list(Model model, @RequestParam("keyword14") Optional<String> name,
			@RequestParam("p") Optional<Integer> p) {
		String findName;
		if (session.get("keyword14") == null) {
			findName = name.orElse("");
		} else {
			findName = name.orElse(session.get("keyword14"));
		}

		session.set("keyword14", findName);

		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<LoaiSanPham> page = dao.fillToTable("%" + findName + "%", pageable);
		model.addAttribute("demoItem", page);
		return "manager/demoTable";
	}
	
	@GetMapping("/admin/demo/list/load/{id}")
	public String load(Model model, @PathVariable("id") int id) {
		LoaiSanPham entity = dao.getById(id);
		model.addAttribute("loaisp", entity);
		return "manager/demoForm";
	}
	
	
}
