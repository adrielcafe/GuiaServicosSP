package com.adrielcafe.guiaservicossp.model;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
	private static final long serialVersionUID = 6992793098168955709L;
	
	public String title;
	public List<Service> services;
}