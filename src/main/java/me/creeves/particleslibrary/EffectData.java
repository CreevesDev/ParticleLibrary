package me.creeves.particleslibrary;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EffectData { //Used in ESParticles to build effects connected to each player.
    private final List<ParticleData> particles = new ArrayList<>();
    private String name;

    public EffectData(String name, ConfigurationSection configurationSection) {
        this.name = name;
        for (Object item : configurationSection.getValues(false).values()) {
            if (item instanceof ConfigurationSection) {
                ConfigurationSection particleSection = (ConfigurationSection) item;
                ParticleData particle = new ParticleData(particleSection);
                particles.add(particle);
            }
        }
    }

    public void spawnEffect(LivingEntity entity) {
        for (ParticleData particle : particles) {
            particle.spawnParticle(entity);
        }
    }

    public String getName() {
        return name;
    }

    public List<ParticleData> getParticles() {
        return particles;
    }
}
