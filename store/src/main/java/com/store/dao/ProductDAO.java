package com.store.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.store.entity.Product;


import java.util.List;


public class ProductDAO {
  //Create the Record
    public void saveProduct(Product p) {
      try(Session session = Product.getSessionFactory().openSession()){
        
        Transaction tx = session.beginTransaction();
        session.save(p);
        tx.commit();
        
      }
    } 
    //Reading all the Products
    public List getProducts(){
      try(Session session = Product.getSessionFactory().openSession()){
        return (List) session.createQuery("FROM Product", Product.class).list();
      }
    }
    // Get the Record by ProductID
    public Product getByProductId(int pid) {
      try(Session session = Product.getSessionFactory().openSession()){
        return session.get(Product.class, pid);
      }
    }
    //update the Record
    public boolean updateProduct(Product p) {
      Transaction tx = null;
      try(Session session = Product.getSessionFactory().openSession()){
        tx = session.beginTransaction();
        session.merge(p);
        tx.commit();
        return true;
      }catch(Exception e){
        if( tx != null)
          tx.rollback();
        e.printStackTrace();
        return false;
      }
    }
    //Delete the Record
    public boolean deleteProduct(int pid) {
      Transaction tx = null;
      try(Session session = Product.getSessionFactory().openSession()){
        tx = session.beginTransaction();
        session.delete(getByProductId(pid));
        tx.commit();
        return true;
      }catch(Exception e) {
        if(tx != null)
          tx.rollback();
        e.printStackTrace();
        return false;
      }
    }

}