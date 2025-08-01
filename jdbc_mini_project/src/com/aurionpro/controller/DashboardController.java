package com.aurionpro.controller;

import java.util.List;

import com.aurionpro.model.Dashboard;
import com.aurionpro.service.DashboardService;

public class DashboardController {

    public static void main(String[] args) {
        showDashboard(); // Static method call to show dashboard
    }

    public static void showDashboard() {
        DashboardService dashboardService = new DashboardService();
        List<Dashboard> dataList = dashboardService.getDashboard();

        if (dataList.isEmpty()) {
            System.out.println("No data found for dashboard.");
            return;
        }

        System.out.printf("%-5s %-10s %-20s %-15s %-10s %-12s %-10s %-20s %-25s\n",
                "Sr", "StudentID", "Name", "Course", "PaidFee", "PendingFee", "TotalFee", "Subjects", "Teachers");

        int srNo = 1;
        for (Dashboard data : dataList) {
            System.out.printf("%-5d %-10d %-20s %-15s %-10.2f %-12.2f %-10.2f %-20s %-25s\n",
                    srNo++,
                    data.getStudentId(),
                    data.getName(),
                    data.getCourse(),
                    data.getPaidFee(),
                    data.getPendingFee(),
                    data.getTotalFee(),
                    data.getSubjects(),
                    data.getTeachers()
            );
        }
    }
}

