package employeemanagement;

public class Employee {
	private int empId;
	private String empName;
	private String email;
	private String address;
	private String telephone;
	private String empType;
	
	
	public Employee(int empId, String empName, String email, String address, String telephone, String empType) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.email = email;
		this.address = address;
		this.telephone = telephone;
		this.empType = empType;
	}


	public int getEmpId() {
		return empId;
	}


	public String getEmpName() {
		return empName;
	}


	public String getEmail() {
		return email;
	}


	public String getAddress() {
		return address;
	}


	public String getTelephone() {
		return telephone;
	}


	public String getEmpType() {
		return empType;
	}


	public void setEmpId(int empId) {
		this.empId = empId;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public void setEmpType(String empType) {
		this.empType = empType;
	}
	
	
	
}
