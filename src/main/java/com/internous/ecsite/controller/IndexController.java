package com.internous.ecsite.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.internous.ecsite.model.dao.GoodsRepository;
import com.internous.ecsite.model.dao.PurchaseRepository;
import com.internous.ecsite.model.dao.UserRepository;
import com.internous.ecsite.model.dto.HistoryDto;
import com.internous.ecsite.model.dto.LoginDto;
import com.internous.ecsite.model.entity.Goods;
import com.internous.ecsite.model.entity.Purchase;
import com.internous.ecsite.model.entity.User;
import com.internous.ecsite.model.form.CartForm;
import com.internous.ecsite.model.form.HistoryForm;
import com.internous.ecsite.model.form.LoginForm;

@Controller
@RequestMapping("/ecsite")
public class IndexController {
	
	@Autowired
	private UserRepository userRepos;
	
	@Autowired
	private GoodsRepository goodsRepos;
	
	@Autowired
	private PurchaseRepository purchaseRepos;
	
	private Gson gson = new Gson();
	
	@RequestMapping("/")
	public String index(Model model) {
		
		List<Goods> goods = goodsRepos.findAll();
		model.addAttribute("goods", goods);
		
		return "index";
	}
	
	
	@ResponseBody
	@PostMapping("/api/login")
	public String loginApi(@RequestBody LoginForm form) {
		
		List<User> users = userRepos.findByUserNameAndPassword(form.getUserName(), form.getPassword());
		
		LoginDto dto = null;
		
		if(users.size() > 0) {
			dto = new LoginDto(users.get(0)); 
		} else {
			dto = new LoginDto(0, null, null, "ゲスト");
		}	
		
		return gson.toJson(dto);
	}
	
	
	@ResponseBody
	@PostMapping("/api/purchase")
	public String purchaseApi(@RequestBody CartForm form) {
		
		form.getCartList().forEach((cart) -> {
			long total = cart.getPrice() * cart.getCount();
			purchaseRepos.persist(form.getUserId(), cart.getId(), cart.getGoodsName(), cart.getCount(), total);
		});
		
		return String.valueOf(form.getCartList().size());
		
	}
	
	
	@ResponseBody
	@PostMapping("/api/history")
	public String historyApi(@RequestBody HistoryForm form) {
		
		String userId = form.getUserId();
		
		List<Purchase> history = purchaseRepos.findHistory(Long.parseLong(userId));
		
		List<HistoryDto> historyDtoList = new ArrayList<>();
		
		history.forEach((v) -> {
			HistoryDto dto = new HistoryDto(v);
			historyDtoList.add(dto);
		});
		
		return gson.toJson(historyDtoList);
	}
	
}

