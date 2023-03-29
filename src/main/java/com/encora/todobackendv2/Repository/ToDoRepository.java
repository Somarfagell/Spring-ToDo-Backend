package com.encora.todobackendv2.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.encora.todobackendv2.Model.Page;
import com.encora.todobackendv2.Model.Specs;
import com.encora.todobackendv2.Model.Statistics;
import com.encora.todobackendv2.Model.ToDo;


@Repository
public class ToDoRepository {
    List<ToDo> ToDos;
    HashMap <String,String> config = new HashMap<String, String>();

    //To keep track of all the filters and sorting the user is using
    //the unique methods that are going to return something to the controller
    //are the following.

    //Everybody can connect if they follow the Specs structure because
    //the backend does not save the state of the clients
    public ToDoRepository() {
        ToDos = new ArrayList<ToDo>();
        //Testing to do
        ToDos.add(new ToDo("Limpiar las ventanas", LocalDateTime.parse("2023-04-14T15:32:56.000"), 2));
    }

    public Page pageRequest(Specs details){
        List<ToDo> filtered = new ArrayList<ToDo>(ToDos);
        filtered = filterBy("name", details.getSpecifications()[0],ToDos); 
        filtered = filterBy("priority", details.getSpecifications()[1],filtered); 
        filtered = filterBy("state", details.getSpecifications()[2],filtered);
        filtered = sortByPriority(details.getSpecifications()[3],filtered);
        filtered = sortByDate(details.getSpecifications()[4],filtered);
        return new Page(filtered, details.getActualPage(), 10);
    }

    public void createToDo(ToDo newToDo){
        //add the new ToDo
        //Check if it already exist
        for(ToDo task : ToDos){
            if(newToDo.getText().equals(task.getText())){
                return;
            } 
        }
        ToDos.add(newToDo);
        return;
    }

    public Statistics getStatistic(){
        return new Statistics(ToDos);
    }

    public Boolean updateToDo(String id, ToDo newToDo){
        //Search and update
        int index = 0;
        for(ToDo task : ToDos) {
            if(task.getId().equals(id)){
                //Replace old ToDo with the new ToDo
                ToDos.set(index,newToDo);
                return true;
            }
            index ++;
        }   
        //If there does not exist just return the page?
        return false;
    }

    public Boolean setDoneToDo(String id){
        //Search and update
        for(ToDo task : ToDos) {
            if(task.getId().equals(id)){
                //Replace old ToDo with the new ToDo
                task.setDone(true);
                task.setDoneDate(LocalDateTime.now());
                return true;
            }
        }   
        //If there does not exist just return the page?
        return false;
    }

    public Boolean setUndoneToDo(String id){
        //Search and update
        for(ToDo task : ToDos) {
            if(task.getId().equals(id)){
                //Replace old ToDo with the new ToDo
                task.setDone(false);
                task.setDoneDate(null);
                return true;
            }
        }   
        //If there does not exist just return the page?
        return false;
    }

    public Boolean deleteToDo(String id){
        //Search and update
        int index = 0;
        for(ToDo task : ToDos) {
            if(task.getId().equals(id)){
                //Replace old ToDo with the new ToDo
                ToDos.remove(index);
                return true;
            }
            index++;
        }   
        //If there does not exist just return the page?
        return false;
    }

    //Erase a ToDo inside the repo


    //------------------------------------------------------//
    //Complementary methods
    //Sorting by text method, asc and desc to specify order

    //Sort by priority
    public List<ToDo> sortByPriority(String order, List<ToDo> actual){
        List<ToDo> sorted = new ArrayList<ToDo>(actual);
        if(order.equals("asc")){
            //Ascending
            sorted.sort((A1,A2) -> A1.getPriority() > A2.getPriority()? 1: A1.getPriority() < A2.getPriority()? -1 : 0) ;
        }
        else if(order.equals("desc")){
            //Descending
            sorted.sort((A1,A2) -> A1.getPriority() < A2.getPriority()? 1: A1.getPriority() > A2.getPriority()? -1 : 0) ;
        }
        return sorted;
    }

    //Sort by date
    public List<ToDo> sortByDate(String order, List<ToDo> actual){
        List<ToDo> sorted = new ArrayList<ToDo>(actual);
        if(order.equals("asc")){
            //Ascending
            sorted.sort((A1,A2) -> A1.getDueDate().isAfter(A2.getDueDate()) ? 1: A1.getDueDate().isBefore(A2.getDueDate())? -1 : 0);
        }
        else if(order.equals("desc")){
            //Descending
            sorted.sort((A1,A2) -> A1.getDueDate().isBefore(A2.getDueDate()) ? 1: A1.getDueDate().isAfter(A2.getDueDate())? -1 : 0);
        }
        return sorted;
    }

    //Filter by method
    public List<ToDo> filterBy(String filter, String criterion, List<ToDo> actual){    
        List<ToDo> filtered = new ArrayList<ToDo>();

        //Filter by name
        if(filter == "name"){
            if(criterion != ""){
                for(ToDo task : actual){
                    if(task.getText().contains(criterion)){
                        filtered.add(task);
                    }
                }
            }
            else{
                return actual;
            }
        //Filter by priority
        }else if(filter == "priority"){
            //All, High, Medium, Low from 3 to 1
            switch(criterion){
                case "All":
                return actual;
                case "High":
                    for(ToDo task : actual){
                        if(task.getPriority() == 3){
                            filtered.add(task);
                        }
                    }
                break;
                case "Medium":
                    for(ToDo task : actual){
                        if(task.getPriority() == 2){
                            filtered.add(task);
                        }
                    }
                break;
                case "Low":
                    for(ToDo task : actual){
                        if(task.getPriority() == 1){
                            filtered.add(task);
                        }
                    }
                break;
                default:
                    return actual;
            }
        //Filter by state
        }else if(filter == "state"){
            //All, High, Medium, Low from 3 to 1
            switch(criterion){
                case "All":
                    return actual;
                case "Done":
                for(ToDo task : actual){
                    if(task.getDone() == true){
                        filtered.add(task);
                    }
                }
                break;
                case "Undone":
                for(ToDo task : actual){
                    if(task.getDone() == false){
                        filtered.add(task);
                    }
                }
                break;
            }
        } 
        return filtered;
    }

}
