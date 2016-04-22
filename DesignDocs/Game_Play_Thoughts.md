Standard RPG system - world map, encounter map.  The chronicle swizzle
possibly local grid ala instance in WOW.

A player has more than one character under their control - average would be
six.

An encounter consists of a series of rounds and a round consists of a sequence
of turns of all the characters.

A player enters an encounter by starting a fight. The encounter switches to an
encounter map, a zoomed in world map. The player arranges the starting
location of team members. The fastest character in the encounter, AI or
player, initiates combat. That character is optionally moved either by the AI
or the player.  Once movement is complete the player can choose to move more
or choose to use an ability.  After all movement is concluded the player can
end their turn or choose to use an ability.  When a player picks an ability
the player must also choose targets for that ability.  The AI then processes
the abilities, with corresponding animation, updates the affected characters.
After the processing a player may have an opportunity to move or act again.
After all movement or action is complete the turn is over.  The AI then
chooses the next fastest character and the process happens again.

The encounter ends when a win condition has been fulfilled.

Examples of win conditions:

 - A boss dies
 - All of one team is dead
 - Control of a given location
 - All enemies are dead

Teams have relationships: allied, hostile, neutral. When an encounter begins,
relationships between teams are determined.  Team relationships can change
during an encounter and characters can change teams during an encounter.

Relationships are between teams and allegiances are between a character and
their team. A character can be only on one team. Conceptually allegiance is
synonymous with membership

A player's team can include AI characters that the player does not control
movement or use of abilities.

After turns win conditions are checked. Between turns of the same team the
team character to act next can be changed by a manager.

Between rounds game play rules are assessed.

Definitions
-----------

Allied means that two teams on the same side.  You don't attack each other and
you help each other. You can have non-combatant allies.

Hostile means that two teams will attack each other.

Neutral means teams don't interact beyond conversation.

Relationships exist between teams and can be one of allied, hostile, or neutral.

A character is controlled by either a player or a conductor. A character has
a collection of roles.

A role is a collection of abilities.

A team is a collection of characters, potentially either human or AI
controlled, all with the same relationship to other teams.

A manager is an object that makes decisions about how characters move and act.

A *player* is a human manager

A *conductor* is an AI manager

World
-----

In the world a player can move, interact with inanimate objects, converse
and otherwise interact with animate objects, modify characteristics,
modify inventory, initiate an encounter.

The team has a 'cart' to hold items that aren't allocated to a specific
character. Characteristics and abilities must be allocated to a character
before an encounter.

Since this is a single player game there is no concept of round or turn out in
the world.

