package com.kebaiwei.main;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Application;

import com.kebaiwei.entity.Food;
import com.kebaiwei.entity.Order;
import com.kebaiwei.entity.User;

/** 
 * @author ³ÂÃ÷ 
 * @date 2015-3-26 ÉÏÎç9:38:13 
 * @description TODO
 */
public class MainApplication extends Application {
	private static MainApplication mInstance;
	private String username;
	private String password;
	private User user;
	private ArrayList<Food> foodList;
	private HashMap<String, ArrayList> hashFood = new HashMap<String, ArrayList>();
	private ArrayList<Order> payedOrderList = new ArrayList<Order>();
	private ArrayList<Order> payingOrderList = new ArrayList<Order>();
	
	
	public ArrayList<Order> getPayedOrderList() {
		return payedOrderList;
	}
	public void setPayedOrderList(ArrayList<Order> payedOrderList) {
		this.payedOrderList = payedOrderList;
	}
	public ArrayList<Order> getPayingOrderList() {
		return payingOrderList;
	}
	public void setPayingOrderList(ArrayList<Order> payingOrderList) {
		this.payingOrderList = payingOrderList;
	}
	public HashMap<String, ArrayList> getHashFood() {
		return hashFood;
	}
	public void setHashFood(HashMap<String, ArrayList> hashFood) {
		this.hashFood = hashFood;
	}
	public void setFoodList(ArrayList<Food> foodList) {
		this.foodList = foodList;
	}
	
	public ArrayList<Food> getFoodList() {
		return foodList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	private ArrayList<Integer> food_id = new ArrayList<Integer>();
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mInstance = this;
	}
	public static MainApplication getInstance() {
		return mInstance;
	}
	
	public ArrayList<Integer> getFood_id() {
		return food_id;
	}
	public void setFood_id(ArrayList<Integer> food_id) {
		this.food_id = food_id;
	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	
}
