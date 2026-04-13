---
title: AI Use
description: "Tasks used for AI in each sprint"
order: 90
---

{% include ddc-abbreviations.md %}

## Page contents
{:.no_toc:}

- ToC
{:toc}

## Milestone 1

+ No AI was used to complete Milestone 1 beyond inline complete in IntelliJ IDEA.

## Milestone 2

+ Claude was used to create Entity Classes from my ERD. 

+ Claude was used to create DAO classes after I wrote the first one based on in-class exercise.

+ Claude helped me add two fields to DigSiteGrid.

## Milestone 3

+ Frequent use of IntelliJ Idea's code completion.

+ Claude was used to create mostly-empty fragments with navigation between them.

+ Claude was used to make a login fragment, update the nav graph to make it the entry point, logic for only navigating to main when on successful login, a global navigation action back to login fragment for use after logout.

+ Claude wrote a LoginViewModel to consume the GoogleAuthRepository copied from class examples.

+ Claude made the fossil view dialog fragment and added to nav graph and added test buttons to view it from the appropriate fragments.

+ Had Claude give it a shot to just implement the Repositories from the stubs I managed to make. 
  Using its implementation besides removing the explicit executor assignment and just letting it 
  use the common for join thread pool.

+ Had Claude write me an SQL query to get any fossils that were not assigned to a collected 
  fossil yet. it was just a left join but I forgot how they worked and it got it for me.

## Milestone 3

+ Frequent use of IntelliJ Idea's code completion.

+ Gemini helped me figure out chipsGroup and custom selectable cards to use as chips.

+ Claude helped make that into a RadioGroup so it's now ugly and hard-coded size but actually 
  works even though you can't tell which is selected.

+ Claude implemented the click listener handling in DigSiteView from a description of the kind of listener I wanted.

+ added colored background to RadioGroup that's not visible yet beyond the image and messed up 
  the text. tried to add tint to the vector drawables but it's not doing anything at all. 

+ Claude added brush and scanner count to game page!

+ Claude implemented decrementing brush and scanner resources on use including not allowing 
  scanner use if there are no scanners left.

+ Claude added some Javadocs and Kdocs with it's final credits! I gotta check if it really finished.