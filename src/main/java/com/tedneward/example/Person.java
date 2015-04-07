package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
  private static int count = 0;
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    count++;
    name = n;
    age = a;
    salary = s;
    ssn="";
  }

  public static int getCount() {
    return count;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int value) {
    if(value <= 0) {
      throw new IllegalArgumentException("age cannot be less than 0");
    }
    age = value;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String value) {
    if(value == null) {
      throw new IllegalArgumentException("name cannot be null");
    }
    name = value;
  }
  
  public double getSalary() {
    return salary;
  }

  public void setSalary(double value) {
    salary = value;
  }
  
  public String getSSN() {
    return ssn;
  }
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof Person)) {
      return false;
    }
    Person other = (Person)o;
    return (name.equals(other.name) && age == other.age);
  }

  @Override
  public String toString() {
    return "[Person name:" + name + " age:" + age + " salary:" + salary + "]";
  }

  public int compareTo(Person other) {
    double result = other.salary - this.salary;
    if(result < 0) {
      return -1;
    } else if(result > 0) {
      return 1;
    } else {
      return 0;
    }
  }

  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> fam = new ArrayList<Person>();
    fam.add(new Person("Ted", 41, 250000));
    fam.add(new Person("Charlotte", 43, 150000));
    fam.add(new Person("Michael", 22, 10000));
    fam.add(new Person("Matthew", 15, 0));
    return fam;
  }


  public static class AgeComparator implements Comparator {

    @Override
    public int compare(Object a1, Object b1) {
      Person a = (Person)a1;
      Person b = (Person)b1;
      return a.getAge() - b.getAge();
    }

  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
