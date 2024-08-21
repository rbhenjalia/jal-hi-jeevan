package sih.practice.teststs;

public class LoadList {

    public String state;
    public String date;
    public String complaintID;

    public String getState()
    {
        return state;
    }
    public String getDate()
    {
        return date;
    }
    public String getID()
    {
        return complaintID;
    }
    public void setState(String state)
    {
        this.state=state;
    }
    public void setDate(String date)
    {
        this.date=date;
    }
    public void setID(String complaintID)
    {
        this.complaintID=complaintID;
    }
}
