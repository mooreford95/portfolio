#flags
CC = gcc
CFLAGS = -g -Wall -std=c99
LDLIBS = -lm 
#may not need lm

#various targets and dependencies.
explorer: map.o
map.o: map.h map.c


#cleans up temp files for testing.
#Also incuded: executables,
#in event clean build is needed.
clean:
	rm -f map explorer actual_3.txt actual_5.txt actual_7.txt actual_8.txt actual1.txt