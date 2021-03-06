=========== AIRBUS PLANNING PROBLEM SIMULATOR ===========
All the planners that take a PDDL problem as input, generate an execution plan in plain text format.
Even though this is effective is boooring.

In this project, is defined a planning problem (pddl) for sitting passengers in an airbus plane. Once this problem is solved by LAMA, the simulator takes the execution plan and shows it graphically in an animated way.

The problem:
____________
Given a plane of N rows and 6 seats by row (ABC__DEF) and M passengers, the problem is to sit every passenger in their seats in an optimal way. The order of execution is important due to the space restrictions present in the plane cabin:
	if a passenger will sit in or stand up from B, C must be free (the same goes for E and D).
	if a passenger will sit in or stand up from A, B and D must be free (the same goes for F and D and E).
	only one person can be standing in the hallway in each row.
	and of course, if a seat will be used, it must not be taken.

The simulator:
____________
The simulator takes three arguments
	execution plan generated by LAMA
	problem definition file (pddl)
	speed (ms)

Example:
	java -jar Airbus.jar plans/p01-plan problems/airbus-seats-p01-6f-6p.pddl 500

Some considerations:
	The problems must be defined using as object names:
		row1 row2 row3 ... for rows
		pass1 pass2 pass3 ... for passengers
	Everything must be enumerated ascending.

Note: The execution plans generated by MpC my be modified a little in order to be accepted by the simulator.
