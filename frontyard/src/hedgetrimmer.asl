// Agent hedgetrimmer in project frontyard.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").
+trimm(hedge) <- trimm(hedge); .print("Hedgetrimmer active").
+water[source(sprinkler1)] : true <- .drop_desire(trimm(hedge)); .print("Hedgetrimmer stopped by sprinkler1").
+water[source(sprinkler2)] : true <- .drop_desire(trimm(hedge)); .print("Hedgetrimmer stopped by sprinkler2").
+water[source(sprinkler3)] : true <- .drop_desire(trimm(hedge)); .print("Hedgetrimmer stopped by sprinkler3").


