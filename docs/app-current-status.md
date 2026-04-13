---
title: Current State
description: "As-is state of the application at the end of bootcamp."
order: 50
---

{% include ddc-abbreviations.md %}

## Page contents
{:.no_toc:}

- ToC
  {:toc}

## Missing Features
* Choosing which saved game to continue, or starting a new game
  Currently just selects the most recent game, or starts a new one if it does not exist.
* Logic and enhanced fragment (and fragment wiring) to show a new collected fossil on screen during gameplay.
* Awarding the player a scanner item for a perfect game where all fossils are extracted.
* Collected Fossil Gallery just isn't there. It's a blank placeholder right now.
* logic and facilities to create new Collected Fossil attached to the user and fill with a Fossil if any are available.
* While the Fossil webservice is written, I'm never using it and I still need to expand the entity class to keep the fields I chose to keep.
* Fossil prefetch, fossil image precache, and usable fossil URL check-and-reject logic all unimplemented.
    * Prefetch would spin off background tasks from the main menu that would get fossils from the webervice until there are enough to fulfill all fossils being collected from the next game.
    * Image Precache fetches and caches the images associated with Fossil entries. cache is intended to be relatively large and long-lasting persistant storage.
    * URL check-and-reject is if the URL does not resolve to an image, a decision is made if it is likely to work later, and if not (e.g. credentials are required) then the Fossil is marked unusable. (keep record so do not re-fetch)
* Related to Fossil prefetch is Collected Fossil backfill, which is like an ending step to prefetch where any unlinked Collected Fossils are provided with Fossils once they are available.



## Bugs
* RadioGroup at the top to select tool is not showing current selection.
* Also need to end the game if there are no more fossils on the board (you could have smashed some so have leftover brushes)
* I need to actually lock the board when the game is over. -- And make sure to still show a Collected Fossil popup if the game-ending action would display one!


## Aesthetic Want List
* Better board graphics
    * Style these basic tiles or use custom tiles
    * Add color map for different "surrounding fossils" numbers.
    * Style or use a different font for "surrounding fossils" numbers.
* Custom theme for app including light and dark mode with cohesive color themes that feel like a paleontological dig. 
