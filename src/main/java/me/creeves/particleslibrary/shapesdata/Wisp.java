package me.creeves.particleslibrary.shapesdata;

import me.creeves.particleslibrary.Shapes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Wisp extends Shape {
    //Similar to helix except
    //Example of the shape produced can be viewed here: https://gyazo.com/a2599daf5bc26b79bd9ec6cd545d06fb

    private double radius;
    private double speed;
    private double waveFrequency;
    private double height;

    public Wisp(Shapes shape, ConfigurationSection configurationSection) {
        super(shape, configurationSection);
        this.radius = configurationSection.getDouble("radius", 0.5D); //Defines radius of helix centred on players (x,y) coordinates.
        this.speed = configurationSection.getDouble("speed", 1); //Defines how quickly animation moves around helix.
        this.waveFrequency = configurationSection.getDouble("wave_frequency", 1); //Can be seen as number of rotations helix makes around player in a set vertical distance
        this.height = configurationSection.getDouble("height", 2); //Defines how high above the players current y-location the helix should extend to.
    }

    @Override
    public List<Location> getWireframe(LivingEntity entity) {
        Location location = entity.getLocation();
        List<Location> wireframe = new ArrayList<>();
        double trigInput = (Bukkit.getCurrentTick()*speed);
        double xCoordinate = location.getX() + radius * Math.cos(trigInput);
        double zCoordinate = location.getZ() + radius * Math.sin(trigInput);
        double cyclePosition = (Math.sin(Bukkit.getCurrentTick() * waveFrequency) + 1)/2;
        double yCoordinate = cyclePosition*height + location.getY();
        Location particleLocation = new Location(entity.getWorld(), xCoordinate, yCoordinate, zCoordinate);
        wireframe.add(particleLocation);
        return wireframe;
    }
}
