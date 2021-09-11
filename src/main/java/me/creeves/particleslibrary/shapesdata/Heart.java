package me.creeves.particleslibrary.shapesdata;

import me.creeves.particleslibrary.Shapes;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Heart extends Shape {
    private double height;
    private double frequency;
    private double size;

    public Heart(Shapes shapes, ConfigurationSection configurationSection) {
        this.height = configurationSection.getDouble("height", 2);
        this.frequency = configurationSection.getDouble("frequency", 0.05);
        this.size = configurationSection.getDouble("size", 1);
    }

    @Override
    public List<Location> getWireframe(LivingEntity entity) {
        Location location = entity.getLocation();
        List<Location> wireframe = new ArrayList<>();
        Vector perpendicularVector = getPerpendicularVector(entity.getEyeLocation().getDirection());
        for (double theta = 0; theta < 2*Math.PI; theta += frequency) {
            double radius = 3 - 2*Math.sin(theta) + Math.cos(2*theta) - 2*Math.abs(Math.cos(theta));
            double horizontalOffset = radius * Math.cos(theta) * size;
            double verticalOffset = radius * Math.sin(theta) * size + height;
            Vector offset = perpendicularVector.clone().multiply(horizontalOffset).setY(verticalOffset);
            Location particleLocation = location.clone().add(offset);
            wireframe.add(particleLocation);
        }
        return wireframe;
    }
}
