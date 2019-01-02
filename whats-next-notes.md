Dec 30, 2018 - Cycling through players turns

* I have implementations for processing a players turn, 
but still need something that cycles through the players (in turn).
* Will need to have something the determines when the game is over.
That's seems like something that should customizable, but I'll try to stick to YANGI.
* Need _something_ that outputs (generates information/objects) the results of the turns/game


Jan 1, 2019 - Outcomes: 

The reason the RollProcessor spec was forced into using a mock for the PlayerMap 
(which was at least better than relying on knowledge of how the PlayerMap worked within the RollProcessor spec)
was the result of the RollProcessor function telling the PlayerMap what to do. 
A more functional solution would be to have the RollProcessor function generate information (objects) 
that can be passed to PlayerMap function(s) so that it can decide what to do. 
I'm calling those objects, outcomes. 

It occurs to me that I could just pass the die rolls directly to the PlayerMap as that
_is_ information the PlayerMap could use to decide what to do. 
However, it feels like that there are potential future enhancements where... 
* there are other _outcomes_ that don't involve the PlayerMap, but are a result of die rolls
i.e. it was a drinking game afterall
* there are _outcomes_ caused by actions not _directly_ tied to die rolls that do involve the PlayerMap, 
i.e. what happens when a player runs out of pieces?
* the need to specify what happened in a round w/o parsing diffs in the player map, 
I'm going to need _some_ kind of front end for this and these outcomes seem potentially useful in that regard

I know YANGI in all, but in this case passing in die rolls directly to the player map is already forcing changes
so if I'm making changes anyway might as well leave it in place that's more extensible, right?

Jan 1, 2019 - Random Events (not necessarily Die Rolls):

LCR is a dice game, but dice aren't the only thing that can generate random events
* This is running on a computer program, so I could just use straight up random number generators
* Cards can actually be a better random event generator because the constrain the variation
    * If you roll a (six-sided) die 52 times, the number of 1s rolled could be just a few or 20+ (even if the average is 8+)
    * If you draw from a 52-card deck 52 times, the number of 2s & 3s will be 8, 
    but there's still variation because if you draw 13 cards from that deck 
    the number of 2s and 3s could anywhere from 0 to 8 (even if the average is 2)
    
It seems like there is an abstraction to be made for Die and DieRolls
(say RandomEvent and RandomEventGenerator). I am going to follow YANGI on this one for the time being 
because I don't think the cost of change later will be too high
 