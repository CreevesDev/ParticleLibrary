package me.creeves.particleslibrary.shapesdata;

import me.creeves.particleslibrary.Shapes;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Halo extends Shape {
    private double radius;
    private double height;
    private double resolution;

    public Halo(Shapes shape, ConfigurationSection configurationSection) {
        super(shape, configurationSection);
        height = configurationSection.getDouble("height", 0.3);
        radius = configurationSection.getDouble("radius", 0.3);
        resolution = configurationSection.getInt("resolution", 16);
    }

    @Override
    public List<Location> getWireframe(LivingEntity entity) {
        Location location = entity.getLocation();
        List<Location> wireframe = new ArrayList<>();
        double yCoordinate = location.getY() + 2 + height;
        for (double radians = 0; radians < 2*Math.PI; radians+=(Math.PI/resolution)) {
            double xCoordinate = location.getX() + (radius * Math.sin(radians));
            double zCoordinate = location.getZ() + (radius * Math.cos(radians));
            Location particleLocation = new Location(entity.getWorld(), xCoordinate, yCoordinate, zCoordinate);
            wireframe.add(particleLocation);
        }
        return wireframe;
    }
}
