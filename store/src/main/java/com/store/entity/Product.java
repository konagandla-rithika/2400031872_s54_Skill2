package com.store.entity;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Product {
  private static SessionFactory sf = null;
  static {
    try {
      sf = new Configuration().configure().buildSessionFactory();
    }catch (Throwable e) {
      e.printStackTrace();
      throw new ExceptionInInitializerError();
    }
  }
  public static SessionFactory getSessionFactory() {
    return sf;
  }
  public static void shutdown() {
    if(sf!=null)
      sf.close();
  }

}