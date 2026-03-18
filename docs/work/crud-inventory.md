# CRUD Inventory

## fossil

### Create
* Create a new batch of fossils from a webservice hit.

### Read
* viewing any collected fossils from your gallery (as part of a collected_fossil)
* viewing a single fossil
* get an unassigned fossil to award the player (wrap in a collected_fossil)

### Update

### Delete
* Clearing game save hall of fame...?

## collected_fossil

### Create
* On uncovering a fossil on the board (filled with a fossil, or to be filled with a fossil.)

### Read
* viewing a single collected fossil
* populating recycler view with cards of collected fossils filled with fossils.
* count the entries belonging to a user, to display the "score" as a single number.

### Update
* changing "favorite" status.
* assigning a fossil to a collected fossil awarded while no fossils were ready to assign.

### Delete
* manual delete...???

## user_profile

### Create
* Create a new save slot. (I'm not sure I even want to do this...??) 

### Read
* every app load to see what games are in progress and which gallery to go to.
* Game board observes live data of scanner_items integer.

### Update
* awarding or using a fossil scanner item, changing the inventory count.

### Delete
full game delete.

## dig_site_grid

### Create
* starting a new game

### Read
* viewing the game board (for dimensions)
* viewing the game board (with squares, for display???)

### Update


### Delete
* give up on a game
* excavate every fossil on the board, ending the game normally.
* use up the last brush, ending the game normally.


## dig_site_square

### Create
* populating a new game.

### Read
* displaying the game board. (or just bundled with the board?) (depends on immutable qualities as well as state)

### Update
* almost all gameplay: clicking on the board with 3 out of 4 tools updates the state of the square clicked on. (FENCED<->UNTOUCHED, UNTOUCHED->DUG, UNTOUCHED->EXTRACTED)

### Delete
* deleted with the parent game.