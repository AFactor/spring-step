package com.rbs.sample.Models;

public class MemBp
{
    private String isJoint;

    private String bpDisplayName;

    private String[] owner;

    private Integer bpKey;

    private Integer bpObjId;

    public String getIsJoint ()
    {
        return isJoint;
    }

    public void setIsJoint (String isJoint)
    {
        this.isJoint = isJoint;
    }

    

    public String getBpDisplayName ()
    {
        return bpDisplayName;
    }

    public void setBpDisplayName (String bpDisplayName)
    {
        this.bpDisplayName = bpDisplayName;
    }

    public String[] getOwner ()
    {
        return owner;
    }

    public void setOwner (String[] owner)
    {
        this.owner = owner;
    }

    public Integer getBpKey ()
    {
        return bpKey;
    }

    public void setBpKey (Integer bpKey)
    {
        this.bpKey = bpKey;
    }

    public Integer getBpObjId ()
    {
        return bpObjId;
    }

    public void setBpObjId (Integer bpObjId)
    {
        this.bpObjId = bpObjId;
    }

    
}