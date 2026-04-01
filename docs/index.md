---
title: Overview
description: "Summary of in-progress or completed project."
order: 0
---

{% include ddc-abbreviations.md %}

## Page contents
{:.no_toc}

- ToC
{:toc}

## Summary

Fossil themed minesweeper with a card collecting element and a small window into real academic paleontology. Instead of avoiding mines, you avoid fossils so as not to break them. Every fossil you successfully uncover displays an image and info block from a real academic database. All uncovered fossils are viewable in a gallery and counted up as lifetime score.

## Intended users and user stories

+ Casual Fossil Lover

    As someone interested in paleontology and ancient creatures, I like discovering new fossils by uncovering them in Fossil Sweeper.

+ Puzzle Game Enthusiast

    As a puzzle pro, I appreciate Fossil Sweeper's tile reveal item to perfect win sweeper games that would otherwise come down to luck.

+ Timekilling Puzzler

    Since I'm just learning puzzles, I like that making a mistake doesn't end the whole game and lets me keep playing and get the other fossils.

+ Paleontology Educator

    As a science teacher, I introduce this game to my students to get them familiar with real-world archaeology data.


## Functionality

General rules of Fossil Sweeper are the same as Minesweeper.  Tapping a tile digs it out, revealing either nothing, the number of nearby fossils, or a broken fossil (oh no!).  Selecting the fence tool makes tapping protect the tile so you don't accidentally excavate it. Selecting the brush tool extracts the tile carefully, so if it contains a fossil, you acquire it.  There are only as many brushes as fossils, so wasting any will result in the player not acquiring all the fossils on the board. The game ends when you use your last brush. A "travel" tool allows panning and zooming only.

The board fills the screen, except for a control bar along the top. The board can be zoomed to make the buttons big enough to hit on large boards.

Every time you successfully excavate a fossil, you are shown a fossil card from a scientific database with a photo and infoblock, and that card is stored permanently in a gallery accessible from the app.


## Persistent data

+ Fossils acquired—including potential fossils to fill in info once available.
+ Prefetched fossils to be acquired later
+ In-progress game
+ inventory of long-term power ups


## Device/external services

+ Fossil image database with species information
    + Periodic access to this database will be required to enjoy the fossil discovery feature of 
      this game, but it will queue up fossils uncovered when offline, and automatically 
      "acquire" them next time it has network access to query the database and download the 
      image links. The game will be fully playable without access, but all acquired fossils will 
      be blank until it's able to use the database to fill them in.
    + GBIF (Global Biodiversity Information Facility)
      + [GBIF API reference](https://techdocs.gbif.org/en/openapi/)
    + iDigBio (Integrated Digitized Biocollections) 
      + [iDigBio APIs homepage](https://www.idigbio.org/wiki/index.php/IDigBio_API)
      + [iDigBio Search API](https://github.com/idigbio/idigbio-search-api/wiki)

## Stretch goals and possible enhancements 

[//]: # (TODO If you can identify functional elements of the software that you think might not be achievable in the scope of the project, but which would nonetheless add significant value if you were able to include them, list them here. For now, we recommend listing them in order of complexity/amount of work, from the least to the most.)
