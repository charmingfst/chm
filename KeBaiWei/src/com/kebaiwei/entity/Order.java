package com.kebaiwei.entity;
/** 
 * @author ³ÂÃ÷ 
 * @date 2015-5-11 ÏÂÎç8:43:30 
 * @description TODO
 */
public class Order {
	private String id;
	private boolean pay;
	private String addTime;
	private String tableNo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isPay() {
		return pay;
	}
	public void setPay(boolean pay) {
		this.pay = pay;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getTableNo() {
		return tableNo;
	}
	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}
	
}
