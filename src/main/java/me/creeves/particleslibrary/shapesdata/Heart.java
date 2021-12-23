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
    //Heart shape above players head.
    //Example of the shape produced can be viewed here: https://gyazo.com/870054a859b5576c6369a62ca5437686

    private double height;
    private double frequency;
    private double size;

    public Heart(Shapes shapes, ConfigurationSection configurationSection) {
        this.height = configurationSection.getDouble("height", 2); //Offset from the player's feet y-location.
        this.frequency = configurationSection.getDouble("frequency", 0.05); //Requires renaming as represents variable inversely proportional to frequency. Similar to interval in helix.
        this.size = configurationSection.getDouble("size", 1); //Arbitrary multiplier to size of heart shape produced.
    }

    @Override
    public List<Location> getWireframe(LivingEntity entity) {
        Location location = entity.getLocation();
        List<Location> wireframe = new ArrayList<>();
        Vector perpendicularVector = getPerpendicularVector(entity.getEyeLocation().getDirection());
        //Vector used to ensure heart remains above players head, facing forward as their orientation changes.
        for (double theta = 0; theta < 2*Math.PI; theta += frequency) {
            double radius = 3 - 2*Math.sin(theta) + Math.cos(2*theta) - 2*Math.abs(Math.cos(theta));
            //Polar equation that can represent heart like shape.
            double horizontalOffset = radius * Math.cos(theta) * size;
            double verticalOffset = radius * Math.sin(theta) * size + height;
            Vector offset = perpendicularVector.clone().multiply(horizontalOffset).setY(verticalOffset);
            Location particleLocation = location.clone().add(offset);
            wireframe.add(particleLocation);
        }
        return wireframe;
    }
}
