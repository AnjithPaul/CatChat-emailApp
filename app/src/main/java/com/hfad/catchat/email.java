package com.hfad.catchat;

public class email {
    private String name;
    private String message;
    private int imageResourceId;

    private email(String name,String message,int imageResourceId){
        this.name= name;
        this.message= message;
        this.imageResourceId= imageResourceId;
    }

    public static final email[] emails= {
            new email("Stanley Kubrick", "Should've made 2020: A global pandemic", R.drawable.default_dp),
            new email("Alfred Hitchcock", "Psychooooooo", R.drawable.default_dp),
            new email("Martin Scorsese", "Good fellas ride in Taxi", R.drawable.default_dp)
    };

    public String getName(){
        return name;
    }
    public String getMessage(){
        return message;
    }
    public int getImageResourceId(){
        return imageResourceId;
    }

}
