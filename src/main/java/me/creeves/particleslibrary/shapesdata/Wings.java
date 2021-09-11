package me.creeves.particleslibrary.shapesdata;

import me.creeves.particleslibrary.Shapes;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;

public class Wings extends Shape {
    public Wings(Shapes shape, ConfigurationSection configurationSection) {
        super(shape, configurationSection);
    }

    @Override
    public List<Location> getWireframe(LivingEntity entity) {
        return null;
    }
}
