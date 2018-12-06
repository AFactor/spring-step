package com.rbs.sample.Models;

public class MemBp
{
    private String isJoint;

    private String bpDisplayName;

    private String[] owner;

    private String bpKey;

    private String bpObjId;

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

    public String getBpKey ()
    {
        return bpKey;
    }

    public void setBpKey (String bpKey)
    {
        this.bpKey = bpKey;
    }

    public String getBpObjId ()
    {
        return bpObjId;
    }

    public void setBpObjId (String bpObjId)
    {
        this.bpObjId = bpObjId;
    }

    
}