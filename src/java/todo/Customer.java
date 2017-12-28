/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

/**
 *
 * @author try
 */
public class Customer {

  private int Customer_Id;
  private String Customer_Name;
  private String Customer_Pass;
  private String Customer_Age;
  private String Customer_Address;

  public Customer() {
  }

  public Customer(int Customer_Id, String Customer_Name, String Customer_Pass, String Customer_Age, String Customer_Address) {
    this.Customer_Id = Customer_Id;
    this.Customer_Name = Customer_Name;
    this.Customer_Pass = Customer_Pass;
    this.Customer_Age = Customer_Age;
    this.Customer_Address = Customer_Address;
  }

  public String getCustomer_Name() {
    return Customer_Name;
  }

  /**
   * @return the Customer_Pass
   */
  public String getCustomer_Pass() {
    return Customer_Pass;
  }

  /**
   * @return the Customer_Age
   */
  public String getCustomer_Age() {
    return Customer_Age;
  }

  /**
   * @return the Customer_Address
   */
  public String getCustomer_Address() {
    return Customer_Address;
  }

  /**
   * @return the Customer_Id
   */
  public int getCustomer_Id() {
    return Customer_Id;
  }

  /**
   * @param Customer_Id the Customer_Id to set
   */
  public void setCustomer_Id(int Customer_Id) {
    this.Customer_Id = Customer_Id;
  }

  /**
   * @param Customer_Name the Customer_Name to set
   */
  public void setCustomer_Name(String Customer_Name) {
    this.Customer_Name = Customer_Name;
  }

  /**
   * @param Customer_Pass the Customer_Pass to set
   */
  public void setCustomer_Pass(String Customer_Pass) {
    this.Customer_Pass = Customer_Pass;
  }

  /**
   * @param Customer_Age the Customer_Age to set
   */
  public void setCustomer_Age(String Customer_Age) {
    this.Customer_Age = Customer_Age;
  }

  /**
   * @param Customer_Address the Customer_Address to set
   */
  public void setCustomer_Address(String Customer_Address) {
    this.Customer_Address = Customer_Address;
  }

  @Override
  public String toString() {
    return "右記の情報を顧客情報として追加しました{" + "Customer_Id=" + Customer_Id + ", Customer_Name=" + Customer_Name + ", Customer_Pass=" + Customer_Pass + ", Customer_Age=" + Customer_Age + ", Customer_Address=" + Customer_Address + '}';
  }
  

}
