package me.creeves.particleslibrary;

import me.creeves.particleslibrary.shapesdata.Shape;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class ParticleData {

    private int duration;
    private Particle particle;
    private int amount;
    private Shape shapeData;
    private double extra;
    private List<Color> colours;
    private float scale = 1;

    public ParticleData(ConfigurationSection configurationSection) {
        duration = (int) Math.round(configurationSection.getDouble("duration", 2.5D) * 20);
        particle = Particle.valueOf(configurationSection.getString("type", "PORTAL"));
        amount = configurationSection.getInt("amount", 1);
        extra = configurationSection.getInt("extra", 0);
        ConfigurationSection shapeSection = configurationSection.getConfigurationSection("shape");
        Shapes shape = Shapes.valueOf(shapeSection.getString("type"));
        shapeData = Shape.create(shape, shapeSection);
        ConfigurationSection optionsSection = configurationSection.getConfigurationSection("options");
        if (optionsSection != null && particle == Particle.REDSTONE) {
            OptionsData optionsData = new OptionsData(optionsSection);
            colours = optionsData.getColours();
            scale = optionsData.getScale();
        }
    }

    private Color getColourForTick() {
        if (colours == null) return null;
        return colours.get(Bukkit.getCurrentTick()/4 % colours.size());
    }

    public void spawnParticle(LivingEntity entity) {
        List<Location> particleLocations = shapeData.getWireframe(entity);
        for (Location location : particleLocations) {
            Color colour = getColourForTick();
            Particle.DustOptions dustOptions = colour == null ? null : new Particle.DustOptions(colour, scale);
            entity.getWorld().spawnParticle(particle, location, amount, 0, 0, 0, 0, dustOptions);
        }
    }

    public int getDuration() {
        return duration;
    }

    public Particle getParticle() {
        return particle;
    }
}