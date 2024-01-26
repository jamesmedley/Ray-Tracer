/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tracer;

import entities.Entity;

/**
 *
 * @author james
 */
public class Intersection {
    private final Vector intersection;
    private final Vector surfaceNormal;
    private final Entity entity;
    
    public Intersection(Vector intersection, Vector surfaceNormal, Entity entity){
        this.intersection = intersection;
        this.surfaceNormal = surfaceNormal;
        this.entity = entity;
    }
    
    public Vector getIntersection(){
        return intersection;
    }
    
    public Vector getSurfaceNormal(){
        return surfaceNormal;
    }
    
    public Entity getEntity(){
        return entity;
    }
}
