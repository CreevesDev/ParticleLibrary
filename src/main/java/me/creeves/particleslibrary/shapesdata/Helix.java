package me.creeves.particleslibrary.shapesdata;

import me.creeves.particleslibrary.Shapes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Helix extends Shape {
    //Creates animated helix.
    //Example of the shape produced can be viewed here: https://gyazo.com/ead409d61c8fc0bdf22997c1c83d05f4

    private double radius;
    private double frequency;
    private double speed;
    private double waveFrequency;
    private double height;

    public Helix(Shapes shape, ConfigurationSection configurationSection) {
        super(shape, configurationSection);
        this.radius = configurationSection.getDouble("radius", 0.5D); //Defines radius (in blocks) of helix centred on players (x,y) coordinates.
        this.speed = configurationSection.getDouble("speed", 1); //Defines how many ticks it takes for animation to make a full rotation around player at a particular y.
        this.waveFrequency = configurationSection.getDouble("wave_frequency", 1); //'Horizontal' (when graphed) stretch transformation applied to trig function, determines frequency of rotations.
        this.frequency = configurationSection.getDouble("interval", 0.05); //Determines interval (in blocks) between y-coordinates for which locations computed at. Smaller value = more particles.
        this.height = configurationSection.getDouble("height", 2); //Height the helix should extend to starting at the player's feet location.
    }

    @Override
    public List<Location> getWireframe(LivingEntity entity) {
        Location location = entity.getLocation();
        List<Location> wireframe = new ArrayList<>();
        for (double y = 0; y < height; y+=frequency) {
            double trigInput = y*waveFrequency*2*Math.PI + Bukkit.getCurrentTick()*speed;
            double xCoordinate = location.getX() + radius * Math.cos(trigInput);
            double zCoordinate = location.getZ() + radius * Math.sin(trigInput);
            double yCoordinate = y + location.getY();
            Location particleLocation = new Location(entity.getWorld(), xCoordinate, yCoordinate, zCoordinate);
            wireframe.add(particleLocation);
        }
        return wireframe;
    }
}
