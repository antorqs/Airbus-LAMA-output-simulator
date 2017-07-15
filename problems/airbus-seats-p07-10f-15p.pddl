(define (problem airbus-seats-p07-10f-15p)
	(:domain airbus-seats)
	(:objects 
		row1 - row
		row2 - row
		row3 - row
		row4 - row
		row5 - row
		row6 - row
		row7 - row
		row8 - row
		row9 - row
		row10 - row
		pass1
		pass2
		pass3
		pass4
		pass5
		pass6
		pass7
		pass8
		pass9
		pass10
		pass11
		pass12
		pass13
		pass14
		pass15 - passenger)
	(:init 
		(boarded pass1)
		(boarded pass2)
		(boarded pass3)
		(boarded pass4)
		(boarded pass5)
		(boarded pass6)
		(occupied row1 B)
		(passenger-seated row1 B pass1)
		(occupied row1 C)
		(passenger-seated row1 C pass2)
		(occupied row2 D)
		(passenger-seated row2 D pass3)
		(occupied row2 E)
		(passenger-seated row2 E pass4)
		(occupied row3 B)
		(passenger-seated row3 B pass5)
		(occupied row3 C)
		(passenger-seated row3 C pass6)
	)
	(:goal
		(and 
		(occupied row1 A)
		(occupied row1 B)
		(passenger-seated row1 B pass1)
		(occupied row1 C)
		(passenger-seated row1 C pass2)
		(occupied row2 D)
		(passenger-seated row2 D pass3)
		(occupied row2 E)
		(passenger-seated row2 E pass4)
		(occupied row2 F)
		(occupied row3 A)
		(occupied row3 B)
		(passenger-seated row3 B pass5)
		(occupied row3 C)
		(passenger-seated row3 C pass6)
		(occupied row4 D)
		(occupied row4 E)
		(occupied row4 F)
		(occupied row5 A)
		(occupied row5 B)
		(occupied row5 C)
		)
	)
)