package com.navitsa.hrm.entity;

import java.io.IOException;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "employee_master")
public class Employee {

	@Id
	@Column(name = "Employee_ID")
	private String empID;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "Nationality_ID", referencedColumnName = "Nationality_ID")
	private NationalityMaster nationality;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "Religion_ID", referencedColumnName = "Religion_ID")
	// @Column(name="Religion_ID")
	private ReligionMaster religion;

	@Column(name = "Material_Status")
	private String mStatus;

//	@Pattern(regexp="^[a-zA-Z ]+$",message="Please type characters only")
//	@NotEmpty(message = "Please enter Employee First Name")
	@Column(name = "Name")
	private String name;

	@Column(name = "DOB")
	private String dob;

	@Column(name = "Gender")
	private String gender;

	@Column(name = "Profile_Img")
	private byte[] profileImg;

	@Column(name = "Address")
	private String address;

	@Column(name = "City")
	private String city;

	@Column(name = "State")
	private String state;

//	@Pattern(regexp="^[A-Za-z0-9 ]+$",message=" Please do not type Special  Characters")
//	@NotEmpty(message = "Please enter Employee ID Number")
	@Column(name = "ID_Number")
	private String id_Number;

//	@Pattern(regexp="^[A-Za-z0-9 ]+$",message=" Please do not type Special  Characters")

	@Column(name = "DL_Number")
	private String dl_number;

//	@Pattern(regexp="^[A-Za-z0-9 ]+$",message=" Please do not type Special  Characters")

	@Column(name = "Passport_Number")
	private String passport_Number;

	@Column(name = "Emergency_Contact_No")
	private String emergency_Contact_No;

	@Column(name = "Blood_Group")
	private String blood_Group;

//	@ManyToOne(optional=false, fetch = FetchType.EAGER)
//	@JoinColumn(name="Bank_ID", referencedColumnName ="Bank_ID")
	@Column(name = "Bank_ID")
	private String bank_Code;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "Branch_ID", referencedColumnName = "Branch_ID")
	private Bank bankBranch_Code;

	@Column(name = "Bank_Account")
	private String bank_Account;

//	@Pattern(regexp="^[a-zA-Z ]+$",message="Please type characters only")
//	@NotEmpty(message = "Please enter Employee Lastname")
	@Column(name = "lastname")
	private String lastname;

	// @Pattern(regexp="^[0-9 ]+$",message=" Please do not type Special Characters")
	// @NotEmpty(message = "Please enter Contact number")
	@Column(name = "contact_num1")
	private String contact_num1;

	// @Pattern(regexp="^[0-9 ]+$",message=" Please do not type Special Characters")
	// @NotEmpty(message = "Please enter Contact number")
	@Column(name = "contact_num2")
	private String contact_num2;

	// @Pattern(regexp="^(.+)@(.+)$",message="@ sign is missing in email address")
	// @NotEmpty(message = "Please enter Employee Emial ")
	@Column(name = "email")
	private String email;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Company_ID", referencedColumnName = "Company_ID")
	private CompanyMaster company;

//	@NotEmpty(message = "Please enter Employee Password ")
	@Column(name = "Emp_Password")
	private String password;

	@Column(name = "EmpCateCode")
	private String employeeCategory;
	
	@Column(name = "EPF_No")
	private String epfNo;
	
	@Column(name = "Finger_Print_ID")
	private String fingerPrintId;
	
	@Column(name = "pay_type")
	private String payType;

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public NationalityMaster getNationality() {
		return nationality;
	}

	public void setNationality(NationalityMaster nationality) {
		this.nationality = nationality;
	}

	public ReligionMaster getReligion() {
		return religion;
	}

	public void setReligion(ReligionMaster religion) {
		this.religion = religion;
	}

	public String getmStatus() {
		return mStatus;
	}

	public void setmStatus(String mStatus) {
		this.mStatus = mStatus;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public byte[] getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(MultipartFile profileImg) throws IOException {
		this.profileImg = profileImg.getBytes();
	}

	public String getProfileImgView() {
		return Base64.getEncoder().encodeToString(this.profileImg);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getId_Number() {
		return id_Number;
	}

	public void setId_Number(String id_Number) {
		this.id_Number = id_Number;
	}

	public String getDl_number() {
		return dl_number;
	}

	public void setDl_number(String dl_number) {
		this.dl_number = dl_number;
	}

	public String getPassport_Number() {
		return passport_Number;
	}

	public void setPassport_Number(String passport_Number) {
		this.passport_Number = passport_Number;
	}

	public String getEmergency_Contact_No() {
		return emergency_Contact_No;
	}

	public void setEmergency_Contact_No(String emergency_Contact_No) {
		this.emergency_Contact_No = emergency_Contact_No;
	}

	public String getBlood_Group() {
		return blood_Group;
	}

	public void setBlood_Group(String blood_Group) {
		this.blood_Group = blood_Group;
	}

	public String getBank_Code() {
		return bank_Code;
	}

	public void setBank_Code(String bank_Code) {
		this.bank_Code = bank_Code;
	}

	public Bank getBankBranch_Code() {
		return bankBranch_Code;
	}

	public void setBankBranch_Code(Bank bankBranch_Code) {
		this.bankBranch_Code = bankBranch_Code;
	}

	public String getBank_Account() {
		return bank_Account;
	}

	public void setBank_Account(String bank_Account) {
		this.bank_Account = bank_Account;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getContact_num1() {
		return contact_num1;
	}

	public void setContact_num1(String contact_num1) {
		this.contact_num1 = contact_num1;
	}

	public String getContact_num2() {
		return contact_num2;
	}

	public void setContact_num2(String contact_num2) {
		this.contact_num2 = contact_num2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public Employee(String empID, NationalityMaster nationality, ReligionMaster religion, String mStatus, String name,
			String dob, String gender, MultipartFile profileImg, String address, String city, String state,
			String salaryGrade, String id_Number, String dl_number, String passport_Number, String emergency_Contact_No,
			String blood_Group, String bank_Code, Bank bankBranch_Code, String bank_Account, String lastname,
			String contact_num1, String contact_num2, String email, String password, CompanyMaster company, String epfNo, String fingerPrintId, String payType)
			throws IOException {
		this.empID = empID;
		this.nationality = nationality;
		this.religion = religion;
		this.mStatus = mStatus;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.profileImg = profileImg.getBytes();
		this.address = address;
		this.city = city;
		this.state = state;
		this.id_Number = id_Number;
		this.dl_number = dl_number;
		this.passport_Number = passport_Number;
		this.emergency_Contact_No = emergency_Contact_No;
		this.blood_Group = blood_Group;
		this.bank_Code = bank_Code;
		this.bankBranch_Code = bankBranch_Code;
		this.bank_Account = bank_Account;
		this.lastname = lastname;
		this.contact_num1 = contact_num1;
		this.contact_num2 = contact_num2;
		this.email = email;
		this.password = password;
		this.company = company;
		this.epfNo = epfNo;
		this.fingerPrintId = fingerPrintId;
		this.payType = payType;
	}

	public Employee(String empID, NationalityMaster nationality, ReligionMaster religion, String mStatus, String name,
			String dob, String gender, MultipartFile profileImg, String id_Number, String dl_number,
			String passport_Number, String emergency_Contact_No, String blood_Group, String bank_Code,
			Bank bankBranch_Code, String bank_Account, String address, String city, String state, String lastname,
			String contact_num1, String contact_num2, String email, String password, CompanyMaster company, String epfNo, String fingerPrintId, String payType)
			throws IOException {

		this.empID = empID;
		this.nationality = nationality;
		this.religion = religion;
		this.mStatus = mStatus;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.profileImg = profileImg.getBytes();
		this.id_Number = id_Number;
		this.dl_number = dl_number;
		this.passport_Number = passport_Number;
		this.emergency_Contact_No = emergency_Contact_No;
		this.blood_Group = blood_Group;
		this.bank_Code = bank_Code;
		this.bankBranch_Code = bankBranch_Code;
		this.bank_Account = bank_Account;
		this.address = address;
		this.city = city;
		this.state = state;
		this.lastname = lastname;
		this.contact_num1 = contact_num1;
		this.contact_num2 = contact_num2;
		this.email = email;
		this.password = password;
		this.company = company;
		this.epfNo = epfNo;
		this.fingerPrintId = fingerPrintId;
		this.payType = payType;
	}

	public Employee() {
	}

	public Employee(String empID) {
		this.empID = empID;
	}

	public Employee(String empID, NationalityMaster nationality, String mStatus, String name, String dob, String gender,
			MultipartFile profileImg, String id_Number, String dl_number, String passport_Number,
			String emergency_Contact_No, String blood_Group, String bank_Code, Bank bankBranch_Code,
			String bank_Account, String address, String city, String state, String lastname, String contact_num1,
			String contact_num2, String email, String password, CompanyMaster company, String epfNo, String fingerPrintId, String payType) throws IOException {

		this.empID = empID;
		this.nationality = nationality;
		this.mStatus = mStatus;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.profileImg = profileImg.getBytes();
		this.id_Number = id_Number;
		this.dl_number = dl_number;
		this.passport_Number = passport_Number;
		this.emergency_Contact_No = emergency_Contact_No;
		this.blood_Group = blood_Group;
		this.bank_Code = bank_Code;
		this.bankBranch_Code = bankBranch_Code;
		this.bank_Account = bank_Account;
		this.address = address;
		this.city = city;
		this.state = state;
		this.lastname = lastname;
		this.contact_num1 = contact_num1;
		this.contact_num2 = contact_num2;
		this.email = email;
		this.password = password;
		this.company = company;
		this.epfNo = epfNo;
		this.fingerPrintId = fingerPrintId;
		this.payType = payType;
	}

	public String getEmployeeCategory() {
		return employeeCategory;
	}

	public void setEmployeeCategory(String employeeCategory) {
		this.employeeCategory = employeeCategory;
	}

	public String getEpfNo() {
		return epfNo;
	}

	public void setEpfNo(String epfNo) {
		this.epfNo = epfNo;
	}

	public String getFingerPrintId() {
		return fingerPrintId;
	}

	public void setFingerPrintId(String fingerPrintId) {
		this.fingerPrintId = fingerPrintId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
}
