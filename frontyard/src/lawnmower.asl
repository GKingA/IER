// Agent lawnmower in project frontyard.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").
+mow(grass) <- mow(grass); .print("Lawnmower active").
+water[source(sprinkler1)] : true <- .drop_desire(mow(grass)); .print("Lawmower stopped by sprinkler1").
+water[source(sprinkler2)] : true <- .drop_desire(mow(grass)); .print("Lawmower stopped by sprinkler2").
+water[source(sprinkler3)] : true <- .drop_desire(mow(grass)); .print("Lawmower stopped by sprinkler3").
