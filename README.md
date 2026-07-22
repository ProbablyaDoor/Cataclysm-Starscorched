# Cataclysm-Starscorched



## What is it?
Cataclysm: Starscorched or just Cataclysm is my first ever attempt at making a Minecraft mod. It adds a new weapon progression into the game with 
custom attacks, systems and detailed models. It also adds custom blocks, items, mobs and minibosses to the game, which are vital in the process of
making each weapon. Lastly, every weapon will be made with a new crafting system, based on a "multiblock," a structure made from a large chunk of
blocks rather than just a single station. **ONE MORE THING:**, this mod is best played on a server with several people, as it is meant to be a
parody of those "custom weapon" or "custom gimmick" SMP servers.


## How this was made
This project was made in the **Fabric modding engine using Java**
with some help for animations from libraries such as **Geckolib** and **Player Animation Library**


## Some *totally small* issues
My worst enemy ended up being the **BASE MINECRAFT GAME ENGINE**. Every weapon ability I made had me checking Minecraft's source code, just to see
if a feature was hardcoded or not. Eventually, I learned the fix to this was simply just using something called a `mixin` to inject new code into 
Minecraft's base code in order to "un-hardcode" everything I needed to implement that was already hardcoded. The first few items I added were made
with the help of a tutorial series and the Fabric API documentation so that I could get a baseline understanding of how to use Java code.


## How to run the project:

### For Modloaders (Curseforge, Modrinth, Prism, etc):
Make a new instance of Minecraft on fabric version 1.21.1. Then, import the '.jar' file from the GitHub page. Then, add the dependencies of the mod
(Fabric API is already added for fabric installations.)

### For Non-Modloaders:
Press "Windows + R" or simply open "run" and type in the path `C:\Users\Example\AppData\Roaming\.minecraft`. Then, add a new folder called `mods`
Within this folder, add the latest version of fabric api for 1.21.1 and the mod file from the GitHub. Next go to the Fabric modloader website
and install the 1.21.1 version for your operating system. Open minecraft and you should be good to go!



## AI DISCLOSURE:
Almost no AI was used in the making of the project. However, it was used for fixing a certain bug involving one of the weapons that almost
caused me to scrap it entirely due to how janky Minecraft's source code is. It was also partially used to help me understand how to create
different events, but most of the help came from the Fabric documentation and tutorials.


## Extra Info (Why I ended up making Cataclysm and what inspired it):
This mod was created from a joke about me hosting an event where one player would get extremely unfair and overpowered Minecraft weapons. **HARRY YAO**
suggested a weapon to me and thus, Cataclysm: Starscorched was born.

Weapons and mobs are inspired by other mods such as: Celestisynth, Simply Swords, Marium's Soulslike Weaponry, Reliquary and Scape and Run: Parasites.
In NO way does this relate to L_Ender's Cataclysm, a mod with a similar name to this one. Some of the weapons are sort of derived from their source
material. However, two of them (currently) are just the weapons from their respective mods, only on steroids due to my *amazing* balancing skills.




