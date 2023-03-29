package com.encora.todobackendv2.Model;

//This model is recibed to get all the specificiations
//we need to send a response from the backend so we
//format the array

//It also can contain a new ToDo in case it needs to be created or updated
public class Specs {
    private String[] specifications;
    private int actualPage;
    //Specifications goes as follows:
    //name, priority, state, sortPriority, sortDueDate 

    public Specs(String [] specifications, int actualPage) {
        this.specifications = specifications;
        this.actualPage = actualPage;
    }
    public int getActualPage() {
        return actualPage;
    }
    public void setActualPage(int actualPage) {
        this.actualPage = actualPage;
    }
    public String[] getSpecifications() {
        return specifications;
    }
    public void setSpecifications(String [] specifications) {
        this.specifications = specifications;
    }
    
}
