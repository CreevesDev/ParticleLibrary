package me.creeves.particleslibrary.shapesdata;

import me.creeves.particleslibrary.Shapes;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class Shape {
    private Shapes shape;
    private ConfigurationSection configurationSection;

    public Shape(Shapes shape, ConfigurationSection configurationSection) {
        this.shape = shape;
        this.configurationSection = configurationSection;
    }

    public Shape() {

    }

    public abstract List<Location> getWireframe(LivingEntity entity);

    public static Shape create(Shapes shape, ConfigurationSection configurationSection) {
        try {
            return shape.getShapeClass().getConstructor(Shapes.class, ConfigurationSection.class).newInstance(shape, configurationSection);
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Shapes getShape() {
        return shape;
    }

    public ConfigurationSection getConfigurationSection() {
        return configurationSection;
    }

    public Vector getPerpendicularVector(Vector directionVector) {
        double x = ((directionVector.getZ() * 1)) / (directionVector.getX() * -1);
        Vector perpendicularVector = new Vector(x, 0, 1).normalize();
        return perpendicularVector;
    }
}
