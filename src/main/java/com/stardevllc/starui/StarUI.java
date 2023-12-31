package com.stardevllc.starui;

import com.stardevllc.starui.element.Element;
import com.stardevllc.starui.element.button.Button;
import com.stardevllc.starui.gui.InventoryGUI;
import com.stardevllc.starui.handler.InventoryHandler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

/**
 * Main plugin class of StarUI. This registers a default GuiManager to the Bukkit ServicesManager as well as providng a getter method<br>
 */
public class StarUI extends JavaPlugin {
    
    private GuiManager guiManager;
    private boolean debug;
    
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        saveConfig();
        guiManager = new GuiManager(this);
        guiManager.setup();
        getServer().getServicesManager().register(GuiManager.class, guiManager, this, ServicePriority.Normal);
        getServer().getPluginManager().registerEvents(new DebugListener(this), this);
        getServer().getPluginManager().registerEvents(guiManager, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equals("starui")) {
            return true;
        }
        
        if (!sender.hasPermission("starui.admin")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use that command.");
            return true;
        }
        
        if (args.length == 0) {
            if (!sender.hasPermission("starui.admin.help")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use that command.");
                return true;
            }
            sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "StarUI Help");
            ChatColor valueColor = debug ? ChatColor.GREEN : ChatColor.RED;
            String modeAction = debug ? "on" : "off";
            sender.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Debug Mode: " + valueColor + modeAction);
            sender.sendMessage(ChatColor.GOLD + " Commands: ");
            sender.sendMessage(ChatColor.YELLOW + "  debug " + ChatColor.WHITE + "Toggles the Debug Mode on or off.");
            sender.sendMessage(ChatColor.YELLOW + "  testgui " + ChatColor.WHITE + "Opens a TestGUI with at lest one of each base type.");
            sender.sendMessage(ChatColor.YELLOW + "  listguis " + ChatColor.WHITE + "Provides information about currently open GUIs from this plugin.");
            return true;
        }
        
        if (args[0].equalsIgnoreCase("debug")) {
            if (!sender.hasPermission("starui.admin.debug")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use that command.");
                return true;
            }
            if (args.length == 1) {
                this.debug = !debug;
                ChatColor valueColor = debug ? ChatColor.GREEN : ChatColor.RED;
                String modeAction = debug ? "on" : "off";
                sender.sendMessage(ChatColor.WHITE + "You have toggled " + valueColor + modeAction + ChatColor.WHITE + " debug mode.");
                return true;
            }
        } else if (args[0].equalsIgnoreCase("testgui")) {
            if (!sender.hasPermission("starui.admin.testgui")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use that command.");
                return true;
            }
            InventoryGUI gui = new InventoryGUI(6, "Test GUI");
            gui.setElement(0, 0, new Element().iconCreator(player -> new ItemStack(Material.DIAMOND)));
            gui.setElement(1, 4, new Button().consumer(e -> e.getWhoClicked().sendMessage(ChatColor.GREEN + "You clicked the button.")).iconCreator(player -> new ItemStack(Material.IRON_INGOT)));
            guiManager.openGUI(gui, (Player) sender);
        } else if (args[0].equalsIgnoreCase("listguis")) {
            if (!sender.hasPermission("starui.admin.listguis")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use that command.");
                return true;
            }
            Map<Inventory, InventoryHandler> activeHandlers = guiManager.getActiveHandlers();
            sender.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "Active GUIs Information");
            sender.sendMessage(ChatColor.YELLOW + " Total Active: " + ChatColor.AQUA + activeHandlers.size());
            activeHandlers.forEach((inv, handler) -> sender.sendMessage(ChatColor.YELLOW + "  Handler " + ChatColor.AQUA + handler.getClass().getSimpleName() + ChatColor.YELLOW + " with " + ChatColor.AQUA + inv.getViewers().size() + ChatColor.YELLOW + " total viewer(s)."));
        } else {
            sender.sendMessage(ChatColor.RED + "Invalid sub command");
        }
        
        return true;
    }

    /**
     * @return The GuiManager instance
     */
    public GuiManager getGuiManager() {
        return guiManager;
    }

    /**
     * @return If debug mode is toggled on or off - Internal use
     */
    public boolean isDebug() {
        return debug;
    }
}