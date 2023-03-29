package com.encora.todobackendv2.Model;

import java.util.List;

public class Statistics {
    private Integer total;
    private Integer low;
    private Integer medium;
    private Integer high;

    

    public Statistics(List<ToDo>ToDos) {
        int l=0, m=0, h=0;
        int lCounter = 0, mCounter = 0, hCounter = 0;
        for(ToDo task : ToDos){
            if(task.getDone() == true){
                switch(task.getPriority()){
                    case 1:
                        l+=ToDo.getCompletionTime(task.getCreationDate(), task.getDoneDate());
                        lCounter+=1;
                    break;
                    case 2:
                        m+=ToDo.getCompletionTime(task.getCreationDate(), task.getDoneDate());
                        mCounter+=1;
                    break;
                    case 3:
                        h+=ToDo.getCompletionTime(task.getCreationDate(), task.getDoneDate());
                        hCounter+=1;
                    break;
                }
            }
        }
        if(lCounter > 0)
            this.low = (l/lCounter);
        else
            this.low = 0;

        if(mCounter > 0)
            this.medium = (m/mCounter);
        else
            this.medium = 0;

        if(hCounter > 0)
            this.high = (h/hCounter);
        else
            this.high = 0;
        
        if(ToDos.size() >= 1)
            this.total = (this.low + this.high + this.medium)/3;
    }

    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public Integer getLow() {
        return low;
    }
    public void setLow(Integer low) {
        this.low = low;
    }
    public Integer getMedium() {
        return medium;
    }
    public void setMedium(Integer medium) {
        this.medium = medium;
    }
    public Integer getHigh() {
        return high;
    }
    public void setHigh(Integer high) {
        this.high = high;
    }
}
