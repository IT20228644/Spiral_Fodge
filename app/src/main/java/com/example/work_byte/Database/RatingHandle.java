package com.example.work_byte.Database;

public class RatingHandle {

    private String worker_name, worker_rating;

    public RatingHandle(){

    }

    public RatingHandle(String worker_id, String worker_rating){
        this.worker_name = worker_id;
        this.worker_rating = worker_rating;
    }

    public String getWorker_name() {return worker_name;}

    public void setWorker_name(String worker_name) { this.worker_name = worker_name;}

    public String getWorker_rating() {return worker_rating;}

    public void setWorker_rating(String worker_rating){this.worker_rating = worker_rating;}


}