package ca.cmpt213.a3.onlinesuperherotracker.controllers;


import ca.cmpt213.a3.onlinesuperherotracker.model.Superhero;
import ca.cmpt213.a3.onlinesuperherotracker.controllers.SuperheroNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * controller class that handles all the gets/post requests
 * using SpringBoot
 *
 * @author Mike Kreutz
 */
@RestController
public class SuperheroController {
    private List<Superhero> superheroes = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong();


    //return greeting message (respond with status 200)
    @GetMapping("/hello")
    @ResponseStatus(value = HttpStatus.OK)      //returns 200
    public String getGreeting(){
        return "Hello, welcome to Superhero Tracker Online!";
    }


    //return all superheroes (respond with status 200)
    @GetMapping("/listAll")
    @ResponseStatus(value = HttpStatus.OK)      //returns 200
    public List<Superhero> getAllHeroes(){
        return superheroes;
    }


    // add a new superhero object (respond with status 201, 400[Illegal Values] if not valid)
    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)         //returns 201
    public Superhero addSuperhero(@RequestBody Superhero superheroToAdd){
        if(superheroToAdd.getHeightInCm() < 0 || superheroToAdd.getCivilianSaveCount() < 0){
            throw new IllegalArgumentException();
        }
        superheroToAdd.setId(nextId.incrementAndGet());
        superheroes.add(superheroToAdd);
        return superheroToAdd;
    }


    //remove a superhero (respond with status 201 if successful, 404[not found] if not successful)
    @PostMapping("/remove/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)         //returns 201
    public List<Superhero> removeHero(@PathVariable("id") long superheroId){
        for(int i = 0; i < superheroes.size(); i++){
            if(superheroes.get(i).getId() == superheroId){
                superheroes.remove(i);
                return superheroes;
            }
        }
        throw new SuperheroNotFoundException();
    }


    // update information of a superhero (respond with 201 if successful, 404[not found] if not successful)
    @PostMapping("/update/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)             //returns 201
    public Superhero updateHero(@PathVariable("id") long heroId, @RequestBody Superhero updatedHero){
        updatedHero.setId(heroId);
        if(updatedHero.getHeightInCm() < 0 || updatedHero.getCivilianSaveCount() < 0){
            throw new IllegalArgumentException();
        }
        for(int i = 0; i < superheroes.size(); i++){
            if(superheroes.get(i).getId() == heroId){
                superheroes.set(i, updatedHero);
                return updatedHero;
            }
        }
        throw new SuperheroNotFoundException();
    }



    //returns top three heros (repond with status 200 if successful, 404[not enough heroes] if not successful)
    @GetMapping("/listTop3")
    @ResponseStatus(value = HttpStatus.OK)              //returns 200
    public List<Superhero> getTopThreeHeroes(){
        if(superheroes.size() < 3){
            throw new NotEnoughSuperheroesException();
        }

        List<Superhero> tempCopy = new ArrayList<>();
        for (Superhero hero : superheroes) {            //performs deep-copy
            tempCopy.add(hero);
        }
        tempCopy.sort(new Comparator<Superhero>() {        //sorts the list copy
            @Override
            public int compare(Superhero o1, Superhero o2) {
                return o1.getCivilianSaveCount() - o2.getCivilianSaveCount();
            }
        });

        Collections.reverse(tempCopy);              //top heroes at front of list

        List<Superhero> topThreeHeroes = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            topThreeHeroes.add(tempCopy.get(i));
        }
        return topThreeHeroes;
    }



    @ResponseStatus(value = HttpStatus.BAD_REQUEST,             //returns 400
            reason = "Either CivilianSaveCount or HeightInCm is less than Zero")
    @ExceptionHandler(IllegalArgumentException.class)
    public void illegalArgumentException()
    { }
}
