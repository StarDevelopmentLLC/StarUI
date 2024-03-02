# Creating Simple Guis
At it's core, StarUI is pretty simple, as it doesn't provide many high level features for UIs, however where it is simple, it allows a lot of flexibility with how you can interact with them.  
There are plans to add some more features, however these will not override the exisiting simplicity, so you can always use the bare-bones stuff to full customizability.

## InventoryGUI
This class is a default implementation for the `InventoryHandler` interface and if you want simple GUis, this is your entry point.  
### Creation
First, you call the constructor and pass in how many rows (Max of 6) and the Title of the GUI. The title can use color codes, however only default Spigot colors are supported. I plan on adding support for StarMCLib's ColorUtils, but that will be later.  
```java 
InventoryGUI gui = new InventoryGUI(6, "Test GUI");
```
### Adding Elements
Elements are the base for everything within this library and this is done to allow expandability.  
```java 
gui.setElement(0, 0, new Element().iconCreator(player -> new ItemStack(Material.DIAMOND)));
```
Now, you may be asking, what is with that `iconCreator`? Well, this is to allow more dynamic creation of items and usage of other item building libraries.  
I do plan on adding some factory methods to Element to make this a bit easier, but I have been using this in another project and it has come in extremely handy. Although, this library came from that project.  
The `setElement` method takes in a `row`, `column` and `element`.  Rows and columns start at zero.  
There is an `addElement` method which looks for the first empty slot and sets it there.  
You can also set an element using the `index`. This is calculated using `row * 9 + column`.

### Removing Elements
Removal of elements is done by a `row` and `column`

## Buttons
Buttons allows you specify what happens when a player interacts with the item in the GUI.  
Buttons are a sub-class of Element and have some additional things.  
Buttons are added via the `addElement`, and `setElement` methods just like regular elements. 
### Event  Consumer
This is a `Consumer` that gives you the `InventoryClickEvent`. This is safe to assume that it is only within the GUI as there are several checks for that before this is called.  
### Click Sound and Pitch
In addition, you can also provide a `Sound` and a `pitch` for when the button is clicked. These are methods similar to the iconCreator method whereas they are builder-pattern.  

## Filler Element
This is a special element where you can add it as a filler, and if you call `addElement`, the method will treat it as if it is empty