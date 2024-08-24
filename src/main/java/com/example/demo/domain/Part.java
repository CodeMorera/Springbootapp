package com.example.demo.domain;

import com.example.demo.validators.ValidDeletePart;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 *
 *
 */
@Entity
@ValidDeletePart
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="part_type",discriminatorType = DiscriminatorType.INTEGER)
@Table(name="Parts")
public abstract class Part implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    @Min(value = 0, message = "Price value must be positive")
    double price;
    @Min(value = 0, message = "Inventory value must be positive")
    int inv;

    @Min(value = 0,message = "Min inventory must be positive.")
    int minimumInv;

    @Min(value = 0, message="Max inventory must be zero or above."  )
    @Max(value = 200, message = "Inventory value cannot exceed set maximum" )
    int maximumInv;

    @ManyToMany
    @JoinTable(name="product_part", joinColumns = @JoinColumn(name="part_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    Set<Product> products= new HashSet<>();

    public Part() {
    }

    public Part(String name, double price, int inv) {
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.minimumInv = minimumInv;
        this.maximumInv = maximumInv;
    }

    public Part(long id, String name, double price, int inv) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.minimumInv = minimumInv;
        this.maximumInv = maximumInv;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv) {
        this.inv = inv;
    }

    public int getMaximumInv() {return maximumInv;}

    public void setMaximumInv(int maximumInv) {this.maximumInv = maximumInv;}

    public int getMinimumInv() {return minimumInv;}

    public void setMinimumInv(int minimumInv) {this.minimumInv = minimumInv;}

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String toString(){
        return this.name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        return id == part.id;
    }

    public void inventoryValidation(){
        if (this.inv < this.minimumInv){
            throw new RuntimeException("Inventory value cannot fall below set minimum inventory");
        }
        else if (this.inv > this.maximumInv){
            throw new RuntimeException("Inventory value cannot exceed set maximum inventory");
        }
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
