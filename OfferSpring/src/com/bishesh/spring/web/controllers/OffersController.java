package com.bishesh.spring.web.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bishesh.spring.web.dao.Offer;
import com.bishesh.spring.web.service.OffersService;

@Controller
public class OffersController {
	/*
	@RequestMapping("/")
	public ModelAndView showHome(){
		
		ModelAndView mv=new ModelAndView("home");
		Map<String,Object> model=mv.getModel();
		model.put("name", "Test");
		return mv;
	}
	*/
	
	private OffersService offersService;
	
	
	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}

	@RequestMapping("/")
	public String showHome(Model model) throws NamingException{
		
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		
		List<Offer> offers=offersService.getCurrent();
		model.addAttribute("offers", offers);
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
		String test="";
		try {
			Connection conn = ds.getConnection();
			Statement stmt=conn.createStatement();  
			
			//step4 execute query  
			ResultSet rs=stmt.executeQuery("select name from offers");  
			while(rs.next())  
			{
			 test=rs.getString(1);
			System.out.println(test);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("name", test);
		return "home";
	}	
}
