package steps;

import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.EmployeeListPage;
import pages.LoginPage;

public class PageInitializer {
    public static LoginPage login;
    //When I create new pages, I will create their objects as well and add it under the method
    //intializePageObjects
    public static DashboardPage dashboard;
    public static AddEmployeePage addEmployee;
    public static EmployeeListPage employeeList;

    public static void initializePageObject(){
        login = new LoginPage();
        dashboard = new DashboardPage();
        addEmployee = new AddEmployeePage();
        employeeList = new EmployeeListPage();
    }
}
