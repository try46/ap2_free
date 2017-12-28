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
public class Product {

  private int Product_Id;
  private String Product_Name;
  private int Product_Price;
  private int Product_Count;

  public Product() {
  }

  public Product(int Product_Id, String Product_Name, int Product_Price, int Product_Count) {
    this.Product_Id = Product_Id;
    this.Product_Name = Product_Name;
    this.Product_Price = Product_Price;
    this.Product_Count = Product_Count;
  }

  /**
   * @return the Product_Name
   */
  public String getProduct_Name() {
    return Product_Name;
  }

  /**
   * @return the Product_Price
   */
  public int getProduct_Price() {
    return Product_Price;
  }

  /**
   * @return the Product_Count
   */
  public int getProduct_Count() {
    return Product_Count;
  }

  /**
   * @return the Product_Id
   */
  public int getProduct_Id() {
    return Product_Id;
  }

  /**
   * @param Product_Id the Product_Id to set
   */
  public void setProduct_Id(int Product_Id) {
    this.Product_Id = Product_Id;
  }

  /**
   * @param Product_Name the Product_Name to set
   */
  public void setProduct_Name(String Product_Name) {
    this.Product_Name = Product_Name;
  }

  /**
   * @param Product_Price the Product_Price to set
   */
  public void setProduct_Price(int Product_Price) {
    this.Product_Price = Product_Price;
  }

  /**
   * @param Product_Count the Product_Count to set
   */
  public void setProduct_Count(int Product_Count) {
    this.Product_Count = Product_Count;
  }

}
