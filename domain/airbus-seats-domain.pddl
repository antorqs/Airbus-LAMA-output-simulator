(define (domain airbus-seats)
	(:requirements :typing)
	(:types row 
		passenger)
	(:constants A B C D E F)
	(:predicates (occupied ?row - row ?seat - object)
		(passenger-seated ?row - row ?seat - object ?passenger - passenger)
		(boarded ?passenger - passenger)
		(at-hallway-row ?row - row ?passenger - passenger)
		(hallway-blocked ?row - row)
	)
		
	(:action sit_in_A
	:parameters (?row - row ?passenger - passenger)
	:precondition (and 
		(not (occupied ?row A))
		(not (occupied ?row B))
		(not (occupied ?row C))
		(at-hallway-row ?row ?passenger)
		)
	:effect (and 
		(occupied ?row A)
		(passenger-seated ?row A ?passenger)
		(not (at-hallway-row ?row ?passenger))
		(not (hallway-blocked ?row))
		)
	)
	(:action sit_in_B
	:parameters (?row - row ?passenger - passenger)
	:precondition (and 
		(not (occupied ?row B))
		(not (occupied ?row C))
		(at-hallway-row ?row ?passenger)
		)
	:effect (and 
		(occupied ?row B)
		(passenger-seated ?row B ?passenger)
		(not (at-hallway-row ?row ?passenger))
		(not (hallway-blocked ?row))
		)
	)
	(:action sit_in_C
	:parameters (?row - row ?passenger - passenger)
	:precondition (and
		(not (occupied ?row C))
		(at-hallway-row ?row ?passenger)
		)
	:effect (and 
		(occupied ?row C)
		(passenger-seated ?row C ?passenger)
		(not (at-hallway-row ?row ?passenger))
		(not (hallway-blocked ?row))
		)
	)
	;Hallway
	(:action sit_in_D
	:parameters (?row - row ?passenger - passenger)
	:precondition (and
		(not (occupied ?row D))
		(at-hallway-row ?row ?passenger)
		)
	:effect (and 
		(occupied ?row D)
		(passenger-seated ?row D ?passenger)
		(not (at-hallway-row ?row ?passenger))
		(not (hallway-blocked ?row))
		)
	)
	(:action sit_in_E
	:parameters (?row - row ?passenger - passenger)
	:precondition (and 
		(not (occupied ?row E))
		(not (occupied ?row D))
		(at-hallway-row ?row ?passenger)
		)
	:effect (and 
		(occupied ?row E)
		(passenger-seated ?row E ?passenger)
		(not (at-hallway-row ?row ?passenger))
		(not (hallway-blocked ?row))
		)
	)
	(:action sit_in_F
	:parameters (?row - row ?passenger - passenger)
	:precondition (and 
		(not (occupied ?row F))
		(not (occupied ?row E))
		(not (occupied ?row D))
		(at-hallway-row ?row ?passenger)
		)
	:effect (and 
		(occupied ?row F)
		(passenger-seated ?row F ?passenger)
		(not (at-hallway-row ?row ?passenger))
		(not (hallway-blocked ?row))
		)
	)

	(:action stand_up_from_A
	:parameters (?row - row ?passenger - passenger)
	:precondition (and 
		(occupied ?row A)
		(passenger-seated ?row A ?passenger)
		(not (occupied ?row B))
		(not (occupied ?row C))
		(not (hallway-blocked ?row))
		)
	:effect (and
		(not (occupied ?row A))
		(not (passenger-seated ?row A ?passenger))
		(hallway-blocked ?row)
		(at-hallway-row ?row ?passenger)
		)
	)
	(:action stand_up_from_B
	:parameters (?row - row ?passenger - passenger)
	:precondition (and 
		(occupied ?row B)
		(passenger-seated ?row B ?passenger)
		(not (occupied ?row C))
		(not (hallway-blocked ?row))
		)
	:effect (and
		(not (occupied ?row B))
		(not (passenger-seated ?row B ?passenger))
		(hallway-blocked ?row)
		(at-hallway-row ?row ?passenger)
		)
	)
	(:action stand_up_from_C
	:parameters (?row - row ?passenger - passenger)
	:precondition (and
		(occupied ?row C)
		(passenger-seated ?row C ?passenger)
		(not (hallway-blocked ?row))
		)
	:effect (and
		(not (occupied ?row C))
		(not (passenger-seated ?row C ?passenger))
		(hallway-blocked ?row)
		(at-hallway-row ?row ?passenger)
		)
	)
	;Hallway
	(:action stand_up_from_D
	:parameters (?row - row ?passenger - passenger)
	:precondition (and
		(occupied ?row D)
		(passenger-seated ?row D ?passenger)
		(not (hallway-blocked ?row))
		)
	:effect (and
		(not (occupied ?row D))
		(not (passenger-seated ?row D ?passenger))
		(hallway-blocked ?row)
		(at-hallway-row ?row ?passenger)
		)
	)
	(:action stand_up_from_E
	:parameters (?row - row ?passenger - passenger)
	:precondition (and 
		(occupied ?row E)
		(passenger-seated ?row E ?passenger)
		(not (occupied ?row D))
		(not (hallway-blocked ?row))
		)
	:effect (and
		(not (occupied ?row E))
		(not (passenger-seated ?row E ?passenger))
		(hallway-blocked ?row)
		(at-hallway-row ?row ?passenger)
		)
	)
	(:action stand_up_from_F
	:parameters (?row - row ?passenger - passenger)
	:precondition (and 
		(occupied ?row F)
		(passenger-seated ?row F ?passenger)
		(not (occupied ?row E))
		(not (occupied ?row D))
		(not (hallway-blocked ?row))
		)
	:effect (and
		(not (occupied ?row F))
		(not (passenger-seated ?row F ?passenger))
		(hallway-blocked ?row)
		(at-hallway-row ?row ?passenger)
		)
	)

	(:action walk-to-row
	:parameters (?row - row ?passenger - passenger)
	:precondition (and 
		(not (hallway-blocked ?row))
		(not (boarded ?passenger))
		)
	:effect (and (boarded ?passenger)
				(hallway-blocked ?row)
				(at-hallway-row ?row ?passenger)
			)
	)
	(:action move-to-other-row
	:parameters (?from - row ?to - row ?passenger - passenger)
	:precondition (and 
		(not (hallway-blocked ?to))
		(boarded ?passenger)
		(at-hallway-row ?from ?passenger)
		)
	:effect (and (not (at-hallway-row ?from ?passenger))
				(not (hallway-blocked ?from))
				(hallway-blocked ?to)
				(at-hallway-row ?to ?passenger)
			)
	)
)

