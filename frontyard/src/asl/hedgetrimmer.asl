// Agent hedgetrimmer in project frontyard.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.
!short.

/* Plans */

+!start : true <- .print("hello world.").
+!short : true <- .print("Everything is done.").
+!short : false <- trimm.

