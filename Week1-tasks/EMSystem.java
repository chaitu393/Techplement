import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

class Employee implements Serializable
{
	 private static final long serialVersionUID = 1L;
   public int empId;
   public String empName;
   public double empSal;
   public String designation;
   
   public Employee(int empId,String empName, double empSal, String designation)
   {
	   this.empId=empId;
	   this.empName=empName;
	   this.empSal=empSal;
	   this.designation=designation;
	   
   }

  public int getEmpId() {
	return empId;
  }

  public void setEmpId(int empId) {
	this.empId = empId;
  }

  public String getEmpName() {
	return empName;
  }

  public void setEmpName(String empName) {
	this.empName = empName;
  }

  public double getEmpSal() {
	return empSal;
  }

  public void setEmpSal(double empSal) {
	this.empSal = empSal;
  }

  public String getDesignation() {
	return designation;
  }

  public void setDesignation(String designation) {
	this.designation = designation;
  }
  
  public void updateEmp(int empId,String empName, double empSal, String designation)
  {
	  this.empId=empId;
	   this.empName=empName;
	   this.empSal=empSal;
	   this.designation=designation;
	  
  }

  @Override
  public String toString() {
	return "Employee [empId=" + empId + ", "
			+ "empName=" + empName + ", "
		    + "empSal=" + empSal + ", "
			+ "designation=" + designation
			+ "]";
  }

   
   
	
}

class Operations
{
private static final  String fs="empData.txt";
	
	private ArrayList<Employee> employees;
	
	public Operations()
	{
		employees=new ArrayList<Employee>();
		loadEmployeesFile();
	}
	public void addEmployee(int empId,String empName, double empSal, String designation)
	{
		if (validateId(empId)) {
			
			Employee newEmployee=new Employee(empId, empName, empSal, designation);
			
			employees.add(newEmployee);
			saveEmployeesFile();
			System.out.println("Employees Added Successfully.....!");
			
		}
		else
		{
			System.out.println("Invalid Employee Id");
		}
		
	}
	public void removeEmployee(int empId)
	{
		employees.removeIf(emp->emp.getEmpId()==empId);
		
		saveEmployeesFile();
		System.out.println("Employee removed successfully");
	}
	public void viewAllEmp()
	{
		for(Employee emp:employees)
		{
			System.out.println(emp);
			
		}
	}
    public void searchEmp(int empId)
    {
    	boolean there=false;
    	
    	for(Employee emp:employees)
		{
			if (emp.getEmpId()==empId) {
				
				System.out.println(emp);
				there =true;
				
			}
			
			
		}
    	if (!there) 
    		
    		{
				System.out.println("No match found");
			}
			
		
    }
	private void saveEmployeesFile() {
		
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(fs)))
		{
			oos.writeObject(employees);
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	private boolean validateId(int empId) {
		
		return empId>0&& employees.stream().noneMatch(emp->
		   emp.getEmpId()==empId
		);
	}
	@SuppressWarnings("unchecked")
	private void loadEmployeesFile() {
		 try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fs)))
		 {
	            employees = (ArrayList<Employee>) ois.readObject();
	     }
		 catch (FileNotFoundException e) {
	            // Ignore for the first run, as the file might not exist yet.
	     } 
		 catch (IOException | ClassNotFoundException e) 
		 {
	            e.printStackTrace();
	     }
		
	}
}




public class EMSystem 
{
	public static void main(String[] args) {
		Operations op=new Operations();
		
		try (Scanner ref = new Scanner(System.in)) {
			while(true)
			{
				
				System.out.println("\nEmployee Management System Menu:");
			    System.out.println("1. Add Employee");
			    System.out.println("2. Remove Employee");
			    System.out.println("3. View All Employees");
			    System.out.println("4. Search Employees");
			    System.out.println("5. Exit");
			    System.out.print("Enter your choice: ");
			    
			    int choice=ref.nextInt();
			    ref.nextLine();
			    switch (choice) {
				case 1:
					System.out.println("Enter the Employee id");
					int addId=ref.nextInt();
					ref.nextLine();
					System.out.println("Enter the Emp name");
					String addname=ref.nextLine();
					System.out.println("Enter the Emp salary");
					double addSal=ref.nextDouble();
					ref.nextLine();
					System.out.println("Enter the Emp designation");
					String adddes=ref.nextLine();
					
					op.addEmployee(addId, addname, addSal, adddes);
					break;
				case 2:
					System.out.println("Enter the id to remove");
					int remId=ref.nextInt();
					op.removeEmployee(remId);
					break;
				case 3:
					op.viewAllEmp();
					break;
				case 4:
					System.out.println("Enter the Id for Search");
					int searchId=ref.nextInt();
					ref.nextLine();
					op.searchEmp(searchId);
					break;
					
				case 5:
					System.out.println("Program Exited...!");
					System.exit(0);
					
				default:
					System.out.println("Invalid Choice Please Enter the Valid Choice.....");
				}
			}
		}
	}
			
			
		

}