// Agent sprinkler in project frontyard.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.
!moist.

/* Plans */

+!start : true <- .print("hello world.").
+!moist : true <- .print("Everything is done.").
+!moist : false <- water.
