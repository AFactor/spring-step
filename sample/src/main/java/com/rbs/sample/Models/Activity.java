package com.rbs.sample.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Activity
{
    

    private String ceeId;

    private MemBp[] memBp;

    
    public String getCeeId ()
    {
        return ceeId;
    }

    public void setCeeId (Integer ceeId)
    {
        this.ceeId = ceeId.toString();
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