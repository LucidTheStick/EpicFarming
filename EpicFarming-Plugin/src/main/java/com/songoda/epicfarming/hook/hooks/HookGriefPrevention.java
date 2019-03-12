package com.songoda.epicfarming.hook.hooks;

import com.songoda.epicfarming.hook.HookType;
import com.songoda.epicfarming.hook.ProtectionPluginHook;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HookGriefPrevention implements ProtectionPluginHook {

    private final GriefPrevention griefPrevention;

    public HookGriefPrevention() {
        this.griefPrevention = GriefPrevention.instance;
    }

    @Override
    public JavaPlugin getPlugin() {
        return griefPrevention;
    }

    @Override
    public HookType getHookType() {
        return HookType.REGULAR;
    }

    @Override
    public boolean canBuild(Player player, Location location) {
        Claim claim = griefPrevention.dataStore.getClaimAt(location, false, null);
        return claim != null && claim.allowBuild(player, Material.SPAWNER) == null;
    }

    @Override
    public boolean isInClaim(Location location) {
        return griefPrevention.dataStore.getClaimAt(location, false, null) != null;
    }

    @Override
    public boolean isInClaim(Location location, String id) {
        return false;
    }

    @Override
    public String getClaimID(String name) {
        return null;
    }

}