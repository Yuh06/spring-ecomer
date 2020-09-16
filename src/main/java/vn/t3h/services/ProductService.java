package vn.t3h.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.t3h.dao.ProductDao;
import vn.t3h.model.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao categorytDao;
	private ProductDao productDao;
	private static ProductService intance;
	
	@PostConstruct
	public void initCategory() {
		intance=this;
	}
	public static ProductService getIntance() {
		return intance;
	}
	
	public List<Product> getAllProduct() {
		return productDao.listProducts();
	}
}
