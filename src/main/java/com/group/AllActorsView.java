package com.group;

import com.group.entity.Actor;
import com.group.service.ActorService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class AllActorsView {
    private List<Actor> actors;

    @EJB
    private ActorService actorService;

    @PostConstruct
    public void init() {
        actors = new ArrayList<>();
        actors.addAll(actorService.getAll());
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public String deleteActor(long id) {
        actorService.delete(actorService.findById(id));
        return "/actors.xhtml?faces-redirect=true";
    }


    public String redirectToEditActor() {
        return "/editActor.xhtml?faces-redirect=true";
    }

}
