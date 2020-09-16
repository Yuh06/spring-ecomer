package vn.t3h.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import vn.t3h.data.CardInfo;
import vn.t3h.data.CardManager;
import vn.t3h.model.Menu;
import vn.t3h.services.MenuService;

@Controller
public class BaseController {
	
	Logger log = LoggerFactory.getLogger(BaseController.class);
	private @Autowired MenuService mnService;
	private @Autowired HttpSession httpSession;
	
	@ModelAttribute("menus")
	public List<Menu> listMenus() {
		var listMenus = mnService.getMenu();
		return listMenus;
	}
	
	@ModelAttribute("configs")
	public Map<String, String> listConfigs() {
		var configs = new HashMap<String, String>();
		configs.put("hotline", "0987938491");
		return configs;
	}
	
	@ModelAttribute("cards")
	public List<CardInfo> cardsList() {
		@SuppressWarnings("unchecked")
		var listCard = (List<CardInfo>) httpSession.getAttribute(CardManager.KEY_IN_CARD);
		return listCard;
	}
}
