package me.creeves.particleslibrary.shapesdata;

import me.creeves.particleslibrary.Shapes;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Point extends Shape {
    //Simplest particle type. Wireframe contains a single particle with random coordinates within config defined bounds.
    //Example of the shape produced can be viewed here: https://gyazo.com/c408559c28c76ea20667b0bb501fc1a4
    //Example above shows point with offset to 0 and spread at 0.5.

    private double spread;
    private double yOffset;

    public Point(Shapes shape, ConfigurationSection configurationSection) {
        super(shape, configurationSection);
        spread = configurationSection.getDouble("spread", 0.5);
        yOffset = configurationSection.getDouble("offset", 0);
    }

    @Override
    public List<Location> getWireframe(LivingEntity entity) {
        Location location = entity.getLocation();
        List<Location> wireframe = new ArrayList<>();
        double xCoordinate = location.getX() + (2 * spread * Math.random() - spread) - 0.2;
        double yCoordinate = location.getY() + (2 * spread * Math.random() - spread) + yOffset;
        double zCoordinate = location.getZ() + (2 * spread * Math.random() - spread) - 0.2;
        Location particleLocation = new Location(entity.getWorld(), xCoordinate, yCoordinate, zCoordinate);
        wireframe.add(particleLocation);
        return wireframe;
    }

    public double getSpread() {
        return spread;
    }
}
