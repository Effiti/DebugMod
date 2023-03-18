# Effiti's Debug Mod
--------------------
This is a mod which allows the user to display some information needed for debugging.

## Features
### `/-msg`
the command `/-msg <type>` allows you to specify what kinds of messages the mod should display.
Valid options are:
- `entities` if set to true, you will receive chat messages when your client receives an EntitySpawn Packet.
- `join` if set to true, you will receive chat messages when the PlayerList is updated. This happens when...
  1. a Player joins the server
  2. a Player leaves the server
  3. a Player changes Gamemode (minecraft even sends this packet if there's no use for it. 
  The client only needs to know the difference between Spectators and non-Spectators in order to correctly display the Tablist.
  I have yet to see a server or plugin which fixes this)
- `send` if set to true, you will receive messages for **all** packets sent by the server (Because of remapping, the class names are not recognizable
TODO: use instanceof checks to get a string for the Packet name)

### `/-dump`
the `/-dump <value>` command allows you to dump current server information.
At the moment only the `scoreboard` value is (poorly) implemented, allowing you to see the current scoreboard information.

### Invisible Hitboxes
minecraft usually does not render the hitboxes of invisible entities.
This is highly impractical, The Mod overwrites this behaviour.