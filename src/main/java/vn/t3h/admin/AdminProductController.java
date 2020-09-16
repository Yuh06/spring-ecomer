package vn.t3h.admin;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.t3h.controller.BaseController;
import vn.t3h.dao.CategoryDao;
import vn.t3h.model.Category;
import vn.t3h.services.CategoryService;
import vn.t3h.services.ProductService;

@Controller
@RequestMapping("admin/product")
public class AdminProductController extends BaseController {
	
	@Autowired ProductService productService;
	@Autowired CategoryService categoryService;
	@Autowired CategoryDao categoryDao;
	
	@GetMapping(value = {"", "/"})
	public String getProducts(Model model) {
		model.addAttribute("listProduct", productService.getAllProduct());
		return "admin/product/index";
	}
	
	@GetMapping(value = "/create")
	public String createCategory(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("listCategory", categoryService.categoryWithIndent());
		return "admin/category/form";
	}
	
	@PostMapping(value = "/create")
	public String categoryForm(@Valid @ModelAttribute(value="category") Category category, 
			BindingResult bindingResult,  Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("category", category);
			model.addAttribute("listCategory", categoryService.categoryWithIndent());
			return "admin/category/form";
		}
		categoryDao.create(category);
		return "redirect:/admin/category";
	}
	
	@GetMapping(value = "/update")
	public String updateCategory(
			@RequestParam Integer id,
			Model model) {
		var category = categoryDao.findById(id);
		if(category == null) {
			throw new RuntimeException("Không có record id: " + id);
		}
		model.addAttribute("category", category);
		model.addAttribute("listCategory", categoryService.categoryWithIndent());
		return "admin/category/form";
	}
	
	@PostMapping(value = "/update")
	public String categoryFormUpdate(
			@Valid 
			@ModelAttribute(value="category") Category category, 
			BindingResult bindingResult,
			@RequestParam Integer id,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("category", category);
			model.addAttribute("listCategory", categoryService.categoryWithIndent());
			return "admin/category/form";
		}
		var cateInDb = categoryDao.findById(id);
		if(cateInDb == null) {
			throw new RuntimeException("Không có record id: " + id);
		}
		
		BeanUtils.copyProperties(category, cateInDb, new String[] {"createTime", "icon"});
		categoryDao.update(cateInDb);
		return "redirect:/admin/category";
	}
}