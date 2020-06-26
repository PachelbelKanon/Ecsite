package com.internous.ecsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.internous.ecsite.model.dao.GoodsRepository;
import com.internous.ecsite.model.dao.UserRepository;
import com.internous.ecsite.model.entity.Goods;
import com.internous.ecsite.model.entity.User;
import com.internous.ecsite.model.form.GoodsForm;
import com.internous.ecsite.model.form.LoginForm;

@Controller
@RequestMapping("/ecsite/admin")
public class AdminController {

	@Autowired
	private UserRepository userRepos;
	
	@Autowired
	private GoodsRepository goodsRepos;
	
	@RequestMapping("/")
	public String index() {
		return "admin_index";
	}
	
	@PostMapping("/welcome")
	public String welcome(LoginForm form, Model model) {
		
		List<User> users = userRepos.findByUserNameAndPassword(form.getUserName(), form.getPassword());

			if(users != null && users.size() > 0) {
				boolean isAdmin = users.get(0).getIsAdmin() != 0;
				
				if(isAdmin) {
					List<Goods> goods = goodsRepos.findAll();
					model.addAttribute("userName", users.get(0).getUserName());
					model.addAttribute("password", users.get(0).getPassword());
					model.addAttribute("goods", goods);				
				}
			}
			
		return "admin_welcome";
	}
	
	
	@PostMapping("/goodsMst")
	public String goodsMst(LoginForm form, Model model) {
		
		model.addAttribute("userName", form.getUserName());
		model.addAttribute("password", form.getPassword());
		
		return "admin_goodsmst";
	}
	
	
	@PostMapping("/addGoods")
	public String addGoods(GoodsForm goodsForm, LoginForm form, Model model) {
		
		model.addAttribute("userName", form.getUserName());
		model.addAttribute("password", form.getPassword());
		
		Goods goods = new Goods();
		
		goods.setGoodsName(goodsForm.getGoodsName());
		goods.setPrice(goodsForm.getPrice());
		
		goodsRepos.saveAndFlush(goods);
		
		return "forward:/ecsite/admin/welcome";
	}
	
	
	@ResponseBody
	@PostMapping("/api/deleteGoods")
	public String deleteApi(@RequestBody GoodsForm form, Model model) {
	
		try {
			goodsRepos.deleteById(form.getId());
			
		} catch(IllegalArgumentException e) {
			return "-1";
			
		} catch(EmptyResultDataAccessException e) {
			return "-1";
		}
		
		return "1";
	}
	
}
