package me.creeves.particleslibrary.shapesdata;

import me.creeves.particleslibrary.Shapes;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Halo extends Shape {
    //Example of the shape produced can be viewed here: https://gyazo.com/22b14f9dafc91c9bd8112ffe9b1aa888

    private double radius;
    private double height;
    private double resolution;

    public Halo(Shapes shape, ConfigurationSection configurationSection) {
        super(shape, configurationSection);
        height = configurationSection.getDouble("height", 0.3);
        radius = configurationSection.getDouble("radius", 0.3);
        resolution = configurationSection.getInt("resolution", 32); //Number of particles per full circle.
    }

    @Override
    public List<Location> getWireframe(LivingEntity entity) {
        Location location = entity.getLocation();
        List<Location> wireframe = new ArrayList<>();
        double yCoordinate = location.getY() + 2 + height; //By default uses player head height as designed to sit on top of head
        for (double radians = 0; radians < 2*Math.PI; radians+=(2*Math.PI/resolution)) {
            double xCoordinate = location.getX() + (radius * Math.sin(radians));
            double zCoordinate = location.getZ() + (radius * Math.cos(radians));
            Location particleLocation = new Location(entity.getWorld(), xCoordinate, yCoordinate, zCoordinate);
            wireframe.add(particleLocation);
        }
        return wireframe;
    }
}
