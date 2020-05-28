package com.hfad.catchat;

public class email {
    //private String name;
    //private String message;
    private int imageResourceId;

    private email(int imageResourceId){
      //  this.name= name;
      //  this.message= message;
        this.imageResourceId= imageResourceId;
    }

    public static final email[] emails= {
            new email(R.drawable.alien),
            new email(R.drawable.astronaut),
            new email(R.drawable.bouncer),
            new email(R.drawable.child),
            new email(R.drawable.diver),
            new email(R.drawable.girl),
            new email(R.drawable.grandfather),
            new email(R.drawable.monster),
            new email(R.drawable.superhero),
            new email(R.drawable.woman)
    };
    public static final int[] images={R.drawable.alien,R.drawable.astronaut,R.drawable.bouncer,R.drawable.child,R.drawable.diver,R.drawable.girl,R.drawable.grandfather,R.drawable.monster,R.drawable.superhero,R.drawable.woman};

   /* public String getName(){
        return name;
    }
    public String getMessage(){
        return message;
    }

    */
    public int getImageResourceId(){
        return imageResourceId;
    }

}
