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
public class Clerk {
  private int Clerk_Id;
  private String Clerk_Name;
  private String Clerk_Pass;

 

  public Clerk() {
  }

  /**
   * @return the Clerk_Name
   */
  public String getClerk_Name() {
    return Clerk_Name;
  }

  /**
   * @return the Clerk_Pass
   */
  public String getClerk_Pass() {
    return Clerk_Pass;
  }

  /**
   * @return the Clerk_Id
   */
  public int getClerk_Id() {
    return Clerk_Id;
  }

  /**
   * @param Clerk_Id the Clerk_Id to set
   */
  public void setClerk_Id(int Clerk_Id) {
    this.Clerk_Id = Clerk_Id;
  }

  /**
   * @param Clerk_Name the Clerk_Name to set
   */
  public void setClerk_Name(String Clerk_Name) {
    this.Clerk_Name = Clerk_Name;
  }

  /**
   * @param Clerk_Pass the Clerk_Pass to set
   */
  public void setClerk_Pass(String Clerk_Pass) {
    this.Clerk_Pass = Clerk_Pass;
  }

}