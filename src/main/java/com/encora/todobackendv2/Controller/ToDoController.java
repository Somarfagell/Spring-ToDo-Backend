package com.encora.todobackendv2.Controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encora.todobackendv2.Model.Page;
import com.encora.todobackendv2.Model.Specs;
import com.encora.todobackendv2.Model.Statistics;
import com.encora.todobackendv2.Model.ToDo;
import com.encora.todobackendv2.Repository.ToDoRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;



//Rest controller anotations and cross origin for request
@RestController
@CrossOrigin
public class ToDoController {
    @Autowired
    ToDoRepository repo = new ToDoRepository();

    //Retrieve all the data in a pagination mode
    @GetMapping(value="/todos")
    public ResponseEntity<Page> todosData(@RequestParam String text, @RequestParam String priority, @RequestParam String status, @RequestParam int page, @RequestParam String prioritySort, @RequestParam String dateSort){
        String [] configs = {text,priority,status,prioritySort, dateSort};
        Specs data = new Specs(configs, page);
        return ResponseEntity.ok().body(repo.pageRequest(data));
    }    

    //Retrieve the statistics
    @GetMapping(value="/todos/statistics")
    public ResponseEntity<Statistics> statisticsData(){
        return ResponseEntity.ok().body(repo.getStatistic());
    }    

    //Create a new ToDo inside the Data Collection
    @PostMapping(value="/todos")
    public ResponseEntity<Void> createToDo(@RequestBody ToDo task) {
        repo.createToDo(task);
        URI uri = URI.create("/todos"+task.getId());
        return ResponseEntity.created(uri).body(null);
    }

    //Update a ToDo data
    @PutMapping(value="todos/{id}")
    public ResponseEntity<Void> updateToDo(@PathVariable String id, @RequestBody ToDo task) {
        if(repo.updateToDo(id, task)){
            return ResponseEntity.ok().body(null);
        }
        else{
            return ResponseEntity.notFound().build();    
        }
    }

    //Update status of a ToDo to done
    @PutMapping(value="todos/{id}/done")
    public ResponseEntity<Void> updateDoneToDo(@PathVariable String id) {
        if(repo.setDoneToDo(id)){
            return ResponseEntity.ok().body(null);
        }
        else{
            return ResponseEntity.notFound().build();    
        }
    }

    //Update status of a ToDo to undone
    @PutMapping(value="todos/{id}/undone")
    public ResponseEntity<Void> updateUndoneToDo(@PathVariable String id) {
        if(repo.setUndoneToDo(id)){
            return ResponseEntity.ok().body(null);
        }
        else{
            return ResponseEntity.notFound().build();    
        }
    }

    //Delete a ToDo based on the ID
    @DeleteMapping(value="todos/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable String id) {
        if(repo.deleteToDo(id)){
            return ResponseEntity.ok().body(null);
        }
        else{
            return ResponseEntity.notFound().build();    
        }
    }
    
    
}
