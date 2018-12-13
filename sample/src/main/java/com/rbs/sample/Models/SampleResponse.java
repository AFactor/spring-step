package com.rbs.sample.Models;



public class SampleResponse{
    public SampleResponse(int id, String name){
        this.setId(id);
        this.setName(name);
    }

    public SampleResponse(){}

    private int id;
    private String name;

    public void setId(int value)
    {
        id=value;
    }

    public int getId()
    {
        return id;
    }

    public void setName(String value)
    {
        name=value;
    }

    public String getName()
    {
        return name;
    }
}




