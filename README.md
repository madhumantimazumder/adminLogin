# adminLogin and Course Creation
1.	Use App.java to create dependent tables and fields in Database to run the code succesfully. <br/>
	or in Resources folder Database(CourseProject) is provided.Please import it to run the project successfully</br>
2.	Admin - Username AD101 Password Madhumanti //should login <br/>
	Teacher - Username TC101 Password ujan //should not login <br/>
3.	For Employee we have following fields - <br/>
	int EmpID; // Auto generated <br/>
	String EmpName; // Name <br/>
	String Username; <br/>
	String Password; <br/>
	Department dept;//foreign key to Department table <br/>
4.	For Courses we have following fields <br/>
	private int id;// Auto generated <br/>
	private int courseId;// Unique and Not null <br/>
	private String name; <br/>
	private int credit; <br/>
	private int capacity; <br/>
	Set<Schedule> schedules=new HashSet<Schedule>(); <br/>
	Set<Courses> pre_req=new HashSet<Courses>(); //This is not a mandatory field <br/>
	Set<Specialization> sp=new HashSet<Specialization>(); <br/>
5.	Dummy Department table to know whether the user is from Admin or not <br/>
    int DeptID;  <br/>
    String name; <br/>
6.	Schedule entry - <br/>
    private int sc_id; //Auto Generated <br/>
	private String day; <br/>
	private String time; <br/>
7.	Specialization table Entry <br/>
    private int spId; //Auto Generated <br/>
	private String name; <br/>
8.	For Login user is checked against Employee table for username and password and then deprtment table for Admin
9.	For course Creation- <br/>
    In the client Side- <br/>
	    a. Admin is not allowed to left the mandatory fields blank <br/>
	    b. Admin is not allowed to make duplicate entrys of Specialization and Schedule <br/>
	In Server Side- <br/>
	    a. Duplicate Course Code is not allowed <br/>
