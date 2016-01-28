#flags
CC = gcc
CFLAGS = -g -Wall -std=c99
LDLIBS = -lm

#drawing dependencies and operations
drawing: image.o drawing.o
	gcc drawing.o image.o -lm -o drawing

image.o: image.h image.c
drawing.o: image.c image.h

#cleans up temp files for testing
clean:
	rm -f actual_1.pgm
	rm -f actual_2.pgm
	rm -f test.txt
	rm -f in.txt