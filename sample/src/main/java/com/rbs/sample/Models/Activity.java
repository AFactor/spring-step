package com.rbs.sample.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Activity
{
    
    private String corelationId;
    private String ceeId;

    private MemBp[] memBp;

    
    public String getCorelationId(){
        return corelationId;
    }

    public void setCorelationId(String value){
         corelationId=value;
    }

    public String getCeeId ()
    {
        return ceeId;
    }

    public void setCeeId (Integer ceeId)
    {
        this.ceeId = String.valueOf(ceeId);
    }

    public void setCeeId (String ceeId)
    {
        this.ceeId = ceeId;
    }


    public MemBp[] getMemBp ()
    {
        return memBp;
    }

    public void setMemBp (MemBp[] memBp)
    {
        this.memBp = memBp;
    }

    
}