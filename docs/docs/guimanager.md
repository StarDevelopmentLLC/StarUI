# GuiManager Class
This class is the entry point into StarUI, regardless of which approch you are using for how the library is loaded.  

## Obtaining an Instance
### Usage as a Plugin
When using StarUI as a stand-alone plugin, getting and instance is pretty easy. 
```java
GuiManager guiManager = Bukkit.getServer().getServicesManager().getRegistration(GuiManager.class).getProvider();
```
Note: It is recommended to have a null check before calling `getProvider`, but for the sake of readability, I didn't do that here.  
Alternatively you can use the instanceof StarUI to get it.  
```java
StarUI starUiPlugin = (StarUI) Bukkit.getServer().getPluginManager().getPlugin("StarUI");
GuiManager guiManager = starUiPlugin.getGuiManager();
```
Again, you should do a null check on the `starUiPlugin` instance before calling `getGuiManager`.  
The second method is not recommended though and either one returns the same instance.  

### Usage as a Shaded Library
Getting a GuiManager instance is pretty simple as a shaded library, you just need to call the constructor, passing in your plugin. 
```java 
GuiManager guiManager = new GuiManager(plugin);
```
Then all you do is call the `setup` method to register things. 
```java
guiManager.setup();
```
And you are good to go. 

## Further Reading
Now you can move on to [Creating GUIs](creatingguis.md). This is the same for both methods now that you have a GuiManager instance. 