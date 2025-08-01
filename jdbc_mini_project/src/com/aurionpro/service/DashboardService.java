package com.aurionpro.service;

import java.util.List;

import com.aurionpro.dao.DashboardDao;
import com.aurionpro.model.Dashboard;

public class DashboardService {
	private DashboardDao dashboardDao;
	
	public DashboardService() {
		dashboardDao = new DashboardDao();
	}
	
	public List<Dashboard> getDashboard() {
		return dashboardDao.getDashboard();
	}
}
