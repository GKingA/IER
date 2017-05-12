// Agent sprinkler in project frontyard.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").
+water(grass) <- water(grass); .send(lawnmower1,tell,water); .send(lawnmower2,tell,water); .send(lawnmower3,tell,water); .send(hedgetrimmer,tell,water); .print("Sprinkler active").
