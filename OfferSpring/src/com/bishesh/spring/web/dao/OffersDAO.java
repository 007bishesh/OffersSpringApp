package com.bishesh.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("offersDao")
public class OffersDAO {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setJdbc(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Offer> getOffers(){
		MapSqlParameterSource params=new MapSqlParameterSource();
		return	 jdbc.query("select * from offers ", params,new RowMapper<Offer>(){
			
				public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					Offer offer=new Offer();
					offer.setId(rs.getInt("id"));
					offer.setName(rs.getString("name"));
					offer.setEmail(rs.getString("email"));
					offer.setText(rs.getString("text"));
					return offer;
				}
				
			});
		
 
 

		
		
	}
	
	public boolean delete(int id){
		MapSqlParameterSource params=new MapSqlParameterSource("id",id);
		return jdbc.update("delete from offers where id=:id", params)==1;
	}
	

	public Offer getOffer(int id){

		MapSqlParameterSource params=new MapSqlParameterSource();
		
		params.addValue("id",id);
		
		return	 jdbc.queryForObject("select * from offers where  id=:id",params, new RowMapper<Offer>(){
			
				public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					Offer offer=new Offer();
					offer.setId(rs.getInt("id"));
					offer.setName(rs.getString("name"));
					offer.setEmail(rs.getString("email"));
					offer.setText(rs.getString("text"));
					
					return offer;
				}
				
			});
		
 
 

		
		
	}
}
