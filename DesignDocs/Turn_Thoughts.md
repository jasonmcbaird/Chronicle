Turn Logic
==========

Overview
--------

A turn comprises:

AI Player

  - check start-turn triggers
  - find nearest enemy
  - check movement triggers
  - move towards nearest enemy
  - choose ability
  - find nearest target (could be enemy or ally, depending on the ability)
  - if target is within range,
  - check action triggers
  - execute action
  - check end-turn triggers
  - end turn
  
Human Player

  - check start-turn triggers
  - check movement triggers
  - move
  - get ability list, show to player
  - player chooses ability
  - player chooses target
  - if target is withing range,
  - check action triggers
  - execute action
  - check end-turn triggers
  - end turn

Distillation

  - assess
    - check triggers
    - 
  - move
    - where am I supposed to move (Player type dependent)
    - check triggers
    - move character
  - act
    - which ability am I going to use (Player type dependent)
    - who am I targeting (Player type dependent)
    - validate action
    - reconcile consequences
    - check triggers
    - act on target 
  - react
    - check triggers
    - reconcile consequences
  - assess
    - check triggers

Classes

  Turn

    Responsible for turn-level behaviors: 

      - entry and exit triggers
      - sequencing of movement, action and reaction

  Move

    Responsible for figuring out where to move and for processing movement

      - where am I supposed to move (Player type dependent)
      - check triggers
      - move character

  Act

    Responsible for figuring out abilities and reconciling consequences

      - which ability am I going to use (Player type dependent)
      - who am I targeting (Player type dependent)
      - validate action
      - reconcile consequences
      - check triggers

  React

    Responsible for reconcile consequences

      - check triggers
      - reconcile consequences


Examples to ponder:

Berserker character with the Fury ability.  The rules of the Fury ability are
whenever you take damage gain two energy of rage type.

Berserker character with the Rage ability.  The rules of the Rage ability are
whenever I attack gain a random amount of energy of rage type.  Whenever my
Rage energy is full start raging whenever my turns end assess if I attacked while
in a rage - yes rage energy -4, no rage energy -2.  When rage energy goes to
zero stop raging.  While raging you cannot gain rage energy and increase strength by 6.

  rageBarFull?
  rageBarEmpty?
  raging?
  attacked?

Abilities and characteristics are functional cousins - abilities are verbs, characteristics are nouns

Queue Types ??

	Phase:      OVERIDE, START_TURN, END_TURN, END_ENCOUNTER
	Actions:    ACT, ATTACK, MOVE
	Reactions:  TAKE_DAMAGE, GAIN_ENERGY, HEAL
	Movement:   MOVE

Publish Subscribe
-----------------

Character publishes to Action

  - beforeDamage


