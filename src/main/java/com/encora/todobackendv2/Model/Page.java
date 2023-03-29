package com.encora.todobackendv2.Model;

import java.util.ArrayList;
import java.util.List;


//This model gets returned once a request of data
//is procesed, the data is procesed inside the backend.
public class Page {
    private List<ToDo> content;
    private Integer page;
    private Integer pageSize;

    public Page(List<ToDo> content, Integer page, Integer pageSize) {
        this.content = new ArrayList<ToDo>();

        if(content.size() < pageSize){
            this.content = content;
            this.page = 0;
        }
        else{
            int totalPages = content.size()/10;
            int index = page*10;
            int count = 0;
            while(count < pageSize & index < content.size()){
                this.content.add(content.get(index));
                count+=1;
                index+=1;
            }
            if(page > totalPages){
                page = totalPages;
                index = page*10;
                this.page = totalPages;
                while(count < pageSize & index < content.size()){
                    this.content.add(content.get(index));
                    count+=1;
                    index+=1;
                }
            }else
                this.page = page;
        }
        
        this.pageSize = pageSize;
    }
    public List<ToDo> getContent() {
        return content;
    }
    public void setContent(List<ToDo> content) {
        this.content = content;
    }
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    
}
