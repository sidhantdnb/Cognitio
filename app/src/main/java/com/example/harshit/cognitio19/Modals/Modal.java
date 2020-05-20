package com.example.harshit.cognitio19.Modals;

public class Modal {
    private String eventName;
    private String image;
    private String eventPrize;
    private String description;
    private String timeStamp;
    private int drawableImage;

    public Modal (String image,String description,String eventPrize,String eventName){
        this.eventPrize =eventPrize;
        this.eventName=eventName;
        this.image=image;
        this.description=description;
    }
    public Modal (int drawableImage,String description,String eventPrize,String eventName){
        this.eventPrize =eventPrize;
        this.eventName=eventName;
        this.drawableImage=drawableImage;
        this.description=description;
    }
    public Modal (String description,String eventPrize,String eventName){
        this.description=description;
        this.eventName=eventName;
        this.eventPrize=eventPrize;
    }
    public String getEventPrize() {
        return eventPrize;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEventName() {
        return eventName;
    }

    public String getImage() {
        return image;
    }

    public int getDrawableImage(){ return drawableImage;}

    public String getDescription() {
        return description;
    }
}