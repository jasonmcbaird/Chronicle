Thoughts on Rules
=================

Discussion
----------

After much struggle and thought we discovered that there was a Rule class
hiding in the extant Ability class.  These notes are the result of working on
the design for the Rule class and their interactions with the TurnEvent and
TurnQueue classes.

Rules are the most granular and detailed game play object and a collection of
Rules make up the behavior of a Ability.  A collection of Abilities defines
the behavior of a Role.

Ability comes in two flavors - passive and active.  The rules associated with
the two flavors and the activation timing are quite different.

Passive Abilities and their corresponding rules are activated by Encounter and
**mostly** effect only the source character. Active Abilities and their
corresponding rules are activated at the end of act phase and **mostly**
effect target characters.

When an Ability is activated it activates all its Rules.

Encounter, Round and Turn will cause the TurnQueue to be processed for each
EventType and Character at the appropriate times which will call the
Rule.notify method.  This mechanism will allow a Rule to operate at a temporal
distance from itself and to act on target Characters.  

Rule Object
-----------

Rule attributes:

  - none (??)

Rule methods:

  - constructor

  - activate(Character)

    - Optionally creates an TurnEvent and places in the TurnQueue.

  - notify(TurnEvent)

    - assesses conditions
    - processes actions
    - creates TurnEvent(s) and adds them to TurnQueue.

Event Rule and a target character (can be null)

Sample Rules
------------

~~~~
Ability: Thunderbolt
Rules:   1.  When you use this ability, lose (4) mana. If you can't, the ability fails.
         2.  When you use this ability, roll to see if you hit. You're more
             likely to hit if you have high accuracy and less if your target has
             high dodge.
         3.  When you use this ability, deal damage to a target enemy
             character. You deal more damage if your Magic is high and less if the
             target's Resilience is high.
         4.  When you use this ability, deal damage to another target enemy. Same Magic/Resilience.
~~~~

~~~~
Ability: Fury
Rules:   1.  When damage is taken increase rage energy by 2
~~~~

~~~~
Rules:   1.  Whenever you use an ability on an enemy, gain a random amount of rage from 2-4.
         2.  When your rage bar fills, go berserk.
         3.  When your rage bar empties, stop being berserk. Note: your health will 
             drop. That drop could kill you.
         4.  While berserk, your strength is increased by (4). This increases your 
             health temporarily, too.
         5.  While berserk, the AI controls you.
         6.  While berserk, you cannot gain rage.
         7.  At end of turn, if berserk, lose 2 rage at the end of every turn if you 
             attacked that turn or lose 4 rage if you didn't. 
~~~~

Event Types
-----------

The EventType values are probably going to change as the implementation of
Rule instances progresses.

At the moment the values are taken from the old TriggerCondition with the
notable addition of an OVERRIDE value which provides for a TurnQueue that is
processed at every publication event before any other queue processing is
performed.

    ACTIVATE_ABILITY, TAKE_DAMAGE, HEAL, MOVE, START_TURN, ATTACK, 
    END_ENCOUNTER, END_TURN, GAIN_RAGE, DODGE 

    OVERRIDE
    
Attack currently means USE an ability ON an enemy.
    
