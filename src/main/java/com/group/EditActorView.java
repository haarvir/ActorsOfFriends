package com.group;

import com.group.entity.Actor;
import com.group.service.ActorService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class EditActorView implements Serializable {
    private String name;
    private int age;
    private String movie;
    private String country;

    @EJB
    private ActorService actorService;
    private transient Actor actorToUpdate;

    public void populateView(long actorId) {
        actorToUpdate = actorService.findById(actorId);
        this.setName(actorToUpdate.getName());
        this.setCountry(actorToUpdate.getCountry());
        this.setAge(actorToUpdate.getAge());
        this.setMovie(actorToUpdate.getMovie());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String save() {
        Actor createdActor = new Actor(name, age, movie, country);
        if (actorToUpdate != null) {
            createdActor.setId(actorToUpdate.getId());
            actorService.update(createdActor);
        } else {
            actorService.create(createdActor);
        }
        nullifyFields();
        return "/actors.xhtml?faces-redirect=true";
    }

    private void nullifyFields() {
        actorToUpdate = null;
        this.setMovie(null);
        this.setAge(0);
        this.setCountry(null);
        this.setName(null);
    }
}
