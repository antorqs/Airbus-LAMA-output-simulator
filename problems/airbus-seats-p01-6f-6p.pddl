(define (problem airbus-seats-p01-6f-6p)
	(:domain airbus-seats)
	(:objects 
		row1 - row
		row2 - row
		row3 - row
		row4 - row
		row5 - row
		row6 - row
		pass1
		pass2
		pass3
		pass4
		pass5
		pass6 - passenger)
	(:init 

	)
	(:goal
		(and 
		(occupied row2 A)
		(occupied row2 B)
		(occupied row2 C)
		(occupied row2 D)
		(occupied row2 E)
		(occupied row2 F)
		)
	)
)