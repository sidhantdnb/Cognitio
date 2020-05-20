package com.example.harshit.cognitio19.Modals;

public class EventHeadModal {
    String image,name,phone;

    public EventHeadModal(String image,String name,String phone){
        this.image=image;
        this.name=name;
        this.phone=phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getImage(){ return image; }
}
