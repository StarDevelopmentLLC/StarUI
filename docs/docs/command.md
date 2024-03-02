---
hide:
  - navigation
---

# The Command
This command is intended as a maintainer and dependent plugin based command and has little to no use for a server owner.  
However, this page does document what the commands do as well as their permissions. 
The main permission is `starui.admin`

## `/starui help`
Permission: `starui.admin.help`
Prints out the command and sub-command usage to the one that used the command.

## `/starui debug`
Permission: `starui.admin.debug`  
This command toggles debug mode, which all this does is print out most event information to the console. This is mostly used as an internal feature for creating and maintaining this plugin, however it may have some use to developers.  

## `/starui testgui`
Permission: `starui.admin.testgui`
This command creates a test gui, where it has a normal element that does nothing that is a Diamond and an iron ingot button that prints out "You click the button".  
As more features get added to this GUI, there may bemore parameters to this command as well as more elements added to the test gui. This is mainly an internal development command, however it can be used by developers to see if the core of StarUI is working.  

## `/starui listguis`
Permission: `starui.admin.listguis`
This command just lists out all of the open GUIs available to the GuiManager instance within StarUI. There isn't a while lot of information, but I do plan on adding sub commands to this one as well as doing some more stuff internally.