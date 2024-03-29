package dev._2lstudios.worldsentinel.listener;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonRetractEvent;

import dev._2lstudios.worldsentinel.region.Region;
import dev._2lstudios.worldsentinel.region.RegionManager;

class PistonRetractListener implements Listener {
    private final RegionManager regionManager;

    PistonRetractListener(final RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPistonRetract(final BlockPistonRetractEvent event) {
        final List<Block> retractedBlocks = (List<Block>) event.getBlocks();
        if (retractedBlocks != null && !retractedBlocks.isEmpty()) {
            final Block piston = event.getBlock();
            final Region region = this.regionManager.getRegionInside(piston.getLocation());
            for (final Block block : retractedBlocks) {
                final Region region2 = this.regionManager.getRegionInside(block.getLocation());
                if (region != region2) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
